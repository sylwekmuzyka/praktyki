package zegar;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.IOException;
import java.net.URL;
import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.swing.JPanel;

public class VideoPlayer extends JPanel
{
    private Player mVideoPlayer;

    public VideoPlayer(URL videoURL)
    {
        setLayout(new BorderLayout());

        Manager.setHint(Manager.LIGHTWEIGHT_RENDERER, true);

        try
        {
            mVideoPlayer = Manager.createRealizedPlayer(videoURL);
            Component video = mVideoPlayer.getVisualComponent();

            if (video != null)
            {
                add(video, BorderLayout.CENTER);
            }

            mVideoPlayer.start();
        }
        catch (NoPlayerException noPlayerException)
        {
            System.err.println("Nie znaleziono pliku wideo");
        }
        catch (CannotRealizeException cannotRealizeException)
        {
            System.err.println("Nie można uzyskać dostępu do odtwarzacza wideo");
        }
        catch (IOException ioException)
        {
            System.err.println("Nie można otworzyć pliku wideo");
        }
    }
}
