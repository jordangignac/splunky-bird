package com.volhynia.main;

import com.volhynia.framework.GameObject;

public class Camera {

	private float x, y;

	public Camera(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	public void tick(GameObject player) {

		x = x - 4; // use for constantly scrolling camera
		// x += (-player.getX() - (x-Game.WIDTH/2)) * 0.1;
		y += ((-player.getY() - (y - (Game.HEIGHT - (Game.HEIGHT / 2)))) * 0.1) - 16;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
