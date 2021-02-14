package fr.caemur.jtetris;

import static org.lwjgl.opengl.GL11.*;

public class Renderer
{
	public static void quadData(int x, int y, int width, int height, float[] color)
	{
		glColor4f(color[0], color[1], color[2], color[3]);
		glVertex2f(x, y);
		glVertex2f(x + width, y);
		glVertex2f(x + width, y + height);
		glVertex2f(x, y + height);
	}

	public static void quadData(int x, int y, int width, int height, float[] color, int xOffset, int yOffset, float wCut, float hCut)
	{		
		glColor4f(color[0], color[1], color[2], color[3]);
		
		glTexCoord2f((0 + xOffset) / wCut, (0 + yOffset) / hCut);
		glVertex2f(x, y);

		glTexCoord2f((1 + xOffset) / wCut, (0 + yOffset) / hCut);
		glVertex2f(x + width, y);
     
		glTexCoord2f((1 + xOffset) / wCut, (1 + yOffset) / hCut);
		glVertex2f(x + width, y + height);

		glTexCoord2f((0 + xOffset) / wCut, (1 + yOffset) / hCut);
		glVertex2f(x, y + height);		
	}
	
	public static void renderTile(int x, int y, float[] color) {
		glBegin(GL_QUADS);
		quadData(x*Main.TILE_SIZE, y*Main.TILE_SIZE, Main.TILE_SIZE, Main.TILE_SIZE, new float[] {0,0,0,color[3]});
	   	glEnd();
		
		glBegin(GL_QUADS);
		quadData(x*Main.TILE_SIZE+(int)(Main.TILE_SIZE*.04), y*Main.TILE_SIZE+(int)(Main.TILE_SIZE*.04), (int)(Main.TILE_SIZE*.91), (int)(Main.TILE_SIZE*.91), new float[] {color[0]*2f,color[1]*2f,color[2]*2f,color[3]});
		glEnd();
		
		glBegin(GL_QUADS);
		quadData(x*Main.TILE_SIZE+(int)(Main.TILE_SIZE*.06), y*Main.TILE_SIZE+(int)(Main.TILE_SIZE*.06), (int)(Main.TILE_SIZE*.91), (int)(Main.TILE_SIZE*.91), color);
		glEnd();
	}
}