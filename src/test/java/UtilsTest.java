
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class UtilsTest {


    @Test
    static void testGenerate() {
        Utils utils = new Utils();
        List<Card> cards1 = new ArrayList<>(Arrays.asList(new Card("7", "h"), new Card("j", "h"), new Card("a", "c"),
                new Card("8", "s"), new Card("a", "d"), new Card("a", "s"), new Card("6", "h")));



        List<Card> cards2 = new ArrayList<>(Arrays.asList(new Card("j", "d"), new Card("j", "h"), new Card("a", "c"),
                new Card("J", "s"), new Card("a", "d"), new Card("2", "h"), new Card("3", "h")));


        List<Card> cards3 = new ArrayList<>(Arrays.asList(new Card("j", "h"), new Card("10", "h"), new Card("K", "h"),
                new Card("q", "h"), new Card("a", "h"), new Card("2", "h"), new Card("3", "h")));

        Hand bestHand1 = utils.bestHand(cards1);
        bestHand1.printHand();

        Hand bestHand2 = utils.bestHand(cards2);
        bestHand2.printHand();

        Hand bestHand3 = utils.bestHand(cards3);
        bestHand3.printHand();


    }

    static void testOddsGenerator() {

        List<Card> player1 = new ArrayList<>(Arrays.asList(new Card("a", "h"), new Card("k", "h")));
        List<Card> player2 = new ArrayList<>(Arrays.asList(new Card("7", "s"), new Card("7", "c")));
        List<Card> tableCards = new ArrayList<>(Arrays.asList(new Card("9", "s")));

        OddsGenerator oG = new OddsGenerator(player1, player2, tableCards);
        System.out.println(oG.player1Odds);
        System.out.println(oG.player2Odds);
        System.out.println(oG.tieOdds);

    }

    void testPrintList() {
    }

    public static void main(String[] args) {
        testGenerate();
    }
}