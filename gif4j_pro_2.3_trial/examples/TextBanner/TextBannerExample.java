
import com.gif4j.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

import com.gif4j.*;

/**
 * This simple example demonstrates text-based animated banner creation.
 */

public class TextBannerExample {

    public static void main(String[] args) {

        // change out directory if it is necessary
        File outputDir = new File("."+File.separator+"result");
        if (!outputDir.exists())
            outputDir.mkdirs();

        // Create GifImage instance
        GifImage gifImage = new GifImage(GifImage.RESIZE_STRATEGY_EXTEND_TO_CURRENT);
        // set default delay
        gifImage.setDefaultDelay(200);
        // set indefinite looping
        gifImage.setLoopNumber(0);

        // Create first top part of the banner.
        // This part will contain slogan "LIMITIED TIME OFFER!" and
        // we scale up this slogan by redrawing it with resized font.

        // animate top part with 10 frames
        int framesNumber = 10;
        //create initial font
        Font font = new Font("Arial", Font.BOLD, 5);
        //create TextPainter
        TextPainter painter = new TextPainter(font, Color.WHITE, Color.RED, new Color(237, 163, 36), true);
        // Create animation frames in loop
        for (int i = 1; i <= framesNumber; i++) {
            // update font's size
            painter.setFont(painter.getFont().deriveFont((float) 5 + i));
            // render the slogan with the specified parameters
            BufferedImage textBI = painter.renderString("LIMITED TIME OFFER!", true);
            // create new GifFrame
            GifFrame frame = new GifFrame(textBI, GifFrame.LAYOUT_TOP_CENTER);
            frame.setDisposalMethod(GifFrame.DISPOSAL_METHOD_DO_NOT_DISPOSE);
            // if frame not last then set delay time small (10). For the last set delay time 2 seconds
            frame.setDelay(i < framesNumber ? 10 : 200);
            // add frame to image
            gifImage.addGifFrame(frame);
        }
        // end top part creation

        // Create bottom part of the banner. The part will contain 3 slogans.

        // update painter with new colors
        // new background color
        painter.setBackgroundColor(Color.WHITE);
        // new text color
        painter.setForegroundPaint(new GradientPaint(0, 0, new Color(83, 165, 84), 10, 0, new Color(0, 82, 1), true));
        // final width - we will not extend result GifImage width = width of the top part.
        int finalwidth = gifImage.getCurrentLogicWidth();
        //  height of the top part
        int topPartHeight = gifImage.getCurrentLogicHeight();
        // left-top corner point of the bottom part
        Point bottomPointToPlace = new Point(0, topPartHeight);
        // render "Only Today" slogan
        BufferedImage strBI = painter.renderText("Only Today", finalwidth, false, true);
        // save its' height
        int bottomBIheight = strBI.getHeight();
        // create GifFrame and add it to GifImage with MozaicFilter filter
        // new background color
        // update painter with new colors

        // new background color
        painter.setBackgroundColor(new Color(47,87,182));
        // new outline color
        painter.setOutlinePaint(new Color(29, 52, 166));
        // new text color
        painter.setForegroundPaint(new Color(255, 255, 0));
        GifFrame frame = new GifFrame(strBI, bottomPointToPlace);
        gifImage.addGifFrame(frame, new MozaicFilter());
        // render "buy 5 any books" slogan
        strBI = painter.renderText("buy 3 or more books at once", finalwidth, true, true);
        // check new slogan height and if it's greater than previous then remember it.
        // If not then extend this image with colored border (It's necessary to be sure that current frame
        // will repaint the previous one)
        if (strBI.getHeight() > bottomBIheight)
            bottomBIheight = strBI.getHeight();
        else
            strBI = ImageUtils.borderWithPaint(strBI, finalwidth,
                    bottomBIheight, painter.getBackgroundColor());
        // create GifFrame and add it to banner
        frame = new GifFrame(strBI, bottomPointToPlace);
        // the below code similar to the previous slogan
        // new background color
        painter.setBackgroundColor(new Color(83, 165, 84));
        // new outline color
        painter.setOutlinePaint(new Color(0, 82, 1));
        // new text color
        painter.setForegroundPaint(Color.WHITE);
        gifImage.addGifFrame(frame, new CurtainFilter(CurtainFilter.MOVE_FROM_LEFT_RIGHT_TO_CENTER));
        strBI = painter.renderText("and get 10% discount for life!",finalwidth,true,true);
        if (strBI.getHeight() > bottomBIheight)
            bottomBIheight = strBI.getHeight();
        else
            strBI = ImageUtils.borderWithPaint(strBI, finalwidth,
                    bottomBIheight, painter.getBackgroundColor());
        frame = new GifFrame(strBI, bottomPointToPlace);
        frame.setDelay(1000);
        gifImage.addGifFrame(frame, new TunnelFilter(true));
        // result encoding to provided OutputStream.
        try {
            GifEncoder.encode(gifImage, new File(outputDir, "TextBannerExample.gif"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
