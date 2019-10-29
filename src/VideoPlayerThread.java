import com.github.sarxos.webcam.Webcam;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;

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
         //   Image image = SwingFXUtils.toFXImage(webcam.getImage(), null);
          //  imageView.setImage(image);
            DatagramSocket datagramSocket = null;
            try {
                datagramSocket =new DatagramSocket();
            } catch (SocketException e) {
                e.printStackTrace();
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream(200000);
            try {
                ImageIO.write(webcam.getImage(),"jpg",baos);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {

                byte[] data = baos.toByteArray();
                DatagramPacket dp = new DatagramPacket(data,data.length,InetAddress.getLocalHost(),8188);
                //System.out.println(data.length);
                datagramSocket.send(dp);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
