import java.io.Serializable;

public class AudioPacket implements Serializable
{
    byte[] audioData;
    int counter;

    public AudioPacket(byte[] audioData, int counter) {
        this.audioData = audioData;
        this.counter = counter;
    }
}
