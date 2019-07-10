

import java.util.HashMap;

class Card {
    int rank, suit;
    private String wordRank, wordSuit;
    boolean validCard = true;
    int cardVal;

    Card(String rank, String suit) {
        HashMap<String, Integer> conv = new HashMap<>();
        HashMap<String, String> name = new HashMap<>();
        name.put("C", "Clubs");
        name.put("D", "Diamonds");
        name.put("H", "Hearts");
        name.put("S", "Spades");
        conv.put("A", 14);
        conv.put("K", 13);
        conv.put("Q", 12);
        conv.put("J", 11);
        conv.put("C", 0);
        conv.put("D", 1);
        conv.put("H", 2);
        conv.put("S", 3);
        wordRank = rank.toUpperCase();
        try {
            if (wordRank.equals("A") || wordRank.equals("K") || wordRank.equals("Q") || wordRank.equals("J")) {
                this.rank = conv.get(wordRank);
            } else {
                this.rank = Integer.parseInt(wordRank);
            }
            suit = suit.toUpperCase();
            this.suit = conv.get(suit);
            wordSuit = name.get(suit);
            this.cardVal = 3*this.rank + this.suit;
        } catch (Exception e) {
            System.out.println("Invalid Card Entered!");
            validCard = false;
        }


    }
    void printCard () {
        System.out.println(wordRank + " of " + wordSuit);
    }



}