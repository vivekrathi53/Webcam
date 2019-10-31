import javax.sound.sampled.*;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class AudioReceiverThread implements Runnable
{

    @Override
    public void run()
    {
        AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);
       // TargetDataLine microphone;
        SourceDataLine speakers;
        try {
         //   microphone = AudioSystem.getTargetDataLine(format);

            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
          //  microphone = (TargetDataLine) AudioSystem.getLine(info);
           // microphone.open(format);



            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
            speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            speakers.open(format);
            speakers.start();
            DatagramSocket ds = new DatagramSocket(8189);
            long i=0;// adjust condition of loop for extent of microphone use
            while (i==0) {
              //  i++;
                byte[] data2 = new byte[1700];

                DatagramPacket dp = new DatagramPacket(data2,data2.length);
               // System.out.printf("Receiving");
                ds.receive(dp);
               // System.out.println(dp.getData().length);
                ByteArrayInputStream bais = new ByteArrayInputStream(data2);
                ObjectInputStream ois = new ObjectInputStream(bais);
                AudioPacket ap = (AudioPacket)ois.readObject();
                byte data[]=ap.audioData;
                speakers.write(data, 0, data.length);
               // System.out.println("Audio "+ap.counter);
                ReceiveSynchronizer.setCounter(ap.counter);
            }
            speakers.drain();
            speakers.close();
            //microphone.close();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
