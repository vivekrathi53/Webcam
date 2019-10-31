import java.io.Serializable;

public class FramePacket implements Serializable
{
    byte[] frameData;
    int counter;

    public FramePacket(byte[] frameData, int counter) {
        this.frameData = frameData;
        this.counter = counter;
    }
}
