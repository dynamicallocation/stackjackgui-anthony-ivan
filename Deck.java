package sample;

import java.util.*;

public class Deck {
    ArrayList<Card> allCards = new ArrayList<>();
    Stack<Card> stackedDeck;
    private int numOfDecks = 6;

    //constructor
    public Deck() {
        for(int j = 1; j <= numOfDecks; j++) {
            for(int i = 1; i <= 13; i++) {
                allCards.add(new Card("Spade", i));
                allCards.add(new Card("Heart", i));
                allCards.add(new Card("Diamond", i));
                allCards.add(new Card("Club", i));
            }
        }
    }

    public void setNumofDecks() {
        System.out.println("How many decks would you like to play with?\n" + "  | Recommended: 6   |" + "\n  |      Limits: 1-8 |");
        System.out.print("Number of Decks: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if(!isAlpha(input)) {
            int num = Integer.parseInt(input);
            if(num > 0 && num < 9) {
                numOfDecks = num;
            }
            else {
                System.out.println("! Invalid Number");
                setNumofDecks();
            }
        }
        else {
            System.out.println("! Invalid Number");
            setNumofDecks();
        }
    }

    public void setDeck() {
        System.out.println("---shuffling a new deck---");
        stackedDeck = shuffleAndStack();
    }

    public Stack<Card> shuffleAndStack() {
        ArrayList<Integer> nums = new ArrayList<>();
        Stack<Card> randomizedCards = new Stack<Card>();
        nums.addAll(drawAtRandom());
        for(int i = 0; i < 51*numOfDecks; i++) {
            randomizedCards.push(allCards.get(nums.get(i)));
        }
        return randomizedCards;

    }
    public ArrayList<Integer> drawAtRandom() {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i <= 51*numOfDecks; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers;
    }

    public Card popStack() {
        if(!stackedDeck.empty()) {
            return stackedDeck.pop();
        }
        else {
            System.out.println("---shuffling a new deck---");
            stackedDeck = shuffleAndStack();
            return stackedDeck.pop();
        }
    }

    // input verifier
    public boolean isAlpha(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if(Character.isLetter(c)) {
                return true;
            }
        }
        return false;
    }
}