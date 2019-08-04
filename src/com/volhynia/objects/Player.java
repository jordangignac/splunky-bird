package com.volhynia.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.volhynia.framework.GameObject;
import com.volhynia.framework.ObjectId;
import com.volhynia.framework.Texture;
import com.volhynia.main.Animation;
import com.volhynia.main.Game;
import com.volhynia.main.Handler;

public class Player extends GameObject {
	private float width = 64, height = 64;
	private float gravity = 0.4f;
	private final float MAX_SPEED = 10f;

	private Handler handler;

	private static Animation anim_1;

	Texture tex = Game.getTexInstance();

	public Player(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		anim_1 = new Animation(5, tex.sPlayer[0], tex.sPlayer[1], tex.sPlayer[2]);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		this.setvelX(4);

		x += velX;
		y += velY;

		if (falling || jumping) {
			velY += gravity;

			if (velY > MAX_SPEED) {
				velY = MAX_SPEED;
			}
		}

		if (velY > 0) {
			jumping = false;
		}

		Collision(object);

		anim_1.runAnimation();
	}

	private void Collision(LinkedList<GameObject> object) {

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			// collision handling top
			if (tempObject.getId() == ObjectId.Block) {
				if (getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + height / 2;
					velY = 0;
				}
			}

			// collision handling bottom
			if (tempObject.getId() == ObjectId.Block) {
				if (getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height + 16;
					velY = 0;
					falling = false;
				} else {
					falling = true;
				}
			}

			// collision handling right
			if (tempObject.getId() == ObjectId.Block) {
				if (getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - width;
				}
			}

			// collision handling left
			if (tempObject.getId() == ObjectId.Block) {
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 32;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if (velY < 0) {
			animType = 1;
		} else {
			animType = 0;
		}

		if (animType == 1) {
			((Graphics2D) g).rotate(Math.toRadians(velY * 4), x + width / 2, y + height / 2);
			anim_1.drawAnimation(g, (int) x, (int) y, 96, 96);
		} else if (animType == 0 && velY > 6) {
			((Graphics2D) g).rotate(Math.toRadians(velY * 4), x + width / 2, y + height / 2);
			g.drawImage(tex.sPlayer[3], (int) x, (int) y, 96, 96, null);
		} else if (animType == 0) {
			((Graphics2D) g).rotate(Math.toRadians(velY * 4), x + width / 2, y + height / 2);
			g.drawImage(tex.sPlayer[0], (int) x, (int) y, 96, 96, null);
		}

		// draw collision boxes
		// Graphics2D g2d = (Graphics2D) g;
		//
		// g2d.setColor(Color.red);
		// g2d.draw(getBounds());
		// g2d.draw(getBoundsTop());
		// g2d.setColor(Color.yellow);
		// g2d.draw(getBoundsRight());
		// g2d.draw(getBoundsLeft());
	}

	public Rectangle getBounds() {
		return new Rectangle((int) ((int) x + width / 4), ((int) ((int) y + height / 2)),
				(int) ((int) width - width / 2) - 5, (int) height / 4);
	}

	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int) x + width / 4), (int) ((int) y + height / 4),
				(int) ((int) width - width / 2) - 5, (int) height / 4);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x + 10, (int) y + (int) (width / 4) + 5, 5,
				(int) (((int) height - 20) - height / 4) - 5);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int) x + (width) - 20), (int) y + (int) (width / 4) + 5, 5,
				(int) (((int) height - 20) - height / 4) - 5);
	}
}
