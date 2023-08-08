package com.victor.main;

import java.awt.Color;
import java.awt.Graphics;

import com.victor.world.Tile;

public class Inventory {
	
	public int selected = 0; 
	public boolean isPressed = false;
	public int mx, my;
	
	public String[] itens = {"grama", "terra", "neve", "areia", "ar", ""};	// Itens do inventario
	public int inventoryBoxSize = 45;
	public int inicialPosition = ((Game.WIDTH * Game.SCALE ) / 2) - ((itens.length * inventoryBoxSize) / 2); 

		
	public void tick() {
		
		// LOGICA DE SELECAO DE ITENS
		if(isPressed) {
			isPressed = false;
			
			// Demarcando area de selecao
			if(mx >= inicialPosition && mx < inicialPosition + (inventoryBoxSize * itens.length)) {
				if(my >= Game.HEIGHT * Game.SCALE - inventoryBoxSize - 1 && my <  Game.HEIGHT * Game.SCALE - inventoryBoxSize - 1 + inventoryBoxSize ) {
					selected = (int)(mx - inicialPosition) /inventoryBoxSize;
				}
			}
		}	
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < itens.length; i++) {
			g.setColor(Color.gray);
			g.fillRect(inicialPosition + (i * inventoryBoxSize) + 1, Game.HEIGHT * Game.SCALE - inventoryBoxSize - 1, inventoryBoxSize, inventoryBoxSize);
			g.setColor(Color.black);
			g.drawRect(inicialPosition + (i * inventoryBoxSize) + 1, Game.HEIGHT * Game.SCALE - inventoryBoxSize - 1, inventoryBoxSize, inventoryBoxSize);
			
			if(itens[i] == "grama") {
				g.drawImage(Tile.TILE_GRAMA,inicialPosition + (i * inventoryBoxSize) + 7, Game.HEIGHT * Game.SCALE - inventoryBoxSize + 7, 32, 32, null);
			}else if (itens[i] == "terra"){
				g.drawImage(Tile.TILE_TERRA,inicialPosition + (i * inventoryBoxSize) + 7, Game.HEIGHT * Game.SCALE - inventoryBoxSize + 7, 32, 32, null);
			}else if (itens[i] == "ar"){
				g.drawImage(Tile.TILE_AR,inicialPosition + (i * inventoryBoxSize) + 7, Game.HEIGHT * Game.SCALE - inventoryBoxSize + 7, 32, 32, null);
			}
			
			if(selected == i) {
				g.setColor(Color.red);
				g.drawRect(inicialPosition + (i * inventoryBoxSize), Game.HEIGHT * Game.SCALE - inventoryBoxSize - 1, inventoryBoxSize, inventoryBoxSize);
			}
		}
	}

}
