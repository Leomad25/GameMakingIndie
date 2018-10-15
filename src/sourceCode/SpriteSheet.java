package sourceCode;


import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {
    private final int size_X;
    private final int size_Y;
    //public final int[] pixels;
    public final int[][] pixels;
    
    
    // Sprite sheet Collection --------------------------------------------------------------------------------------------------------------------------------------------------------
    
    public static SpriteSheet grass = new SpriteSheet("/textures/grass2.png", 480, 480);
    
    //  End of Collection -------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    public SpriteSheet(final String Route, final int Size_X, final int Size_Y){
        this.size_X = Size_X;
        this.size_Y = Size_Y;
        
        //pixels = new int[Size_X * Size_Y];
        pixels = new int[Size_X][Size_Y];
        
        BufferedImage image;
        
        /* try {
        image = ImageIO.read(SpriteSheet.class.getResource(Route));
        
        image.getRGB(0, 0, Size_X, Size_Y, pixels, 0, Size_X);
        } catch(IOException e) {
            e.printStackTrace();
        } */
        try {
            image = ImageIO.read(SpriteSheet.class.getResource(Route));
            
            for (int x = 0; x < Size_X; x++){
                for (int y = 0; y < Size_Y; y++){
                    pixels[x][y] = image.getRGB(x, y);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
