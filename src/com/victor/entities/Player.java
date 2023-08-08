package com.victor.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.victor.main.Game;
import com.victor.world.Camera;
import com.victor.world.World;


public class Player extends Entity {
	
	public boolean right, left, moved;
	
	public double life = 100;
	
	public boolean isJumping = false;
	public boolean jump = false;
	public int jumpHeight = 30, jumpFrames = 0;
	
	private int framesAnimation = 0;
	private int maxFrames = 15;
	private int maxSprite = 2;
	private int curSprite = 0;
	
	private double gravity = 2;
	private double vspd = 0;		// Altura do pulo gravidade avancada
	public int dir = 1;
	
	private BufferedImage[] PLAYER_RIGHT;
	private BufferedImage[] PLAYER_LEFT;
	
	
	public Player(int x, int y, int width, int height,double speed, BufferedImage sprite) {
		super(x, y, width, height,speed, sprite);
	
		PLAYER_RIGHT = new BufferedImage[2];
		PLAYER_LEFT = new BufferedImage[2];
		
		PLAYER_RIGHT[0] = Game.spritesheet.getSprite(48, 0, 16, 16);
		PLAYER_RIGHT[1] = Game.spritesheet.getSprite(64, 0, 16, 16);
		PLAYER_LEFT[0] = Game.spritesheet.getSprite(48, 16, 16, 16);
		PLAYER_LEFT[1] = Game.spritesheet.getSprite(64, 16, 16, 16);

	}
	
	public void tick() {
		// CAMADA DE RENDER
		depth = 2;
		moved = false;
		
		vspd += gravity;		
		
		//LOGICA GRAVIDADE AVANCADA
		if(!World.isFree((int)x, (int) (y + 1)) && jump) {
			vspd = -6;
			jump = false;
		}
		
		
		//LOGICA GRAVIDADE SIMPLES
		if(World.isFree((int) x, (int) (y + gravity)) && isJumping == false) {
			y += gravity;
			

		}
		
		
		//MOVIMENTACAO
		if(right && World.isFree((int) (x + speed),(int) y)) {
			x+=speed;
			dir = 1;
			moved = true;
		}else if(left && World.isFree((int) (x - speed),(int) y)) {
			x-=speed;
			dir = -1;
			moved = true;
		}
		
		//LOGICA DE PULO
		if(jump) {
			if(!World.isFree(this.getX(), this.getY() + 1)) {
				//System.out.println("Pulando");
				isJumping = true;			
			}else {
				jump = false;
			}
		}
		if (isJumping) {
			if(World.isFree(this.getX(), this.getY() - 2)) {
				y-=2;
				jumpFrames+=2;
				if(jumpFrames == jumpHeight) {
					isJumping = false;
					jump = false;
					jumpFrames = 0;
				}
			}else {
				isJumping =false;
				jump = false;
				jumpFrames = 0;
			}
		}
		
		if(moved == true) {
			framesAnimation++;
			if(framesAnimation == maxFrames) {
				curSprite++;
				framesAnimation = 0;
				if(curSprite == maxSprite) {
					curSprite = 0;
				}
			}
			
		}
		

		
		//SISTEMA DE CAMERA
		Camera.x = Camera.clamp((int)x - Game.WIDTH / 2, 0, World.WIDTH * 16 - Game.WIDTH);
		Camera.y = Camera.clamp((int)y - Game.HEIGHT / 2, 0, World.HEIGHT * 16 - Game.HEIGHT);
	}
		

	
	public void render(Graphics g) { 
		
		if(moved == false) {
			sprite = PLAYER_RIGHT[0];
		}
			
			if(dir == 1) {
				sprite = PLAYER_RIGHT[curSprite];
			}else if (dir == -1) {
				sprite = PLAYER_LEFT[curSprite];
			}
		super.render(g);
	}
}
