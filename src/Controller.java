import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamListener;
import com.github.sarxos.webcam.WebcamResolution;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Controller {
    @FXML
    ImageView imagehold;
    @FXML
    Button captureButton;
    @FXML
    AnchorPane anchorPane;

    Webcam webcam;

    public Controller()throws IOException
    {
        webcam=Webcam.getDefault();
        webcam.setViewSize(new Dimension(320,240));
//        webcam.setViewSize(WebcamResolution.VGA.getSize());// better way to do above task

        webcam.addWebcamListener(new WebcamListener() {
            @Override
            public void webcamOpen(WebcamEvent webcamEvent) {
                System.out.println("Open Cam");
            }

            @Override
            public void webcamClosed(WebcamEvent webcamEvent) {
                System.out.println("Close Cam");
            }

            @Override
            public void webcamDisposed(WebcamEvent webcamEvent) {
                System.out.println("Dispose Cam");
            }

            @Override
            public void webcamImageObtained(WebcamEvent webcamEvent) {
                System.out.println("Image Taken");
            }
        });
        webcam.open();

    }

    public void capture()
    {
        try {
            ImageIO.write(webcam.getImage(),"JPG",new File("firstCapture.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}