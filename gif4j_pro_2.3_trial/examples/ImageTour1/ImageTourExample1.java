
import com.gif4j.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.*;

import com.gif4j.*;

/**
 * This example demostrates how to create image tour.
 * Every image will sign with some describing title using {@link com.gif4j.Watermark} instance.
 * You can simply extend this example by adding morhping between frames (see {@link com.gif4j.MorphingFilter} sub-classes).
 */

public class ImageTourExample1 {

    public static void main(String[] args) {

        // change out directory if it is necessary
        File outputDir = new File("."+File.separator+"result");
        if (!outputDir.exists())
            outputDir.mkdirs();

        ImageIO.scanForPlugins();
        try {
            // read images. Here we read from files but it can be any source (internet, database etc.)
            BufferedImage[] images = new BufferedImage[4];
            for (int i=1;i<=4;i++)
                images[i-1] = ImageIO.read(ImageTourExample1.class.getResourceAsStream("house_"+i+".jpg"));
            // create gif image
            GifImage gifImage = new GifImage(160,120,GifImage.RESIZE_STRATEGY_SCALE_TO_FIT_IMAGE_SIZE);
            // set indefinite looping
            gifImage.setLoopNumber(0);

            //create font to draw titles
            Font font = new Font("Arial",Font.BOLD,15);
            //create TextPainter instance
            TextPainter painter = new TextPainter(font,true);
            painter.setForegroundPaint(Color.WHITE);
            // create the first frame
            GifFrame frame1 = new GifFrame(images[0]);
            // Render title for this frame
            painter.setOutlinePaint(Color.RED);
            BufferedImage titleForFrame1 = painter.renderString("House",true);
            // wrap title with Watermark instance and add frame to gif image
            gifImage.addGifFrame(frame1,new Watermark(titleForFrame1,Watermark.LAYOUT_TOP_CENTER,1.0f));
            // create the second frame
            GifFrame frame2 = new GifFrame(images[1]);
            // Render title for this frame
            painter.setOutlinePaint(Color.BLUE);
            BufferedImage titleForFrame2 = painter.renderString("Room",true);
            // wrap title with Watermark instance and add frame to gif image
            gifImage.addGifFrame(frame2,new Watermark(titleForFrame2,Watermark.LAYOUT_MIDDLE_CENTER,0.8f));
            // create the third frame
            GifFrame frame3 = new GifFrame(images[2]);
            // Render title for this frame
            painter.setOutlinePaint(new Color(0,82,1));
            BufferedImage titleForFrame3 = painter.renderString("Kitchen",true);
            // wrap title with Watermark instance and add frame to gif image
            gifImage.addGifFrame(frame3,new Watermark(titleForFrame3,Watermark.LAYOUT_BOTTOM_CENTER,0.6f));
            // create the forth frame
            GifFrame frame4 = new GifFrame(images[3]);
            // Render title for this frame
            painter.setOutlinePaint(Color.BLACK);
            BufferedImage titleForFrame4 = painter.renderString("Garden",true);
            // wrap header with Watermark instance and add frame to gif image
            gifImage.addGifFrame(frame4,new Watermark(titleForFrame4,Watermark.LAYOUT_COVER_CONSECUTIVELY,0.4f));
            // encode gif image
            GifEncoder.encode(gifImage,new File(outputDir,"ImageTourExample1.gif"),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
