package com.victor.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class FloorTile extends Tile{

	public FloorTile(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
	}
	
	public void render(Graphics g) {
		
		// MUDANCA DE HORARIO
		if(World.CICLO == World.dia) {
			g.drawImage(TILE_DIA, x - Camera.x, y - Camera.y, null);
		}else if(World.CICLO == World.noite) {
			g.drawImage(TILE_NOITE, x - Camera.x, y - Camera.y, null);
		}
	}

}
