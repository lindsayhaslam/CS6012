package assign01;


import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;


/**
 * Represents a grayscale (black and white) image as a 2D array of "pixel" brightnesses
 * 255 is "white" 127 is "gray" 0 is "black" with intermediate values in between
 * Author: Ben Jones and Lindsay Haslam
 */
public class GrayscaleImage {
    private double[][] imageData; // the actual image data


    /**
     * Initialize an image from a 2D array of doubles
     * This constructor creates a copy of the input array
     *
     * @param data initial pixel values
     * @throws IllegalArgumentException if the input array is empty or "jagged" meaning not all rows are the same length
     */
    public GrayscaleImage(double[][] data) {
        if (data.length == 0 || data[0].length == 0) {
            throw new IllegalArgumentException("Image is empty");
        }

        imageData = new double[data.length][data[0].length];
        for (var row = 0; row < imageData.length; row++) {
            if (data[row].length != imageData[row].length) {
                throw new IllegalArgumentException("All rows must have the same length");
            }
            for (var col = 0; col < imageData[row].length; col++) {
                imageData[row][col] = data[row][col];
            }
        }
    }

    /**
     * Fetches an image from the specified URL and converts it to grayscale
     * Uses the AWT Graphics2D class to do the conversion, so it may add
     * an item to your dock/menu bar as if you're loading a GUI program
     *
     * @param url where to download the image
     * @throws IOException if the image can't be downloaded for some reason
     */
    public GrayscaleImage(URL url) throws IOException {
        var inputImage = ImageIO.read(url);
        //convert input image to grayscale
        //based on (https://stackoverflow.com/questions/6881578/how-to-convert-between-color-models)
        var grayImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d = grayImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, null);
        g2d.dispose();
        imageData = new double[grayImage.getHeight()][grayImage.getWidth()];

        //raster is basically a width x height x 1 3-dimensional array
        var grayRaster = grayImage.getRaster();
        for (var row = 0; row < imageData.length; row++) {
            for (var col = 0; col < imageData[0].length; col++) {
                //getSample parameters are x (our column) and y (our row), so they're "backwards"
                imageData[row][col] = grayRaster.getSampleDouble(col, row, 0);
            }
        }
    }

    public void savePNG(File filename) throws IOException {
        var outputImage = new BufferedImage(imageData[0].length, imageData.length, BufferedImage.TYPE_BYTE_GRAY);
        var raster = outputImage.getRaster();
        for (var row = 0; row < imageData.length; row++) {
            for (var col = 0; col < imageData[0].length; col++) {
                raster.setSample(col, row, 0, imageData[row][col]);
            }
        }
        ImageIO.write(outputImage, "png", filename);
    }

    ///Methods to be filled in by students below

    /**
     * Get the pixel brightness value at the specified coordinates
     * (0,0) is the top left corner of the image, (width -1, height -1) is the bottom right corner
     *
     * @param x horizontal position, increases left to right
     * @param y vertical position, **increases top to bottom**
     * @return the brightness value at the specified coordinates
     * @throws IllegalArgumentException if x, y are not within the image width/height
     */
    public double getPixel(int x, int y) {

        if (x < 0 || x >= imageData.length || y < 0 || y >= imageData[0].length) {
            throw new IllegalArgumentException("Coordinates (x, y) are not within the image width/height.");
        }
        double pixel = imageData[y][x];
        return pixel;
    }

    /**
     * Two images are equal if they have the same size and each corresponding pixel
     * in the two images is exactly equal
     *
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof GrayscaleImage)) {
            return false;
        }
        GrayscaleImage otherImage = (GrayscaleImage) other;

        if (this.imageData.length != otherImage.imageData.length || this.imageData[0].length != otherImage.imageData[0].length) {
            return false;
        }

        for (var row = 0; row < imageData.length; row++) {
            for (var col = 0; col < imageData[0].length; col++) {
                if (this.imageData[row][col] != otherImage.imageData[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Computes the average of all values in image data
     *
     * @return the average of the imageData array
     */
    public double averageBrightness() {
        double totalBrightness = 0.0;
        for (var row = 0; row < imageData.length; row++) {
            for (var col = 0; col < imageData[0].length; col++) {
                totalBrightness += imageData[row][col];
            }
        }
            int totalPixels = imageData.length * imageData[0].length;
            double average = totalBrightness / totalPixels;
    return average;
}

    /**
     * Return a new GrayScale image where the average new average brightness is 127
     * To do this, uniformly scale each pixel (ie, multiply each imageData entry by the same value)
     * Due to rounding, the new average brightness will not be 127 exactly, but should be very close
     * The original image should not be modified
     * @return a GrayScale image with pixel data uniformly rescaled so that its averageBrightness() is 127
     */

   //Helper function
    public void setPixel (int x, int y, double data){
        imageData[y][x] = data;
    }
    public GrayscaleImage normalized() {
        double scale = 127 / averageBrightness();
        int numRows = imageData.length;
        int numCols = imageData[0].length;

        // Check if averageBrightness is zero to prevent division by zero
        if (averageBrightness() == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }

        GrayscaleImage normalizedImage = new GrayscaleImage(new double[numRows][numCols]);

        for (var row = 0; row < imageData.length; row++) {
            for (var col = 0; col < imageData[0].length; col++) {
                double originalValue = imageData[row][col];
                double normalizedValue = originalValue * scale;
                normalizedImage.setPixel(col, row, normalizedValue);
            }
        }
            return normalizedImage;
    }


        /**
         * Returns a new grayscale image that has been "mirrored" across the y-axis
         * In other words, each row of the image should be reversed
         * The original image should be unchanged
         * @return a new GrayscaleImage that is a mirrored version of the this
         */
        public GrayscaleImage mirrored () {

            GrayscaleImage mirroredImage = new GrayscaleImage(this.imageData);

            for (var row = 0; row < imageData.length; row++) {
                for (var col = 0; col < imageData[0].length; col++) {
                    mirroredImage.setPixel(col, row, imageData[row][imageData[0].length - 1 - col]);
                }
            }
            return mirroredImage;
        }

    /**
     * Returns a new GrayscaleImage of size width x height, containing the part of `this`
     * from startRow -> startRow + height, startCol -> startCol + width
     * The original image should be unmodified
     * @param startRow
     * @param startCol
     * @param width
     * @param height
     * @return A new GrayscaleImage containing the sub-image in the specified rectangle
     * @throws IllegalArgumentException if the specified rectangle goes outside the bounds of the original image
     */
    public GrayscaleImage cropped(int startRow, int startCol, int width, int height) {
        int numRows = imageData.length;
        int numCols = imageData[0].length;

        if (startRow < 0 || startCol < 0 || startRow + height > numRows || startCol + width > numCols) {
            throw new IllegalArgumentException("Specified rectangle goes outside the bounds of the original image");
        }

        GrayscaleImage croppedImage = new GrayscaleImage(new double[height][width]);

        for (var row = 0; row < startRow; row++) {
            for (var col = 0; col < startCol; col++) {
                croppedImage.setPixel(col, row, imageData[startRow + row][startCol + col]);
            }
        }
        return croppedImage;
    }

    /**
     * Returns a new "centered" square image (new width == new height)
     * For example, if the width is 20 pixels greater than the height,
     * this should return a height x height image, with 10 pixels removed from the left and right
     * edges of the image
     * If the number of pixels to be removed is odd, remove 1 fewer pixel from the left or top part
     * (note this convention should be SIMPLER/EASIER to implement than the alternative)
     * The original image should not be changed
     * @return a new, square, GrayscaleImage
     */
    public GrayscaleImage squarified(){
        int numRows = imageData.length;
        int numCols = imageData[0].length;

        if (numRows == numCols){
            return this;
        }

        int newSize = Math.min(numRows, numCols);
        double[][] squaredImage = new double[newSize][newSize];

        int removeTop = (numRows - newSize) / 2;
        int removeLeft = (numCols - newSize) / 2;

        for (int newRow = 0; newRow < newSize; newRow++) {
            for (int newCol = 0; newCol < newSize; newCol++) {
                squaredImage[newRow][newCol] = imageData[newRow + removeTop][newCol + removeLeft];
            }
        }

        return new GrayscaleImage(squaredImage);
    }
}