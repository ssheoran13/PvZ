package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.awt.*;

//class Zombie {

//Create Zombie and Translate it
//public void ZombieMove() {

//Instantiate zombie
//Image Zmbi = new Image(Main.class.getResource("/PNG Art/Zombies/conehead/conehead_zombie_moving.gif").toString());
//ImageView zmbi = new ImageView(Zmbi);
//zmbi.


//Place zombie at particular location

//Translate zmbi

//}
//}

//class SunToken {

//}

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
//        primaryStage.setMaximized(true);
//        primaryStage.setFullScreen(true);

        primaryStage.setTitle("PlantsVsZombies");
        primaryStage.setScene(new Scene(root, 630, 350));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
