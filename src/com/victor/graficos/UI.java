package com.victor.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.victor.entities.Player;
import com.victor.main.Game;
import com.victor.world.World;


public class UI {
	
	public static int seconds = 0;
	public static int minutes = 0;
	public static int frames = 0;
	
	public void tick() {
		
		// LOGICA CONTADOR DE HORARIOS
		 frames++;
		 if(frames == 60) {
			 // Passou 1 seg
			 frames=0;
			 seconds++;
			 
			 if(seconds == 60) {
				 seconds = 0;
				 minutes++;
				 
				 
				// LOGICA DE MUDANCA HORARIO
				if(UI.minutes % 2 == 0) {	// a cada 2 min troca o ciclo
					World.CICLO++;
					if(World.CICLO > World.noite) {
						World.CICLO = 0;
					}
				}
			 }
		 }
	}

	public void render(Graphics g) {
		int curLife = (int)((Game.player.life / 100) * 200);
		
		// BARRA DE VIDA
		g.setColor(Color.red);
		g.fillRect(Game.WIDTH * Game.SCALE - 220, 10 , 200, 30);
		g.setColor(Color.green);
		g.fillRect(Game.WIDTH * Game.SCALE - 220, 10 , curLife, 30);
		
		// VIDA
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 23));
		g.drawString((int)(Game.player.life) + "/" + "100" , Game.WIDTH * Game.SCALE - 110, 34);
		
		// CONTADOR
		String formatTime = "";
		if(minutes < 10) {
			formatTime += "0" + minutes + ":";
		}else {
			formatTime += minutes + ":";
		}
		
		if(seconds < 10) {
			formatTime += "0" + seconds;
		}else {
			formatTime += seconds;
		}
		
		g.setFont(new Font("arial", Font.BOLD, 23));
		g.drawString(formatTime, 30, 34);
	}
	
}
