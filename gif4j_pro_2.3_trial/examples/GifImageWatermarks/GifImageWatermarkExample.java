
import com.gif4j.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This example demostrates the whole GIF image watermarking using Gif4J PRO
 *
 * NOTE: IN SPITE OF THE SIZE THE DEFAULT GIF IMAGE TO TEST "gif4j_logotype.gif"
 * IS THE ARDUOUS EXAMPLE - IT CONTAINS 45 FRAMES (GIF IMAGES USUALLY CONTAIN LESS THAN 10 FRAMES)!
 */


public class GifImageWatermarkExample {

    // Usage: java GifImageWatermarkExample [PathToGifImageToWatermark] (please be sure that the gif4j jar is in your CLASSPATH)
    // By default the gif4j_logotype.gif as an example gif image is used
    public static void main(String[] args) {
        File gifImageFileToWatermark = null;
        if (args.length == 0) {
            gifImageFileToWatermark = new File("gif4j_logotype.gif");
        } else
            gifImageFileToWatermark = new File(args[0]);

        // load and decode gif image
        GifImage gifImage = null;
        try {
            gifImage = GifDecoder.decode(gifImageFileToWatermark);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // change out directory if it is necessary
        File outputDir = new File("." + File.separator + "result");
        if (!outputDir.exists())
            outputDir.mkdirs();

        // create watermark image using TextPainter
        TextPainter painter = new TextPainter(new Font("Verdana", Font.BOLD, 10));
        painter.setOutlinePaint(Color.WHITE);
        BufferedImage watermarkImage = painter.renderString("Gif4J", true);

        // create watermark
        Watermark watermark = new Watermark(watermarkImage, Watermark.LAYOUT_TOP_LEFT, 0.7f);

        // apply watermark
        GifImage topLeftWatermarked = watermark.apply(gifImage, false);
        // apply watermark smoothly
        GifImage topLeftWatermarked_smoothly = watermark.apply(gifImage, true);

        // change the watermark alignment
        watermark.setLayoutConstraint(Watermark.LAYOUT_MIDDLE_CENTER);

        // apply watermark
        GifImage middleCenterWatermarked = watermark.apply(gifImage, false);
        // apply watermark smoothly
        GifImage middleCenterWatermarked_smoothly = watermark.apply(gifImage, true);

        // change the watermark alignment
        watermark.setLayoutConstraint(Watermark.LAYOUT_BOTTOM_RIGHT);

        // apply watermark
        GifImage bottomRightWatermarked = watermark.apply(gifImage, false);
        // apply watermark smoothly
        GifImage bottomRightWatermarked_smoothly = watermark.apply(gifImage, true);

        // change the watermark alignment
        watermark.setLayoutConstraint(Watermark.LAYOUT_COVER_CONSECUTIVELY);
        // change the watermark transparency
        watermark.setTransparency(0.25f);

        // apply watermark
        GifImage coverConsWatermarked = watermark.apply(gifImage, false);
        // apply watermark smoothly
        GifImage coverConsWatermarked_smoothly = watermark.apply(gifImage, true);

        // Save the results
        try {
            GifEncoder.encode(topLeftWatermarked,new File(outputDir,"topLeftWatermarked.gif"));
            GifEncoder.encode(topLeftWatermarked_smoothly,new File(outputDir,"topLeftWatermarked_smooth.gif"));
            GifEncoder.encode(middleCenterWatermarked,new File(outputDir,"middleCenterWatermarked.gif"));
            GifEncoder.encode(middleCenterWatermarked_smoothly,new File(outputDir,"middleCenterWatermarked_smooth.gif"));
            GifEncoder.encode(bottomRightWatermarked,new File(outputDir,"bottomRightWatermarked.gif"));
            GifEncoder.encode(bottomRightWatermarked_smoothly,new File(outputDir,"bottomRightWatermarked_smooth.gif"));
            GifEncoder.encode(coverConsWatermarked,new File(outputDir,"coverConsWatermarked.gif"));
            GifEncoder.encode(coverConsWatermarked_smoothly,new File(outputDir,"coverConsWatermarked_smooth.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
