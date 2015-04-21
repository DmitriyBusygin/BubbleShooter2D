package bubbleShooter;

import java.awt.*;

public class Enemy {

    //Fields
    private double xCoordinates;
    private double yCoordinates;
    private int radiusEnemy;

    private double speedEnemy;
    private double dx;
    private double dy;

    private double healtEnemy;

    private int typeEnemy;
    private int rankEnemy;

    private Color colorEnemy;

    //Constructor
    public Enemy(int typeEnemy, int rankEnemy){
        this.typeEnemy = typeEnemy;
        this.rankEnemy = rankEnemy;

        xCoordinates = (Math.random() * GamePanel.FIELD_WIDTH);
        yCoordinates = 0;

        //Type enemy
        switch(rankEnemy){
            //Green enemy
            case(1): colorEnemy = Color.GREEN;
                switch(typeEnemy) {
                    case (1):
                        radiusEnemy = 7;
                        speedEnemy = 2;
                        healtEnemy = 2;

                        double angle = Math.toRadians(Math.random() * 360);

                        dx = Math.sin(angle) * speedEnemy;
                        dy = Math.cos(angle) * speedEnemy;
                        break;
                }
                break;
            //Pink enemy
            case(2): colorEnemy = Color.PINK;
                switch(typeEnemy){
                    case(1):
                        radiusEnemy = 6;
                        speedEnemy = 2;
                        healtEnemy = 3;

                        double angle = Math.toRadians(Math.random() * 360);

                        dx = Math.sin(angle) * speedEnemy;
                        dy = Math.cos(angle) * speedEnemy;
                        break;
                }
                break;
            //Red enemy
            case(3): colorEnemy = Color.RED;
                switch(typeEnemy){
                    case(1):
                        radiusEnemy = 6;
                        speedEnemy = 3;
                        healtEnemy = 4;

                        double angle = Math.toRadians(Math.random() * 360);

                        dx = Math.sin(angle) * speedEnemy;
                        dy = Math.cos(angle) * speedEnemy;
                        break;
                }
                break;

        }
    }


    //Functions
    public double getxCoordinates(){
        return xCoordinates;
    }

    public double getyCoordinates(){
        return yCoordinates;
    }

    public int getRadiusEnemy(){
        return radiusEnemy;
    }

    public boolean isRemoveEnemy(){
        if(healtEnemy <= 0){
            return true;
        }
        return false;
    }

    public void hitInEnemy(){
        healtEnemy--;

    }

    public void update(){
        xCoordinates += dx;
        yCoordinates += dy;

        if(xCoordinates < 0 && dx < 0) dx = -dx;
        if(xCoordinates > GamePanel.FIELD_WIDTH && dx > 0) dx = -dx;
        if(yCoordinates < 0 && dy < 0) dy = -dy;
        if(yCoordinates > GamePanel.FIELD_HEIGHT && dy > 0) dy = -dy;
    }

    public void draw(Graphics2D graphics2D){
        graphics2D.setColor(colorEnemy);
        graphics2D.fillOval((int) xCoordinates - radiusEnemy, (int)yCoordinates - radiusEnemy, 2 * radiusEnemy, 2 * radiusEnemy);
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.setColor(colorEnemy.darker());
        graphics2D.drawOval((int) xCoordinates - radiusEnemy, (int)yCoordinates - radiusEnemy, 2 * radiusEnemy, 2 * radiusEnemy);
        graphics2D.setStroke(new BasicStroke(1));
    }

}
