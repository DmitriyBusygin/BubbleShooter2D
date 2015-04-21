package bubbleShooter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{

    //Fields
    public static final int FIELD_WIDTH = 400;
    public static final int FIELD_HEIGHT = 400;

    private Thread threadGamePanel;

    private BufferedImage imagePanelGame;
    private Graphics2D graphics2dPanelGame;

    public static GameBack backGround;
    public static Player player;
    public static ArrayList<Bullet> bulletsArray;
    public static ArrayList<Enemy> enemysArray;
    public static WaveEnemy waveEnemy;

    //Constructor
    public GamePanel(){
        super();
        setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
        setFocusable(true);
        requestFocus();

        addKeyListener(new Listeners());
    }

    public void start(){
        threadGamePanel = new Thread(this);
        threadGamePanel.start();
    }

    //Functions
    @Override
    public void run(){

        imagePanelGame = new BufferedImage(FIELD_WIDTH, FIELD_HEIGHT, BufferedImage.TYPE_INT_RGB);
        graphics2dPanelGame = (Graphics2D) imagePanelGame.getGraphics();
        graphics2dPanelGame.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        backGround = new GameBack();
        player = new Player();
        bulletsArray = new ArrayList<Bullet>();
        enemysArray = new ArrayList<Enemy>();
        waveEnemy = new WaveEnemy();

        while(true){ //TODO States

            gameUpdate();
            gameRender();
            gameDraw();

            try {
                threadGamePanel.sleep(25); //TODO FPS
            } catch (InterruptedException ignore) {/*NOP*/}
        }
    }

    public void gameUpdate(){
        //BackGround update
        backGround.upDate();

        //Wave update
        waveEnemy.update();

        //Player update
        player.upDate();

        //Billets update
        for(int i = 0; i < bulletsArray.size(); i++){
            bulletsArray.get(i).update();
            boolean isRemoveBullet = bulletsArray.get(i).removeArrayBullet();
            if(isRemoveBullet){
                bulletsArray.remove(i);
                i--;
            }
        }

        //Enemys update
        for(int i = 0; i < enemysArray.size(); i++){
            enemysArray.get(i).update();
        }

        //Bullets - enemies collide
        for(int countEnemy = 0; countEnemy < enemysArray.size(); countEnemy++){
            Enemy tempEnemy = enemysArray.get(countEnemy);
            double xCoordinateEnemy = tempEnemy.getxCoordinates();
            double yCoordinateEnemy = tempEnemy.getyCoordinates();

            for(int countBullet = 0; countBullet < bulletsArray.size(); countBullet++){
                Bullet tempBullet = bulletsArray.get(countBullet);
                double xCoordinateBullet = tempBullet.getxCoordinates();
                double yCoordinateBullet = tempBullet.getyCoordinates();
                double dx = xCoordinateEnemy - xCoordinateBullet;
                double dy = yCoordinateEnemy - yCoordinateBullet;
                double dist = Math.sqrt(dx * dx + dy * dy);
                if((int) dist < tempEnemy.getRadiusEnemy() + tempBullet.getRadiusBullet()){
                    tempEnemy.hitInEnemy();
                    bulletsArray.remove(countBullet);
                    countBullet--;
                    if(tempEnemy.isRemoveEnemy()){
                        enemysArray.remove(countEnemy);
                        countEnemy--;
                        break;
                    }
                }
            }
        }
        //Player-enemy collides
        for (int countEnemy = 0; countEnemy < enemysArray.size(); countEnemy++){
            Enemy tempEnemy = enemysArray.get(countEnemy);
            double xEnemyCore = tempEnemy.getxCoordinates();
            double yEnemyCore = tempEnemy.getyCoordinates();

            double xPlayerCore = player.getCoordinatesX();
            double yPlayerCore = player.getCoordinatesY();
            double dx = xEnemyCore - xPlayerCore;
            double dy = yEnemyCore - yPlayerCore;
            double dist = Math.sqrt(dx * dx + dy * dy);
            if(dist <= tempEnemy.getRadiusEnemy() + player.getPlayerRadius()){
                tempEnemy.hitInEnemy();
                player.hitInEnemy();
                if(tempEnemy.isRemoveEnemy()) {
                    enemysArray.remove(countEnemy);
                    countEnemy--;
                }
            }
        }

    }

    public void gameRender(){
        //BackGround draw
        backGround.draw(graphics2dPanelGame);

        //Wave draw
        if(waveEnemy.showWave()){
            waveEnemy.draw(graphics2dPanelGame);
        }
        //Player update
        player.draw(graphics2dPanelGame);


        //Bullets draw
        for(Bullet tempBullet : bulletsArray){
            tempBullet.draw(graphics2dPanelGame);
        }

        //Enemy's draw
        for(Enemy tempEnemy : enemysArray){
            tempEnemy.draw(graphics2dPanelGame);
        }


    }

    private void gameDraw(){
        Graphics graphics = this.getGraphics();
        graphics.drawImage(imagePanelGame, 0, 0, null);
        graphics.dispose();
    }
}
