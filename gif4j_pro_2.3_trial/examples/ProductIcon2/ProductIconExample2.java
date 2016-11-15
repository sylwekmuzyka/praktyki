
import com.gif4j.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.*;

import com.gif4j.*;

/**
 * Create an animated product icon
 */

public class ProductIconExample2 {

    public static void main(String[] args) {

        // change out directory if it is necessary
        File outputDir = new File("."+File.separator+"result");
        if (!outputDir.exists())
            outputDir.mkdirs();
        try {
            // read icon. Here we read from file but it can be any source (internet, database etc.)
            BufferedImage sourceIcon = ImageIO.read(ProductIconExample2.class.getResourceAsStream("casio_exp600.jpg"));
            // add some place to render title old price and new price
            sourceIcon = ImageUtils.addInsets(sourceIcon,new Insets(15,15,15,15),Color.WHITE);
            int sourceW = sourceIcon.getWidth();
            int sourceH = sourceIcon.getHeight();
            // create image with product title
            Font font = new Font("Arial", Font.BOLD, 12);
            GradientPaint titlePaint = new GradientPaint(0,0,Color.YELLOW,10,14,Color.BLUE,true);
            TextPainter painter =
                    new TextPainter(font,titlePaint,null,Color.WHITE,true);
            BufferedImage titleImage = painter.renderString("Casio Exilim EX-P600",true);
            // create image with old product price
            painter.setForegroundPaint(Color.DARK_GRAY);
            BufferedImage oldPriceImage =
                    ImageUtils.borderWithPaint(
                            painter.renderString("old price: 400$",false),
                            sourceW,0,Color.WHITE);
            Point oldPricePositionInsideImage = new Point(0,sourceH-15);

            // draw title and price images using Watermark instances
            Watermark watermark = new Watermark(titleImage, Watermark.LAYOUT_TOP_CENTER, 1.0f);
            BufferedImage iconWithTitle = watermark.apply(sourceIcon);
            watermark = new Watermark(oldPriceImage, oldPricePositionInsideImage, 1.0f);
            BufferedImage iconWithTitleAndPrice = watermark.apply(iconWithTitle);
            // create GifImage
            GifImage gifImage = new GifImage();
            // set indefinite looping
            gifImage.setLoopNumber(0);

            gifImage.addGifFrame(new GifFrame(iconWithTitleAndPrice,GifFrame.LAYOUT_TOP_CENTER));

            // now create animation which informs custmer about new price.
            // At first we animate gradually 'crossing out' the old price
            int crossWidth = oldPriceImage.getWidth();
            int crossHeight = oldPriceImage.getHeight();
            // create the red cross animation and add it to gifImage
            // here we can do it directly but of course you can prepare such animation images one time and then reuse
            BufferedImage crossImage1 = oldPriceImage.getSubimage(0, 0, crossWidth, crossHeight);
            Graphics2D graphics2D = crossImage1.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.setPaint(Color.RED);
            graphics2D.drawLine(0, 0, crossWidth, crossHeight);
            graphics2D.dispose();
            GifFrame frame = new GifFrame(crossImage1, oldPricePositionInsideImage);
            frame.setDelay(50);
            gifImage.addGifFrame(frame);
            BufferedImage crossImage2 = oldPriceImage.getSubimage(0, 0, crossWidth, crossHeight);
            graphics2D = crossImage2.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.setPaint(Color.RED);
            graphics2D.drawLine(0, 0, crossWidth, crossHeight);
            graphics2D.drawLine(0, crossHeight, crossWidth, 0);
            graphics2D.dispose();
            frame = new GifFrame(crossImage2, oldPricePositionInsideImage);
            frame.setDelay(100);
            gifImage.addGifFrame(frame);
            // create image with new product price and add it to gif image
            painter.setForegroundPaint(new Color(138,220,139));
            painter.setOutlinePaint(new Color(0,82,1));
            BufferedImage newPriceImage = painter.renderString("new price: 300$",true);
            // extend width to 'oldPriceImage' width
            newPriceImage = ImageUtils.borderWithPaint(newPriceImage,oldPriceImage.getWidth(),0,Color.WHITE);
            Point newPricePosition = new Point(0,gifImage.getCurrentLogicHeight());
            GifFrame newPriceFrame =
                    new GifFrame(newPriceImage, newPricePosition);
            newPriceFrame.setDelay(500);
            gifImage.addGifFrame(newPriceFrame);
            GifEncoder.encode(gifImage, new File(outputDir, "ProductIconExample2.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
