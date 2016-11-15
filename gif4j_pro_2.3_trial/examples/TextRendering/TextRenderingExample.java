
import com.gif4j.TextPainter;
import com.gif4j.GifEncoder;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class TextRenderingExample {

    public static void main(String[] args) {
        // change out directory if it is necessary
        File outputDir = new File("."+File.separator+"result");
        if (!outputDir.exists())
            outputDir.mkdirs();
        // create new TextPainter instance
        TextPainter painter = new TextPainter(new Font("Arial",Font.BOLD,14),Color.WHITE,Color.WHITE,Color.BLACK,true);
        // draw single string on the transparent background (default mode)
        BufferedImage stringImage = painter.renderString("white string on the black background");
        try {
            GifEncoder.encode(stringImage, new File(outputDir, "TextRenderingExample_str1.gif"));
        } catch (IOException e) {
        }
        // draw single string on the colored background
        painter.setForegroundPaint(Color.RED);
        painter.setBackgroundColor(Color.YELLOW);
        stringImage = painter.renderString("red string on the yellow background");
        try {
            GifEncoder.encode(stringImage, new File(outputDir, "TextRenderingExample_str2.gif"));
        } catch (IOException e) {
        }
        // draw single outlined string on the colored background
        painter.setForegroundPaint(Color.WHITE);
        painter.setOutlinePaint(Color.RED);
        painter.setBackgroundColor(Color.YELLOW);
        stringImage = painter.renderString("outlined string on the yellow background",true);
        try {
            GifEncoder.encode(stringImage, new File(outputDir, "TextRenderingExample_str3.gif"));
        } catch (IOException e) {
        }
        // change font
        painter.setFont(new Font("Impact",Font.PLAIN,14));
        // draw single string with gradient paint on the colored background
        painter.setForegroundPaint(new GradientPaint(0,0,Color.RED,20,0,Color.BLUE,true));
        painter.setBackgroundColor(Color.WHITE);
        stringImage = painter.renderString("gradient string on the white background");
        try {
            GifEncoder.encode(stringImage, new File(outputDir, "TextRenderingExample_str4.gif"));
        } catch (IOException e) {
        }
        // draw single string with texture paint on the colored background
        try {
        BufferedImage textureImage = ImageIO.read(TextRenderingExample.class.getResourceAsStream("texture1.jpg"));
        painter.setForegroundPaint(new TexturePaint(textureImage,new Rectangle2D.Double(0,0,20,20)));
        painter.setBackgroundColor(Color.WHITE);
        stringImage = painter.renderString("textured string on the white background");
            GifEncoder.encode(stringImage, new File(outputDir, "TextRenderingExample_str5.gif"));
        } catch (IOException e) {
        }
        // draw none centralized text on the colored background, width = 100
        try {
        painter.setForegroundPaint(Color.BLACK);
        painter.setBackgroundColor(Color.WHITE);
        stringImage = painter.renderText("very long black text on the colored background, width=100, not centralized",100,false,false);
            GifEncoder.encode(stringImage, new File(outputDir, "TextRenderingExample_text1.gif"));
        } catch (IOException e) {
        }
        // change font
        painter.setFont(new Font("Times New Roman",Font.BOLD,14));
        // draw none centralized text on the colored background, width = 150
        try {
        painter.setForegroundPaint(Color.DARK_GRAY);
        painter.setBackgroundColor(Color.WHITE);
        stringImage = painter.renderText("very long black text on the colored background, width=150, not centralized",150,false,false);
            GifEncoder.encode(stringImage, new File(outputDir, "TextRenderingExample_text2.gif"));
        } catch (IOException e) {
        }
        // draw centralized text on the colored background
        try {
        painter.setForegroundPaint(Color.BLACK);
        painter.setBackgroundColor(Color.WHITE);
        stringImage = painter.renderText("very long black text on the colored background, width=100, centralized",100,false,true);
            GifEncoder.encode(stringImage, new File(outputDir, "TextRenderingExample_text3.gif"));
        } catch (IOException e) {
        }
        // change font
        painter.setFont(new Font("Tahoma",Font.BOLD,14));

        // draw centralized outlined text on the colored background, width = 150
        try {
        painter.setForegroundPaint(Color.WHITE);
        painter.setOutlinePaint(new Color(0, 82, 1));
        painter.setBackgroundColor(new Color(83, 165, 84));
        stringImage = painter.renderText("very long outlined text on the colored background, width=150, centralized",150,true,true);
            GifEncoder.encode(stringImage, new File(outputDir, "TextRenderingExample_text4.gif"));
        } catch (IOException e) {
        }

    }
}
