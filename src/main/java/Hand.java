

class Hand {
    private Card[] cards;
    String kind;
    int value;
    private boolean validHand = true;

    Hand(Card[] cards) {
        for (Card c : cards) {
            if (!c.validCard) {
                validHand = false;
                break;
            }
        }

        if (validHand && cards.length == 5) {
            this.cards = cards;
            this.sortByRank(this.cards);
            this.valueHand();

            //valueHand();
        } else {
            System.out.println("Invalid Hand!");
        }
    }


    public void printHand() {
        if (validHand) {
            for (int i = 0; i <= 4; i++) {
                cards[i].printCard();
            }
        }
        System.out.println("Kind of Hand: " + kind);
        System.out.println("Value of Hand: " + value);
    }


    public void valueHand() {
        if (isStraightFlush(cards)) {
            value = valueStraightFlush(cards);
            kind = "Straight Flush";
        } else if (isQuads(cards)) {
            value = valueQuads(cards);
            kind = "Quads";
        } else if (isFullHouse(cards)) {
            value = valueFullHouse(cards);
            kind = "Full House";
        } else if (isFlush(cards)) {
            value = valueFlush(cards);
            kind = "Flush";
        } else if (isStraight(cards)) {
            value = valueStraight(cards);
            kind = "Straight";
        } else if (isTrips(cards)) {
            value = valueTrips(cards);
            kind = "Trips";
        } else if (isTwoPair(cards)) {
            value = valueTwoPair(cards);
            kind = "Two Pair";
        } else if (isPair(cards)) {
            value = valuePair(cards);
            kind = "Pair";
        } else {
            value = valueHighCard(cards);
            kind = "High Card";
        }
    }

    public int valueStraightFlush(Card[] cards) {
        return Utils.straightFlushBase * cards[4].rank;
    }

    public int valueQuads(Card[] cards) {
        // cards = sortByRank(cards);
        int cardVal = cards[2].rank * 14;
        if (cards[0].rank == cards[1].rank) {
            cardVal += cards[4].rank;
        } else {
            cardVal += cards[0].rank;
        }
        return Utils.quadsBase + cardVal;
    }

    public int valueFullHouse(Card[] cards) {
        //cards = sortByRank(cards);
        int cardVal = cards[2].rank * 14;
        if (cards[2].rank == cards[3].rank) {
            cardVal += cards[1].rank;
        } else {
            cardVal += cards[3].rank;
        }
        return Utils.fullHouseBase + cardVal;

    }

    public int valueFlush(Card[] cards) {
        //cards = sortByRank(cards);
        return Utils.flushBase + cards[4].rank;


    }

    public int valueStraight(Card[] cards) {
        int cardVal = 0;
        //cards = sortByRank(cards);
        if (cards[4].rank == 14 && cards[0].rank == 2) {
            cardVal = cards[3].rank;
        } else {
            cardVal = cards[4].rank;
        }
        return Utils.straightBase + cardVal;
    }

    public int valueTrips(Card[] cards) {
        int cardVal = 0;
        //cards = sortByRank(cards);
        if (cards[0].rank == cards[2].rank) {
            cardVal = cards[0].rank * Utils.second + cards[4].rank * 14 + cards[3].rank;
        } else if (cards[1].rank == cards[3].rank) {
            cardVal = cards[1].rank * Utils.second + cards[4].rank * 14 + cards[0].rank;
        } else {
            cardVal = cards[2].rank * Utils.second + cards[1].rank * 14 + cards[0].rank;
        }
        return Utils.tripsBase + cardVal;
    }

    public int valueTwoPair(Card[] cards) {
        int cardVal = 0;
        //cards = sortByRank(cards);
        if (cards[0].rank == cards[1].rank) {
            if (cards[2].rank == cards[3].rank) {
                cardVal = cards[2].rank * Utils.second + cards[1].rank * 14 + cards[4].rank;
            } else if (cards[3].rank == cards[4].rank) {
                cardVal = cards[3].rank * Utils.second + cards[1].rank * 14 + cards[2].rank;
            }
        } else {
            cardVal = cards[4].rank * Utils.second + cards[2].rank * Utils.second + cards[0].rank;

        }
        return Utils.twoPairBase + cardVal;
    }

    public int valuePair(Card[] cards) {
        int cardVal = 0;
        //cards = sortByRank(cards);
        if (cards[0].rank == cards[1].rank) {
            cardVal = cards[0].rank * Utils.third + cards[4].rank * Utils.second + cards[3].rank * 14 + cards[2].rank;
        } else if (cards[1].rank == cards[2].rank) {
            cardVal = cards[1].rank * Utils.third + cards[4].rank * Utils.second + cards[3].rank * 14 + cards[0].rank;
        } else if (cards[2].rank == cards[3].rank) {
            cardVal = cards[2].rank * Utils.third + cards[4].rank * Utils.second + cards[1].rank * 14 + cards[0].rank;
        } else {
            cardVal = cards[3].rank * Utils.third + cards[2].rank * Utils.second + cards[1].rank * 14 + cards[0].rank;

        }
        return Utils.pairBase + cardVal;

    }

    public int valueHighCard(Card[] cards) {
        //cards = sortByRank(cards);

        return cards[4].rank * Utils.fourth + cards[3].rank * Utils.third + cards[2].rank * Utils.second + cards[1].rank * 14 + cards[0].rank;
    }

    public boolean isStraightFlush(Card[] cards) {
        return isStraight(cards) && isFlush(cards);
    }

    public int getQuads() {
        // cards = sortByRank(cards);
        boolean quads = false;
        int quadRank = 0;
        int kickerRank = 0;
        if (cards[3].rank == cards[6].rank) {
            quads = true;
            quadRank = cards[3].rank;
            kickerRank = cards[2].rank;
        } else if (cards[2].rank == cards[5].rank) {
            quads = true;
            quadRank = cards[2].rank;
            kickerRank = cards[6].rank;
        } else if (cards[1].rank == cards[4].rank) {
            quads = true;
            quadRank = cards[1].rank;
            kickerRank = cards[6].rank;
        } else if (cards[0].rank == cards[3].rank) {
            quads = true;
            quadRank = cards[0].rank;
            kickerRank = cards[6].rank;
        }

        if (quads) {
            return Utils.quadsBase + 14*quadRank + kickerRank;
        }
        return 0;
    }

    public int getFullHouse() {
        boolean fullHouse = false;
        int topRank = 0;
        int botRank = 0;
        if (cards[4].rank == cards[6].rank) {
            if (cards[2].rank == cards[3].rank) {
                fullHouse = true;
                topRank = cards[4].rank;
                botRank = cards[2].rank;
            } else if (cards[1].rank == cards[2].rank) {
                fullHouse = true;
                topRank = cards[4].rank;
                botRank = cards[1].rank;
            } else if (cards[0].rank == cards[1].rank) {
                fullHouse = true;
                topRank = cards[4].rank;
                botRank = cards[0].rank;
            }
        } else if (cards[3].rank == cards[5].rank) {
            if (cards[1].rank == cards[2].rank) {
                fullHouse = true;
                topRank = cards[3].rank;
                botRank = cards[1].rank;
            } else if (cards[0].rank == cards[1].rank) {
                fullHouse = true;
                topRank = cards[3].rank;
                botRank = cards[0].rank;
            }
        } else if (cards[2].rank == cards[4].rank) {
            if (cards[5].rank == cards[6].rank) {
                fullHouse = true;
                topRank = cards[2].rank;
                botRank = cards[5].rank;
            } else if (cards[0].rank == cards[1].rank) {
                fullHouse = true;
                topRank = cards[2].rank;
                botRank = cards[0].rank;
            }
        } else if (cards[1].rank == cards[3].rank) {
            if (cards[5].rank == cards[6].rank) {
                fullHouse = true;
                topRank = cards[1].rank;
                botRank = cards[5].rank;
            } else if (cards[4].rank == cards[5].rank) {
                fullHouse = true;
                topRank = cards[1].rank;
                botRank = cards[4].rank;
            }
        } else if (cards[0].rank == cards[2].rank) {
            if (cards[5].rank == cards[6].rank) {
                fullHouse = true;
                topRank = cards[0].rank;
                botRank = cards[5].rank;
            } else if (cards[4].rank == cards[5].rank) {
                fullHouse = true;
                topRank = cards[0].rank;
                botRank = cards[4].rank;
            } else if (cards[3].rank == cards[4].rank) {
                fullHouse = true;
                topRank = cards[0].rank;
                botRank = cards[3].rank;
            }
        }

        if(fullHouse) {
            return Utils.fullHouseBase + 14*topRank + botRank;
        }
        return 0;

    }

    public int getFlush() {
        //cards = sortByRank(cards);
        boolean flush = false;
        int first = 0, second = 0, third = 0, fourth = 0, fifth = 0;
        sortBySuit(cards);
        if (cards[2].suit == cards[6].suit) {
            flush = true;
            first = cards[6].rank;
            second = cards[5].rank;
            third = cards[4].rank;
            fourth = cards[3].rank;
            fifth = cards[2].rank;
        } else if (cards[1].suit == cards[5].suit) {
            flush = true;
            first = cards[5].rank;
            second = cards[4].rank;
            third = cards[3].rank;
            fourth = cards[2].rank;
            fifth = cards[1].rank;
        } else if (cards[0].suit == cards[4].suit) {
            flush = true;
            first = cards[4].rank;
            second = cards[3].rank;
            third = cards[2].rank;
            fourth = cards[1].rank;
            fifth = cards[0].rank;
        }
        sortByRank(cards);

        if (flush) {
            return Utils.flushBase + Utils.fourth*first + Utils.third*second + Utils.second*third + 14*fourth + fifth;
        }
        return 0;
    }

    public int getStraight(Card[] cards) {

        int [] ranks = new int[cards.length];

        for (int i = 0; i < cards.length; i++) {
            ranks[i] = cards[i].rank;
        }

        for (int i = 0; i < cards.length - 1; i++) {
            if (ranks[i] == ranks[i+1]) {
                
            }
        }

        if (cards[4].rank == 14) {
            if (cards[0].rank == 2 && cards[1].rank == 3 && cards[2].rank == 4 && cards[3].rank == 5) {
                return true;
            } else if (cards[0].rank == 10 && cards[1].rank == 11 && cards[2].rank == 12 && cards[3].rank == 13) {
                return true;
            } else {
                return false;
            }
        } else {


            int next = 1;
            for (int i = 0; i < 4; i++) {
                if (cards[i].rank + 1 != cards[next].rank) {
                    return false;
                }
                next++;
            }
            return true;
        }
    }

    public boolean isTrips(Card[] cards) {
        //cards = sortByRank(cards);
        if (cards[0].rank == cards[2].rank || cards[1].rank == cards[3].rank || cards[2].rank == cards[4].rank) {
            return true;
        }
        return false;

    }

    public boolean isTwoPair(Card[] cards) {
        //cards = sortByRank(cards);
        if (cards[0].rank == cards[1].rank) {
            if (cards[2].rank == cards[3].rank || cards[3].rank == cards[4].rank) {
                return true;
            }
        } else if (cards[1].rank == cards[2].rank) {
            if (cards[3].rank == cards[4].rank) {
                return true;
            }
        }
        return false;
    }

    public boolean isPair(Card[] cards) {
        //cards = sortByRank(cards);
        if (cards[0].rank == cards[1].rank || cards[1].rank == cards[2].rank ||
                cards[2].rank == cards[3].rank || cards[3].rank == cards[4].rank) {
            return true;
        }
        return false;
    }

    public void sortBySuit(Card[] cards) {
        int curr = 0;
        while (curr < cards.length) {
            int min_rem = curr;
            for (int i = curr + 1; i < cards.length; i++) {
                if (cards[i].suit < cards[min_rem].suit) {
                    min_rem = i;
                }
            }
            Card temp = cards[curr];
            cards[curr] = cards[min_rem];
            cards[min_rem] = temp;
            curr++;
        }
        this.cards = cards;
    }

    public void sortByRank(Card[] cards) {
        int curr = 0;
        while (curr < cards.length) {
            int min_rem = curr;
            for (int i = curr + 1; i < cards.length; i++) {
                if (cards[i].rank < cards[min_rem].rank) {
                    min_rem = i;
                }
            }
            Card temp = cards[curr];
            cards[curr] = cards[min_rem];
            cards[min_rem] = temp;
            curr++;
        }
        this.cards = cards;
    }

}






