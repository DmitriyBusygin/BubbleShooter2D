package bubbleShooter;

import java.awt.event.*;

public class Listeners implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W){
            Player.upPlayer = true;
        }
        if(key == KeyEvent.VK_S){
            Player.downPlayer = true;
        }
        if(key == KeyEvent.VK_A){
            Player.leftPlayer = true;
        }
        if(key == KeyEvent.VK_D){
            Player.rightPlayer = true;
        }
        if(key == KeyEvent.VK_SPACE){
            Player.isFiring = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W){
            Player.upPlayer = false;
        }
        if(key == KeyEvent.VK_S){
            Player.downPlayer = false;
        }
        if(key == KeyEvent.VK_A){
            Player.leftPlayer = false;
        }
        if(key == KeyEvent.VK_D){
            Player.rightPlayer = false;
        }
        if(key == KeyEvent.VK_SPACE){
            Player.isFiring = false;
        }
    }

    @Override
     public void keyTyped(KeyEvent e) {

    }
}
