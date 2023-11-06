package assign01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrayscaleImageTest {

    private GrayscaleImage smallSquare;
    private GrayscaleImage smallWide;

    private GrayscaleImage allBlackSquare;
    private GrayscaleImage allWhiteSquare;
    private GrayscaleImage blackAndWhiteSquare;
    private GrayscaleImage stripedSquare;

    @BeforeEach
    void setUp() {
        smallSquare = new GrayscaleImage(new double[][]{{1,2},{3,4}});
        smallWide = new GrayscaleImage(new double[][]{{1,2,3},{4,5,6}});
        allBlackSquare = new GrayscaleImage(new double[][]{{0, 0}, {0, 0}});
        allWhiteSquare = new GrayscaleImage(new double[][]{{255, 255}, {255,255}});
        blackAndWhiteSquare = new GrayscaleImage(new double[][]{{255, 0}, {0, 255}});
        stripedSquare = new GrayscaleImage(new double [][]{{255,0,255}, {255, 0, 255}});
    }

    @Test
    void testGetPixel(){
        assertEquals(smallSquare.getPixel(0,0), 1);
        assertEquals(smallSquare.getPixel(1,0), 2);
        assertEquals(smallSquare.getPixel(0,1), 3);
        assertEquals(smallSquare.getPixel(1,1), 4);

    }

    @Test
    void testGetPixelBW(){
        assertEquals(blackAndWhiteSquare.getPixel(0,0), 255);
    }

    @Test
    void testGetPixelThrowsOnNegativeX(){
        assertThrows(IllegalArgumentException.class, () -> { smallSquare.getPixel(-1,0);});
    }

    @Test
    void testEquals() {
        assertEquals(smallSquare, smallSquare);
        var equivalent = new GrayscaleImage(new double[][]{{1,2},{3,4}});
        assertEquals(smallSquare, equivalent);
    }

    @Test
    void testBlackWhiteNotEquals() {
        assertNotEquals(allBlackSquare, allWhiteSquare);
    }

    @Test
    void averageBrightness() {
        assertEquals(smallSquare.averageBrightness(), 2.5, 2.5*.001);
        var bigZero = new GrayscaleImage(new double[1000][1000]);
        assertEquals(bigZero.averageBrightness(), 0);
    }

    @Test
    void testAverageBrightnessAllBlackSquare() {
        assertEquals(allBlackSquare.averageBrightness(), 0, 0.001); // Tolerance of 0.001 for double comparison
    }

    @Test
    void testAverageBrightnessAllWhiteSquare(){
        assertEquals(allWhiteSquare.averageBrightness(), 255, 0.001);
    }

    @Test
    void normalized() {
        var smallNorm = smallSquare.normalized();
        assertEquals(smallNorm.averageBrightness(), 127, 127*.001);
        var scale = 127/2.5;
        var expectedNorm = new GrayscaleImage(new double[][]{{scale, 2*scale},{3*scale, 4*scale}});
        for(var row = 0; row < 2; row++){
            for(var col = 0; col < 2; col++){
                assertEquals(smallNorm.getPixel(col, row), expectedNorm.getPixel(col, row),
                        expectedNorm.getPixel(col, row)*.001,
                        "pixel at row: " + row + " col: " + col + " incorrect");
            }
        }
        assertThrows(ArithmeticException.class, () -> {
            allBlackSquare.normalized();
        });
    }

    @Test
    void mirrored() {
        var expected = new GrayscaleImage(new double[][]{{2,1},{4,3}});
        assertEquals(smallSquare.mirrored(), expected);
    }

    @Test
    void mirroredBlackAndWhite(){
        var expected = new GrayscaleImage(new double[][]{{0,255},{255,0}});
        assertEquals(blackAndWhiteSquare.mirrored(), expected);
    }

    @Test
    void mirrorablePicture(){
        assertEquals(stripedSquare.mirrored(), stripedSquare);
        assertNotEquals(smallSquare.mirrored(), smallSquare);
    }

    @Test
    void cropped() {
        var cropped = smallSquare.cropped(1,1,1,1);
        assertEquals(cropped, new GrayscaleImage(new double[][]{{4}}));
    }

    @Test
    void testCroppedThrowsIllegalArgumentException() {

        // Test specifying a rectangle outside the image bounds
        assertThrows(IllegalArgumentException.class, () -> {
            smallWide.cropped(0, 0, 4, 2); // The specified rectangle exceeds the image bounds
        });

        assertThrows(IllegalArgumentException.class, () -> {
            smallWide.cropped(1, 1, 3, 2); // The specified rectangle exceeds the image bounds
        });
    }

    @Test
    void croppedBlackAndWhiteSquare() {
        var cropped = blackAndWhiteSquare.cropped(0, 0, 1, 1);
        assertEquals(cropped, new GrayscaleImage(new double[][]{{0}}));
    }

    @Test
    void squarified() {
        var squared = smallWide.squarified();
        var expected = new GrayscaleImage(new double[][]{{1,2},{4,5}});
        assertEquals(squared, expected);
    }

    //Test that the square that is already even on all sides doesn't get cropped
    @Test
    void squarifiedSmallSquare(){
        var squared = smallSquare.squarified();
        assertEquals(squared, smallSquare);

    }
}