package fr.caemur.jtetris;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.util.ArrayList;

public class Game {
	protected ArrayList<Scene> scenes;
	protected int activeScene;
	
	public MainScene mainScene;
	
	private float[] backgroundGradient;
	
	public Game() {
		backgroundGradient = Gradient.getGradient();
		
		scenes = new ArrayList<Scene>();
		mainScene = new MainScene();
		scenes.add(mainScene);
		mainScene.createBlock();
		activeScene = 0;
		loadScene("main");
	}
	
	public void addScene(Scene newScene) {
		scenes.add(newScene);
	}
	
	public void removeScene(String id) {
		ArrayList<Scene> newArray = scenes;
		for (int i = 0; i < scenes.size(); i++) {
			if (scenes.get(i).getId() == id) newArray.remove(i);
		}
		scenes = newArray;
	}
	
	public Scene getActiveScene() {
		return scenes.get(activeScene);
	}
	
	public void loadScene(String id) {
		int i = 0;
		for (Scene scene : scenes) {
			if (scene.getId() == id) {
				getActiveScene().kill();
				activeScene = i;
				scene.onLoad();
				return;
			}
			i++;
		}
	}
	
	public void init() {
		
	}
	
	public void update() {
		getActiveScene().update();
	}
	
	public void render() {
		glBegin(GL_QUADS);
		glColor4f(backgroundGradient[0], backgroundGradient[1], backgroundGradient[2], 1);
		glVertex2f(0, 0);

		glVertex2f(0 + Main.MAP_WIDTH*Main.TILE_SIZE, 0);

		glColor4f(backgroundGradient[3], backgroundGradient[4], backgroundGradient[5], 1);
		glVertex2f(0 + Main.MAP_WIDTH*Main.TILE_SIZE, 0 + Main.MAP_HEIGHT*Main.TILE_SIZE);

		glVertex2f(0, 0 + Main.MAP_HEIGHT*Main.TILE_SIZE);	
		glEnd();
		
		getActiveScene().render();
	}
	
	public void reset() {
		mainScene.reset();
		backgroundGradient = Gradient.getGradient();
		mainScene.createBlock();
	}
}
