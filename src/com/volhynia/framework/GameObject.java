package com.volhynia.framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {

	// protected object values
	protected float x, y;
	protected float velX = 0, velY = 0;
	protected boolean falling = true;
	protected boolean jumping = false;
	protected ObjectId id;

	// extra uselessness
	protected int animType = 0;

	public GameObject(float x, float y, ObjectId id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public void resetAnimation() {
	};

	public abstract void tick(LinkedList<GameObject> object);

	public abstract void render(Graphics g);

	public abstract Rectangle getBounds();

	public float getX() {
		return this.x;
	};

	public float getY() {
		return this.y;
	};

	public void setX(float x) {
		this.x = x;
	};

	public void setY(float y) {
		this.y = y;
	};

	public float getvelX() {
		return this.velX;
	};

	public float getvelY() {
		return this.velY;
	};

	public void setvelX(float velX) {
		this.velX = velX;
	};

	public void setvelY(float velY) {
		this.velY = velY;
	};

	public ObjectId getId() {
		return this.id;
	};

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public int getAnimType() {
		return animType;
	}

	public void setAnimType(int animType) {
		this.animType = animType;
	}

}
