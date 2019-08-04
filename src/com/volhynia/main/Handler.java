package com.volhynia.main;

import java.awt.Graphics;
import java.util.LinkedList;

import com.volhynia.framework.GameObject;
import com.volhynia.framework.ObjectId;
import com.volhynia.objects.Block;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();

	private GameObject tempObject;

	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.tick(object);
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);

			tempObject.render(g);
		}
	}

	public void addObject(GameObject object) {
		this.object.add(object);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	public void createLevel() {

		// draws floor
		for (int i = 0; i < Game.WIDTH * 2; i += 32) {
			addObject(new Block(i, Game.HEIGHT - 32, ObjectId.Block));
		}

		// draws platform1
		for (int i = Game.WIDTH / 4; i < Game.WIDTH - (Game.WIDTH / 4 + 32); i += 32) {
			addObject(new Block(i, Game.HEIGHT / 2 + 20, ObjectId.Block));
		}

		// draws obstacle1
		for (int i = Game.HEIGHT - (32 * 5); i < Game.HEIGHT - 32; i += 32) {
			addObject(new Block(Game.WIDTH - (Game.WIDTH / 4) + 32, i, ObjectId.Block));
		}

		// draws wall1
		for (int i = Game.HEIGHT - (32 * 15); i < Game.HEIGHT - 32; i += 32) {
			addObject(new Block(0, i, ObjectId.Block));
		}
	}
}
