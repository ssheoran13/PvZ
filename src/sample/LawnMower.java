package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.Serializable;

public class LawnMower implements Serializable {
    private int row;
    private boolean activate;
    transient private ImageView lawnimage;

    public static final long serialVersionUID = 45L;

    private double ivx;
    private double ivy;

    transient File file=new File("PNGArt/Lawn/lawnmower.png");
    transient Image image=new Image(file.toURI().toString());

    public double getIvx() {
        return ivx;
    }

    public void setIvx(double ivx) {
        this.ivx = ivx;
    }

    public double getIvy() {
        return ivy;
    }

    public void setIvy(double ivy) {
        this.ivy = ivy;
    }

    public LawnMower(int row) {
        this.row = row;
        this.activate=false;
        this.lawnimage=new ImageView(image);
    }

    public ImageView getLawnimage() {
        return lawnimage;
    }

    public void setLawnimage(ImageView lawnimage) {
        this.lawnimage = lawnimage;
    }

    public int getRow(){
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }
}
