package game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.time.Instant;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

import game.input.Input;

public class Main implements Runnable {
	
	private int width = 1280;
	private int height = 720;
	
	private Thread thread;
	private boolean running = false;
	private long window;
	private Instant instant = Instant.now();
	boolean colorActiveted = false;
	
	
	public void start() {
		running = true;
		thread = new Thread(this, "Game");
		thread.start();
	}
	
	private void init() {
		if (GLFW.glfwInit() != true) {
			//TODO: handle
		}
		 
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GL11.GL_TRUE);
		window = GLFW.glfwCreateWindow(width, height, "Zone to Defend", 0, MemoryUtil.NULL);
		if (window == MemoryUtil.NULL) {
			//TODO: handle
			return;
		}
		GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		GLFW.glfwSetWindowPos(window, ((vidmode.width() - width) /2), ((vidmode.height() - width) /2));
		
		GLFW.glfwSetKeyCallback(window, new Input());
		
		GLFW.glfwMakeContextCurrent(window);
		GLFW.glfwShowWindow(window);
		
		GL.createCapabilities();
		
		
		GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		System.out.println(instant + " - [OpenGL] Version: " + GL11.glGetString(GL11.GL_VERSION));
		
	}
	
	public void run() {
		init();
		while (running) {
			FPSCounter.StartCounter();
			update();
			render();
			String fps = FPSCounter.StopAndPost();
			
			
			
			if (GLFW.glfwWindowShouldClose(window) == true) {
				running = false;
			}
		}
	}
	
	private void update() {
		GLFW.glfwPollEvents();
		if (Input.keys[GLFW.GLFW_KEY_SPACE]) {
			if (colorActiveted == false) {
				GL11.glClearColor(0.0f, 1.0f, 1.0f, 1.0f);
				colorActiveted = true;
			}
			if (colorActiveted == true) {
				GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
				colorActiveted = false;
			}
		}
	}
	
	private void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GLFW.glfwSwapBuffers(window);
	}
	
	public static void main(String[] args) {
		new Main().start();
	}
}
