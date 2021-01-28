package com.wemestery.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.wemestery.main.Game;
import com.wemestery.main.Sound;

public class Ball extends Entity{

	private double dirX, dirY;
	private final double SPEED = 2.5;
	private int angle, scorePlayer, scoreEnemy;
	
	// Construtor
	public Ball(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		//Ângulação da Bola
		int angleX = new Random().nextInt(30) + 15;
		int angleY = new Random().nextInt(200) + 45;
		this.dirX = Math.cos(Math.toRadians(angleX));
		this.dirY = Math.sin(Math.toRadians(angleY));
		
	}
	
	//MÉTODOS
	// Atualização
	public void tick() {
		
		//Direção da Bola
		x += dirX * SPEED;
		y += dirY * SPEED;
		
		//Colisão com Mapa
		if(this.getX() + (dirX * SPEED) + this.getWidth() > Game.WIDTH) {
			Sound.hurtEffect.play();
			dirX *= -1;
		}else if(this.getX() + (dirX * SPEED) < 0) {
			Sound.hurtEffect.play();
			dirX *= -1;
		}
		
		this.checkColliderEntities();
		this.Scoreboard();
	}
	
	public void checkColliderEntities() {
		//Caixa de Colisão das Entidades
		Rectangle boundBall = new Rectangle((int)(this.getX()), (int)(this.getY()), Game.ball.getWidth(), Game.ball.getHeight());
		Rectangle boundPlayer = new Rectangle(Game.player.getX(), Game.player.getY(), Game.player.width, Game.player.height);
		Rectangle boundEnemy = new Rectangle(Game.enemy.getX(), Game.enemy.getY(), Game.enemy.width, Game.enemy.height);
		
		if(boundBall.intersects(boundPlayer)) {
			Sound.hurtEffect.play();
			angle = new Random().nextInt(85) + 45;
			dirX = Math.cos(Math.toRadians(angle));
			dirY = Math.sin(Math.toRadians(angle));
		}else if(boundBall.intersects(boundEnemy)) {
			Sound.hurtEffect.play();
			angle = new Random().nextInt(85) + 45;
			dirX = Math.cos(Math.toRadians(angle));
			dirY = Math.sin(Math.toRadians(angle));
			dirY *= -1;
		}
	}
	
	// Sistema de Pontuação
	public void Scoreboard() {
		// Pontuação do Player
		if(this.getY() > Game.HEIGHT) {
			Sound.pontuationEffect.play();
			scorePlayer++;
			this.setX(Game.enemy.getX() + (15 - 2));
			this.setY(Game.enemy.getY() - 4);
			angle = new Random().nextInt(85) + 45;
			dirX = Math.cos(Math.toRadians(angle));
			dirY = Math.sin(Math.toRadians(angle));

		// Pontuação do Inimigo
		}else if(this.getY() < 0) {
			Sound.pontuationEffect.play();
			scoreEnemy++;
			this.setX(Game.player.getX() + (15 - 2));
			this.setY(Game.player.getY() + 3);
			angle = new Random().nextInt(85) + 45;
			dirX = Math.cos(Math.toRadians(angle));
			dirY = Math.sin(Math.toRadians(angle));
		}
	}
	
	// Renderização
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

	// Getters e Setters
	public double getDirX() {return dirX;}
	public void setDirX(double dirX) {this.dirX = dirX;}

	public double getDirY() {return dirY;}
	public void setDirY(double dirY) {this.dirY = dirY;}

	public int getScorePlayer() {return scorePlayer;}
	public int getScoreEnemy() {return scoreEnemy;}

}
