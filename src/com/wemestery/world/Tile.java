package com.wemestery.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.wemestery.main.Game;

public class Tile {

	private final static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(0, 0, 16, 16);
	private final static BufferedImage TILE_WALL = Game.spritesheet.getSprite(16, 0, 16, 16);
	
	private int x, y;
	private BufferedImage spriteTile;
	
	public Tile(int x, int y, BufferedImage spriteTile) {
		this.x = x;
		this.y = y;
		this.spriteTile = spriteTile;
	}
	
	// Renderização
	public void render(Graphics g) {
		g.drawImage(spriteTile, x, y, null);
	}
	// Getters e Setters

	public static BufferedImage getTileFloor() {return TILE_FLOOR;}
	public static BufferedImage getTileWall() {return TILE_WALL;}
}
