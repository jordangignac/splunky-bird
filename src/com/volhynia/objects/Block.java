package com.volhynia.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.volhynia.framework.GameObject;
import com.volhynia.framework.ObjectId;
import com.volhynia.framework.Texture;
import com.volhynia.main.Game;

public class Block extends GameObject {

	Texture tex = Game.getTexInstance();

	public Block(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	public void tick(LinkedList<GameObject> object) {

	}

	public void render(Graphics g) {
		// g.setColor(Color.white);
		// TODO texture draw and optimization
		g.drawImage(tex.sBlock[0], (int) x, (int) y, null);
		// g.drawRect((int)x, (int)y, 32, 32);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}
