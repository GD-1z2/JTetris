package fr.caemur.jtetris.blocks;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import fr.caemur.jtetris.Main;
import fr.caemur.jtetris.MainScene;
import fr.caemur.jtetris.Renderer;

public class Block {
	protected Tile[] tiles;
	protected int x;
	protected int y;
	private int r;
	private boolean fixed;
	protected float[] color;
	private Shape shape;
	
	public Block(int x, int y, Tile[] tiles, float[] color, Shape shape) {
		this.x = x;
		this.y = y;
		this.tiles = tiles;
		this.color = color;
		this.fixed = false;
		this.shape = shape;
	}
	
	public Block(int x, int y, Tile[] tiles, float[] color, Shape shape, boolean fixed) {
		this.x = x;
		this.y = y;
		this.tiles = tiles;
		this.fixed = fixed;
		this.color = color;
	}
	
	public void update() {
		if (!fixed) {
			boolean down = false;
			if (MainScene.mainStep()) {
				down = true;
				for (Tile tile : tiles) {
					if (!tile.canGo(2)) down = false;
				}
				
				if (down) {
					move(2);
				}
				else fix();
			}
			
			if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
				if (! Main.game.mainScene.getDown()) {
					down = true;
					while (down) {
						for (Tile tile : tiles) {
							 if (!tile.canGo(2)) {
								 down = false;
							}
						}
						if (down) move(2);
					}
					fix();
				}
				Main.game.mainScene.setDown(true);
			} else {
				Main.game.mainScene.setDown(false);
			}
			
			
			if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) && Main.game.mainScene.canMove()) {
				boolean left = true;
				for (Tile tile : tiles) {
					if (!tile.canGo(0)) left = false;
				}
				if (left) move(0);
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && Main.game.mainScene.canMove()) {
				boolean right = true;
				for (Tile tile : tiles) {
					if (!tile.canGo(1)) right = false;
				}
				if (right) move(1);
			}
			
			if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
				if (!Main.game.mainScene.getUp()) {
					rotate(1);
				}
				Main.game.mainScene.setUp(true);
			} else Main.game.mainScene.setUp(false);
		}
	}
	
	public void render() {
		for (Tile tile : tiles) {
			Renderer.renderTile(tile.getX(), tile.getY(), color);
		}
	}

	public static Tile[] calcTiles(int x, int y, Tile[] tiles) {
		ArrayList<Tile> rTile = new ArrayList<Tile>();
		for (Tile tile : tiles) {
			rTile.add(new Tile(x+tile.getX(), y+tile.getY()));
		}
		return rTile.toArray(new Tile[0]);
	}
	
	public void rotate(int r) {
		if (r == 1) {
			// get rotated ver
			Tile[] rotatedTiles = calcTiles(x, y, shape.getRot((this.r+1)%4));
			for (Tile tile : rotatedTiles) {
				if (tile.overlapsTiles(Main.game.mainScene.getTiles().toArray(new Tile[0])) != 0) return;
				if (tile.getX()<0 || tile.getX()>Main.MAP_WIDTH-1) return;
			}
			this.r++;
			this.r%=4;

			tiles = rotatedTiles;
		}
	}
	
	public void move(int dir) {
		if (dir == 0) {
			x--;
			Main.game.mainScene.updateMoveTick();
		} else if (dir == 1) {
			x++;
			Main.game.mainScene.updateMoveTick();
		} else {
			y++;
		}
		
		for (Tile tile : tiles) {
			tile.move(dir);
		}
	}
	
	protected void fix() {
		Main.game.mainScene.createBlockNext();
		Main.game.mainScene.explodeBlock(this);
	}
	
	public Tile[] getTiles() {
		return tiles;
	}
	
	public float[] getColor() {
		return color;
	}
	
	public boolean overlapsTile(Tile tileI) {
		for (Tile tile : tiles) {
			if (tile.overlapsTile(tileI)) return true;
		}
		return false;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
