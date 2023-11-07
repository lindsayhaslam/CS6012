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

    //Checks if coordinates are within the image width/height
    //Image data at the input coordinates are returned.
    public double getPixel(int x, int y) {

        if (x < 0 || x >= imageData.length || y < 0 || y >= imageData[0].length) {
            throw new IllegalArgumentException("Coordinates (x, y) are not within the image width/height.");
        }
        double pixel = imageData[y][x];
        return pixel;
    }

    //Checks if two images are equal in size (rows and columns)
    //Nested for-loop for sifting through the data of each pixel
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

    //Initialize totalBrightness to zero
    //Nested for-loop to sift through both rows and columns, concatenating to totalBrightness as you go.
    //Find totalPixels by multiplying rows by columns
    //Divide totalBrightness by totalPixels
    //Return
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


   //Helper function to be used in normalized, mirrored, and cropped.
   //Inserts data input into the coordinates input
    public void setPixel (int x, int y, double data){
        imageData[y][x] = data;
    }

    //Get the scale by dividing 127 by averageBrightness.
    //Store rows and columns into variables.
    //Check if the averageBrightness is 0, throw an exception.
    //Create a new Grayscale Image to store new normalized image.
    //Nested for-loops to sift through all columns and rows.
    //Store original value, then multiple originalValue by scale, store in normalizedValue.
    //Call setPixel and store normalizedValue at set coordinates into normalizedImage.
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

    //Create new GrayScaleImage object to store mirroredImage
    //Nested for-loops to go through all columns and rows
    //Call .setPixel, giving the set column, row, and data, which is the column, minus 1, minus col (which is 0)
    //Return the mirrored image
        public GrayscaleImage mirrored () {

            GrayscaleImage mirroredImage = new GrayscaleImage(this.imageData);

            for (var row = 0; row < imageData.length; row++) {
                for (var col = 0; col < imageData[0].length; col++) {
                    mirroredImage.setPixel(col, row, imageData[row][imageData[0].length - 1 - col]);
                }
            }
            return mirroredImage;
        }


        //Set numRows and numCols
        //Checks if the rectangle goes outside the bounds of the original image
        //Create new greyscale image called croppedImage that takes in the height and width input
        //Nested for-loop to sift through all columns and rows, calling .setPixel
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

    //Set numRows and numCols
    //If the image is already square, return it.
    //Find the lowest number between numRows and numCols and store it in newSize.
    //Create dan array of doubles called squaredImage and store the newSize in both x and y coordinates.
    //Find the difference of numRows and newSize, divide it by 2. Same thing for numCols.
    //Nested for-loops that iterated over all rows and columns, taking into account the removal of rows and columns
    //from the top and left to create the square image.
    //Return the new squaredImage.

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