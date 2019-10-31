import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Queue;

import static java.lang.Thread.sleep;

public class BufferedPlayer implements Runnable
{
    Queue<FramePacket > q;
    ImageView imageView;
    Label loadingLabel;
    public BufferedPlayer(Queue<FramePacket> q, ImageView imageView, Label loadingLabel) {
        this.q = q;
        this.imageView = imageView;
        this.loadingLabel=loadingLabel;
    }
    public void addFramePacket(FramePacket fp)
    {
        q.add(fp);
    }

    @Override
    public void run()
    {
        try {
            sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(true)
        {

            try
            {
                if(q.peek().counter==ReceiveSynchronizer.getCounter())
                {
                    ByteArrayInputStream bais = new ByteArrayInputStream(q.peek().frameData);
                    BufferedImage bi = ImageIO.read(bais);
                    Image image = SwingFXUtils.toFXImage(bi, null);
                    imageView.setImage(image);
                    q.remove();
                    sleep(20);
                    Platform.runLater(new Runnable()//To perform UI work from different Thread
                    {
                        @Override
                        public void run() {
                           loadingLabel.setText("Live");
                        }
                    });
                }
                else if(q.peek().counter<ReceiveSynchronizer.getCounter())
                {
                    Platform.runLater(new Runnable()//To perform UI work from different Thread
                    {
                        @Override
                        public void run() {
                            loadingLabel.setText("Loading");
                        }
                    });
                    q.remove();
                    sleep(20);
                }
                else
                {
                    Platform.runLater(new Runnable()//To perform UI work from different Thread
                    {
                        @Override
                        public void run() {
                            loadingLabel.setText("Loading");
                        }
                    });
                    sleep(20);
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
