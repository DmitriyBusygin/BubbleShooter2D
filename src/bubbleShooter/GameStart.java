package bubbleShooter;

import javax.swing.*;

public class GameStart {

    public static void main(String[] args) {
        GamePanel panel = new GamePanel();
        JFrame startFrameGame = new JFrame("BubbleShooter2D");
        startFrameGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startFrameGame.setContentPane(panel);
        startFrameGame.pack();
        startFrameGame.setLocationRelativeTo(null);
        startFrameGame.setVisible(true);
        panel.start();
    }
}
