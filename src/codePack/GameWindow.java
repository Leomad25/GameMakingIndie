
package codePack;

import controls.Keyboard;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;
import sourceCode.Screen;

public class GameWindow extends Canvas implements Runnable{
    
    private static final long serialVersionUID = 1L;
    
    private static final int window_X = 400, window_Y = 300;
    private static final String gameName = "GameWindows";
    
    private static JFrame frame;
    private static Thread thread;
    private static Keyboard keyboard;
    private static Screen screen;

    private static int ups = 0;
    private static int fps = 0;
    
    private static int x = 0;
    private static int y = 0;
    
    private static volatile boolean inWorking = false;
    
    private static BufferedImage image = new BufferedImage(window_X, window_Y, BufferedImage.TYPE_INT_RGB);
    private static int[][] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getBankData();

    private GameWindow(){
        setPreferredSize(new Dimension(window_X, window_Y));
        
        screen = new Screen(window_X, window_Y);
        
        keyboard = new Keyboard();
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
            y++;
        }
        if (keyboard.movement_down) {
            y--;
        }
        if (keyboard.movement_left) {
            x++;
        }
        if (keyboard.movement_right) {
            x--;
        }
        
        ups++;
    }
    
    private void showGame(){
        BufferStrategy strategy = getBufferStrategy();
        
        if (strategy == null) {
            createBufferStrategy(3);
            return;
        }
        
        screen.clear();
        screen.show(x, y);
        
        //POSIBLE FALLO ----------------------------------------------------------------------------------------------------------------------------------
        System.arraycopy(screen.pixels, 0, pixels, 0, pixels.length);
        
        Graphics g = strategy.getDrawGraphics();
        
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        
        strategy.show();
        
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
