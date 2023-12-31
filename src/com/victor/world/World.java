package com.victor.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.victor.entities.Enemy1;
import com.victor.entities.Entity;
import com.victor.entities.Player;
import com.victor.graficos.UI;
import com.victor.main.Game;

public class World {

	public static Tile[] tiles;
	public static int WIDTH,HEIGHT;
	public static final int TILE_SIZE = 16;
	
	// LOGICA DE MUDANCA DE CICLO TA NO UI
	public static int dia = 0, noite = 1;
	public static int CICLO = dia; 
	
	
	
	public World(){
		
		// DIMENSOES MUNDO
		WIDTH = 1000;	
		HEIGHT = 80;
		tiles = new Tile[WIDTH * HEIGHT];
		
		// VARIAVEIS
		String [] tilesTypes = {"grama", "terra", "areia", "neve ", ""};
		int inicialHeight = Entity.rand.nextInt(17 - 11) +11;
		int divisao = WIDTH/tilesTypes.length;
		
		// DIVISOR MAP
		for(int xx =0 ;xx< WIDTH; xx++) {
			for(int yy =0; yy < HEIGHT; yy++) {
				if(yy == (HEIGHT) - 1 || xx == (WIDTH) - 1 || xx == 0 || yy == 0) {
					tiles[xx+yy*WIDTH] = new WallTile(xx*16, yy*16, Tile.TILE_SOLID);
					tiles[xx+yy*WIDTH].solid = true;
				}else {
					
					// SISTEMA DE BIOMAS
					if(yy >= inicialHeight) {
						int indexBioma = xx / divisao;
						if(tilesTypes[indexBioma] == "grama") {
							tiles[xx+yy*WIDTH] = new WallTile(xx*16, yy*16, Tile.TILE_GRAMA);
						}else if (tilesTypes[indexBioma] == "terra") {
							tiles[xx+yy*WIDTH] = new WallTile(xx*16, yy*16, Tile.TILE_TERRA);
						}else if (tilesTypes[indexBioma] == "areia") {
							tiles[xx+yy*WIDTH] = new WallTile(xx*16, yy*16, Tile.TILE_AREIA);
						}else if (tilesTypes[indexBioma] == "neve") {
							tiles[xx+yy*WIDTH] = new WallTile(xx*16, yy*16, Tile.TILE_NEVE);
						}
						
					}else {
						tiles[xx+yy*WIDTH] = new FloorTile(xx*16, yy*16, Tile.TILE_DIA);
					}
				}
			}
		}
	}
		
	
	public static boolean isFree(int xnext,int ynext){
		
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext+TILE_SIZE-1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;
		
		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		int x4 = (xnext+TILE_SIZE-1) / TILE_SIZE;
		int y4 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		return !((tiles[x1 + (y1*World.WIDTH)] instanceof WallTile) ||
				(tiles[x2 + (y2*World.WIDTH)] instanceof WallTile) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof WallTile) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof WallTile));
	}
	
	
	public static void restartGame(String level){
		//new Game();

		return;
	}
	
	public void render(Graphics g){
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		
		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4);
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}
	}
	
}
