

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    AnchorPane anchorPane;
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("."));
        try
        {
            anchorPane=(AnchorPane) loader.load();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Controller controller = loader.getController();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {
        launch(args);
       // Webcam webcam=Webcam.getDefault();//if more than one webcam this will default webcam
//        for(Dimension supportedSize: webcam.getViewSizes()){//get list of available size suppoertwd by computer
//            System.out.println(supportedSize.toString());
//        }
//        webcam.setViewSize(new Dimension(640,480));// to set default size
//        webcam.setViewSize(WebcamResolution.VGA.getSize());// better way to do above task
//
//        webcam.addWebcamListener(new WebcamListener() {
//            @Override
//            public void webcamOpen(WebcamEvent webcamEvent) {
//                System.out.println("Open Cam");
//            }
//
//            @Override
//            public void webcamClosed(WebcamEvent webcamEvent) {
//                System.out.println("Close Cam");
//            }
//
//            @Override
//            public void webcamDisposed(WebcamEvent webcamEvent) {
//                System.out.println("Dispose Cam");
//            }
//
//            @Override
//            public void webcamImageObtained(WebcamEvent webcamEvent) {
//                System.out.println("Image Taken");
//            }
//        });



        //webcam.open();
        //ImageIO.write(webcam.getImage(),"JPG",new File("firstCapture.jpg"));
        //webcam.close();
        System.out.println("Running");
       // Controller control=new Controller();
        System.out.println("Done");
    }
}
