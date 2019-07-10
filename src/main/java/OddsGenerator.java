import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class OddsGenerator {

    private List<Card> deck = new ArrayList<>();
    private List<Card> player1 = new ArrayList<>();
    private List<Card> player2 = new ArrayList<>();
    private List<Card> tableCards = new ArrayList<>();
    double player1Odds, player2Odds, tieOdds;
    private int cardsToSim;
    private HashMap<Integer, Card> cardIdentifier = new HashMap<>();
    private Utils utils = new Utils();

    OddsGenerator(List<Card> player1, List<Card> player2, List<Card> tableCards) {
        if (player1.size() == 2 || player2.size() == 2) {
            player1.addAll(tableCards);
            player2.addAll(tableCards);
            this.player1 = player1;
            this.player2 = player2;
            this.tableCards = tableCards;
            this.cardsToSim = 5 -tableCards.size();
            setUp();
            doTheMath();

        } else {
            System.out.println("Invalid player hands");
        }

    }

    private void setUp() {

        cardsToSim = 5 - tableCards.size();
        createDeck();
        List<Card> knownCards = new ArrayList<>();
        knownCards.addAll(player1);
        knownCards.addAll(player2);
        if (tableCards.size() > 0) {
            knownCards.addAll(tableCards);
        }
        getRemainingCards(knownCards);
    }

    private void createDeck() {
       List<String> suits = new ArrayList<>(Arrays.asList("h", "c", "d", "s"));
       List<String> ranks = new ArrayList<>(Arrays.asList("a", "k", "q", "j"));
       int val = 10;
       while (val > 0) {
           ranks.add(Integer.toString(val));
           val--;
       }

       for (String rank: ranks) {
           for (String suit: suits) {
               Card newCard = new Card(rank, suit);
               deck.add(newCard);
               cardIdentifier.put(newCard.cardVal, newCard);
           }
       }

    }

    private void doTheMath() {
        List<List<Card>> possCards = utils.allCards(deck, cardsToSim);
        double total = possCards.size();
        double player1Wins = 0, player2Wins = 0;
        for (List<Card> cardComb : possCards) {
            int result = whoWins(cardComb);
            if (result == 1) {
                player1Wins++;
            } else if (result == 2) {
                player2Wins++;
            }

        }
        this.player1Odds = 100.0*(player1Wins / total);
        this.player2Odds = 100.0*(player2Wins / total);
        this.tieOdds = 100.0 - (this.player1Odds + this.player2Odds);

    }

    private int whoWins(List<Card> cardComb) {
        List <Card> player1Cards = new ArrayList<>();
        List <Card> player2Cards = new ArrayList<>();
        player1Cards.addAll(player1);
        player1Cards.addAll(cardComb);
        player2Cards.addAll(player2);
        player2Cards.addAll(cardComb);
        Hand player1Hand = utils.bestHand(player1Cards);
        Hand player2Hand = utils.bestHand(player2Cards);
        if (player1Hand.value > player2Hand.value) {
            return 1;
        } else if (player1Hand.value < player2Hand.value) {
            return 2;
        }
        return 0;

    }


    private void getRemainingCards(List<Card> knownCards) {
        for (Card card : knownCards) {
            deck.remove(cardIdentifier.get(card.cardVal));
        }
    }

}
