package model;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Zd {
    int x, y;

    //���췽��
    public Zd(int a, int b) { //Ӣ�ۻ�����
        
        this.x=a;
        this.y=b;
        
    }
    
    //���ӵ�
    public void drawZd(Graphics g) {
        Image zd=new ImageIcon("src/images/bullet_7.png").getImage();
        g.drawImage(zd, x-19, y-80, null);

    }

    //x,y��get��set����
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
