package com.wemestery.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.wemestery.main.Game;

public class World {

	private static Tile[] tiles;
	private static int mapWidth, mapHeight;
	private static final int TILE_SIZE = 16;
	
	// Construtor
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			mapWidth = map.getWidth();
			mapHeight = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
	
			for(int xx = 0; xx < map.getWidth(); xx++) {
				for(int yy = 0; yy < map.getHeight(); yy++) {
					int pixelAtual = pixels[xx + (yy * map.getWidth())];
					tiles[xx + (yy * mapWidth)] = new FloorTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.getTileFloor()); // Standard floor
					if(pixelAtual == 0xFF000000) {
						tiles[xx + (yy * mapWidth)] = new FloorTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.getTileFloor()); // Grass floor
					}else if(pixelAtual == 0xFFFFFFFF) {
						tiles[xx + (yy * mapWidth)] = new WallTile(xx * TILE_SIZE, yy * TILE_SIZE, Tile.getTileWall()); // Wall
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public void render(Graphics g) {
		for(int xx = 0; xx <= mapWidth; xx++) {
			for(int yy = 0; yy <= mapHeight; yy++) {
				if(xx < 0 || yy < 0 || xx >= mapWidth || yy >= mapHeight) {
					continue;
				}
				Tile tile = tiles[xx + (yy * mapWidth)];
				tile.render(g);
			}
		}
	}
		
}
