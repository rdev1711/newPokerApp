

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class Utils {
    static int fourth = 14*14*14*14;
    static int third = 14*14*14;
    static int second = 14*14;
    static int pairBase = 1000000;
    static int twoPairBase = 2*pairBase;
    static int tripsBase = 3*pairBase;
    static int straightBase = 4*pairBase;
    static int flushBase = 5*pairBase;
    static int fullHouseBase = 6*pairBase;
    static int quadsBase = 7*pairBase;
    static int straightFlushBase = 8*pairBase;



      List<int[]> generateCombs(int options, int combSize) {
      Iterator<int[]> combIterator = CombinatoricsUtils.combinationsIterator(options,combSize);
      List<int[]> combinations = new ArrayList<>();
      while(combIterator.hasNext()) {
          combinations.add(combIterator.next());
      }
      return combinations;
    }

    List<List<Card>> allCards(List<Card> cards, int handSize) {
        List<int[]> combs = generateCombs(cards.size(), handSize);
        List<List<Card>> possHands = new ArrayList<>();
        for (int[] comb: combs) {
            possHands.add(makeCards(comb, cards));
        }
        return possHands;
    }

    Hand bestHand(List<Card> cards) {
        List<List<Card>> allCards = allCards(cards, 5);
        List<Hand> possHands = new ArrayList<>();
        for (List<Card> cardList : allCards) {
            possHands.add(new Hand(cardList.toArray(new Card[cardList.size()])));
        }
        Hand bestHand = possHands.get(0);
        int bestValue = bestHand.value;

        for (Hand hand: possHands) {
            if (hand.value > bestValue) {
                bestValue = hand.value;
                bestHand = hand;
            }
           // hand.printHand();
        }
        return bestHand;

    }

    private List<Card> makeCards(int[] comb, List<Card> cards) {
        List<Card> handCards = new ArrayList<>();
        for (int i = 0; i < comb.length; i++) {
            handCards.add(cards.get(comb[i]));
        }
        return handCards;
    }

    private static int factorial(int n) {
        if (n == 1 || n == 0) {
            return 1;
        }
        return n * factorial(n-1);
    }

     static void printList(int[] lst) {
        String lstString = "[";
        for (int item: lst){
            lstString += Integer.toString(item);
            lstString += " ";
        }
        lstString = lstString.substring(0, lstString.length() - 1) + "]";
        System.out.println(lstString);
    }
}
