import com.github.sarxos.webcam.Webcam;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.image.BufferedImage;

import static java.lang.Thread.sleep;


public class VideoPlayerThread implements Runnable
{
    ImageView imageView;
    Webcam webcam;
    @Override
    public void run()
    {
        while(true)
        {
            Image image = SwingFXUtils.toFXImage(webcam.getImage(), null);
            imageView.setImage(image);
            try {
                sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public VideoPlayerThread(ImageView temp,Webcam webcam)
    {
        imageView=temp;
        this.webcam=webcam;
    }

}
