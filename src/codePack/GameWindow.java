
package codePack;

import controls.keyboard;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class GameWindow extends Canvas implements Runnable{
    
    private static final long serialVersionUID = 1L;
    
    private static JFrame frame;
    private static Thread thread;
    private static keyboard keyboard;

    private static final int window_X = 1280, window_Y = 720;
    private static final String gameName = "GameWindows";
    
    private static int ups = 0;
    private static int fps = 0;
    
    private static volatile boolean inWorking = false;

    private GameWindow(){
        setPreferredSize(new Dimension(window_X, window_Y));
        
        keyboard = new keyboard();
        addKeyListener(keyboard);
        
        frame = new JFrame(gameName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        GameWindow game = new GameWindow();
        game.start();
    }
    
    private synchronized void start(){
        inWorking = true;
        
        thread = new Thread(this, "Graphic");
        thread.start();
    }
    
    private synchronized void stop(){
        inWorking = false;
        
        
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private void updateGame(){
        keyboard.update();
        
        if (keyboard.movement_up) {
            
        }
        if (keyboard.movement_down) {
            
        }
        if (keyboard.movement_left) {
            
        }
        if (keyboard.movement_right) {
            
        }
        
        ups++;
    }
    
    private void showGame(){
        fps++;
    }

    @Override
    public void run() {
        final int NS_PER_SECOND = 1000000000;
        final byte UPS_Objetive = 60;
        final double NS_PER_UPDATE = NS_PER_SECOND / UPS_Objetive;
        
        long updateReference = System.nanoTime();
        long cointReference = System.nanoTime();
        
        double timeLapsed;
        double delta = 0;
        
        requestFocus();
        
        while (inWorking) {
            final long loopStart = System.nanoTime();
            
            timeLapsed = loopStart - updateReference;
            updateReference = loopStart;
            
            delta += timeLapsed / NS_PER_UPDATE;
            
            while (delta >= 1) {
                updateGame();
                delta--;
            }
            
            showGame();
            
            if (System.nanoTime() - cointReference > NS_PER_SECOND) {
                frame.setTitle(gameName + " || UPS: " + ups + " || FPS: " + fps);
                ups = 0;
                fps = 0;
                cointReference = System.nanoTime();
            }
        }
    }
}
