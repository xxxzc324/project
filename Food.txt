package com.huowolf.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.huowolf.util.Global;


public class Food extends Point{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Color foodColor;
	
	

	public void setFoodColor(Color foodColor) {
		this.foodColor = foodColor;
	}

	public Color getFoodColor() {
		return foodColor;
	}

	public void newFood(Point p) {
		setLocation(p);
	}
	
	public boolean isFoodEated(Snake snake) {			
		return 	this.equals(snake.getHead());
	}
	
	public void drawMe(Graphics g) {
		if(foodColor==null) {
			g.setColor(Color.RED);
		}else {
			g.setColor(foodColor);
		}
		
		g.fill3DRect(x*Global.CELL_SIZE, y*Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
	}
}
