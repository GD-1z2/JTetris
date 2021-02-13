package fr.caemur.jtetris.blocks;

public class Shape {
	private Tile[] rot0;
	private Tile[] rot1;
	private Tile[] rot2;
	private Tile[] rot3;
	
	public static final Tile[] TILES_SQUARE = new Tile[] {
		new Tile(0, 0), new Tile(1, 0), new Tile(0, 1), new Tile(1, 1),
	};
	
	public static final Tile[] TILES_TRIANGLE = new Tile[] {
		new Tile(0, 0),	new Tile(1, 0),	new Tile(2, 0),	new Tile(1, 1),	
	};
	private static final Tile[] TILES_TRIANGLE_1 = new Tile[] {
		new Tile(1, -1),	new Tile(1, 0),	new Tile(2, 0),	new Tile(1, 1),	
	};
	private static final Tile[] TILES_TRIANGLE_2 = new Tile[] {
		new Tile(1, -1),	new Tile(0, 0),	new Tile(1, 0),	new Tile(2, 0),	
	};
	private static final Tile[] TILES_TRIANGLE_3 = new Tile[] {
		new Tile(1, -1), new Tile(0, 0), new Tile(1, 0), new Tile(1, 1),	
	};
	
	public static final Tile[] TILES_STICK = new Tile[] {
		new Tile(0, 0),	new Tile(0, 1),	new Tile(0, 2),	new Tile(0, 3),	
	};
	public static final Tile[] TILES_STICK_1 = new Tile[] {
		new Tile(-1, 1),	new Tile(0, 1),	new Tile(1, 1),	new Tile(2, 1),	
	};
	
	public static final Tile[] TILES_L = new Tile[] {
		new Tile(0, 0),	new Tile(0, 1),	new Tile(0, 2),	new Tile(1, 2),	
	};
	public static final Tile[] TILES_L_1 = new Tile[] {
		new Tile(1, 0), new Tile(-1, 1), new Tile(0, 1), new Tile(1, 1),
	};
	public static final Tile[] TILES_L_2 = new Tile[] {
		new Tile(-1, 0), new Tile(0, 0), new Tile(0, 1), new Tile(0, 2),	
	};
	public static final Tile[] TILES_L_3 = new Tile[] {
		new Tile(-1, 1), new Tile(0, 1), new Tile(1, 1), new Tile(-1, 2),	
	};
	
	public static final Tile[] TILES_J = new Tile[] {
		new Tile(1, 0),	new Tile(1, 1),	new Tile(1, 2),	new Tile(0, 2),	
	};
	public static final Tile[] TILES_J_1 = new Tile[] {
		new Tile(0, 1),	new Tile(1, 1),	new Tile(2, 1),	new Tile(2, 2),	
	};
	public static final Tile[] TILES_J_2 = new Tile[] {
		new Tile(1, 0),	new Tile(2, 0),	new Tile(1, 1),	new Tile(1, 2),	
	};
	public static final Tile[] TILES_J_3 = new Tile[] {
		new Tile(0, 0),	new Tile(0, 1),	new Tile(1, 1),	new Tile(2, 1),	
	};
	
	public static final Tile[] TILES_S = new Tile[] {
		new Tile(1, 0),	new Tile(2, 0),	new Tile(0, 1),	new Tile(1, 1),
	};
	public static final Tile[] TILES_S_1 = new Tile[] {
		new Tile(1, -1), new Tile(1, 0), new Tile(2, 0), new Tile(2, 1),
	};
	public static final Tile[] TILES_S_2 = new Tile[] {
		new Tile(1, -1), new Tile(2, -1), new Tile(0, 0), new Tile(1, 0),
	};
	public static final Tile[] TILES_S_3 = new Tile[] {
		new Tile(0, -1), new Tile(0, 0), new Tile(1, 0), new Tile(1, 1),
	};
	
	public static final Tile[] TILES_Z = new Tile[] {
		new Tile(0, 0),	new Tile(1, 0),	new Tile(1, 1),	new Tile(2, 1),	
	};
	public static final Tile[] TILES_Z_1 = new Tile[] {
		new Tile(2, -1), new Tile(1, 0), new Tile(2, 0), new Tile(1, 1),
	};
	public static final Tile[] TILES_Z_2 = new Tile[] {
		new Tile(0, -1), new Tile(1, -1), new Tile(1, 0), new Tile(2, 0),
	};
	public static final Tile[] TILES_Z_3 = new Tile[] {
		new Tile(1, -1), new Tile(0, 0), new Tile(1, 0), new Tile(0, 1),
	};
	
	public static final Shape SHAPE_SQUARE = new Shape(TILES_SQUARE, TILES_SQUARE, TILES_SQUARE, TILES_SQUARE);
	public static final Shape SHAPE_TRIANGLE = new Shape(TILES_TRIANGLE, TILES_TRIANGLE_1, TILES_TRIANGLE_2, TILES_TRIANGLE_3);
	public static final Shape SHAPE_STICK = new Shape(TILES_STICK, TILES_STICK_1, TILES_STICK, TILES_STICK_1);
	public static final Shape SHAPE_L= new Shape(TILES_L, TILES_L_1, TILES_L_2, TILES_L_3);
	public static final Shape SHAPE_J= new Shape(TILES_J, TILES_J_1, TILES_J_2, TILES_J_3);
	public static final Shape SHAPE_S= new Shape(TILES_S, TILES_S_1, TILES_S_2, TILES_S_3);
	public static final Shape SHAPE_Z= new Shape(TILES_Z, TILES_Z_1, TILES_Z_2, TILES_Z_3);

	
	public Shape(Tile[] rot0, Tile[] rot1, Tile[] rot2, Tile[] rot3) {
		super();
		this.rot0 = rot0;
		this.rot1 = rot1;
		this.rot2 = rot2;
		this.rot3 = rot3;
	}

	public Tile[] getRot0() {
		return rot0;
	}

	public Tile[] getRot1() {
		return rot1;
	}

	public Tile[] getRot2() {
		return rot2;
	}

	public Tile[] getRot3() {
		return rot3;
	}
	
	public Tile[] getRot(int rot) {
		if (rot == 0) return rot0;
		else if (rot == 1) return rot1;
		else if (rot == 2) return rot2;
		else return rot3;
	}
}
