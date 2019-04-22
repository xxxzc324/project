package model;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
public class Dj {
	 int x,y;
	    String icon;
	    
	    public Dj(String icon) {
	        this.icon=icon;
	        x=(int)(Math.random()*360)+10; //随机生成x坐标
	        y=-30;
	     }
	    
	    //画敌机
	    public void drawPlane(Graphics g) {
	        Image plane=new ImageIcon(icon).getImage();
	        g.drawImage(plane, x-15, y-15, null);

	    }

	    //x,y的get和set方法
	    public int getX() {
	        return x;
	    }

	    public void setX(int x) {
	        this.x = x;
	    }

	    public int getY() {
	        return y;
	    }

	    public void setY(int y) {
	        this.y = y;
	    }

	}
