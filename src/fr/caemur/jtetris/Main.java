package fr.caemur.jtetris;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glViewport;

import java.nio.ByteBuffer;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

public class Main {
	public boolean running = false;
	
	public String title = "";
	public String icon = "";
	public int width;
	public int height;
	public boolean showFps = true;
	
	public int ticks, frames, totalTicks;
	
	int time = 0;
	
	public static boolean tick = false;
	public static boolean render = false;
	
	DisplayMode mode;
	
	public static Game game;
	
	public static Main instance;
	
	public static final int MAP_WIDTH = 10;
	public static final int MAP_HEIGHT = 20;
	public static final int TILE_SIZE = 35;
	
	public static void main(String[] args) {
		game = new Game();
		instance = new Main(MAP_WIDTH*TILE_SIZE, MAP_HEIGHT*TILE_SIZE, "JTetris", "/tile.png", game);
		instance.start();
	}
	
	public Main(int width, int height, String title, String icon, Game game)
	{
		this.width = width;
		this.height = height;
		this.title = title;
		this.icon = icon;
		mode = new DisplayMode(width, height);
		display();
		Main.game = game;
	}
	
	public void start()
	{
		running = true;
		loop();
	}
	
	public void stop()
	{
		running = false;
	}
	
	public void exit()
	{
		Display.destroy();
		System.exit(0);
	}
	
	public void loop()
	{
		game.init();
		Display.setIcon(new ByteBuffer[] {Texture.getIcon(icon)});
		
		long timer = System.currentTimeMillis();
		
		long before = System.nanoTime();
		double elapsed = 0;
		double nanoSeconds = 1000000000.0/60.0;
		
		ticks = 0;
		frames = 0;
		
		while (running)
		{
			if (Display.isCloseRequested()) stop();

			Display.update();
			
			width = Display.getWidth();
			height = Display.getHeight();
			
			tick = false;
			render = false;
			
			long now = System.nanoTime();
			elapsed = now - before;

			if (elapsed > nanoSeconds)
			{
				before += nanoSeconds;
				tick = true;
				ticks++;
				totalTicks++;
			} else {
				render = true;
				frames++;
			}
			
			if (tick) update();
			if (render) render();
			
			if (System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				Display.setTitle(showFps ? title + " / " + ticks + " Ticks / " + frames + " FPS" : title);
				ticks = 0;
				frames = 0;
			}
		}
		exit();
	}
	
	public void update()
	{
		time++;
		game.update();
	}
	
	public void render()
	{
		view2D(width, height);
		
		glClear(GL_COLOR_BUFFER_BIT);
		glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		
		game.render();
	}
	
	public void display()
	{
		try
		{
			Display.setDisplayMode(mode);
			Display.setResizable(false);
			Display.setFullscreen(false);
			Display.setTitle(title);
			Display.setLocation(0, 0);
			Display.create();
			
			view2D(width, height);
		} catch (LWJGLException e)
		{
			e.printStackTrace();
		}
	}
	
	private void view2D(int width, int height)
	{
		glViewport(0, 0, width, height);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluOrtho2D(0, width, height, 0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		
		glEnable(GL_TEXTURE_2D);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
}
