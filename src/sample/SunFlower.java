package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class SunFlower extends Plant {

    public static final long serialVersionUID = 49L;

    transient File file4 = new File("PNGArt/Plants/sunflower/sunflower_moving.gif");

    public SunFlower() {
        super();
        this.plantimage=new ImageView(new Image(file4.toURI().toString()));
        this.cost=50;

    }


}
