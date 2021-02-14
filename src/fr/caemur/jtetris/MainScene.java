package fr.caemur.jtetris;

import java.util.ArrayList;
import java.util.Random;

import fr.caemur.jtetris.blocks.Block;
import fr.caemur.jtetris.blocks.BlocksColors;
import fr.caemur.jtetris.blocks.FixedTile;
import fr.caemur.jtetris.blocks.PreviewBlock;
import fr.caemur.jtetris.blocks.Shape;
import fr.caemur.jtetris.blocks.Tile;

public class MainScene extends Scene {

	ArrayList<Block> blocks;
	ArrayList<Block> blocksQueue;
	ArrayList<Block> removeBlocksQueue;
	ArrayList<FixedTile> tiles;

	public static final int TILE_SPAWN_X = 4;
	public static final int TILE_SPAWN_Y = 0;

	private int lastMoveTick = 0;

	private boolean pressedUp = false;
	private boolean pressedDown = false;

	Random rand;

	private boolean resetNext;
	
	private PreviewBlock previewBlock;
	
	public MainScene() {
		super("main");

		blocks = new ArrayList<Block>();
		blocksQueue = new ArrayList<Block>();
		removeBlocksQueue = new ArrayList<Block>();
		tiles = new ArrayList<FixedTile>();

		rand = new Random();

		lastMoveTick = 0;
		
		resetNext = false;
	}

	@Override
	public void update() {
		super.update();

		for (Block block : blocksQueue) {
			addBlock(block);
		}
		blocksQueue.clear();

		if (resetNext) {
			resetNext = false;
			Main.game.reset();
		}
		
		for (Block block : removeBlocksQueue) {
			blocks.remove(block);
		}

		for (Block block : blocks) {
			block.update();
		}

		previewBlock.update(blocks.get(0));

		int[] lineWeights = new int[Main.MAP_HEIGHT];
		for (Tile tile : tiles) {
			lineWeights[tile.getY()]++;
		}
		for (int iWeight = 0; iWeight < lineWeights.length; iWeight++) {
			if (lineWeights[iWeight] == Main.MAP_WIDTH) {
				clearLine(iWeight);
			}
		}
	}

	private void clearLine(int line) {
		ArrayList<FixedTile> newTiles = (ArrayList<FixedTile>) tiles.clone();

		for (int i = 0; i < tiles.size(); i++) {
			FixedTile tile = tiles.get(i);
			if (tile.getY() == line) {
				newTiles.remove(tile);
			} else if (tile.getY() < line) {
				newTiles.get(newTiles.indexOf(tile)).move(2);
			}
		}
		tiles = newTiles;
	}

	@Override
	public void render() {
		super.render();

		for (Block block : blocks) {
			block.render();
		}

		for (FixedTile tile : tiles) {
			Renderer.renderTile(tile.getX(), tile.getY(), tile.getColor());
		}
		
		previewBlock.render();
	}

	public void createBlock() {
		addBlock(randomBlock());
	}

	public void createBlockNext() {
		blocksQueue.add(randomBlock());
	}

	private Block randomBlock() {
		switch (rand.nextInt(7)) {
		case 1:
			return new Block(TILE_SPAWN_X, TILE_SPAWN_Y,
					Block.calcTiles(TILE_SPAWN_X, TILE_SPAWN_Y, Shape.SHAPE_TRIANGLE.getRot0()), BlocksColors.PURPLE,
					Shape.SHAPE_TRIANGLE);
		case 2:
			return new Block(TILE_SPAWN_X, TILE_SPAWN_Y,
					Block.calcTiles(TILE_SPAWN_X, TILE_SPAWN_Y, Shape.SHAPE_STICK.getRot0()), BlocksColors.CYAN,
					Shape.SHAPE_STICK);
		case 3:
			return new Block(TILE_SPAWN_X, TILE_SPAWN_Y,
					Block.calcTiles(TILE_SPAWN_X, TILE_SPAWN_Y, Shape.SHAPE_L.getRot0()), BlocksColors.ORANGE,
					Shape.SHAPE_L);
		case 4:
			return new Block(TILE_SPAWN_X, TILE_SPAWN_Y,
					Block.calcTiles(TILE_SPAWN_X, TILE_SPAWN_Y, Shape.SHAPE_J.getRot0()), BlocksColors.BLUE,
					Shape.SHAPE_J);
		case 5:
			return new Block(TILE_SPAWN_X, TILE_SPAWN_Y,
					Block.calcTiles(TILE_SPAWN_X, TILE_SPAWN_Y, Shape.SHAPE_S.getRot0()), BlocksColors.GREEN,
					Shape.SHAPE_S);
		case 6:
			return new Block(TILE_SPAWN_X, TILE_SPAWN_Y,
					Block.calcTiles(TILE_SPAWN_X, TILE_SPAWN_Y, Shape.SHAPE_Z.getRot0()), BlocksColors.RED,
					Shape.SHAPE_Z);

		default:
			return new Block(TILE_SPAWN_X, TILE_SPAWN_Y,
					Block.calcTiles(TILE_SPAWN_X, TILE_SPAWN_Y, Shape.SHAPE_SQUARE.getRot0()), BlocksColors.YELLOW,
					Shape.SHAPE_SQUARE);

		}
	}

	private void addBlock(Block block) {
		blocks.add(block);
		for (Tile tile : tiles) { // if block cant spawn because other blocks are too high
			if (block.overlapsTile(tile)) {
//				JOptionPane.showMessageDialog(null, "Game over");
//				System.exit(0);
				resetNext = true;
			}
		}
		
		previewBlock = new PreviewBlock(block.getX(), block.getY(), block.getTiles(), block.getColor());
	}

	public void explodeBlock(Block block) {
		for (Tile tile : block.getTiles()) {
			tiles.add(new FixedTile(tile.getX(), tile.getY(), block.getColor()));
		}
		removeBlocksQueue.add(block);
	}

	public static boolean mainStep() {
		return Main.instance.totalTicks % 30 == 0;
	}

	public boolean canMove() {
		return lastMoveTick < Main.instance.totalTicks - 10;
	}

	public void updateMoveTick() {
		lastMoveTick = Main.instance.totalTicks;
	}

	public ArrayList<FixedTile> getTiles() {
		return tiles;
	}

	public boolean getUp() {
		return pressedUp;
	}

	public void setUp(boolean up) {
		this.pressedUp = up;
	}
	
	public boolean getDown() {
		return pressedDown;
	}

	public void setDown(boolean down) {
		this.pressedDown = down;
	}
	
	public void reset() {
		blocks.clear();
		blocksQueue.clear();
		removeBlocksQueue.clear();
		tiles.clear();
	}
}
