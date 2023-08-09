package com.victor.main;

import com.victor.entities.Enemy1;
import com.victor.entities.Entity;

public class EnemySpawn {
	
	public int interval = 60 * 10;
	public int curTime = 0;
	
	public void tick() {
		curTime++;
		if(curTime == interval) {
			curTime = 0;
			Enemy1 enemy1 = new Enemy1(16, 16, 16, 16, 0.5, Entity.ENEMY1);
			Game.entities.add(enemy1);
		}
	}

}
