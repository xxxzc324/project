package model;

public class Crash {

    // �ж��ӵ��͵л���ײ
    public boolean Boom(Zd z, Dj d) {
        int zx, zy, dx, dy;
        boolean crash = false;

        //�ӵ�����
        zx = z.getX();
        zy = z.getY();
        
        //�л�����
        dx = d.getX();
        dy = d.getY();

        if (dx < zx && zy - dy < 30 && dx + 30 > zx) {
            crash = true; //�����ײ������ֵ

        }
        return crash;

    }

    //�ɻ���ײ
    public boolean Boom2(Dj d, int x, int y) {
        int dx, dy;
        boolean crash = false;
        
        //�л�����
        dx = d.getX();
        dy = d.getY();
        
        //Ӣ�ۻ�����
        x = x - 50;
        y = y - 80;

        //��ײ����
        if (dx > x+20 && y - dy < 20 && dx < x + 60) {
            crash = true; //�����ײ������ֵ

        }
        return crash;

    }

}


