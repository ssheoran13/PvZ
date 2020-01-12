package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class CherryBomb extends Plant{
    public static final long serialVersionUID = 43L;

    transient File file3 = new File("PNGArt/Plants/cherrybomb/cherrybomb_1.png");

    public CherryBomb() {
        super();
        this.plantimage=new ImageView(new Image(file3.toURI().toString()));
        this.cost=150;

    }


}
