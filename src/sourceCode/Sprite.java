package sourceCode;

public class Sprite {
    private final int size_X;
    private final int size_Y;
    
    private int pos_X;
    private int pos_Y;
    
    public int[][] pixels;
    private final SpriteSheet sheet;
    
    
    // Sprite Collection -------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    public static Sprite grass_texture = new Sprite(480, 480, 0, 0, SpriteSheet.grass);
    
    //  End of Collection -------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    
    public Sprite(final int Size_X, final int Size_Y, final int Pos_X, final int Pos_Y, final SpriteSheet SpriteSheet) {
        this.size_X = Size_X;
        this.size_Y = Size_Y;
        
        this.pixels = new int[Size_X][Size_Y];
        
        this.pos_X = Pos_X;
        this.pos_Y = Pos_Y;
        
        this.sheet = SpriteSheet;
        
        for (int x = 0; x < Size_X; x++) {
            for (int y = 0; y < Size_Y; y++) {
                pixels[x][y] = sheet.pixels[pos_X + x][pos_Y + y];
            }
        }
    }
}
