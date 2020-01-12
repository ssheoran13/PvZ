package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.*;
import java.net.URL;

import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable, Serializable {



    @FXML
    transient private AnchorPane AP;
    @FXML
    transient private ImageView meter;

    private ArrayList<saveData> saveDataArrayList=new ArrayList<saveData>();
    public int level;

    public static final long serialVersionUID = 44L;

    public double pbx;
    public double pby;

    transient File win=new File("PNGArt/Logo_You_Win.png");
    transient Image wini=new Image(win.toURI().toString());
    transient ImageView win_image=new ImageView(wini);

    File deadf=new File("PNGArt/Plants/dead.png");
    Image deadi=new Image(deadf.toURI().toString());


    @FXML
    transient private ImageView barr;
    @FXML
    transient private ImageView cherr;
    @FXML
    transient private ImageView mine;
    @FXML
    transient private ImageView sunf;
    @FXML
    transient private ImageView peas;



    @FXML
    transient private Text suncounter_txt;


    transient File p=new File("PNGArt/images.jpg");
    transient Image pimage=new Image(p.toURI().toString());
    transient ImageView endgame=new ImageView(pimage);


    @FXML
    transient private ImageView pauseB;


    boolean[][] grid_plant_placed=new boolean[5][9];

    LawnMower[] list_lawnmowers=new LawnMower[5];


    Plant[][] placed_plants=new Plant[5][9];

    transient ArrayList<Timeline> list_timeline=new ArrayList<Timeline>();

    int suncounter_int=200;

    transient private Plant placeplant;

    transient File file = new File("PNGArt/Plants/sunflower/sun.png");
    transient Image sunimg = new Image(file.toURI().toString());
    transient ImageView suntk = new ImageView();



    transient File Mainfile=new File("PNGArt/blank.png");

    transient Image blank = new Image(Mainfile.toURI().toString());


    transient ImageView[][] grid=new ImageView[5][9];

    transient File peafile = new File("PNGArt/Plants/peashooter/pea.png");

    transient Image peaimage = new Image(peafile.toURI().toString());


    ArrayList<Zombie>[] zombie_list = new ArrayList[5];

    public void movex (ImageView img, int x, int cycle, int time) {

        img.setLayoutX(img.getLayoutX()-5);

    }

    public void fillLawnmowers(LawnMower[] arr)
    {
        for(int i=0;i<5;i++)
        {
            arr[i]=new LawnMower(i);
            ImageView lm=arr[i].getLawnimage();
            lm.setLayoutX(60);
            lm.setLayoutY(53+67*i);
            lm.setFitHeight(40);
            lm.setFitWidth(40);

            System.out.println(AP);
            AP.getChildren().add(lm);
        }
    }


    public void changeSceneButtonPushRestart(Stage window) throws IOException {
        Parent secondpage = FXMLLoader.load(getClass().getResource("LevelScreen.fxml"));
        Scene secondpagescene= new Scene(secondpage);
        window.setScene(secondpagescene);
        window.show();
    }

    public void changeSceneButtonPush(Stage window) throws IOException {
        Parent secondpage = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene secondpagescene= new Scene(secondpage);
        window.setScene(secondpagescene);
        window.show();
    }



    public void collisiondetect(Plant plant,Zombie zombie)
    {
        if(plant==null || zombie==null){
            return;
        }

        ImageView pea=plant.getPea();

        if (Math.abs(pea.getLayoutX()-zombie.getImage().getLayoutX())<20)
        {
            System.out.println("collision occured");
            pea.setDisable(true);
            AP.getChildren().remove(pea);
            System.out.println("p x "+pea.getLayoutX());
            zombie.setHealth(zombie.getHealth()-30);
            System.out.println(zombie.getHealth());
            if(zombie.getHealth()<0)
            {
                AP.getChildren().remove(pea);
                AP.getChildren().remove(zombie.getImage());
                zombie.setKilled(true);
                zombie_list[plant.getY()].remove(zombie);
            }
        }
    }

    public void lawnmower_collision(LawnMower lawnMower,Zombie zombie)
    {
        if(zombie==null)
        {
            return;
        }
        ImageView lm=lawnMower.getLawnimage();
        ImageView z=zombie.getImage();
        if(Math.abs(lm.getLayoutX()-z.getLayoutX())<50)
        {

            zombie.setKilled(true);
            AP.getChildren().remove(z);
            zombie_list[lawnMower.getRow()].remove(zombie);
        }
    }

    public void plantzombie_collision(Plant plant,Zombie zombie) {

        if(plant==null || zombie==null)
        {
            return;
        }

        ImageView zomb_image = zombie.getImage();
        ImageView plant_image = plant.getPlantimage();

        if (plant instanceof PotatoMine ) {
            if (Math.abs(plant_image.getLayoutX() - zomb_image.getLayoutX()) < 10 )
            {
                grid_plant_placed[plant.getY()][plant.getX()] = false;
                zombie.setKilled(true);
                placed_plants[plant.getY()][plant.getX()]=null;
                AP.getChildren().remove(plant_image);
                ImageView iv = new ImageView(blank);
                iv.setOpacity(0);
                iv.setLayoutY(56 + 60 * plant.getY());
                iv.setLayoutX(108 + 34 * plant.getX());
                iv.setFitHeight(50);
                iv.setFitWidth(40);
                AP.getChildren().add(iv);
                grid[plant.getY()][plant.getX()] = iv;
                plant.setPlaced(false);
                AP.getChildren().remove(zombie.getImage());
                zombie_list[plant.getY()].remove(zombie);
            }
        }
        else

            {


                if (Math.abs(plant_image.getLayoutX() - zomb_image.getLayoutX()) < 10)
                {

                    zombie.setMove(false);
            plant.setHealth(plant.getHealth() - zombie.getDamage());
            if (plant.getHealth() < 0) {
                grid_plant_placed[plant.getY()][plant.getX()] = false;
                AP.getChildren().remove(plant_image);
                ImageView iv;
                if(plant instanceof PeaShooter || plant instanceof BarrierPlant || plant instanceof SunFlower)
                {
                     iv = new ImageView(deadi);
                    iv.setOpacity(1);

                }
                else
                {
                     iv = new ImageView(blank);
                    iv.setOpacity(0);
                }

                iv.setLayoutY(56 + 60 * plant.getY());
                iv.setLayoutX(108 + 34 * plant.getX());
                iv.setFitHeight(50);
                iv.setFitWidth(40);
                AP.getChildren().add(iv);
                grid[plant.getY()][plant.getX()] = iv;
                plant.setPlaced(false);
                placed_plants[plant.getY()][plant.getX()]=null;
                zombie.setMove(true);
            }
        }
    }
    }

    public void lock_buy(ImageView plant_type)
    {
        plant_type.setDisable(true);
        Timeline lock=new Timeline(new KeyFrame(Duration.seconds(5),e->{
            plant_type.setDisable(false);

        }));
        lock.setCycleCount(1);
        lock.play();
        list_timeline.add(lock);
    }

    public boolean checkEmpty(ArrayList<Zombie>[] arr)
    {
        boolean check=false;
        for(int i=0;i<5;i++)
        {
            if(arr[i].isEmpty())
            {
                check=true;
            }
            else {
                check=false;
            }
        }
        return check;
    }

    public void Sun_Drop(double x,double y,double i) {

        Random r = new Random();
        Timeline Sun5 = new Timeline(new KeyFrame(Duration.seconds(10), e-> {


                AP.getChildren().add(suntk);
                suntk.setImage(sunimg);
                suntk.setFitWidth(30);
                suntk.setFitHeight(30);
                suntk.setLayoutX(  x+ i*r.nextDouble()*350+150);
                suntk.setLayoutY(y+0);
                suntk.setVisible(true);
                suntk.toFront();

            suntk.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    AP.getChildren().remove(suntk);
                    suncounter_int += 25;
                    suncounter_txt.setText(Integer.toString(suncounter_int));
                }
            });

                Timeline move_y = new Timeline(new KeyFrame(Duration.millis(1000),e1->{
                    suntk.setLayoutY(suntk.getLayoutY()+15);
                }));
                move_y.setCycleCount(20);
                move_y.play();
                list_timeline.add(move_y);
        }));

        Sun5.setCycleCount(Timeline.INDEFINITE);
        Sun5.play();
        list_timeline.add(Sun5);
    }


    public void changeSceneButtonPush(javafx.event.ActionEvent event) throws IOException {
        Parent secondpage = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene secondpagescene= new Scene(secondpage);
        Stage window=(Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(secondpagescene);
        window.show();
    }


    public void zombie_gen(boolean check,int levelnumber)
    {
        for (int i = 0; i < 5; i++) {
            zombie_list[i] = new ArrayList<Zombie>();
        }

        Random r=new Random();

        Timeline zombie_gen=new Timeline(new KeyFrame(Duration.seconds(8),e->{

            int row=r.nextInt(5);
            Zombie zombie=new Zombie(row,levelnumber*10);
            ImageView z=zombie.getImage();
            z.setLayoutY(56+65*row);
            z.setLayoutX(400);
            z.setFitHeight(60);
            z.setFitWidth(50);
            z.toFront();
            zombie_list[row].add(zombie);
            AP.getChildren().add(z);

                Timeline zomb_move = new Timeline(new KeyFrame(Duration.millis(600), e1 -> {
                    if (zombie.isMove()) {

                        movex(z, -300, 1, 20000);
                        if (Math.abs(z.getLayoutX()-list_lawnmowers[row].getLawnimage().getLayoutX())<50 && !zombie.isKilled())
                        {
                            LawnMower curr_lm=list_lawnmowers[row];

                            Timeline lawnmower_move=new Timeline(new KeyFrame(Duration.millis(100),e2->{
                                curr_lm.getLawnimage().setLayoutX(curr_lm.getLawnimage().getLayoutX()+5);
                                lawnmower_collision(curr_lm,zombie);

                            }));
                            lawnmower_move.setCycleCount(1000);
                            lawnmower_move.play();
                        }

                        if(z.getLayoutX()<10 && !zombie.isKilled())
                        {
                            File gameover =new File("PNGArt/blank.png");
                            Image goimage=new Image(gameover.toURI().toString());
                            ImageView goiv=new ImageView(goimage);
                            goiv.setFitHeight(400);
                            goiv.setFitWidth(600);

                            File gameovernew =new File("PNGArt/images.jpg");
                            Image goimagenew=new Image(gameovernew.toURI().toString());
                            ImageView goivnew=new ImageView(goimagenew);
                            goivnew.setFitWidth(300);
                            goivnew.setFitHeight(200);
                            goivnew.setLayoutX(150);
                            goivnew.setLayoutY(70);

                            Text gotext=new Text(50,350,"Click Here to go back to Main Menu");
                            Text youlose=new Text(170,320,"Sorry, you lost!");
                            youlose.setFont(Font.font(30));

                            gotext.setFont(Font.font(30));
                            AP.getChildren().add(goiv);
                            AP.getChildren().add(goivnew);
                            AP.getChildren().add(youlose);
                            AP.getChildren().add(gotext);

                            pause_everything();
                            gotext.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    try {
                                        changeSceneButtonPush((Stage)((Node) mouseEvent.getSource()).getScene().getWindow());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });


                        }
                    }

                }));
                zomb_move.setCycleCount(Timeline.INDEFINITE);
                zomb_move.play();
                list_timeline.add(zomb_move);


        }));
        zombie_gen.setCycleCount(10+levelnumber*2);
        zombie_gen.play();
        list_timeline.add(zombie_gen);
        zombie_gen.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                File gameover =new File("PNGArt/blank.png");
                Image goimage=new Image(gameover.toURI().toString());
                ImageView goiv=new ImageView(goimage);
                goiv.setFitHeight(400);
                goiv.setFitWidth(600);

                File gameovernew =new File("PNGArt/images.jpg");
                Image goimagenew=new Image(gameovernew.toURI().toString());
                ImageView goivnew=new ImageView(goimagenew);
                goivnew.setFitWidth(300);
                goivnew.setFitHeight(200);
                goivnew.setLayoutX(150);
                goivnew.setLayoutY(70);

                Text gotext=new Text(50,350,"Click Here to go back to Main Menu");
                Text youlose=new Text(170,320,"Yayy, you won!");
                youlose.setFont(Font.font(30));

                gotext.setFont(Font.font(30));
                AP.getChildren().add(goiv);
                AP.getChildren().add(goivnew);
                AP.getChildren().add(youlose);
                AP.getChildren().add(gotext);

                pause_everything();
                gotext.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        try {
                            changeSceneButtonPush((Stage)((Node) mouseEvent.getSource()).getScene().getWindow());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }

    public void pause_everything()
    {
        for(int i=0;i<list_timeline.size();i++)
        {
            list_timeline.get(i).pause();
        }
    }

    public void resume_everything()
    {
        for(int i=0;i<list_timeline.size();i++)
        {
            list_timeline.get(i).play();
        }
    }

    public void ProgressBar(ImageView img) {

        Timeline pb = new Timeline(new KeyFrame(Duration.millis(765), e1->{
            img.setLayoutX(img.getLayoutX()-1);
            pbx=img.getLayoutX();
            pby=img.getLayoutY();
        }));

        pb.setCycleCount(183);
        pb.play();
        System.out.println(list_timeline+" lt");
        System.out.println(pb+" pb");
        list_timeline.add(pb);
    }

    public void creatGrid(Image blank)
    {

        for (int j=0;j<5;j++) {
            for (int i = 0; i < 9; i++) {
                ImageView iv = new ImageView(blank);
                iv.setOpacity(0);
                iv.setLayoutY(56 + 60*j);
                iv.setLayoutX(108 + 34 * i);
                iv.setFitHeight(50);
                iv.setFitWidth(40);
                AP.getChildren().add(iv);
                grid[j][i]=iv;
                int finalI = i;
                int finalJ = j;

                    iv.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            placed_plants[finalJ][finalI] = placeplant;
                            placed_plants[finalJ][finalI].setPlaced(true);


                            placed_plants[finalJ][finalI].getPlantimage().setLayoutX(iv.getLayoutX());
                            placed_plants[finalJ][finalI].getPlantimage().setLayoutY(iv.getLayoutY());


                            placed_plants[finalJ][finalI].setX(finalI);
                            placed_plants[finalJ][finalI].setY(finalJ);


                            if (placed_plants[finalJ][finalI] instanceof PeaShooter) {
                                placed_plants[finalJ][finalI] = (PeaShooter) placed_plants[finalJ][finalI];


                                Timeline peagen = new Timeline(new KeyFrame(Duration.seconds(3), e -> {

                                    if (grid_plant_placed[finalJ][finalI]) {
                                        if (zombie_list[placed_plants[finalJ][finalI].getY()].size() != 0) {
                                            ImageView peashot = placed_plants[finalJ][finalI].getPea();
                                            peashot.setFitWidth(12);
                                            peashot.setFitHeight(12);
                                            peashot.setLayoutX(iv.getLayoutX() + 24);
                                            peashot.setLayoutY(iv.getLayoutY() + 14);

                                            AP.getChildren().add(peashot);
                                            Timeline pea_move = new Timeline(new KeyFrame(Duration.millis(100), e1 -> {

                                                peashot.setLayoutX(peashot.getLayoutX() + 5);
                                            }));
                                            pea_move.setCycleCount(Timeline.INDEFINITE);
                                            pea_move.play();
                                            list_timeline.add(pea_move);

                                            Timeline collision_check = new Timeline(new KeyFrame(Duration.millis(100), e2 -> {
                                                try {
                                                    collisiondetect(placed_plants[finalJ][finalI], zombie_list[placed_plants[finalJ][finalI].getY()].get(0));

                                                }
                                                catch (IndexOutOfBoundsException e1)
                                                {

                                                }

                                            }));
                                            collision_check.setCycleCount(Timeline.INDEFINITE);
                                            collision_check.play();
                                            list_timeline.add(collision_check);
                                        }
                                    }
                                }));

                                peagen.setCycleCount(Timeline.INDEFINITE);
                                peagen.play();
                                list_timeline.add(peagen);
                                lock_buy(peas);
                            }
                            else if (placed_plants[finalJ][finalI] instanceof SunFlower) {
                                placed_plants[finalJ][finalI] = (SunFlower) placed_plants[finalJ][finalI];
                                lock_buy(sunf);
                                Random r = new Random();
                                Timeline Sun5 = new Timeline(new KeyFrame(Duration.seconds(10), e -> {
                                    if (grid_plant_placed[finalJ][finalI]) {

                                        File file = new File("PNGArt/Plants/sunflower/sun.png");
                                        Image sunimg = new Image(file.toURI().toString());
                                        ImageView suntk = new ImageView();
                                        AP.getChildren().add(suntk);
                                        suntk.setImage(sunimg);
                                        suntk.setFitWidth(30);
                                        suntk.setFitHeight(30);
                                        suntk.setLayoutX(placed_plants[finalJ][finalI].getPlantimage().getLayoutX() + r.nextDouble() * 30);
                                        suntk.setLayoutY(placed_plants[finalJ][finalI].getPlantimage().getLayoutY());
                                        suntk.setVisible(true);
                                        suntk.toFront();

                                        suntk.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                            @Override
                                            public void handle(MouseEvent mouseEvent) {
                                                AP.getChildren().remove(suntk);
                                                suncounter_int += 25;
                                                suncounter_txt.setText(Integer.toString(suncounter_int));

                                            }
                                        });

                                        Timeline move_y = new Timeline(new KeyFrame(Duration.millis(1000), e1 -> {
                                            suntk.setLayoutY(suntk.getLayoutY() + 10);
                                        }));
                                        move_y.setCycleCount(5);
                                        move_y.play();
                                        list_timeline.add(move_y);

                                    }
                                }));

                                Sun5.setCycleCount(Timeline.INDEFINITE);
                                Sun5.play();
                                list_timeline.add(Sun5);

                            }
                            else if (placeplant instanceof CherryBomb) {
                                placed_plants[finalJ][finalI] = (CherryBomb) placed_plants[finalJ][finalI];
                                lock_buy(cherr);

                                Timeline wait_time=new Timeline(new KeyFrame(Duration.seconds(3),e->{

                                }));
                                wait_time.setCycleCount(1);
                                wait_time.play();
                                list_timeline.add(wait_time);
                                wait_time.setOnFinished(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent) {
                                        ArrayList<Zombie> same_row_zombies=zombie_list[placed_plants[finalJ][finalI].getY()];
                                        Plant plant=placed_plants[finalJ][finalI];


                                        ImageView plant_image=plant.getPlantimage();
                                        if (same_row_zombies.size()!=0)
                                        {
                                            for(int i=0;i<same_row_zombies.size();i++)
                                            {
                                                Zombie zombie=same_row_zombies.get(i);
                                                if(Math.abs(placed_plants[finalJ][finalI].getPlantimage().getLayoutX()-zombie.getImage().getLayoutX())<30)
                                                {
                                                    grid_plant_placed[plant.getY()][plant.getX()] = false;

                                                    AP.getChildren().remove(plant_image);
                                                    ImageView iv = new ImageView(blank);
                                                    iv.setOpacity(0);
                                                    iv.setLayoutY(56 + 60 * plant.getY());
                                                    iv.setLayoutX(108 + 34 * plant.getX());
                                                    iv.setFitHeight(50);
                                                    iv.setFitWidth(40);
                                                    AP.getChildren().add(iv);
                                                    grid[plant.getY()][plant.getX()] = iv;
                                                    plant.setPlaced(false);

                                                    AP.getChildren().remove(zombie.getImage());

                                                    zombie_list[plant.getY()].remove(zombie);
                                                }
                                            }
                                        }
                                        else
                                        {
                                            grid_plant_placed[plant.getY()][plant.getX()] = false;

                                            AP.getChildren().remove(plant_image);
                                            ImageView iv = new ImageView(blank);
                                            iv.setOpacity(0);
                                            iv.setLayoutY(56 + 60 * plant.getY());
                                            iv.setLayoutX(108 + 34 * plant.getX());
                                            iv.setFitHeight(50);
                                            iv.setFitWidth(40);
                                            AP.getChildren().add(iv);
                                            grid[plant.getY()][plant.getX()] = iv;
                                            plant.setPlaced(false);
                                        }

                                    }
                                });

                            }

                            else if (placeplant instanceof PotatoMine) {
                                placed_plants[finalJ][finalI] = (PotatoMine) placed_plants[finalJ][finalI];
                                lock_buy(mine);

                            } else if (placeplant instanceof BarrierPlant) {
                                placed_plants[finalJ][finalI] = (BarrierPlant) placed_plants[finalJ][finalI];
                                lock_buy(barr);
                            }

                                Timeline zombie_stop =new Timeline(new KeyFrame(Duration.millis(400), e->{

                                    try {
                                        for(int i=0;i<zombie_list[placed_plants[finalJ][finalI].getY()].size();i++) {
                                            plantzombie_collision(placed_plants[finalJ][finalI], zombie_list[placed_plants[finalJ][finalI].getY()].get(i));
                                        }

                                    }
                                    catch (NullPointerException e1)
                                    {

                                    }

                                }));
                                zombie_stop.setCycleCount(Timeline.INDEFINITE);
                                zombie_stop.play();
                                list_timeline.add(zombie_stop);

                            if(!grid_plant_placed[finalJ][finalI]) {

                                iv.setImage(placeplant.getPlantimage().getImage());
                                iv.setOpacity(1);

                                placed_plants[finalJ][finalI].setPlantimage(iv);
                                placeplant=null;

                                grid_plant_placed[finalJ][finalI] = true;
                            }
                        }
                    });
            }
        }
    }

    public  void serialize(int levelnumber) throws IOException
    {

        ObjectOutputStream out=null;
        try {
            out=new ObjectOutputStream(new FileOutputStream("out.txt"));
            out.writeObject(this);
        }
        finally {
            out.close();
        }
    }

    public void deserialize() throws IOException,ClassNotFoundException
    {
        ObjectInputStream in=null;
        try
        {
            in=new ObjectInputStream(new FileInputStream("out.txt"));
            Controller loaded_controller=(Controller) in.readObject();
        }
        finally {
            in.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void playLevel(int levelNumber) {

        level=levelNumber;
        ProgressBar(meter);
        fillLawnmowers(list_lawnmowers);


        boolean check=false;

        Sun_Drop(0,0,1);

        if(levelNumber==1)
        {
            barr.setVisible(false);
            cherr.setVisible(false);
            sunf.setVisible(false);
            mine.setVisible(false);
        }
        else if(levelNumber==2)
        {
            mine.setVisible(false);
            cherr.setVisible(false);
            sunf.setVisible(false);
        }
        else if (levelNumber==3)
        {
            mine.setVisible(false);
            cherr.setVisible(false);
        }
        else if (levelNumber==4)
        {
            cherr.setVisible(false);

        }
        else if (levelNumber==5)
        {

        }

        creatGrid(blank);
        zombie_gen(check,levelNumber);

         peas.setOnMouseClicked(new EventHandler<MouseEvent>() {
             @Override
             public void handle(MouseEvent mouseEvent) {

                 PeaShooter pplant=new PeaShooter();
                 if(suncounter_int>=pplant.getCost()) {

                    suncounter_int-=pplant.getCost();
                    suncounter_txt.setText(Integer.toString(suncounter_int));
                     placeplant = pplant;
                 }


             }

         });

        barr.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                BarrierPlant bplant=new BarrierPlant();
                if(suncounter_int>=bplant.getCost()) {

                    suncounter_int-=bplant.getCost();
                    suncounter_txt.setText(Integer.toString(suncounter_int));
                    placeplant = bplant;
                }
//                placeplant=bplant;

            }
        });

        cherr.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                CherryBomb cplant=new CherryBomb();
                if(suncounter_int>=cplant.getCost()) {

                    suncounter_int-=cplant.getCost();
                    suncounter_txt.setText(Integer.toString(suncounter_int));
                    placeplant = cplant;
                }
//                placeplant=cplant;

            }
        });

        mine.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PotatoMine mplant=new PotatoMine();
                if(suncounter_int>=mplant.getCost()) {

                    suncounter_int-=mplant.getCost();
                    suncounter_txt.setText(Integer.toString(suncounter_int));
                    placeplant = mplant;
                }
//                placeplant=mplant;

            }
        });

        sunf.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                SunFlower splant=new SunFlower();
                if(suncounter_int>=splant.getCost()) {

                    suncounter_int-=splant.getCost();
                    suncounter_txt.setText(Integer.toString(suncounter_int));
                    placeplant = splant;
                }
//                placeplant=splant;

            }
        });

        pauseB.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                pause_everything();
                File pausefile=new File("PNGArt/Main/resume_button.png");
                Image pauseimage=new Image(pausefile.toURI().toString());
                ImageView pausepage=new ImageView(pauseimage);
                pausepage.setFitWidth(100);
                pausepage.setFitHeight(70);
                pausepage.setLayoutY(160);
                pausepage.setLayoutX(100);
                AP.getChildren().add(pausepage);

                File exitfile=new File("PNGArt/Main/exit_button.png");
                Image exitimage=new Image(exitfile.toURI().toString());
                ImageView exitpage=new ImageView(exitimage);
                exitpage.setFitWidth(120);
                exitpage.setFitHeight(80);
                exitpage.setLayoutY(150);
                exitpage.setLayoutX(220);
                AP.getChildren().add(exitpage);

                File savefile=new File("PNGArt/Main/save-button.png");
                Image saveimage=new Image(savefile.toURI().toString());
                ImageView savepage=new ImageView(saveimage);
                savepage.setFitWidth(120);
                savepage.setFitHeight(80);
                savepage.setLayoutX(360);
                savepage.setLayoutY(150);
                AP.getChildren().add(savepage);


                File refile=new File("PNGArt/Main/restart.png");
                Image reimage=new Image(refile.toURI().toString());
                ImageView repage=new ImageView(reimage);
                repage.setFitWidth(100);
                repage.setFitHeight(70);
                repage.setLayoutY(70);
                repage.setLayoutX(220);
                AP.getChildren().add(repage);

                repage.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        try {

                            changeSceneButtonPushRestart((Stage)((Node) mouseEvent.getSource()).getScene().getWindow());

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });

                pausepage.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        resume_everything();
                        AP.getChildren().remove(pausepage);
                        exitpage.setVisible(false);
                        savepage.setVisible(false);
                        repage.setVisible(false);
                    }
                });

                exitpage.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        try {
                            changeSceneButtonPush((Stage)((Node) mouseEvent.getSource()).getScene().getWindow());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                savepage.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent)  {
                        try {
                            serialize(levelNumber);

                        }
                        catch (IOException e)
                        {

                        }
                        System.out.println("Saved" +
                                "");
                    }
                });
            }
        });

    }

}
