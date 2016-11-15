
import com.gif4j.*;

import java.io.File;
import java.io.IOException;

/**
 * This example demostrates using morphing filters
 */

public class MorphingFiltersExample {

    public static void main(String[] args) {
        // change out directory if it is necessary
        File outputDir = new File("."+File.separator+"result");
        if (!outputDir.exists())
            outputDir.mkdirs();
        // CellFilter
        GifImage cellFilteredGifImage = new GifImage(100,100);
        cellFilteredGifImage.setDefaultDelay(300);
        // set indefinite looping
        cellFilteredGifImage.setLoopNumber(0);

        cellFilteredGifImage.addGifFrame(
                new GifFrame(TestUtils.genTestImage("cell filtered frame with cell side 4 ",100,100)),
                new CellFilter(4,20));
        cellFilteredGifImage.addGifFrame(
                new GifFrame(TestUtils.genTestImage("cell filtered frame with cell side 8 ",100,100)),
                new CellFilter(8,20));
        cellFilteredGifImage.addGifFrame(
                new GifFrame(TestUtils.genTestImage("cell filtered frame with cell side 12 ",100,100)),
                new CellFilter(12,20));
        try {
            GifEncoder.encode(cellFilteredGifImage, new File(outputDir, "MorphingFiltersExample_cell_filter.gif"), true);
        } catch (IOException e) {
        }
        // CurtainFilter
        GifImage curtainFilteredGifImage = new GifImage(100,100);
        curtainFilteredGifImage.setDefaultDelay(300);
        // set indefinite looping
        curtainFilteredGifImage.setLoopNumber(0);

        curtainFilteredGifImage.addGifFrame(
                new GifFrame(TestUtils.genTestImage("curtain filtered frame - move from bottom to top ",100,100)),
                new CurtainFilter(CurtainFilter.MOVE_FROM_BOTTOM_TO_TOP));
        curtainFilteredGifImage.addGifFrame(
                new GifFrame(TestUtils.genTestImage("curtain filtered frame - move center to left/right ",100,100)),
                new CurtainFilter(CurtainFilter.MOVE_FROM_CENTER_TO_LEFT_RIGHT));
        curtainFilteredGifImage.addGifFrame(
                new GifFrame(TestUtils.genTestImage("curtain filtered frame - move from middle to top/bottom ",100,100)),
                new CurtainFilter(CurtainFilter.MOVE_FROM_MIDDLE_TO_TOP_BOTTOM));
        curtainFilteredGifImage.addGifFrame(
                new GifFrame(TestUtils.genTestImage("curtain filtered frame - move from left/right to center ",100,100)),
                new CurtainFilter(CurtainFilter.MOVE_FROM_LEFT_RIGHT_TO_CENTER));
        curtainFilteredGifImage.addGifFrame(
                new GifFrame(TestUtils.genTestImage("curtain filtered frame - move from left to right ",100,100)),
                new CurtainFilter(CurtainFilter.MOVE_FROM_LEFT_TO_RIGHT));
        curtainFilteredGifImage.addGifFrame(
                new GifFrame(TestUtils.genTestImage("curtain filtered frame - move from right to left ",100,100)),
                new CurtainFilter(CurtainFilter.MOVE_FROM_RIGHT_TO_LEFT));
        curtainFilteredGifImage.addGifFrame(
                new GifFrame(TestUtils.genTestImage("curtain filtered frame - move from top/bottom to middle ",100,100)),
                new CurtainFilter(CurtainFilter.MOVE_FROM_TOP_BOTTOM_TO_MIDDLE));
        curtainFilteredGifImage.addGifFrame(
                new GifFrame(TestUtils.genTestImage("curtain filtered frame - move from top to bottom ",100,100)),
                new CurtainFilter(CurtainFilter.MOVE_FROM_TOP_TO_BOTTOM));
        try {
            GifEncoder.encode(curtainFilteredGifImage, new File(outputDir, "MorphingFiltersExample_curtain_filter.gif"), true);
        } catch (IOException e) {
        }
    // MillFilter
        GifImage millFilteredGifImage = new GifImage(100,100);
        millFilteredGifImage.setDefaultDelay(300);
        // set indefinite looping
        millFilteredGifImage.setLoopNumber(0);

        millFilteredGifImage.addGifFrame(
                new GifFrame(TestUtils.genTestImage("mill filtered frame with number of frames = 4 ",100,100)),
                new MillFilter(4));
        millFilteredGifImage.addGifFrame(
                new GifFrame(TestUtils.genTestImage("mill filtered frame with number of frames = 8 ",100,100)),
                new MillFilter(8));
        millFilteredGifImage.addGifFrame(
                new GifFrame(TestUtils.genTestImage("mill filtered frame with number of frames = 12 ",100,100)),
                new MillFilter(12));
        try {
            GifEncoder.encode(millFilteredGifImage, new File(outputDir, "MorphingFiltersExample_mill_filter.gif"), true);
        } catch (IOException e) {
        }
        //   RadarFilter
        GifImage radarFilteredGifImage = new GifImage(GifImage.RESIZE_STRATEGY_EXTEND_TO_CURRENT);
        radarFilteredGifImage.setDefaultDelay(300);
        // set indefinite looping
        radarFilteredGifImage.setLoopNumber(0);

        radarFilteredGifImage.addGifFrame(
                new GifFrame(TestUtils.genTestImage("radar filtered frame with number of frames = 16 ",100,100),GifFrame.LAYOUT_MIDDLE_CENTER),
                new RadarFilter(4));
        radarFilteredGifImage.addGifFrame(
                new GifFrame(TestUtils.genTestImage("radar filtered frame with number of frames = 24 ",100,100),GifFrame.LAYOUT_MIDDLE_CENTER),
                new RadarFilter(6));
        radarFilteredGifImage.addGifFrame(
                new GifFrame(TestUtils.genTestImage("radar filtered frame with number of frames = 32 ",100,100),GifFrame.LAYOUT_MIDDLE_CENTER),
                new RadarFilter(8,6));
        try {
            GifEncoder.encode(radarFilteredGifImage, new File(outputDir, "MorphingFiltersExample_radar_filter.gif"), true);
        } catch (IOException e) {
        }
    // MozaicFilter
        GifImage mozaicFilteredGifImage = new GifImage(100,100);
        mozaicFilteredGifImage.setDefaultDelay(300);
        // set indefinite looping
        mozaicFilteredGifImage.setLoopNumber(0);

        mozaicFilteredGifImage.addGifFrame(
                new GifFrame(TestUtils.genTestImage("mozaic filtered frame with mozaic box size 4 ",100,100)),
                new MozaicFilter(4,6,10));
        mozaicFilteredGifImage.addGifFrame(
                new GifFrame(TestUtils.genTestImage("mozaic filtered frame with mozaic box size 8 ",100,100)),
                new MozaicFilter(8,6,10));
        mozaicFilteredGifImage.addGifFrame(
                new GifFrame(TestUtils.genTestImage("mozaic filtered frame with mozaic box size 12 ",100,100)),
                new MozaicFilter(12,6,10));
        try {
            GifEncoder.encode(mozaicFilteredGifImage, new File(outputDir, "MorphingFiltersExample_mozaic_filter.gif"), true);
        } catch (IOException e) {
        }
    // SnakeFilter
        GifImage snakeFilteredGifImage = new GifImage(100,100);
        snakeFilteredGifImage.setDefaultDelay(300);
        // set indefinite looping
        snakeFilteredGifImage.setLoopNumber(0);

        snakeFilteredGifImage.addGifFrame(
                new GifFrame(TestUtils.genTestImage("snake filtered frame with 'left-right direct' lay  ",100,100)),
                new SnakeFilter(SnakeFilter.LAY_METHOD_LEFT_RIGHT_DIRECT));
        snakeFilteredGifImage.addGifFrame(
                new GifFrame(TestUtils.genTestImage("snake filtered frame with 'left-right inverse' lay  ",100,100)),
                new SnakeFilter(SnakeFilter.LAY_METHOD_LEFT_RIGHT_INVERSE));
        snakeFilteredGifImage.addGifFrame(
                new GifFrame(TestUtils.genTestImage("snake filtered frame with 'spiral from center' lay  ",100,100)),
                new SnakeFilter(SnakeFilter.LAY_METHOD_SPIRAL_FROM_CENTER));
        snakeFilteredGifImage.addGifFrame(
                new GifFrame(TestUtils.genTestImage("snake filtered frame with 'spiral from corner' lay  ",100,100)),
                new SnakeFilter(SnakeFilter.LAY_METHOD_SPIRAL_FROM_CORNER));
        snakeFilteredGifImage.addGifFrame(
                new GifFrame(TestUtils.genTestImage("snake filtered frame with 'zig-zag corner' lay  ",100,100)),
                new SnakeFilter(SnakeFilter.LAY_METHOD_ZIG_ZAG));
        try {
            GifEncoder.encode(snakeFilteredGifImage, new File(outputDir, "MorphingFiltersExample_snake_filter.gif"), true);
        } catch (IOException e) {
        }
    // TunnelFilter
        GifImage tunnelFilteredGifImage = new GifImage(100,100);
        tunnelFilteredGifImage.setDefaultDelay(300);
        // set indefinite looping
        tunnelFilteredGifImage.setLoopNumber(0);

        tunnelFilteredGifImage.addGifFrame(
                new GifFrame(TestUtils.genTestImage("tunnel filtered frame with 'start from center' option ",100,100)),
                new TunnelFilter(true));
        tunnelFilteredGifImage.addGifFrame(
                new GifFrame(TestUtils.genTestImage("tunnel filtered frame with not 'start from center' option ",100,100)),
                new TunnelFilter(false));
        try {
            GifEncoder.encode(tunnelFilteredGifImage, new File(outputDir, "MorphingFiltersExample_tunnel_filter.gif"), true);
        } catch (IOException e) {
        }
    }
}
