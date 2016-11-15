
import com.gif4j.GifDecoder;
import com.gif4j.GifFrame;
import com.gif4j.GifImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Load a GIF Image and Extract GIF File Format Meta Info
 */

public class GifImageLoadExample {

    // Usage: java GifImageLoadExample [PathToGifImageToLoad] (please be sure that the gif4j jar is in your CLASSPATH)
    // By default the gif4j_logotype.gif as an example gif image is used
    public static void main(String[] args) {
        if (args.length == 0) {
            File gifImageFile = new File("gif4j_logotype.gif");
            GifImageLoadExample gifImageLoadExample = new GifImageLoadExample();
            try {
                System.out.println("-------gif4j_logotype.gif-------");
                System.out.println("");
                gifImageLoadExample.loadGifImageAndExtractMetaInfo(gifImageFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            GifImageLoadExample gifImageLoadExample = new GifImageLoadExample();
            for (int i = 0; i < args.length; i++) {
                File gifImageFile = new File(args[i]);
                System.out.println("-------" + args[i] + "-------");
                System.out.println("");
                try {
                    gifImageLoadExample.loadGifImageAndExtractMetaInfo(gifImageFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("");
            }

        }

    }

    public GifImage loadGifImageAndExtractMetaInfo(File file) throws IOException {
        // load and decode gif image from the file
        GifImage gifImage = GifDecoder.decode(file);
        // print general GIF image info
        System.out.println("gif image format version: " + gifImage.getVersion());
        System.out.println("gif image logic screen width: " + gifImage.getScreenWidth());
        System.out.println("gif image logic screen height: " + gifImage.getScreenHeight());
        // check if one or more comments present
        if (gifImage.getNumberOfComments() > 0) {
            // get iterator over gif image textual comments
            Iterator commentsIterator = gifImage.comments();
            while (commentsIterator.hasNext())
                System.out.println(commentsIterator.next()); // print comments
        }
        System.out.println("number of frames: " + gifImage.getNumberOfFrames());
        // below we iterate frames in loop
        // but it can also be done using Iterator instance: gifImage.frames()
        for (int i = 0; i < gifImage.getNumberOfFrames(); i++) {
            System.out.println("------frame(" + (i + 1) + ")---------");
            GifFrame frame = gifImage.getFrame(i);
            System.out.println("width: " + frame.getWidth());
            System.out.println("height: " + frame.getHeight());
            System.out.println("position: " + frame.getX() + "," + frame.getY());
            System.out.println("disposal method: " + frame.getDisposalMethod());
            System.out.println("delay time: " + frame.getDelay());
            System.out.println("is interlaced: " + frame.isInterlaced());
            // get frame's color model
            IndexColorModel frameColorModel = frame.getColorModel();
            System.out.println("number of colors: " + frameColorModel.getMapSize());
            System.out.println("is transparent: " + frameColorModel.hasAlpha());
            System.out.println("transparent index: " + frameColorModel.getTransparentPixel());
            //get frame's representation as an Image
            Image image = frame.getAsImage();
            //get frame's representation as a BufferedImage
            BufferedImage bufferedImage = frame.getAsBufferedImage();
        }
        return gifImage;
    }
}
