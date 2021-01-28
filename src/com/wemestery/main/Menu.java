package com.wemestery.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu {
	
	private String[] options = {"Novo Jogo", "Carregar", "Sair"};
	private int currentOption = 0, maxOptions = this.options.length - 1;
	public boolean down, up, enter, pause = false;
	
	// Atualização do Menu
	public void tick() {
		if(up) {
			up = false;
			currentOption--;
			if(currentOption < 0) {
				currentOption = maxOptions;
			}
		}	
		
		if(down) {
			down = false;
			currentOption++;
			if(currentOption > maxOptions) {
				currentOption = 0;
			}
		}
		
		if(enter) {
			enter = false;
			if(options[currentOption] == "Novo Jogo" || options[currentOption] == "Continuar") {
				Game.gameState = "NORMAL";
			}else if(options[currentOption] == "Sair") {
				System.exit(1);
			}
		}
		
	}
	
	// Renderização do Menu
	public void render(Graphics g) {
		g.setColor(new Color(0, 0, 0, 120));
		g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		
		g.setColor(Color.magenta);
		g.setFont(new Font("Arial", Font.BOLD, 36));
		g.drawString("Pong", (Game.WIDTH / 2) * Game.SCALE - 285, (Game.HEIGHT / 2) * Game.SCALE - 10);
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 24));
		
		if(pause == false) {
			g.drawString("Novo Jogo", (Game.WIDTH * Game.SCALE) / 2 - 300, 280);
		}else {
			g.drawString("Continuar", (Game.WIDTH * Game.SCALE) / 2 - 300, 280);
		}
		g.drawString("Carregar", (Game.WIDTH * Game.SCALE) / 2 - 300, 320);
		g.drawString("Sair", (Game.WIDTH * Game.SCALE) / 2 - 300, 360);
		
		if(options[currentOption] == "Novo Jogo") {
			g.drawString(">", (Game.WIDTH * Game.SCALE) / 2 - 330 , 280);
		}else if(options[currentOption] == "Carregar") {
			g.drawString(">", (Game.WIDTH * Game.SCALE) / 2 - 330 , 320);
		}else if(options[currentOption] == "Sair") {
			g.drawString(">", (Game.WIDTH * Game.SCALE) / 2 - 330 , 360);
		}
		
	}
}
