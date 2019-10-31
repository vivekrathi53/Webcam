import com.github.sarxos.webcam.Webcam;

import javafx.scene.control.Label;

import javafx.scene.image.ImageView;

import java.io.*;
import java.net.*;
import java.util.LinkedList;


public class VideoReceiverThread implements Runnable
{
    ImageView imageView;
    Webcam webcam;
    Label loadingLabel;
    @Override
    public void run()
    {
        int flag=0;
        BufferedPlayer bp = new BufferedPlayer(new LinkedList<>(),imageView,loadingLabel);
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
    public VideoReceiverThread(ImageView temp,Webcam webcam,Label loadingLabel)
    {
        imageView=temp;
        this.webcam=webcam;
        this.loadingLabel=loadingLabel;
        //q=new LinkedList<byte[]>();
    }

}

