
import com.gif4j.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.*;

import com.gif4j.GifEncoder;
import com.gif4j.ImageUtils;
import com.gif4j.TextPainter;
import com.gif4j.Watermark;

/**
 * This example demostrates how to add some information (it can be title, price, discount etc.) to product icon.
 *
 */

public class ProductIconExample1 {

    public static void main(String[] args) {

        // change out directory if it is necessary
        File outputDir = new File("."+File.separator+"result");
        if (!outputDir.exists())
            outputDir.mkdirs();

        try {
            // read icon. Here we read from file but it can be any source (internet, database etc.)
            BufferedImage sourceIcon = ImageIO.read(ProductIconExample1.class.getResourceAsStream("casio_exp600.jpg"));
            // add some place to render title and price.
            sourceIcon = ImageUtils.addInsets(sourceIcon,new Insets(15,15,15,15),Color.WHITE);
            // create image with product title
            String productTitle = "Casio Exilim EX-P600";
            // font to render
            Font font = new Font("Arial",Font.BOLD,12);
            TextPainter painter = new TextPainter(font);
            BufferedImage titleImage = painter.renderString(productTitle,false);
            // create image with product price
            String productPrice = "price: 400$";
            painter.setForegroundPaint(new Color(0,82,1));
            BufferedImage priceImage = painter.renderString(productPrice,false);
            // draw title and price images using Watermark objects
            Watermark watermark = new Watermark(titleImage,Watermark.LAYOUT_TOP_CENTER,1.0f);
            BufferedImage iconWithTitle = watermark.apply(sourceIcon);
            watermark = new Watermark(priceImage,Watermark.LAYOUT_BOTTOM_RIGHT,1.0f);
            BufferedImage iconWithTitleAndPrice = watermark.apply(iconWithTitle);
            // encode the result image
            GifEncoder.encode(iconWithTitleAndPrice,new File(outputDir,"ProductIconExample1.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
