package com.wemestery.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Entity {
	protected double x, y;
	protected final int width, height;
	protected BufferedImage sprite;
	
	// Construtor
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}
	
	// MÉTODOS
	// Atualização (Será Sobrescrito)
	public void tick() {
		
	}
	
	// Renderização
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX(), this.getY(), null);
	}

	// Getters e Setters
	public int getX() { return (int)x; }
	public void setX(double x) { this.x = x; }
	
	public int getY() { return (int)y; }
	public void setY(double y) { this.y = y; }

	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	
}
