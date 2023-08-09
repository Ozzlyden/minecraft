package com.victor.entities;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.victor.main.Game;
import com.victor.world.Camera;
import com.victor.world.FloorTile;
import com.victor.world.Tile;
import com.victor.world.WallTile;
import com.victor.world.World;

public class Enemy1 extends Entity{
	
	public boolean right = true, left = false;
	
	private int frames = 0, maxFrames = 25, index = 0, maxIndex = 1;
	
	public int vida = 1;
	public int dir = 1;

	public BufferedImage[] ENEMY1;
	

	public Enemy1(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, null);
		// TODO Auto-generated constructor stub
		
		ENEMY1 = new BufferedImage [2];;
		
		ENEMY1[0] = Game.spritesheet.getSprite(0, 80, 16, 16);
		ENEMY1[1] = Game.spritesheet.getSprite(16, 80, 16, 16);	
	}

	public void tick() {
		// CAMADA DE RENDER
		depth = 2;
		
		//LOGICA GAVIDADE
		if(World.isFree((int) x, (int) (y + 1))) {
			y += 1;
		}
		
		if(dir == 1 ) {
			if(World.isFree((int)(x + speed),(int)y)) {
				x += speed;
			}else {
				
				// SISTE DE DESTRUICAO DE BLOCOS
				int xnext = (int) ((x + speed)/ 16) + 1;
				int ynext = (int) (y / 16);
				Tile tile = World.tiles[xnext + ynext*World.WIDTH];
				
				if(World.tiles[xnext + ynext*World.WIDTH] instanceof WallTile && World.tiles[xnext + ynext*World.WIDTH].solid == false) {
					World.tiles[xnext + ynext*World.WIDTH] = new FloorTile((xnext +1) * 16,ynext  * 16, Tile.TILE_AR);
				}
				
				dir = -1;
			}
			
		}else if (dir == -1) {
			if(World.isFree((int)(x - speed),(int)y)) {
				x -= speed;
			}else {
				
				// SISTE DE DESTRUICAO DE BLOCOS
				int xnext = (int) ((x - speed)/ 16);
				int ynext = (int) (y / 16);
				Tile tile = World.tiles[xnext + ynext*World.WIDTH];
				
				if(World.tiles[xnext + ynext*World.WIDTH] instanceof WallTile && World.tiles[xnext + ynext*World.WIDTH].solid == false) {
					World.tiles[xnext + ynext*World.WIDTH] = new FloorTile((xnext +1) * 16,ynext  * 16, Tile.TILE_AR);
				}
				
				dir = 1;
			}
		}
		
		//LOGICA ANIMACAO
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex)
				index = 0;
			}
			
	}
	
	public boolean test(){
		return false;
	}
	
	public void render(Graphics g) {
		/*
		if(right) {
			g.drawImage(ENEMY2_RIGHT[index],this.getX(), this.getY(), null);
		}else if(left) {
			g.drawImage(ENEMY2_LEFT[index],this.getX(), this.getY(), null);
		}
		*/
		/*
		if(right) {
			sprite = sprite1;
		}else if(left) {
			sprite = sprite2;
		}
		*/
		
		g.drawImage(ENEMY1[index],this.getX() - Camera.x, this.getY() - Camera.y, null);
		
	//DEBUG  MASK
	//g.setColor(Color.BLUE);
	//g.fillRect(getX() - Camera.x, getY() - Camera.y, width, height);
	super.render(g);
	}
}
