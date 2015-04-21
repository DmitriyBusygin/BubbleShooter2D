package bubbleShooter;

import java.awt.*;

public class Player {

    //Fields
    private double coordinatesX;
    private double coordinatesY;
    private int playerRadius;

    private double dx; //Move coef
    private double dy;

    private int speedPlayer;
    private int healtPlayer;

    private Color colorPlayer;

    public static boolean upPlayer;
    public static boolean downPlayer;
    public static boolean leftPlayer;
    public static boolean rightPlayer;

    public static boolean isFiring;

    //Constructor
    public Player(){
        coordinatesX = GamePanel.FIELD_WIDTH / 2;
        coordinatesY = GamePanel.FIELD_HEIGHT / 2;
        playerRadius = 5;

        speedPlayer = 5;
        healtPlayer = 10;

        dx = 0;
        dy = 0;

        colorPlayer = Color.WHITE;

        upPlayer = false;
        downPlayer = false;
        leftPlayer = false;
        rightPlayer = false;

        isFiring = false;
    }

    //Functions
    public double getCoordinatesX(){
        return coordinatesX;
    }

    public double getCoordinatesY(){
        return coordinatesY;
    }

    public int getPlayerRadius() {
        return playerRadius;
    }

    public void hitInEnemy(){
        healtPlayer--;
        //System.out.println(healtPlayer);
    }

    public void upDate(){
        if(upPlayer && coordinatesY > playerRadius){
            dy -= speedPlayer;
        }
        if(downPlayer && coordinatesY < GamePanel.FIELD_HEIGHT - playerRadius){
            dy += speedPlayer;
        }
        if(leftPlayer && coordinatesX > playerRadius){
            dx -= speedPlayer;
        }
        if(rightPlayer && coordinatesX < GamePanel.FIELD_WIDTH - playerRadius){
            dx += speedPlayer;
        }
        if(upPlayer && leftPlayer || upPlayer && rightPlayer || downPlayer && leftPlayer || downPlayer && rightPlayer){
            double angle = Math.toRadians(45);
            dy = dy * Math.sin(angle);
            dx = dx * Math.cos(angle);
        }

        coordinatesY += dy;
        coordinatesX += dx;

        dy = 0;
        dx = 0;

        if(isFiring){
            GamePanel.bulletsArray.add(new Bullet());
        }
    }

    public void draw(Graphics2D graphics2D){
        graphics2D.setColor(colorPlayer);
        graphics2D.fillOval((int) coordinatesX - playerRadius,(int) coordinatesY - playerRadius, 2 * playerRadius, 2 * playerRadius);
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.setColor(colorPlayer.darker());
        graphics2D.drawOval((int) coordinatesX - playerRadius,(int) coordinatesY - playerRadius, 2 * playerRadius, 2 * playerRadius);
        graphics2D.setStroke(new BasicStroke(1));
    }

}
