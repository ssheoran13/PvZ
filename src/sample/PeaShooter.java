package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;



public class PeaShooter extends Plant {

//    File peafile = new File("PNGArt/Plants/peashooter/pea.png");
//    Image peaimage = new Image(peafile.toURI().toString());
//    ImageView peashot = new ImageView(pea);
//    peashot.setFitWidth(12);
//    peashot.setFitHeight(12);

    private int damage;

    public static final long serialVersionUID = 46L;

//    private ImageView pea=new ImageView(peaimage);


    transient File file1 = new File("PNGArt/Plants/peashooter/peashooter_moving.gif");


    public PeaShooter() {
        super();
        this.plantimage=new ImageView(new Image(file1.toURI().toString()));
        this.cost=100;
        this.damage=40;
//        this.pea=new ImageView(peaimage);

    }

//    public ImageView getPea() {
//        return pea;
//    }
//
//    public void setPea(ImageView pea) {
//        this.pea = pea;
//    }

    //    public void ShootPea()
//    {
//        Timeline peagen=new Timeline(new KeyFrame())
//    }





}
