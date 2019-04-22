package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.Crash;
import model.Dj;
import model.Zd;

public class GamePanel extends JPanel implements MouseMotionListener, MouseListener {
    /**
     * 
     */
    private static final long serialVersionUID = 1682690307762671162L;
    int bx, by, x, y, dx, dy, score = 0,count=0,state; //背景坐标bx,by,英雄机坐标x,y,敌机坐标dx,dy,分数score,计数器count,运行状态state
    Image bg; //背景图
    boolean db = false; //敌机爆炸图片绘图开关
    List<Zd> zd = new ArrayList<Zd>(); //存储子弹对象
    List<Dj> dj = new ArrayList<Dj>(); //存储敌机对象
    
    //运行状态常量
    public static final int START=1;
    public static final int RUNNING=2;
    public static final int PAUSE=3;
    public static final int OVER=4;

    //构造方法
    public GamePanel() {
        state=START;
        bx = 0;
        by = -5350;
        bg = new ImageIcon("src/images/background_1.png").getImage();

    }

 
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(state==RUNNING){
            String sc = "分数:" + String.valueOf(score);
            g.drawImage(bg, bx, by, null); //画出背景
            g.drawRoundRect(0, 0, 120, 30, 5, 5); //分数框
            g.setFont(new Font("TimesRoman", Font.BOLD, 24));
            g.setColor(Color.RED);
            g.drawString(sc, 2, 20); //画出分数

            Image hero = new ImageIcon("src/images/plane_2.png").getImage(); 
            g.drawImage(hero, x - 50, y - 80, null);  //画出英雄机
            if (db) { //在敌机爆炸时画出爆炸图片
                Image boom = new ImageIcon("src/images/boom1.png").getImage();
                g.drawImage(boom, dx, dy, null);
                db = false;
            }

            for (int i = 0; i < zd.size(); i++) {
                zd.get(i).drawZd(g);  //画出所有子弹

            }

            for (int j = 0; j < dj.size(); j++) {
                dj.get(j).drawPlane(g); //画出所有敌机

            }
        }else if(state==START){ //开始界面
            Image start=new ImageIcon("src/images/start.png").getImage();
            g.drawImage(start, 0, 0, 400, 650, null);
        }else if(state==OVER){ //结束界面
            Image over=new ImageIcon("src/images/over.png").getImage();
            g.drawImage(over, 0, 0, 400, 650, null);
        }else if(state==PAUSE){ //暂停界面
            Image pause=new ImageIcon("src/images/pause.png").getImage();
            g.drawImage(pause, 0, 0, 400, 650, null);
        }
        

    }
 // 移动类
    public void Move() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                
                while (true) {
                    if(state==RUNNING){
                        
                        // 背景移动
                        count++;
                        by += 2;
                        if (by > 0) {
                            by = -5350;
                            bg = new ImageIcon("src/images/background_2.png").getImage();
                        }

                        // 创建子弹
                        if (count % 3 == 0) {
                            zd.add(new Zd(x, y));
                        } 
                        for (int a = 0; a < zd.size(); a++) {
                            zd.get(a).setY(zd.get(a).getY() - 30); //改变子弹坐标，使子弹移动
                            if (zd.get(a).getY() < 0) { //判断子弹越界
                                zd.remove(a);

                            }
                        }

                        // 创建敌机
                        if (count % 21 == 0) {
                            dj.add(new Dj("src/images/e4.png"));
                        }else if (count % 31 == 0) {
                            dj.add(new Dj("src/images/e5.png"));
                        }else if (count % 41 == 0) {
                            dj.add(new Dj("src/images/e6.png"));
                        }
                        else if (count % 51 == 0) {
                            dj.add(new Dj("src/images/e7.png"));
                        }
                        else if (count % 61 == 0) {
                            dj.add(new Dj("src/images/e8.png"));
                        }
                        for (int b = 0; b < dj.size(); b++) {
                            dj.get(b).setY(dj.get(b).getY() + 3); //改变敌机坐标，使敌机移动
                            if (dj.get(b).getY() > 650) { //判断敌机越界
                                state=OVER; 

                            }

                        }
                        // 子弹和敌机碰撞
                        Crash crash = new Crash();
                        for (int j = 0; j < zd.size(); j++) {
                            Zd z = zd.get(j);
                            for (int i = 0; i < dj.size(); i++) {
                                Dj d = dj.get(i);
                                if (crash.Boom(z, d)) { //判断是否相撞
                                    score += 2; 
                                    dx = dj.get(i).getX(); //敌机坐标
                                    dy = dj.get(i).getY();
                                    db = true; //敌机爆炸
                                    dj.remove(i); //从集合中删除敌机
                                    zd.remove(j); //从集合中删除子弹
                                    break;
                                }
                            }
                        }
                        
                        //判断飞机相撞
                        for (int i = 0; i < dj.size(); i++) {
                            Dj d = dj.get(i);
                            if (crash.Boom2(d, x, y)) {
                                dx = dj.get(i).getX();
                                dy = dj.get(i).getY();
                                db = true;
                                state=OVER; //游戏结束
                            }
                        }
                    }

                    
                    repaint(); //重画
                    try {
                        Thread.sleep(20); //线程休眠
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    //鼠标点击事件
    @Override
    public void mouseClicked(MouseEvent e) {
        if(state==START||state==PAUSE){
            state=RUNNING;
        }else if (state==RUNNING){
            state=PAUSE;;
        }else if(state==OVER){
            
            //重新开始后初始化参数值
            state=RUNNING;
            score=0;
            count=0;
            bx = 0;
            bg = new ImageIcon("src/images/background_1.png").getImage();
            by = -5350;
            zd = new ArrayList<Zd>();
            dj = new ArrayList<Dj>();
            
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO 自动生成的方法存根

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO 自动生成的方法存根

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO 自动生成的方法存根

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO 自动生成的方法存根

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO 自动生成的方法存根

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX(); //动态获取鼠标的坐标值
        y = e.getY();

    }

}





