package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class LevelScreenController {

    @FXML
    void initialize() {

    }

    public void level1(ActionEvent event) throws IOException{
        changeSceneButtonPush(event,1);
    }
    public void level2(ActionEvent event) throws IOException{
        changeSceneButtonPush(event,2);
    }
    public void level3(ActionEvent event) throws IOException{
        changeSceneButtonPush(event,3);
    }
    public void level4(ActionEvent event) throws IOException{
        changeSceneButtonPush(event,4);
    }
    public void level5(ActionEvent event) throws IOException{
        changeSceneButtonPush(event,5);
    }

    public void changeSceneButtonPush(javafx.event.ActionEvent event,int levelNumber) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("sample.fxml"));
        Stage app=(Stage)((Node)event.getSource()).getScene().getWindow();
        Parent root=(Parent)loader.load();
        Controller level=loader.<Controller>getController();
        Scene scene=new Scene(root);
        app.setScene(scene);
        app.show();
        level.playLevel(levelNumber);
    }
}
