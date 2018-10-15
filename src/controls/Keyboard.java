package controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
    private final static int numberKeys = 120;
    private boolean[] keys = new boolean[numberKeys];
    
    public boolean movement_up;
    public boolean movement_down;
    public boolean movement_left;
    public boolean movement_right;
    
    public void update(){
        movement_up = keys[KeyEvent.VK_W];
        movement_down = keys[KeyEvent.VK_S];
        movement_left = keys[KeyEvent.VK_A];
        movement_right = keys[KeyEvent.VK_D];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
