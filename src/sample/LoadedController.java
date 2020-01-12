package sample;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoadedController implements Initializable {
    ArrayList<saveData> saveDataArrayList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        saveDataArrayList=new ArrayList<saveData>();
    }

}
