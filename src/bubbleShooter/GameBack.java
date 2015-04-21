package bubbleShooter;

import java.awt.*;

public class GameBack {
    //Fields
    private Color colorBackGround;

    //Constructor
    public GameBack(){
        colorBackGround = Color.blue;
    }


    //Functions
    public void upDate(){

    }

    public void draw(Graphics2D graphics2D){
        graphics2D.setColor(colorBackGround);
        graphics2D.fillRect(0, 0, GamePanel.FIELD_WIDTH, GamePanel.FIELD_HEIGHT);
    }
}
