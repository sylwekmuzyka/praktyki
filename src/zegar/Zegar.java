package zegar;

import com.gif4j.GifDecoder;
import com.gif4j.GifEncoder;
import com.gif4j.GifImage;
import com.gif4j.GifTransformer;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Zegar
{
    private final JFrame mPodglad;
    private final JSplitPane mSplitPane;

    private final JFrame mFrameSzyfr;
    private final JTextField mSzyfrTF;

    private final JFrame mFrameMenu;
    private final JTextField mMenuTF;

    private Timer mTimerWpisywania = null;
    private Timer mTimerProgramu = null;

    private final String KOD_RESETU = "1111";
    private final String KOD_ROZBROJENIA = "4444";
    private String mSzyfr = null;
    private int mLiczbaProb = 0;
    private int mLiczbaZnakow = 0;
    private int mLiczbaPetli = 0;

    private String mSciezkaVideo = null;
    private String mSciezkaDzwiek = null;
    private String mJezyk = null;
    private String mSciezkaGrafika = null;

    private final int mSzerokosc;
    private final int mWysokosc;

    private double mMiejscePodzialu = 0.15;

    private boolean mCzyKoniecProgramu = false;

    public Zegar()
    {
        this.mFrameMenu = new JFrame();
        this.mMenuTF = new JTextField();
        this.mPodglad = new JFrame();
        this.mSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        this.mFrameSzyfr = new JFrame();
        this.mSzyfrTF = new JTextField();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mSzerokosc = screenSize.width;
        mWysokosc = screenSize.height;

//        JFrame tlo = new JFrame();
//        
//        tlo.getContentPane().setBackground(Color.BLACK);
//        tlo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        tlo.setLocationRelativeTo(null);
//        tlo.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        tlo.removeNotify();
//        tlo.setUndecorated(true);
//        tlo.addNotify();
//        tlo.setAlwaysOnTop(false);
//        tlo.toBack();
//        tlo.setVisible(true);
    }

    public void ustawRozmiar(Component component, int szerokosc, int wysokosc)
    {
        Dimension size = new Dimension(szerokosc, wysokosc);
        component.setMinimumSize(size);
        component.setPreferredSize(size);
        component.setMaximumSize(size);
    }

    public void stworzPodgladMenu()
    {
        JPanel podgladMenu = new JPanel();

        JLabel jezyk = new JLabel("Wybierz język");
        jezyk.setFont(jezyk.getFont().deriveFont(17f));
        jezyk.setHorizontalAlignment(SwingConstants.CENTER);
        ustawRozmiar(jezyk, 120, 30);

        JLabel polski = new JLabel("polski (kliknij 1)");
        polski.setForeground(Color.RED);
        polski.setHorizontalAlignment(SwingConstants.CENTER);
        ustawRozmiar(polski, 120, 30);

        JLabel angielski = new JLabel("angielski (kliknij 2)");
        angielski.setForeground(Color.BLUE);
        angielski.setHorizontalAlignment(SwingConstants.CENTER);
        ustawRozmiar(angielski, 120, 30);

        mMenuTF.setEnabled(true);
        mMenuTF.requestFocus();
        ustawRozmiar(mMenuTF, 120, 30);

        podgladMenu.setLayout(new GridLayout(4, 1));
        podgladMenu.add(jezyk);
        podgladMenu.add(polski);
        podgladMenu.add(angielski);
        podgladMenu.add(mMenuTF);
        ustawRozmiar(podgladMenu, 120, 120);
        
        mFrameMenu.setLayout(new GridBagLayout());
        mFrameMenu.add(podgladMenu, new GridBagConstraints());
        mFrameMenu.getContentPane().setBackground(Color.BLACK);
        mFrameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrameMenu.setLocationRelativeTo(null);
        mFrameMenu.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mFrameMenu.removeNotify();
        mFrameMenu.setUndecorated(true);
        mFrameMenu.addNotify();
        mFrameMenu.requestFocus();
        mFrameMenu.setFocusableWindowState(true);
        mFrameMenu.setAlwaysOnTop(true);
        mFrameMenu.toFront();
        mFrameMenu.setVisible(true);
    }

    public void menu()
    {
        mSciezkaDzwiek = "./audio/Click.wav";

        mMenuTF.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if(mLiczbaZnakow < 1)
                {
                    switch(e.getKeyCode())
                    {
                        case KeyEvent.VK_NUMPAD1:
                            mJezyk = "pol";
                            wlaczDzwiek();
                            mLiczbaZnakow++;
                            break;
                        case KeyEvent.VK_NUMPAD2:
                            mJezyk = "eng";
                            wlaczDzwiek();
                            mLiczbaZnakow++;
                            break;
                        default:
                            break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                switch(e.getKeyCode())
                {
                    case KeyEvent.VK_NUMPAD1:
                        break;
                    case KeyEvent.VK_NUMPAD2:
                        break;
                    default:
                        try
                        {
                            mMenuTF.setText(mMenuTF.getText().substring(0, mMenuTF.getText().length() - 1));
                        }
                        catch(StringIndexOutOfBoundsException sioobe)
                        {
                            System.err.println(sioobe);
                        }
                        break;
                }

                if(mLiczbaZnakow == 1)
                {
                    mCzyKoniecProgramu = false;
                    mLiczbaPetli++;
                    mMenuTF.setEnabled(false);
                    mFrameMenu.setVisible(false);
                    mFrameMenu.dispose();
                    mLiczbaZnakow = 0;
                    mLiczbaProb = 0;
                    timerProgramu();
                    mSciezkaVideo = "./video/1.mpg";
                    wlaczVideo();
                    mSciezkaGrafika = "./graphic/" + mJezyk + "/flaga.png";
                    wlaczGrafike();
                    stworzPodgladEkranu();
                    if(mLiczbaPetli < 2)
                    {
                        stworzSzyfrTF();
                        podajSzyfr();
                    }
                    else
                    {
                        mFrameSzyfr.setVisible(true);
                        mSzyfrTF.setText("");
                        mSzyfrTF.setEnabled(true);
                        mSzyfrTF.requestFocus();
                    }
                }
            }
        });
    }

    public void stworzSzyfrTF()
    {
        mFrameSzyfr.add(mSzyfrTF);
        mFrameSzyfr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mFrameSzyfr.setLocationRelativeTo(null);
        mFrameSzyfr.removeNotify();
        mFrameSzyfr.setUndecorated(true);
        mFrameSzyfr.addNotify();
        mFrameSzyfr.requestFocus();
        mFrameSzyfr.setFocusableWindowState(true);
        mFrameSzyfr.setAlwaysOnTop(false);
        mFrameSzyfr.toBack();
        mFrameSzyfr.pack();
        mFrameSzyfr.setVisible(true);

        mSzyfrTF.setEnabled(true);
        mSzyfrTF.requestFocus();
    }

    public void podajSzyfr()
    {
        timerWpisywania();

        mSciezkaDzwiek = "./audio/Click.wav";

        mSzyfrTF.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if(mLiczbaZnakow < 4 && mLiczbaProb < 2)
                {
                    switch(e.getKeyCode())
                    {
                        case KeyEvent.VK_NUMPAD0:
                            mTimerWpisywania.stop();
                            wlaczDzwiek();
                            mLiczbaZnakow++;
                            break;
                        case KeyEvent.VK_NUMPAD1:
                            mTimerWpisywania.stop();
                            wlaczDzwiek();
                            mLiczbaZnakow++;
                            break;
                        case KeyEvent.VK_NUMPAD2:
                            mTimerWpisywania.stop();
                            wlaczDzwiek();
                            mLiczbaZnakow++;
                            break;
                        case KeyEvent.VK_NUMPAD3:
                            mTimerWpisywania.stop();
                            wlaczDzwiek();
                            mLiczbaZnakow++;
                            break;
                        case KeyEvent.VK_NUMPAD4:
                            mTimerWpisywania.stop();
                            wlaczDzwiek();
                            mLiczbaZnakow++;
                            break;
                        case KeyEvent.VK_NUMPAD5:
                            mTimerWpisywania.stop();
                            wlaczDzwiek();
                            mLiczbaZnakow++;
                            break;
                        case KeyEvent.VK_NUMPAD6:
                            mTimerWpisywania.stop();
                            wlaczDzwiek();
                            mLiczbaZnakow++;
                            break;
                        case KeyEvent.VK_NUMPAD7:
                            mTimerWpisywania.stop();
                            wlaczDzwiek();
                            mLiczbaZnakow++;
                            break;
                        case KeyEvent.VK_NUMPAD8:
                            mTimerWpisywania.stop();
                            wlaczDzwiek();
                            mLiczbaZnakow++;
                            break;
                        case KeyEvent.VK_NUMPAD9:
                            mTimerWpisywania.stop();
                            wlaczDzwiek();
                            mLiczbaZnakow++;
                            break;
                        default:
                            break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                switch(e.getKeyCode())
                {
                    case KeyEvent.VK_NUMPAD0:
                        timerWpisywania();
                        break;
                    case KeyEvent.VK_NUMPAD1:
                        timerWpisywania();
                        break;
                    case KeyEvent.VK_NUMPAD2:
                        timerWpisywania();
                        break;
                    case KeyEvent.VK_NUMPAD3:
                        timerWpisywania();
                        break;
                    case KeyEvent.VK_NUMPAD4:
                        timerWpisywania();
                        break;
                    case KeyEvent.VK_NUMPAD5:
                        timerWpisywania();
                        break;
                    case KeyEvent.VK_NUMPAD6:
                        timerWpisywania();
                        break;
                    case KeyEvent.VK_NUMPAD7:
                        timerWpisywania();
                        break;
                    case KeyEvent.VK_NUMPAD8:
                        timerWpisywania();
                        break;
                    case KeyEvent.VK_NUMPAD9:
                        timerWpisywania();
                        break;
                    default:
                        try
                        {
                            mSzyfrTF.setText(mSzyfrTF.getText().substring(0, mSzyfrTF.getText().length() - 1));
                        }
                        catch(StringIndexOutOfBoundsException sioobe)
                        {
                            System.err.println(sioobe);
                        }
                        break;
                }

                sprawdzSzyfr();
                if(mCzyKoniecProgramu)
                {
                    podajKodResetu();
                }
            }
        }
        );
    }

    public void sprawdzSzyfr()
    {
        if(mLiczbaZnakow == 4)
        {
            if(mLiczbaProb < 2 && !mCzyKoniecProgramu)
            {
                mSzyfrTF.setEnabled(false);
                mSzyfr = mSzyfrTF.getText();
                mLiczbaProb++;
                if(mSzyfr.equals(KOD_RESETU))
                {
                    mFrameSzyfr.setVisible(false);
                    mFrameSzyfr.dispose();
                    mTimerProgramu.stop();
                    mFrameMenu.setVisible(true);
                    mMenuTF.setText("");
                    mMenuTF.setEnabled(true);
                    mLiczbaZnakow = 0;
                    menu();
                }
                else if(mSzyfr.equals(KOD_ROZBROJENIA))
                {
                    mTimerProgramu.stop();
                    if(mLiczbaProb == 2)
                    {
                        mLiczbaProb--;
                    }
//                    mFrameSzyfr.setVisible(false);
//                    mFrameSzyfr.dispose();
                    mSciezkaDzwiek = "./audio/Hooray.wav";
                    wlaczDzwiek();
                    mSciezkaGrafika = "./graphic/" + mJezyk + "/juhu.gif";
                    wlaczGrafike();
                    mLiczbaProb = 0;
                    mLiczbaZnakow = 0;
                    mFrameSzyfr.setVisible(true);
                    mSzyfrTF.setText("");
                    mSzyfrTF.setEnabled(true);
                    mSzyfrTF.requestFocus();
                    mCzyKoniecProgramu = true;
                }
                else if(mLiczbaProb < 2 && !mSzyfr.equals(KOD_RESETU) && !mSzyfr.equals(KOD_ROZBROJENIA))
                {
                    mSzyfrTF.setText("");
                    mSzyfrTF.setEnabled(true);
                    mLiczbaZnakow = 0;
                    mSciezkaGrafika = "./graphic/" + mJezyk + "/uwaga.gif";
                    wlaczGrafike();
                }
            }

            if(mLiczbaProb == 2)
            {
                mTimerProgramu.stop();
//                mSzyfrTF.setEnabled(false);
//                mFrameSzyfr.setVisible(false);
//                mFrameSzyfr.dispose();
                mSciezkaDzwiek = "./audio/Bomb.wav";
                wlaczDzwiek();
                mSciezkaVideo = "./video/4.mpg";
                wlaczVideo();
                mSciezkaGrafika = "./graphic/" + mJezyk + "/wybuch.jpg";
                wlaczGrafike();
                mLiczbaProb = 0;
                mLiczbaZnakow = 0;
                mFrameSzyfr.setVisible(true);
                mSzyfrTF.setText("");
                mSzyfrTF.setEnabled(true);
                mSzyfrTF.requestFocus();
                mCzyKoniecProgramu = true;
            }
        }
    }

    public void podajKodResetu()
    {
        mSciezkaDzwiek = "./audio/Click.wav";

        if(mLiczbaZnakow == 4)
        {
            mSzyfrTF.setEnabled(false);
            mSzyfr = mSzyfrTF.getText();

            if(mSzyfr.equals(KOD_RESETU))
            {
                mFrameSzyfr.setVisible(false);
                mFrameSzyfr.dispose();
                mTimerProgramu.stop();
                mFrameMenu.setVisible(true);
                mMenuTF.setText("");
                mMenuTF.setEnabled(true);
                mLiczbaZnakow = 0;
                menu();
            }
            else
            {
                mSzyfrTF.setText("");
                mSzyfrTF.setEnabled(true);
                mLiczbaZnakow = 0;
            }
        }
    }

    public void timerWpisywania()
    {
        int delay = 5000;
        ActionListener actionListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                mSzyfrTF.setText("");
                mLiczbaZnakow = 0;
            }
        };

        mTimerWpisywania = new Timer(delay, actionListener);
        mTimerWpisywania.setRepeats(false);
        mTimerWpisywania.start();
    }

    public void timerProgramu()
    {
        int delay = 720000;
        ActionListener actionListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
//                mSzyfrTF.setEnabled(false);
//                mFrameSzyfr.setVisible(false);
//                mFrameSzyfr.dispose();
                mSciezkaDzwiek = "./audio/Bomb.wav";
                wlaczDzwiek();
                mSciezkaVideo = "./video/4.mpg";
                wlaczVideo();
                mSciezkaGrafika = "./graphic/" + mJezyk + "/wybuch.jpg";
                wlaczGrafike();
                mLiczbaProb = 0;
                mLiczbaZnakow = 0;
                mFrameSzyfr.setVisible(true);
                mSzyfrTF.setText("");
                mSzyfrTF.setEnabled(true);
                mSzyfrTF.requestFocus();
                mCzyKoniecProgramu = true;
            }
        };

        mTimerProgramu = new Timer(delay, actionListener);
        mTimerProgramu.setRepeats(false);
        mTimerProgramu.start();
    }

    public void wlaczDzwiek()
    {
        File dzwiek = new File(mSciezkaDzwiek);
        try
        {
            AudioInputStream audio = AudioSystem.getAudioInputStream(dzwiek.getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException e)
        {
            System.err.println(e);
        }
    }

    public void wlaczGrafike()
    {
        JPanel graphicPanel = new JPanel();
        ImageIcon imageIcon;

        ustawRozmiar(graphicPanel, mSzerokosc, (int) (mMiejscePodzialu * mWysokosc));

        int przeskalowanaSzerokosc = mSzerokosc; //(int) ((mMiejscePodzialu * mWysokosc * grafikaIN.getWidth()) / grafikaIN.getHeight());
        int przeskalowanaWysokosc = (int) (mMiejscePodzialu * mWysokosc);

        try
        {
            graphicPanel.setLayout(new BorderLayout());
            File grafika = new File(mSciezkaGrafika);

            if(mSciezkaGrafika.endsWith(".gif"))
            {
                GifImage gifIN = GifDecoder.decode(grafika);
                GifImage gif = GifTransformer.resize(gifIN, przeskalowanaSzerokosc, przeskalowanaWysokosc, true);
                GifImage gifOUT = GifTransformer.optimize(gif);
                File plikOUT = new File(mSciezkaGrafika.replaceFirst(".gif", "_scaled.gif"));
                GifEncoder.encode(gifOUT, plikOUT);
                URL grafikaOUT = plikOUT.toURI().toURL();

                imageIcon = new ImageIcon(grafikaOUT);
            }
            else
            {
                BufferedImage grafikaIN = ImageIO.read(grafika);
                BufferedImage grafikaOUT = new BufferedImage(przeskalowanaSzerokosc, przeskalowanaWysokosc, grafikaIN.getType());
                Graphics2D g2d = (Graphics2D) grafikaOUT.getGraphics();

                g2d.setComposite(AlphaComposite.Src);
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.drawImage(grafikaIN, 0, 0, przeskalowanaSzerokosc, przeskalowanaWysokosc, null);
                g2d.dispose();

                imageIcon = new ImageIcon(grafikaOUT);
            }

            JLabel label = new JLabel(imageIcon);

            graphicPanel.add(label, BorderLayout.CENTER);
            mSplitPane.setTopComponent(graphicPanel);
        }
        catch(IOException ioe)
        {
            System.err.println(ioe);
        }
    }

    public void wlaczVideo()
    {
        File film = new File(mSciezkaVideo);

        URL filmURL = null;

        try
        {
            filmURL = film.toURI().toURL();
        }
        catch(MalformedURLException malformedURLException)
        {
            System.err.println("Nie można utworzyć URL dla pliku");
        }

        if(filmURL != null)
        {
            VideoPlayer videoPlayer = new VideoPlayer(filmURL);

            mMiejscePodzialu = mMiejscePodzialu - 0.15;
            ustawRozmiar(videoPlayer, mSzerokosc, (int) (mMiejscePodzialu * mWysokosc));
            mMiejscePodzialu = 0.15;
            mSplitPane.setBottomComponent(videoPlayer);
        }
    }

    public void stworzPodgladEkranu()
    {
        mSplitPane.setDividerLocation(mMiejscePodzialu);
        mSplitPane.setDividerSize(0);
        mSplitPane.setEnabled(false);

        mPodglad.getContentPane().add(mSplitPane);
        mPodglad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mPodglad.setLocationRelativeTo(null);
        mPodglad.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mPodglad.removeNotify();
        mPodglad.setUndecorated(true);
        mPodglad.addNotify();
        mPodglad.setAlwaysOnTop(true);
        mPodglad.toFront();
        mPodglad.setVisible(true);
    }

    public static void main(String[] args)
    {
        Zegar zegar = new Zegar();

        zegar.stworzPodgladMenu();
        zegar.menu();
    }
}
