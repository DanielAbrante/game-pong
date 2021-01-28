package com.wemestery.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.wemestery.main.Game;

public class Player extends Entity{
	
	private int key;
	private boolean right, left, up, down;
	private final int SPEED = 2;
	
	// Construtor
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}

	// Atualização do Player
	public void tick() {
		// Movimentação do Player
		if(key == 68) {
			this.setX(this.getX() + SPEED);
		}else if(key == 65) {
			this.setX(this.getX() - SPEED);
		}
		
		// Colisão com Mapa
		if(this.getX() + this.getWidth() > Game.WIDTH) {
			this.setX(Game.WIDTH - this.getWidth());
		}else if(this.getX() < 0) {
			this.setX(0);
		}
	}
	
	// Renderização do Player
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
	
	// Getters e Setters
	public void setRight(boolean right) {this.right = right;}
	public void setLeft(boolean left) {this.left = left;}
	public void setUp(boolean up) {this.up = up;}
	public void setDown(boolean down) {this.down = down;}

	public int getKey() {return key;}
	public void setKey(int key) {this.key = key;}
}
