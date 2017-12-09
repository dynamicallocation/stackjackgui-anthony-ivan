package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application{
    public static void main(String args[])
    {
        launch(args);
    }

    public void start(Stage stage){

        Stage theStage;
        theStage = stage;
        BorderPane border2 = new BorderPane();
        BorderPane border = new BorderPane();
        HBox vbox = new HBox();
        HBox playerControls = new HBox();
        playerControls.setSpacing(20);
        playerControls.setAlignment(Pos.CENTER);
        playerControls.setPadding(new Insets(0,0,10,0));
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0,0,10,10));
        HBox hbuttons = new HBox();
        hbuttons.setSpacing(10);
        hbuttons.setPadding(new Insets(0,0,10,10));
        //Buttons for first scene
        Button deleteBtn = new Button("Remove");
        Button addBtn = new Button("Add Player");
        Button nextBtn = new Button("Next");
        Button loopBtn = new Button("Begin Game");
        //Buttons for second scene
        Button hitMe = new Button("Hit Me");
        Button standOff = new Button("Stand Off");
        Button split = new Button("split");
        Button doubleDown = new Button("Double Down");
        playerControls.getChildren().addAll(hitMe,standOff,split,doubleDown,loopBtn);
        Button betBtn = new Button("Bet");
        nextBtn.setDisable(true);
        TextField playerRoster = new TextField();
        playerRoster.setPromptText("Add another player");
        TextField setBet = new TextField();
        setBet.setPromptText("Input Holdings");
        hbuttons.getChildren().addAll(deleteBtn,addBtn,playerRoster,betBtn,setBet,nextBtn);
        border.setBottom(hbuttons);
        border.setTop(vbox);
        border2.setBottom(playerControls);
        //hello world
        //**Start of Game Logic**

        //add players
        ArrayList<Player> table = new ArrayList<Player>();
        addBtn.setOnAction(e -> {
                StringBuilder msg = new StringBuilder();
                String name = playerRoster.getText();
                int i = table.size() + 1;
                System.out.println("player " + i + ": " + name);
                table.add(new Player(name));
                msg.append("\n");
                playerRoster.clear();
                TextField field = new TextField();
                field.setAlignment(Pos.TOP_LEFT);
                field.setText(name);
                nextBtn.setDisable(false);
                deleteBtn.setDisable(false);
                if(table.size() >= 8)
                {
                    System.out.println("Table has exceeded its capacity");
                    addBtn.setDisable(true);
                }
        });
        //remove players
        deleteBtn.setOnAction(e ->
        {
           int i = table.size() - 1;
           table.remove(i);
            if(table.size() == 7)
            {
                addBtn.setDisable(false);
            }
            else if(i == 0)
            {
                deleteBtn.setDisable(true);
                nextBtn.setDisable(true);
            }
        });

        nextBtn.setOnAction(e -> {
            Scene scene2 = new Scene(border2, 600, 500);
            theStage.setScene(scene2);

        });

        hitMe.setOnAction(e -> {
            Player clicked = new Player("ButtonCheck");

                });
        //game loop
        loopBtn.setOnAction(e -> {
            loopBtn.setDisable(true);
            Player dealer = new Player("Dealer");
            Deck deck = new Deck();
            Controller verify = new Controller();
            while(verify.checkTableMoney(table)) {

                // Place bets
                for(int i = 0; i < table.size(); i++) {
                    if(table.get(i).getMoney() > 1) {
                        table.get(i).makeBet();
                    }
                }

                // Dealer plays
                dealer.playAutoHand(deck);

                // Player plays
                for(int i = 0; i < table.size(); i++) {
                    if(table.get(i).getMoney() > 1 && !dealer.getBlackjack()) {
                        table.get(i).playHand(deck);
                    }
                }

                // if dealer blackjack
                for(int i = 0; i < table.size(); i++) {
                    if(table.get(i).getMoney() > 1 && dealer.getBlackjack()) {
                        table.get(i).playTwoCards(deck);
                    }
                }

                //Dealer reveals
                dealer.revealHand(deck);

                //Compare hands
                for(int i = 0; i < table.size(); i++) {
                    if(table.get(i).getMoney() > 1) {
                        if(!dealer.getBlackjack()) {
                            table.get(i).compareToDealer(dealer, 0);
                            if(table.get(i).countHand(1) != 0) {
                                table.get(i).compareToDealer(dealer, 1);
                            }
                        }
                        else if(dealer.getBlackjack() && table.get(i).getBlackjack()) {
                            System.out.println("***" + table.get(i).getName() + " got a stand-off***");
                        }
                        else {
                            System.out.println("***" + table.get(i).getName() + " lost***");
                            table.get(i).changeMoney(-table.get(i).getBet());
                        }
                        // Reset player
                        table.get(i).resetHand();
                    }
                }

                // Reset dealer
                System.out.println("\n");
                dealer.resetHand();
            }
        });





        Scene scene = new Scene(border, 600, 500);
        stage.setScene(scene);
        stage.show();
    }

}

