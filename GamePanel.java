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
    int bx, by, x, y, dx, dy, score = 0,count=0,state; //��������bx,by,Ӣ�ۻ�����x,y,�л�����dx,dy,����score,������count,����״̬state
    Image bg; //����ͼ
    boolean db = false; //�л���ըͼƬ��ͼ����
    List<Zd> zd = new ArrayList<Zd>(); //�洢�ӵ�����
    List<Dj> dj = new ArrayList<Dj>(); //�洢�л�����
    
    //����״̬����
    public static final int START=1;
    public static final int RUNNING=2;
    public static final int PAUSE=3;
    public static final int OVER=4;

    //���췽��
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
            String sc = "����:" + String.valueOf(score);
            g.drawImage(bg, bx, by, null); //��������
            g.drawRoundRect(0, 0, 120, 30, 5, 5); //������
            g.setFont(new Font("TimesRoman", Font.BOLD, 24));
            g.setColor(Color.RED);
            g.drawString(sc, 2, 20); //��������

            Image hero = new ImageIcon("src/images/plane_2.png").getImage(); 
            g.drawImage(hero, x - 50, y - 80, null);  //����Ӣ�ۻ�
            if (db) { //�ڵл���ըʱ������ըͼƬ
                Image boom = new ImageIcon("src/images/boom1.png").getImage();
                g.drawImage(boom, dx, dy, null);
                db = false;
            }

            for (int i = 0; i < zd.size(); i++) {
                zd.get(i).drawZd(g);  //���������ӵ�

            }

            for (int j = 0; j < dj.size(); j++) {
                dj.get(j).drawPlane(g); //�������ел�

            }
        }else if(state==START){ //��ʼ����
            Image start=new ImageIcon("src/images/start.png").getImage();
            g.drawImage(start, 0, 0, 400, 650, null);
        }else if(state==OVER){ //��������
            Image over=new ImageIcon("src/images/over.png").getImage();
            g.drawImage(over, 0, 0, 400, 650, null);
        }else if(state==PAUSE){ //��ͣ����
            Image pause=new ImageIcon("src/images/pause.png").getImage();
            g.drawImage(pause, 0, 0, 400, 650, null);
        }
        

    }
 // �ƶ���
    public void Move() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                
                while (true) {
                    if(state==RUNNING){
                        
                        // �����ƶ�
                        count++;
                        by += 2;
                        if (by > 0) {
                            by = -5350;
                            bg = new ImageIcon("src/images/background_2.png").getImage();
                        }

                        // �����ӵ�
                        if (count % 3 == 0) {
                            zd.add(new Zd(x, y));
                        } 
                        for (int a = 0; a < zd.size(); a++) {
                            zd.get(a).setY(zd.get(a).getY() - 30); //�ı��ӵ����꣬ʹ�ӵ��ƶ�
                            if (zd.get(a).getY() < 0) { //�ж��ӵ�Խ��
                                zd.remove(a);

                            }
                        }

                        // �����л�
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
                            dj.get(b).setY(dj.get(b).getY() + 3); //�ı�л����꣬ʹ�л��ƶ�
                            if (dj.get(b).getY() > 650) { //�жϵл�Խ��
                                state=OVER; 

                            }

                        }
                        // �ӵ��͵л���ײ
                        Crash crash = new Crash();
                        for (int j = 0; j < zd.size(); j++) {
                            Zd z = zd.get(j);
                            for (int i = 0; i < dj.size(); i++) {
                                Dj d = dj.get(i);
                                if (crash.Boom(z, d)) { //�ж��Ƿ���ײ
                                    score += 2; 
                                    dx = dj.get(i).getX(); //�л�����
                                    dy = dj.get(i).getY();
                                    db = true; //�л���ը
                                    dj.remove(i); //�Ӽ�����ɾ���л�
                                    zd.remove(j); //�Ӽ�����ɾ���ӵ�
                                    break;
                                }
                            }
                        }
                        
                        //�жϷɻ���ײ
                        for (int i = 0; i < dj.size(); i++) {
                            Dj d = dj.get(i);
                            if (crash.Boom2(d, x, y)) {
                                dx = dj.get(i).getX();
                                dy = dj.get(i).getY();
                                db = true;
                                state=OVER; //��Ϸ����
                            }
                        }
                    }

                    
                    repaint(); //�ػ�
                    try {
                        Thread.sleep(20); //�߳�����
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    //������¼�
    @Override
    public void mouseClicked(MouseEvent e) {
        if(state==START||state==PAUSE){
            state=RUNNING;
        }else if (state==RUNNING){
            state=PAUSE;;
        }else if(state==OVER){
            
            //���¿�ʼ���ʼ������ֵ
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
        // TODO �Զ����ɵķ������

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO �Զ����ɵķ������

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO �Զ����ɵķ������

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO �Զ����ɵķ������

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO �Զ����ɵķ������

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX(); //��̬��ȡ��������ֵ
        y = e.getY();

    }

}





