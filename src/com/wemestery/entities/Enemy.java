package com.wemestery.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.wemestery.main.Game;

public class Enemy extends Player{
	
	private boolean right, left, up, down;
	private final int SPEED = 2;
	
	// Construtor
	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}

	// Atualização do Inimigo
	public void tick() {
		// Movimentação do Inimigo
		if(this.getKey() == 68) {
			this.setX(this.getX() + SPEED);
		}else if(this.getKey() == 65) {
			this.setX(this.getX() - SPEED);
		}
		
		// Colisão com Mapa
		if(this.getX() + this.getWidth() > Game.WIDTH) {
			this.setX(Game.WIDTH - this.getWidth());
		}else if(this.getX() < 0) {
			this.setX(0);
		}
	}
	
	// Renderização do Inimigo
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
	
	// Getters e Setters
	public void setRight(boolean right) {this.right = right;}
	public void setLeft(boolean left) {this.left = left;}
	public void setUp(boolean up) {this.up = up;}
	public void setDown(boolean down) {this.down = down;}
}
