package sourceCode;

public class Screen {
    
    private final int width;
    private final int high;
    
    public final int[][] pixels;
    
    
    // Temp
    private final static int MASCARA_DEL_SPRITE = 480 - 1;
    // End temp
    
    public Screen(final int width, final int high) {
        this.width = width;
        this.high = high;
        
        pixels = new int[width][high];
    }
    
    public void clear() {
        for (int y = 0; y < high; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x][y] = 5;
            }
        }
    }
    
    public void show(final int compensation_X, final int compensation_Y) {
        for (int y = 0; y < high; y++) {
            int position_Y = y + compensation_Y;
            
            if(position_Y < 0 || position_Y >= high) {
                continue;
            }
            
            for (int x = 0; x < width; x++) {
                int position_X = x + compensation_X;
                
                if (position_X < 0 || position_X >= width) {
                    continue;
                }
                
                pixels[position_X][position_Y] = Sprite.grass_texture.pixels[x & MASCARA_DEL_SPRITE][y & MASCARA_DEL_SPRITE];
            }
        }
    }
}
