package frame;

import javax.swing.JFrame;

import panel.GamePanel;

public class Game {

    public static void main(String[] args) {
        JFrame j = new JFrame(); //创建窗口
        GamePanel g = new GamePanel(); //创建画布
        j.add(g); //添加画布
        j.setSize(400, 650);
        j.setTitle("PlaneWar"); //设置标题
        j.setLocationRelativeTo(null); //设置居中
        j.setDefaultCloseOperation(3); //默认关闭
        j.setVisible(true); //窗口可见性
        g.Move(); //调用Move方法
        j.addMouseListener(g); //添加监听器
        j.addMouseMotionListener(g);

    }

}


