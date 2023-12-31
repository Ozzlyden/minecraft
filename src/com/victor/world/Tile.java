package com.victor.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.victor.main.Game;

public class Tile {
	
	public static BufferedImage TILE_GRAMA = Game.spritesheet.getSprite(0,0,16,16);
	public static BufferedImage TILE_TERRA = Game.spritesheet.getSprite(16,0,16,16);
	public static BufferedImage TILE_SOLID = Game.spritesheet.getSprite(32,0,16,16);
	public static BufferedImage TILE_AREIA = Game.spritesheet.getSprite(48,0,16,16);
	public static BufferedImage TILE_NEVE = Game.spritesheet.getSprite(64,0,16,16);
	
	public static BufferedImage TILE_DIA = Game.spritesheet.getSprite(0,16,16,16);
	public static BufferedImage TILE_NOITE = Game.spritesheet.getSprite(16,16,16,16);



	private BufferedImage sprite;
	protected int x, y;
	
	public boolean solid = false;	// veriuficado para ver so tile sera solido ou nao
	
	
	public Tile(int x,int y,BufferedImage sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g){
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}

}
