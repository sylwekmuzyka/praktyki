
import com.gif4j.GifDecoder;
import com.gif4j.GifEncoder;
import com.gif4j.GifImage;
import com.gif4j.GifTransformer;

import java.io.File;
import java.io.IOException;

/**
 * This example demostrates GIF image transformation using Gif4J PRO
 *
 * NOTE: IN SPITE OF THE SIZE THE DEFAULT GIF IMAGE TO TEST "gif4j_logotype.gif"
 * IS THE ARDUOUS EXAMPLE - IT CONTAINS 45 FRAMES (GIF IMAGES USUALLY CONTAIN LESS THAN 10 FRAMES)!
 */

public class GifImageTransformExample {


    // Usage: java GifImageTransformExample [PathToGifImageToTranform] (please be sure that the gif4j jar is in your CLASSPATH)
    // By default the gif4j_logotype.gif as an example gif image is used
    public static void main(String[] args) {
        File gifImageFileToTransform = null;
        if (args.length == 0) {
            gifImageFileToTransform = new File("gif4j_logotype.gif");
        } else
            gifImageFileToTransform = new File(args[0]);

        // load and decode gif image
        GifImage gifImage = null;
        try {
            gifImage = GifDecoder.decode(gifImageFileToTransform);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // change out directory if it is necessary
        File outputDir = new File("." + File.separator + "result");
        if (!outputDir.exists())
            outputDir.mkdirs();

        // resize gif image to the width=150px
        // with maintaining the aspect ratio of the original gif image dimension
        GifImage resizedGifImage1 = GifTransformer.resize(gifImage, 150, -1, false);
        // the same but smoothly
        GifImage resizedGifImage1_smooth = GifTransformer.resize(gifImage, 150, -1, true);

        // resize gif image to the width=150px and height=100px
        GifImage resizedGifImage2 = GifTransformer.resize(gifImage, 150, 100, false);
        // the same but smoothly
        GifImage resizedGifImage2_smooth = GifTransformer.resize(gifImage, 150, 100, true);

        // scale gif image by factor 0.7 with maintaining
        // the aspect ratio of the original gif image dimension
        GifImage scaledGifImage1 = GifTransformer.scale(gifImage, 0.7, -1, false);
        // the same but smoothly
        GifImage scaledGifImage1_smooth = GifTransformer.scale(gifImage, 0.7, -1, true);

        // scale gif image by X-axis scale factor = 1.2 and Y-axis scale factor = 0.8
        GifImage scaledGifImage2 = GifTransformer.scale(gifImage, 1.2, 0.8, false);
        // the same but smoothly
        GifImage scaledGifImage2_smooth = GifTransformer.scale(gifImage, 1.2, 0.8, true);

        // rotate gif image by 45 degrees (PI/4 radians)
        GifImage rotatedGifImage45 = GifTransformer.rotate(gifImage, Math.PI / 4, false);
        // the same but smoothly
        GifImage rotatedGifImage45_smooth = GifTransformer.rotate(gifImage, Math.PI / 4, true);

        // rotate gif image by 90 degrees counter-clockwise
        GifImage rotatedGifImage90CCW = GifTransformer.rotate90Left(gifImage);

        // rotate gif image by 90 degrees clockwise
        GifImage rotatedGifImage90CW = GifTransformer.rotate90Right(gifImage);

        // rotate gif image by 180 degrees
        GifImage rotatedGifImage180 = GifTransformer.rotate180(gifImage);

        // flip gif image vertically
        GifImage flipVerticalGifImage = GifTransformer.flipVertical(gifImage);

        // flip gif image horizontally (mirror gif image)
        GifImage flipHorizontalGifImage = GifTransformer.flipHorizontal(gifImage);

        // Save the results
        try {
            GifEncoder.encode(resizedGifImage1,new File(outputDir,"resizedGifImage1.gif"));
            GifEncoder.encode(resizedGifImage1_smooth,new File(outputDir,"resizedGifImage1_smooth.gif"));
            GifEncoder.encode(resizedGifImage2,new File(outputDir,"resizedGifImage2.gif"));
            GifEncoder.encode(resizedGifImage2_smooth,new File(outputDir,"resizedGifImage2_smooth.gif"));
            GifEncoder.encode(scaledGifImage1,new File(outputDir,"scaledGifImage1.gif"));
            GifEncoder.encode(scaledGifImage1_smooth,new File(outputDir,"scaledGifImage1_smooth.gif"));
            GifEncoder.encode(scaledGifImage2,new File(outputDir,"scaledGifImage2.gif"));
            GifEncoder.encode(scaledGifImage2_smooth,new File(outputDir,"scaledGifImage2_smooth.gif"));
            GifEncoder.encode(rotatedGifImage45,new File(outputDir,"rotatedGifImage45.gif"));
            GifEncoder.encode(rotatedGifImage45_smooth,new File(outputDir,"rotatedGifImage45_smooth.gif"));
            GifEncoder.encode(rotatedGifImage90CW,new File(outputDir,"rotatedGifImage90CW.gif"));
            GifEncoder.encode(rotatedGifImage90CCW,new File(outputDir,"rotatedGifImage90CCW.gif"));
            GifEncoder.encode(rotatedGifImage180,new File(outputDir,"rotatedGifImage180.gif"));
            GifEncoder.encode(flipVerticalGifImage,new File(outputDir,"flipVerticalGifImage.gif"));
            GifEncoder.encode(flipHorizontalGifImage,new File(outputDir,"flipHorizontalGifImage.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
