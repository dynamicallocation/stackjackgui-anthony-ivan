package sample;

public class Card {
    //variables
    private String suit;
    private int number;
    private int value;

    //constructor
    public Card(String cardSuit, int cardNumber) {
        suit = cardSuit;
        number = cardNumber;
        if(cardNumber <= 10) {
            value = cardNumber;
        }
        else {
            value = 10;
        }
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public int getNumber() {
        return number;
    }

    public String getCardString() {
        String result = "";
        result += suit;
        String numValue = "";
        if(number > 1 && number <= 10) {
            result += " " + number;
        }
        else if(number == 1) result += " A";
        else if(number == 11) result += " J";
        else if(number == 12) result += " Q";
        else if(number == 13) result += " K";

        return result;
    }
}