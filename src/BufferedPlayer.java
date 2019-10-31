import javafx.embed.swing.SwingFXUtils;
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
    public BufferedPlayer(Queue<FramePacket> q, ImageView imageView) {
        this.q = q;
        this.imageView = imageView;
    }
    public void addFramePacket(FramePacket fp)
    {
        q.add(fp);
    }

    @Override
    public void run()
    {
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
                    sleep(7);
                }
                else if(q.peek().counter<ReceiveSynchronizer.getCounter())
                    q.remove();
                else
                {
                    sleep(7);
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
