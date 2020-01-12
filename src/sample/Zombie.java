package sample;

import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import java.io.File;
import java.io.Serializable;
import java.util.Random;

public class Zombie implements Serializable {
    private int health;
    transient private ImageView image;
    private double ivx;
    private double ivy;
    private int row_no;
    public boolean move;
    public boolean killed;

    public static final long serialVersionUID = 50L;


    private int damage;
    Random r=new Random();

    static int check=1;
    File zombie1=new File("PNGArt/Zombies/conehead/conehead_zombie_moving.gif");
    File zombie2=new File("PNGArt/Zombies/biggestlodu.gif");
    File zombie3=new File("PNGArt/Zombies/biglodu.gif");

    Image zomb1=new Image(zombie1.toURI().toString());
    Image zomb2=new Image(zombie2.toURI().toString());
    Image zomb3=new Image(zombie3.toURI().toString());

    ImageView zv1=new ImageView(zomb1);
    ImageView zv2=new ImageView(zomb2);
    ImageView zv3=new ImageView(zomb3);




//    javafx.scene.image.ImageView z=new javafx.scene.image.ImageView(zomb);

    public Zombie(int row_no,int damage) {
        this.move=true;
        this.row_no = row_no;
        this.health=300;
        this.damage=damage;
        this.killed=false;
        if(check==0)
        {
            this.image=zv1;
            check=1;
        }
        else if(check==1)
        {
            this.image=zv2;
            check=2;
        }
        else
        {
            this.image=zv3;
            check=0;
        }

//        this.image=new ImageView(zomb);
        this.ivx=this.image.getLayoutX();
        this.ivy=this.image.getLayoutY();
    }

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

    public boolean isKilled() {
        return killed;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public int getRow_no() {
        return row_no;
    }

    public void setRow_no(int row_no) {
        this.row_no = row_no;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
}
