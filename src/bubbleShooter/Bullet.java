package bubbleShooter;

import java.awt.*;

public class Bullet {

    //Fields
    private double xCoordinates;
    private double yCoordinates;
    private int radiusBullet;
    private int speedBullet;

    private Color colorBullet;

    //Constructor
    public Bullet(){
        xCoordinates = GamePanel.player.getCoordinatesX();
        yCoordinates = GamePanel.player.getCoordinatesY();
        radiusBullet = 2;

        speedBullet = 10;

        colorBullet = Color.WHITE;
    }

    //Functions
    public int getRadiusBullet(){
        return radiusBullet;
    }
    public double getxCoordinates() {
        return xCoordinates;
    }
    public double getyCoordinates() {
        return yCoordinates;
    }

    public boolean removeArrayBullet(){
        if(yCoordinates < 0){
            return true;
        }
        return false;
    }

    public void update(){
        yCoordinates -= speedBullet;
    }

    public void draw(Graphics2D graphics2D){
        graphics2D.setColor(colorBullet);
        graphics2D.fillOval((int)xCoordinates, (int)yCoordinates, radiusBullet, radiusBullet * 2);
    }
}
