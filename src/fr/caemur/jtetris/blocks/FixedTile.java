package fr.caemur.jtetris.blocks;

public class FixedTile extends Tile {
	private float[] color;
	
	public FixedTile(int x, int y, float[] color) {
		super(x, y);
		this.color = color;
	}

	public float[] getColor() {
		return color;
	}
}