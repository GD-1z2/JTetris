package fr.caemur.jtetris.blocks;

import fr.caemur.jtetris.Main;

public class Tile {
	private int x, y;
	
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void move(int dir) {
		if (dir == 0) {
			x--;
		} else if (dir == 1) {
			x++;
		} else {
			y++;
		}
	}
	
	/**
	 * Can go :
	 * 0 = left;
	 * 1 = right;
	 * any other number = down;
	 * @param dir direction
	 */
	public boolean canGo(int dir) {
		switch (dir) {
		case 0:
			if (x == 0) return false;
			for (Tile tile : Main.game.mainScene.getTiles()) {
				if (x-1 == tile.getX() && y == tile.getY()) return false;
			}
			return true;

		case 1:
			if (x == Main.MAP_WIDTH-1) return false;
			for (Tile tile : Main.game.mainScene.getTiles()) {
				if (x+1 == tile.getX() && y == tile.getY()) return false;
			}
			return true;
			
		default:
			if (y+1 >= Main.MAP_HEIGHT) {
				return false;
			}
			
			for (Tile tile : Main.game.mainScene.getTiles()) {
				if (y+1== tile.getY() && x==tile.getX()) {
					return false;
				}
			}
			
			return true;
		}
	}
	
	public boolean overlapsTile(Tile tile) {
		return this.x == tile.getX() && this.y == tile.getY();
	}
	
	public int overlapsTiles(Tile[] tiles) {
		int r = 0;
		for (Tile tile : tiles) {
			if (overlapsTile(tile)) r++;
		}
		return r;
	}
}