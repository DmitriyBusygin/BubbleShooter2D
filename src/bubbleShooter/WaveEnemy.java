package bubbleShooter;

import java.awt.*;

public class WaveEnemy {

    //Fields
    private int waveNumber;
    private int waveMultiplex;
    private long waveTimer;
    private long waveDelay;
    private long waveTimerDiff;

    private String waveText;

    //Constructor
    public WaveEnemy(){
        waveNumber = 1;
        waveMultiplex = 5;

        waveTimer = 0;
        waveDelay = 5000;
        waveTimerDiff = 0;

        waveText = "W A V E";
    }

    //Functions
    public void createEnemys(){
        int enemyCount = waveNumber * waveMultiplex;
        if(waveNumber <= 13){
            while(enemyCount > 0){
                int typeEnemy = 1;
                int rankEnemy = 1;
                if (enemyCount > 10)
                    rankEnemy = 2;
                if (enemyCount > 20)
                    rankEnemy = 3;
                GamePanel.enemysArray.add(new Enemy(typeEnemy, rankEnemy));
                enemyCount -= typeEnemy * rankEnemy;
            }
        }
        waveNumber++;
    }

    public void update(){
        if(GamePanel.enemysArray.size() == 0 && waveTimer == 0){
            waveTimer = System.nanoTime();

        }
        if(waveTimer > 0){
            waveTimerDiff += (System.nanoTime() - waveTimer) / 1000000;
            waveTimer = System.nanoTime();
        }
        if(waveTimerDiff > waveDelay){
            createEnemys();
            waveTimer = 0;
            waveTimerDiff = 0;
        }
    }
    public boolean showWave(){
        boolean isShow;
        if(waveTimer != 0){
            isShow = true;
        }
        else {
            isShow = false;
        }
        return isShow;
    }

    public void draw(Graphics2D graphics2D){
        double divider = waveDelay / 180;
        double alpha = waveTimerDiff / divider;
        alpha = 255 * Math.sin(Math.toRadians(alpha));
        if(alpha < 0) alpha = 0;
        if(alpha > 255) alpha = 255;
        graphics2D.setFont(new Font("consoles", Font.PLAIN, 20));
        graphics2D.setColor(new Color(255, 255, 255,(int) alpha));
        String textTemp = "-  " +  waveNumber + "  " + waveText + "  -";
        if(waveNumber > 13)
            textTemp = "- Congratulations, You win!-";
        double lengthText = graphics2D.getFontMetrics().getStringBounds(textTemp, graphics2D).getWidth();
        graphics2D.drawString(textTemp, GamePanel.FIELD_WIDTH / 2 - (int) (lengthText / 2), GamePanel.FIELD_HEIGHT / 2);
    }
}
