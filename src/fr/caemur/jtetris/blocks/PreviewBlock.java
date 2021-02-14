package fr.caemur.jtetris.blocks;

import fr.caemur.jtetris.Main;
import fr.caemur.jtetris.Renderer;

import java.util.Random;

import static org.lwjgl.opengl.GL11.*;

public class PreviewBlock extends Block {

	public PreviewBlock(int x, int y, Tile[] tiles, float[] color) {
		super(x, y, tiles, color, null, true);
	}

	@Override
	public void render() {
		for (Tile tile : tiles) {
			glBegin(GL_QUADS);
			Renderer.quadData(tile.getX()*Main.TILE_SIZE, tile.getY()*Main.TILE_SIZE, Main.TILE_SIZE, Main.TILE_SIZE, new float[] {color[0], color[1], color[2], .25f});
			glEnd();
		}
	}

	public void update(Block block) {
		this.x = block.x;
		this.y = block.y;

		this.tiles = new Tile[block.tiles.length];
		for (int i = 0; i < block.tiles.length; i++) {
			this.tiles[i] = new Tile(block.tiles[i].getX(), block.tiles[i].getY());
		}

		this.color = block.color;

		boolean down = true;
		while (down) {
			for (Tile tile : tiles) {
				if (!tile.canGo(2)) {
					down = false;
				}
			}
			if (down)
				move(2);
		}
	}
}