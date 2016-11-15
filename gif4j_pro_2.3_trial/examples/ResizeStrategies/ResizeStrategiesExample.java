
import com.gif4j.GifImage;
import com.gif4j.GifFrame;
import com.gif4j.GifEncoder;

import java.io.File;
import java.io.IOException;
import java.awt.*;

/**
 * This example demostrates {@link com.gif4j.GifImage} resize strategies usage and differences.
 */

public class ResizeStrategiesExample {

    public static void main(String[] args) {
        // change out directory if it is necessary
        File outputDir = new File("."+File.separator+"result");
        if (!outputDir.exists())
            outputDir.mkdirs();
        try {
            GifImage imageWithExtendToCurrentResizeStrategy = new GifImage(GifImage.RESIZE_STRATEGY_EXTEND_TO_CURRENT);
            // set indefinite looping
            imageWithExtendToCurrentResizeStrategy.setLoopNumber(0);

            imageWithExtendToCurrentResizeStrategy.addGifFrame(
                    new GifFrame(TestUtils.genTestImage("frame 1 at position (0,0)", 100, 100)));
            imageWithExtendToCurrentResizeStrategy.addGifFrame(
                    new GifFrame(TestUtils.genTestImage("frame 2 at position (50,50)", 150, 150), new Point(50, 50)));
            imageWithExtendToCurrentResizeStrategy.addGifFrame(
                    new GifFrame(TestUtils.genTestImage("frame 3 at position (100,100)", 100, 100), new Point(100, 100)));
            GifEncoder.encode(imageWithExtendToCurrentResizeStrategy, new File(outputDir, "ResizeStrategiesExample_extend_to_current.gif"), true);
            GifImage imageWithCropToFitImageSize = new GifImage(150,150,GifImage.RESIZE_STRATEGY_CROP_TO_FIT_IMAGE_SIZE);
            // set indefinite looping
            imageWithCropToFitImageSize.setLoopNumber(0);

            imageWithCropToFitImageSize.addGifFrame(
                    new GifFrame(TestUtils.genTestImage("frame 1 at position (0,0)", 100, 100)));
            imageWithCropToFitImageSize.addGifFrame(
                    new GifFrame(TestUtils.genTestImage("frame 2 at position (50,50)", 150, 150), new Point(50, 50)));
            imageWithCropToFitImageSize.addGifFrame(
                    new GifFrame(TestUtils.genTestImage("frame 3 at position (100,100)", 100, 100), new Point(100, 100)));
            GifEncoder.encode(imageWithCropToFitImageSize, new File(outputDir, "ResizeStrategiesExample_crop_to_fit.gif"), true);
            GifImage imageWithScaleToFitImageSize = new GifImage(150,150,GifImage.RESIZE_STRATEGY_SCALE_TO_FIT_IMAGE_SIZE);
            // set indefinite looping
            imageWithScaleToFitImageSize.setLoopNumber(0);

            imageWithScaleToFitImageSize.addGifFrame(
                    new GifFrame(TestUtils.genTestImage("frame 1 at position (0,0)", 100, 100)));
            imageWithScaleToFitImageSize.addGifFrame(
                    new GifFrame(TestUtils.genTestImage("frame 2 at position (50,50)", 150, 150), new Point(50, 50)));
            imageWithScaleToFitImageSize.addGifFrame(
                    new GifFrame(TestUtils.genTestImage("frame 3 at position (100,100)", 100, 100), new Point(100, 100)));
            GifEncoder.encode(imageWithScaleToFitImageSize, new File(outputDir, "ResizeStrategiesExample_scale_to_fit.gif"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
