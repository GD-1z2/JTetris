package fr.caemur.jtetris.blocks;

import fr.caemur.jtetris.Renderer;

public class PreviewBlock extends Block {

	public PreviewBlock(int x, int y, Tile[] tiles, float[] color) {
		super(x, y, tiles, color, null, true);
	}

	@Override
	public void render() {
		for (Tile tile : tiles) {
			Renderer.renderTile(tile.getX(), tile.getY(), new float[] { color[0], color[1], color[2], .5f });
		}
	}

	public void update(Block block) {
		this.x = block.x;
		this.y = block.y;
		this.tiles = block.tiles;
		this.color = block.color;
		
		boolean down = true;
		while (down) {
			for (Tile tile : tiles) {
//				if (!tile.canGo(2)) {
//					down = false;
//				}
			}
			if (down)
				move(2);
			return;
		}
	}
}