package com.wemestery.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener{

	private int key;
	
	@Override
	public void keyPressed(KeyEvent e) {
		key = e.getKeyCode();
		switch(key) {
			case KeyEvent.VK_D:
				Game.player.setKey(68);
			break;
			case KeyEvent.VK_RIGHT:
				Game.enemy.setKey(68);
			break;
			case KeyEvent.VK_A:
				Game.player.setKey(65);
			break;
			case KeyEvent.VK_LEFT:
				Game.enemy.setKey(65);
			break;
			case KeyEvent.VK_W: case KeyEvent.VK_UP:
				switch(Game.gameState){
					case "MENU":
						Game.menu.up = true;
						Sound.menuEffect.play();
					break;
				}
			break;
			case KeyEvent.VK_S: case KeyEvent.VK_DOWN:
				switch(Game.gameState){
					case "MENU":
						Game.menu.down = true;
						Sound.menuEffect.play();
					break;
				}
			break;
			case KeyEvent.VK_ENTER:
				switch(Game.gameState){
					case "MENU":
						Game.menu.enter = true;
						Sound.menuEffect.play();
					break;
				}
			break;
			case KeyEvent.VK_ESCAPE:
				switch(Game.gameState){
					case "MENU":
						Sound.menuEffect.play();
						Game.menu.pause = true;
					break;
				}
			break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		key = e.getKeyCode();
		switch(key) {
			case KeyEvent.VK_D:
				Game.player.setKey(0);
			break;
			case KeyEvent.VK_RIGHT:
				Game.enemy.setKey(0);
			break;
			case KeyEvent.VK_A:
				Game.player.setKey(0);
			break;
			case KeyEvent.VK_LEFT:
				Game.enemy.setKey(0);
			break;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
}
