package model;

public class Crash {

    // 判断子弹和敌机碰撞
    public boolean Boom(Zd z, Dj d) {
        int zx, zy, dx, dy;
        boolean crash = false;

        //子弹坐标
        zx = z.getX();
        zy = z.getY();
        
        //敌机坐标
        dx = d.getX();
        dy = d.getY();

        if (dx < zx && zy - dy < 30 && dx + 30 > zx) {
            crash = true; //如果相撞返回真值

        }
        return crash;

    }

    //飞机相撞
    public boolean Boom2(Dj d, int x, int y) {
        int dx, dy;
        boolean crash = false;
        
        //敌机坐标
        dx = d.getX();
        dy = d.getY();
        
        //英雄机坐标
        x = x - 50;
        y = y - 80;

        //相撞条件
        if (dx > x+20 && y - dy < 20 && dx < x + 60) {
            crash = true; //如果相撞返回真值

        }
        return crash;

    }

}


