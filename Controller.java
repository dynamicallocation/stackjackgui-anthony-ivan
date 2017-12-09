package sample;

import javafx.event.ActionEvent;
import javafx.scene.Scene;

import java.util.ArrayList;

public class Controller {

    public boolean checkTableMoney(ArrayList<Player> table) {
        for(int i = 0; i < table.size(); i++) {
            if(table.get(i).getMoney() > 1) {
                return true;
            }
        }
        return false;
    }


}




