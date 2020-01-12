package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class PotatoMine extends Plant {

    public static final long serialVersionUID = 48L;

    transient File file5 = new File("PNGArt/Plants/potatomine/potatomine_1_armed.png");

    public PotatoMine() {
        super();
        this.plantimage=new ImageView(new Image(file5.toURI().toString()));
        this.cost=15;

    }




}
