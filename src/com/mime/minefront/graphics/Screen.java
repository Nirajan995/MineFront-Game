package com.mime.minefront.graphics;

import com.mime.minefront.Game;
import java.util.Random;

/**
 *
 * @author DELL
 */

public class Screen extends Render {

	private Render test;
	private Render3D render;

	public Screen(int width, int height) {
		super(width, height);
		Random random = new Random();
		render = new Render3D(width, height);
		test = new Render(256, 256);
		for (int i = 0; i < 256 * 256; i++) {
			test.pixels[i] = random.nextInt();
		}
	}

	public void render(Game game) {
		render.floor(game);
		render.renderDistanceLimiter();
               // render.walls();
		draw(render, 0, 0);
	}
}
