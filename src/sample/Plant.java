package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.Serializable;

public class Plant implements Serializable {
    private boolean isPlaced;
    private int health;
    private int x;
    private int y;

    public static final long serialVersionUID = 47L;

    transient protected ImageView plantimage;
    private double ivx;
    private double ivy;

    private Plant currplant;



    protected int cost;

    transient File peafile = new File("PNGArt/Plants/peashooter/pea.png");
    transient Image peaimage = new Image(peafile.toURI().toString());

    transient protected ImageView pea=new ImageView(peaimage);


    public Plant() {
        this.isPlaced=false;
        this.health=200;


    }

    public void factoryPlant(int i)
    {
        if (i==1)
        {
            this.currplant=new SunFlower();
        }
        else if(i==2)
        {
            this.currplant=new PotatoMine();
        }
        else if(i==3){
            this.currplant=new BarrierPlant();
        }

    }



    public ImageView getPea() {
        return pea;
    }

    public void setPea(ImageView pea) {
        this.pea = pea;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public ImageView getPlantimage() {
        return plantimage;
    }

    public void setPlantimage(ImageView plantimage) {
        this.plantimage = plantimage;
    }


    public boolean isPlaced() {
        return isPlaced;
    }

    public void setPlaced(boolean placed) {
        isPlaced = placed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
