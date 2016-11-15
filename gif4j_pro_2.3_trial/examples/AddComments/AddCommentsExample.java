
import com.gif4j.GifDecoder;
import com.gif4j.GifImage;
import com.gif4j.GifEncoder;

import java.io.File;
import java.io.IOException;

/**
 * The example demostrates adding a comment to a GIF image
 */

public class AddCommentsExample {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java AddCommentsExample " +
                    "input.gif \"my comment to add\" output.gif[OPTIONAL]");
            System.exit(1);
        }
        File inputGifFile = new File(args[0]);
        String comment = args[1];
        File outputGifFile = null;
        if (args.length > 2)
            outputGifFile = new File(args[2]);
        //read gif image from the file
        try {
            GifImage gifImage = GifDecoder.decode(inputGifFile);
            // add comment
            gifImage.addComment(comment);
            if (outputGifFile == null) {
                // write commented gif image to the same file
                GifEncoder.encode(gifImage, inputGifFile);
            } else {
                // write commented gif image to the specified file
                GifEncoder.encode(gifImage, outputGifFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
