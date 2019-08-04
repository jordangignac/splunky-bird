package com.volhynia.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.volhynia.framework.KeyInput;
import com.volhynia.framework.ObjectId;
import com.volhynia.framework.Texture;
import com.volhynia.objects.Block;
import com.volhynia.objects.Player;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = -6136671368849674563L;

	public static final int WIDTH = 640; // 20 blocks
	public static final int HEIGHT = 960; // 12 blocks
	public static final String TITLE = "SplunkyBird v0.1";

	private boolean running = false;
	public boolean hasStarted = false;
	private Thread thread;

	public static Game g;

	private BufferedImage level = null;

	Handler handler;

	Camera cam;

	static Texture tex;

	Random rand = new Random();

	private void init() {

		tex = new Texture();

		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level_0.png");

		handler = new Handler();

		cam = new Camera(0, 0);

		loadImageLevel(level);

		handler.addObject(new Player(100, 100, handler, ObjectId.Player));

		this.addKeyListener(new KeyInput(handler));
	}

	public void tick() {
		handler.tick();
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ObjectId.Player) {
				cam.tick(handler.object.get(i));
			}
		}
	}

	public void render() {

		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		////////////////////////////

		// TODO draw here
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());

		g2d.translate(cam.getX(), cam.getY());

		handler.render(g);

		g2d.translate(-cam.getX(), -cam.getY());

		////////////////////////////
		g.dispose();
		bs.show();

	}

	private void loadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		for (int i = 0; i < w; i++) {
			for (int t = 0; t < h; t++) {
				int pixel = image.getRGB(i, t);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 255 && green == 255 && blue == 255) {
					handler.addObject(new Block(i * 32, t * 32, ObjectId.Block));
				}
				if (red == 0 && green == 0 && blue == 255) {
					handler.addObject(new Player(i * 32, t * 32, handler, ObjectId.Player));
				}
			}

		}
	}

	@Override
	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		final double numTick = 60.0;
		double n = 1000000000 / numTick;
		double delta = 0;
		int frames = 0;
		int ticks = 0;
		long timer = System.currentTimeMillis();

		while (running) {
			long currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / n;
			lastTime = currentTime;

			if (delta >= 1) {
				tick();
				ticks++;
				delta--;
			}

			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(ticks + " Ticks, FPS: " + frames);
				ticks = 0;
				frames = 0;
			}
		}
		stop();
	}

	public boolean isHasStarted() {
		return hasStarted;
	}

	public void setHasStarted(boolean hasStarted) {
		this.hasStarted = hasStarted;
	}

	public static void main(String[] args) {
		new Window(WIDTH, HEIGHT, TITLE, g = new Game());
	}

	public synchronized void start() {
		if (running) {
			return;
		} else {
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}

	public synchronized void stop() {
		if (!running) {
			return;
		} else {
			running = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.exit(1);
		}
	}

	public static Texture getTexInstance() {
		return tex;
	}

	public static Game getGameInstance() {
		return g;
	}
}
