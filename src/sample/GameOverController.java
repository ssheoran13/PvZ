package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOverController {

    public void changeSceneButtonPush(javafx.event.ActionEvent event) throws IOException {
        Parent secondpage = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene secondpagescene= new Scene(secondpage);
        Stage window=(Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(secondpagescene);
        window.show();
    }
}
