package sample;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamListener;
import com.github.sarxos.webcam.WebcamResolution;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {
        Webcam webcam=Webcam.getDefault();//if more than one webcam this will default webcam
//        for(Dimension supportedSize: webcam.getViewSizes()){//get list of available size suppoertwd by computer
//            System.out.println(supportedSize.toString());
//        }
//        webcam.setViewSize(new Dimension(640,480));// to set default size
        webcam.setViewSize(WebcamResolution.VGA.getSize());// better way to do above task

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
        ImageIO.write(webcam.getImage(),"JPG",new File("firstCapture.jpg"));
        webcam.close();
    }
}
