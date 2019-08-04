package com.volhynia.framework;

import java.awt.image.BufferedImage;

import com.volhynia.main.BufferedImageLoader;

public class Texture {

	SpriteSheet bs, ps;
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;

	public BufferedImage[] sBlock = new BufferedImage[1];
	public BufferedImage[] sPlayer = new BufferedImage[4];

	public Texture() {
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			block_sheet = loader.loadImage("/block_sheet.png");
			player_sheet = loader.loadImage("/player_sheet2.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);

		getTextures();
	}

	private void getTextures() {
		sBlock[0] = bs.grabImage(1, 1, 32, 32); // the one dirt block
		sPlayer[0] = ps.grabImage(1, 1, 32, 32); // regs flappy bird
		sPlayer[1] = ps.grabImage(2, 1, 32, 32); // mid flap flappy bird
		sPlayer[2] = ps.grabImage(3, 1, 32, 32); // flapping flappy bird
		sPlayer[3] = ps.grabImage(4, 1, 32, 32); // diving flappy bird
	}
}
