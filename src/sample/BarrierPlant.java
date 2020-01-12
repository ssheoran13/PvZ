package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class BarrierPlant extends Plant {
    public static final long serialVersionUID = 42L;
    transient File file2 = new File("PNGArt/Plants/wallnut/wallnut_1.png");

    public BarrierPlant() {
        super();
        this.setHealth(1000);
        this.plantimage=new ImageView(new Image(file2.toURI().toString()));
        this.cost=50;

    }


}
