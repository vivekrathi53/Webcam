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

import static java.lang.Thread.sleep;


public class VideoReceiverThread implements Runnable
{
    ImageView imageView;
    Webcam webcam;
    @Override
    public void run()
    {
        try
        {
            DatagramSocket ds = new DatagramSocket(8188);
            while(true)
            {
                //Image image = SwingFXUtils.toFXImage(webcam.getImage(), null);
                //  imageView.setImage(image);
                byte[] data  = new byte[200000];
                DatagramPacket dp = new DatagramPacket(data,data.length);
                //System.out.printf("Receiving");
                ds.receive(dp);
                //System.out.printf("Received");
                ByteArrayInputStream bais = new ByteArrayInputStream(data);
                BufferedImage bi = ImageIO.read(bais);
                Image image = SwingFXUtils.toFXImage(bi, null);
                imageView.setImage(image);
              //  System.out.println("Image Set");
            }

        }
        catch(Exception e)
        {}
    }
    public VideoReceiverThread(ImageView temp,Webcam webcam)
    {
        imageView=temp;
        this.webcam=webcam;
    }

}
