import com.github.sarxos.webcam.Webcam;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Thread.sleep;


public class VideoReceiverThread implements Runnable
{
    ImageView imageView;
    Webcam webcam;
    @Override
    public void run()
    {
        int flag=0;
        BufferedPlayer bp = new BufferedPlayer(new LinkedList<>(),imageView);
        Thread t1 = new Thread(bp);
        try
        {
            DatagramSocket ds = new DatagramSocket(8188);
            while(true)
            {
                //Image image = SwingFXUtils.toFXImage(webcam.getImage(), null);
                //  imageView.setImage(image);
                byte[] data;
                byte[] data2 = new byte[200000];
                DatagramPacket dp = new DatagramPacket(data2,data2.length);
                //System.out.printf("Receiving");
                ds.receive(dp);
                //System.out.printf("Received");
                ByteArrayInputStream bais2 = new ByteArrayInputStream(data2);
                ObjectInputStream ois = new ObjectInputStream(bais2);
                FramePacket fp = (FramePacket) ois.readObject();
                data = fp.frameData;
                if(flag!=0)
                {
                    bp.addFramePacket(fp);
                }
                else
                {
                    flag=1;
                    bp.addFramePacket(fp);
                    t1.start();
                }
                //System.out.println(fp.counter);

            }

        }
        catch(Exception e)
        {}
    }
    public VideoReceiverThread(ImageView temp,Webcam webcam)
    {
        imageView=temp;
        this.webcam=webcam;
        //q=new LinkedList<byte[]>();
    }

}

