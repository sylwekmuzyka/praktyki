
import com.gif4j.GifImage;
import com.gif4j.GifFrame;
import com.gif4j.GifEncoder;
import com.gif4j.ImageUtils;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

/**
 * This example demostrates differences between absolute and relative frames positioning
 */

public class FramePositioningExample {

    public static void main(String[] args) {
        // change out directory if it is necessary
        File outputDir = new File("." + File.separator + "result");
        if (!outputDir.exists())
            outputDir.mkdirs();
        // first example with relative positioning and without frames covering each on other
        GifImage imageWithRelativeFrames = new GifImage(300, 300);
        // set indefinite looping
        imageWithRelativeFrames.setLoopNumber(0);
        imageWithRelativeFrames.addGifFrame(
                new GifFrame(TestUtils.genTestImage("top-left", 100, 100),
                        GifFrame.LAYOUT_TOP_LEFT));
        imageWithRelativeFrames.addGifFrame(
                new GifFrame(TestUtils.genTestImage("top-center", 100, 100),
                        GifFrame.LAYOUT_TOP_CENTER));
        imageWithRelativeFrames.addGifFrame(
                new GifFrame(TestUtils.genTestImage("top-right", 100, 100),
                        GifFrame.LAYOUT_TOP_RIGHT));
        imageWithRelativeFrames.addGifFrame(
                new GifFrame(TestUtils.genTestImage("middle-left", 100, 100),
                        GifFrame.LAYOUT_MIDDLE_LEFT));
        imageWithRelativeFrames.addGifFrame(
                new GifFrame(TestUtils.genTestImage("middle-center", 100, 100),
                        GifFrame.LAYOUT_MIDDLE_CENTER));
        imageWithRelativeFrames.addGifFrame(
                new GifFrame(TestUtils.genTestImage("middle-right", 100, 100),
                        GifFrame.LAYOUT_MIDDLE_RIGHT));
        imageWithRelativeFrames.addGifFrame(
                new GifFrame(TestUtils.genTestImage("bottom-left", 100, 100),
                        GifFrame.LAYOUT_BOTTOM_LEFT));
        imageWithRelativeFrames.addGifFrame(
                new GifFrame(TestUtils.genTestImage("bottom-center", 100, 100),
                        GifFrame.LAYOUT_BOTTOM_CENTER));
        imageWithRelativeFrames.addGifFrame(
                new GifFrame(TestUtils.genTestImage("bottom-right", 100, 100),
                        GifFrame.LAYOUT_BOTTOM_RIGHT));
        try {
            GifEncoder.encode(imageWithRelativeFrames, new File(outputDir, "FramePositioningExample_relative_1.gif"), true);
        } catch (IOException e) {
        }

        // second example with relative positioning and frames covering each on other
        imageWithRelativeFrames = new GifImage(200, 200);
        // set indefinite looping
        imageWithRelativeFrames.setLoopNumber(0);
        imageWithRelativeFrames.addGifFrame(
                new GifFrame(TestUtils.genTestImage("top-left", 100, 100),
                        GifFrame.LAYOUT_TOP_LEFT));
        imageWithRelativeFrames.addGifFrame(
                new GifFrame(TestUtils.genTestImage("top-center", 100, 100),
                        GifFrame.LAYOUT_TOP_CENTER));
        imageWithRelativeFrames.addGifFrame(
                new GifFrame(TestUtils.genTestImage("top-right", 100, 100),
                        GifFrame.LAYOUT_TOP_RIGHT));
        imageWithRelativeFrames.addGifFrame(
                new GifFrame(TestUtils.genTestImage("middle-left", 100, 100),
                        GifFrame.LAYOUT_MIDDLE_LEFT));
        imageWithRelativeFrames.addGifFrame(
                new GifFrame(TestUtils.genTestImage("middle-center", 100, 100),
                        GifFrame.LAYOUT_MIDDLE_CENTER));
        imageWithRelativeFrames.addGifFrame(
                new GifFrame(TestUtils.genTestImage("middle-right", 100, 100),
                        GifFrame.LAYOUT_MIDDLE_RIGHT));
        imageWithRelativeFrames.addGifFrame(
                new GifFrame(TestUtils.genTestImage("bottom-left", 100, 100),
                        GifFrame.LAYOUT_BOTTOM_LEFT));
        imageWithRelativeFrames.addGifFrame(
                new GifFrame(TestUtils.genTestImage("bottom-center", 100, 100),
                        GifFrame.LAYOUT_BOTTOM_CENTER));
        imageWithRelativeFrames.addGifFrame(
                new GifFrame(TestUtils.genTestImage("bottom-right", 100, 100),
                        GifFrame.LAYOUT_BOTTOM_RIGHT));
        try {
            GifEncoder.encode(imageWithRelativeFrames, new File(outputDir, "FramePositioningExample_relative_2.gif"), true);
        } catch (IOException e) {
        }
        // third example demostrates how relative positioning can be used to produce image tours
        try {
            // read images. Here we read from files but it can be any source (internet, database etc.)
            BufferedImage[] images = new BufferedImage[4];
            for (int i = 1; i <= 4; i++)
                images[i - 1] = ImageIO.read(FramePositioningExample.class.getResourceAsStream("house_" + i + ".jpg"));
            // scale down image to 150x120 icons
            for (int i = 0; i < 4; i++)
                images[i] = ImageUtils.scale(images[i], 150, 120, false);
            // create frames
            GifFrame[] frames = new GifFrame[4];
            for (int i = 0; i < 4; i++) {
                switch (i % 4) {
                    case 0:
                        frames[i] = new GifFrame(images[i], GifFrame.LAYOUT_TOP_LEFT);
                        break;
                    case 1:
                        frames[i] = new GifFrame(images[i], GifFrame.LAYOUT_TOP_RIGHT);
                        break;
                    case 2:
                        frames[i] = new GifFrame(images[i], GifFrame.LAYOUT_BOTTOM_LEFT);
                        break;
                    case 3:
                        frames[i] = new GifFrame(images[i], GifFrame.LAYOUT_BOTTOM_RIGHT);
                        break;
                }
            }
            // set long delay (5 seconds = 500*1/100) for the last frame
            frames[3].setDelay(500);
            GifImage gifImage = new GifImage(300, 240);
            // set indefinite looping
            gifImage.setLoopNumber(0);
            for (int i = 0; i < 4; i++)
                gifImage.addGifFrame(frames[i]);
            GifEncoder.encode(gifImage, new File(outputDir, "FramePositioningExample_relative_3.gif"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
