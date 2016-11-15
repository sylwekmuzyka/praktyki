
import com.gif4j.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.gif4j.*;

/**
 * This example demostrates how to create image tour using layout positioning and morphing filters.
 */

public class ImageTourExample2 {

    public static void main(String[] args) {

        // change out directory if it is necessary
        File outputDir = new File("."+File.separator+"result");
        if (!outputDir.exists())
            outputDir.mkdirs();

        try {
            // read images. Here we read from files but it can be any source (internet, database etc.)
            BufferedImage[] images = new BufferedImage[4];
            for (int i = 1; i <= 4; i++)
                images[i - 1] = ImageIO.read(ImageTourExample2.class.getResourceAsStream("house_" + i + ".jpg"));
            // scale down image to 150x120 icons
            for (int i = 0; i < 4; i++)
                images[i] = ImageUtils.scale(images[i], 150, 120, false);
            // create frames
            GifFrame[] frames = new GifFrame[4];
            for (int i = 0; i < 4; i++){
                switch (i%4){
                    case 0:
                        frames[i] = new GifFrame(images[i],GifFrame.LAYOUT_TOP_LEFT);
                        break;
                    case 1:
                        frames[i] = new GifFrame(images[i],GifFrame.LAYOUT_TOP_RIGHT);
                        break;
                    case 2:
                        frames[i] = new GifFrame(images[i],GifFrame.LAYOUT_BOTTOM_LEFT);
                        break;
                    case 3:
                        frames[i] = new GifFrame(images[i],GifFrame.LAYOUT_BOTTOM_RIGHT);
                        break;
                }
            }
            // set long delay (5 seconds = 500*1/100) for the last frame
            frames[3].setDelay(500);
            GifImage gifImage = new GifImage(300, 240);
            // set indefinite looping
            gifImage.setLoopNumber(0);

            for (int i = 0; i < 4; i++)
                gifImage.addGifFrame(frames[i],new TunnelFilter(i%2==0,8,10));
            GifEncoder.encode(gifImage, new File(outputDir, "ImageTourExample2.gif"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

