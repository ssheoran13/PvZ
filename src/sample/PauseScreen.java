package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PauseScreen {

    public void changeSceneButtonPush1(javafx.event.ActionEvent event) throws IOException {
        Parent secondpage = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene secondpagescene= new Scene(secondpage);
        Stage window=(Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(secondpagescene);
        window.show();
    }

    public void changeSceneButtonPush2(javafx.event.ActionEvent event) throws IOException {
        Parent secondpage = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene secondpagescene= new Scene(secondpage);
        Stage window=(Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(secondpagescene);
        window.show();
    }

}
