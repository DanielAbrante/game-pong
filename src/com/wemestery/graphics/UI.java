package com.wemestery.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.wemestery.main.Game;

public class UI {
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(180, 80, 30, 1);
		g.setColor(Color.blue);
		g.drawString(Game.ball.getScorePlayer() + "", 188, 75);
		g.setColor(Color.red);
		g.drawString(Game.ball.getScoreEnemy() + "", 188, 95);
		//g.setFont(new Font("Arial", Font.BOLD, 8));
	}
}
