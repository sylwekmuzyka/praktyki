
import com.gif4j.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

import com.gif4j.*;

/**
 * This example demostrates {@link com.gif4j.MorphingFilter} sub-classes usage.
 * All used filters are constructed by default.
 *
 */

public class MorphingImageToursExample {

    public static void main(String[] args) {

        // change out directory if it is necessary
        File outputDir = new File("."+File.separator+"result");
        if (!outputDir.exists())
            outputDir.mkdirs();
        try {
            // read images. Here we read from files but it can be any source (internet, database etc.)
            BufferedImage[] images = new BufferedImage[4];
            for (int i=1;i<=4;i++)
                images[i-1] = ImageIO.read(MorphingImageToursExample.class.getResourceAsStream("house_"+i+".jpg"));
            // create gifimage
            GifImage gifImage = new GifImage(160,120,GifImage.RESIZE_STRATEGY_SCALE_TO_FIT_IMAGE_SIZE);
            // set indefinite looping
            gifImage.setLoopNumber(0);

            // examples cell and curtain filters
            // first frame with CellFilter
            gifImage.addGifFrame(new GifFrame(images[0]),new CurtainFilter(CurtainFilter.MOVE_FROM_BOTTOM_TO_TOP));
            // use some CurtainFilter move options
            gifImage.addGifFrame(new GifFrame(images[1]),new CellFilter(8,20));
            gifImage.addGifFrame(new GifFrame(images[2]),new CurtainFilter(CurtainFilter.MOVE_FROM_CENTER_TO_LEFT_RIGHT));
            gifImage.addGifFrame(new GifFrame(images[3]),new CurtainFilter(CurtainFilter.MOVE_FROM_MIDDLE_TO_TOP_BOTTOM));
            // write out the result
            GifEncoder.encode(gifImage,new File(outputDir,"MorphingImageToursExample_cell_curtain_filtered.gif"),true);
            // another gif image
            gifImage = new GifImage(160,120,GifImage.RESIZE_STRATEGY_SCALE_TO_FIT_IMAGE_SIZE);
            // set indefinite looping
            gifImage.setLoopNumber(0);
            // examples mill, mozaic and tunel filters
            // first frame with mazaic
            gifImage.addGifFrame(new GifFrame(images[0]),new MozaicFilter(4,8,10));
            // use some CurtainFilter move options
            gifImage.addGifFrame(new GifFrame(images[1]),new MillFilter());
            gifImage.addGifFrame(new GifFrame(images[2]),new TunnelFilter(true));
            gifImage.addGifFrame(new GifFrame(images[3]),new TunnelFilter(false));
            // write out the result
            GifEncoder.encode(gifImage,new File(outputDir,"MorphingImageToursExample_mill_mozaic_tunnel_filtered.gif"),true);
            // another gif image
            gifImage = new GifImage(160,120,GifImage.RESIZE_STRATEGY_SCALE_TO_FIT_IMAGE_SIZE);
            // set indefinite looping
            gifImage.setLoopNumber(0);
            // examples snake filters
            // first frame with mazaic
            gifImage.addGifFrame(new GifFrame(images[0]),new SnakeFilter(SnakeFilter.LAY_METHOD_LEFT_RIGHT_DIRECT,5,6));
            // use some CurtainFilter move options
            gifImage.addGifFrame(new GifFrame(images[1]),new SnakeFilter(SnakeFilter.LAY_METHOD_LEFT_RIGHT_INVERSE,5,6));
            gifImage.addGifFrame(new GifFrame(images[2]),new SnakeFilter(SnakeFilter.LAY_METHOD_SPIRAL_FROM_CENTER,5,6));
            gifImage.addGifFrame(new GifFrame(images[3]),new SnakeFilter(SnakeFilter.LAY_METHOD_ZIG_ZAG,5,6));
            // encode the result
            GifEncoder.encode(gifImage,new File(outputDir,"MorphingImageToursExample_snake_filtered.gif"),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
