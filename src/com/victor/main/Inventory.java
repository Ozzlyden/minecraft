package com.victor.main;

import java.awt.Color;
import java.awt.Graphics;

import com.victor.world.Tile;

public class Inventory {
	
	public String[] itens = {"grama", "terra", "neve", "areia","", ""};	// Itens do inventario
	public int inventoryBoxSize = 50;
	public int inicialPosition = ((Game.WIDTH * Game.SCALE ) / 2) - ((itens.length * inventoryBoxSize) / 2); 

		
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < itens.length; i++) {
			g.setColor(Color.gray);
			g.fillRect(inicialPosition + (i * inventoryBoxSize), Game.HEIGHT * Game.SCALE - inventoryBoxSize, inventoryBoxSize, inventoryBoxSize);
			g.setColor(Color.black);
			g.drawRect(inicialPosition + (i * inventoryBoxSize), Game.HEIGHT * Game.SCALE - inventoryBoxSize, inventoryBoxSize, inventoryBoxSize);
			
			if(itens[i] == "grama") {
				g.drawImage(Tile.TILE_GRAMA,inicialPosition + (i * inventoryBoxSize) + 10, Game.HEIGHT * Game.SCALE - inventoryBoxSize + 10, 32, 32, null);
			}else if (itens[i] == "terra"){
				g.drawImage(Tile.TILE_TERRA,inicialPosition + (i * inventoryBoxSize) + 10, Game.HEIGHT * Game.SCALE - inventoryBoxSize + 10, 32, 32, null);
			}
		}
	}

}
