package sample;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainPageController {

    @FXML
    transient private AnchorPane AP;

    @FXML
    private Button StartGame;
    @FXML
    private Button Load_Game;
    @FXML
    private Button ChooseLevel;
    @FXML
    private Button Exit;


    transient File fm=new File("PNGArt/Main/FlagMeter.png");
    transient javafx.scene.image.Image fmi=new Image(fm.toURI().toString());
    transient ImageView fmiv=new ImageView(fmi);

    transient ArrayList<Timeline> list_timeline=new ArrayList<Timeline>();


    @FXML
    private ResourceBundle resources;

    @FXML
    void initialize() {

    }

    public void changeSceneButtonPush(javafx.event.ActionEvent event) throws IOException {
        Parent secondpage = FXMLLoader.load(getClass().getResource("LevelScreen.fxml"));
        Scene secondpagescene= new Scene(secondpage);
        Stage window=(Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(secondpagescene);
        window.show();
    }

    public void closeButtonAction(javafx.event.ActionEvent event) throws IOException{
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }



    public void changeSceneButtonPushLoad(javafx.event.ActionEvent event) throws IOException, ClassNotFoundException {
        Parent secondpage = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene secondpagescene= new Scene(secondpage);
        Stage window=(Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(secondpagescene);
        window.show();
        deserialize();
    }

    public void deserialize() throws IOException,ClassNotFoundException
    {
        ObjectInputStream in=null;
        try
        {
            in=new ObjectInputStream(new FileInputStream("out.txt"));
            Controller loaded_controller=(Controller) in.readObject();
//            loaded_controller.playLevel(loaded_controller.level);
//            Controller run_curr=new Controller();
            System.out.println(loaded_controller.level);
//            LevelScreenController
//            loaded_controller.playLevel(loaded_controller.level);
            fmiv.setLayoutX(loaded_controller.pbx);
            fmiv.setLayoutY(loaded_controller.pby);
            loaded_controller.ProgressBar(fmiv);
            ArrayList<Zombie>[] zombie_list = loaded_controller.zombie_list;
            for(int i=0;i<5;i++)
            {
                ArrayList<Zombie> curr_row_zombies=zombie_list[i];
                for (int j=0;j<curr_row_zombies.size();j++)
                {
                    Zombie curr_zombie=curr_row_zombies.get(j);
                    ImageView zombie_image=curr_zombie.getImage();
                    zombie_image.setLayoutX(curr_zombie.getIvx());
                    zombie_image.setLayoutY(curr_zombie.getIvy());
                    zombie_image.setFitHeight(30);
                    zombie_image.setFitWidth(30);
                    AP.getChildren().add(zombie_image);

                }
            }

//            run_curr.pbx=loaded_controller.pbx;
//            run_curr.pby=loaded_controller.pby;
        }
        finally {
            in.close();
        }
    }


    public void level1(ActionEvent event) throws IOException{
        changeSceneButtonPushNew(event,1);
    }


    public void changeSceneButtonPushNew(javafx.event.ActionEvent event,int levelNumber) throws IOException {
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
