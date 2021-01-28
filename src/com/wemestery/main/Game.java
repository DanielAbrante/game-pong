package com.wemestery.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.wemestery.entities.Ball;
import com.wemestery.entities.Enemy;
import com.wemestery.entities.Entity;
import com.wemestery.entities.Player;
import com.wemestery.graphics.Spritesheet;
import com.wemestery.graphics.UI;
import com.wemestery.world.World;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 240, HEIGHT = 160, SCALE = 3;
	private JFrame frame; 
	private boolean isRunning;
	private Thread thread;
	private BufferedImage image;
	private KeyBoard keyBoard;
	
	private List<Entity> entities;
	
	public static Spritesheet spritesheet;
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	public static UI ui;
	public static String gameState = "MENU";
	public static Menu menu;
	
	private static World world;
	
	public Game(){
		keyBoard = new KeyBoard();
		this.addKeyListener(keyBoard);
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame();
		// Inicializando Objetos
		ui = new UI();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<Entity>();
		spritesheet = new Spritesheet("/spritesheet.png");
		player = new Player((WIDTH / 2) - 15, 5, 30, 3, null);
		enemy = new Enemy((WIDTH / 2) - 15, (HEIGHT - 3 - 5), 30, 3, null);
		ball = new Ball((WIDTH / 2) - 1, (HEIGHT / 2) - 1, 4, 4, null);
		entities.add(player);
		entities.add(enemy);
		entities.add(ball);
		world = new World("/map.png");
		menu = new Menu();
	}
	
	public void initFrame() {
		frame = new JFrame("Pong");
		frame.add(this);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	// Start
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
	
	// Update
	public void tick() {
		if(this.gameState == "NORMAL") {
			for(int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.tick();
			}
		}else if(this.gameState == "MENU") {
			menu.tick();
		}
		
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// Renderização do Mapa
		world.render(g);
		
		// Renderização do Jogo 
		// Graphics2D g2 = (Graphics2D) g;
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
		
		// Interface Gráfica
		ui.render(g);
		/***/

		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		if(this.gameState == "MENU") {
			menu.render(g);
		}
		bs.show();
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		requestFocus();
		while(isRunning) {
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println(frames + " FPS");
				frames = 0;
				timer+=1000;
			}
		}
		stop();
	}
	
}

