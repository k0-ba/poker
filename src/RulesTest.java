import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RulesTest {
    @Test
    public void shouldHavePair(){
        Player player = new Player("Test");
        player.addCard(new Card(Suits.CLOVER,2));
        player.addCard(new Card(Suits.DIAMOND,2));
        player.addCard(new Card(Suits.HEART,3));
        player.addCard(new Card(Suits.DIAMOND,5));
        player.addCard(new Card(Suits.DIAMOND,9));
        assertTrue(Rules.hasPair(player.getHand()));
    }

    @Test
    public void shouldHaveThreeOfAKind(){
        Player player = new Player("Test");
        player.addCard(new Card(Suits.CLOVER,2));
        player.addCard(new Card(Suits.DIAMOND,2));
        player.addCard(new Card(Suits.HEART,2));
        player.addCard(new Card(Suits.DIAMOND,9));
        player.addCard(new Card(Suits.DIAMOND,10));
        assertTrue(Rules.hasThreeOfAKind(player.getHand()));
    }

    @Test
    public void shouldHaveFourOfAKind(){
        Player player = new Player("Test");
        player.addCard(new Card(Suits.CLOVER,2));
        player.addCard(new Card(Suits.DIAMOND,9));
        player.addCard(new Card(Suits.HEART,9));
        player.addCard(new Card(Suits.SPADE,9));
        player.addCard(new Card(Suits.CLOVER,9));
        assertTrue(Rules.hasFourOfAKind(player.getHand()));
    }

    @Test
    public void shouldHaveTwoPairs(){
        Player player = new Player("Test");
        player.addCard(new Card(Suits.CLOVER,2));
        player.addCard(new Card(Suits.DIAMOND,2));
        player.addCard(new Card(Suits.HEART,8));
        player.addCard(new Card(Suits.SPADE,10));
        player.addCard(new Card(Suits.CLOVER,10));
        assertTrue(Rules.hasTwoPairs(player.getHand()));
    }

    @Test
    public void shouldHaveFullHouse(){
        Player player = new Player("Test");
        player.addCard(new Card(Suits.CLOVER,2));
        player.addCard(new Card(Suits.DIAMOND,2));
        player.addCard(new Card(Suits.HEART,10));
        player.addCard(new Card(Suits.SPADE,10));
        player.addCard(new Card(Suits.CLOVER,10));
        assertTrue(Rules.hasFullHouse(player.getHand()));
    }

    @Test
    public void shouldHaveStraight(){
        Player player = new Player("Test");
        player.addCard(new Card(Suits.CLOVER,2));
        player.addCard(new Card(Suits.DIAMOND,3));
        player.addCard(new Card(Suits.HEART,4));
        player.addCard(new Card(Suits.SPADE,5));
        player.addCard(new Card(Suits.CLOVER,6));
        assertTrue(Rules.hasStraight(player.getHand()));
    }

    @Test
    public void shouldHaveFlush(){
        Player player = new Player("Test");
        player.addCard(new Card(Suits.CLOVER,2));
        player.addCard(new Card(Suits.CLOVER,3));
        player.addCard(new Card(Suits.CLOVER,4));
        player.addCard(new Card(Suits.CLOVER,5));
        player.addCard(new Card(Suits.CLOVER,6));
        assertTrue(Rules.hasFlush(player.getHand()));
    }

    @Test
    public void shouldHaveStraightFlush(){
        Player player = new Player("Test");
        player.addCard(new Card(Suits.CLOVER,2));
        player.addCard(new Card(Suits.CLOVER,3));
        player.addCard(new Card(Suits.CLOVER,4));
        player.addCard(new Card(Suits.CLOVER,5));
        player.addCard(new Card(Suits.CLOVER,6));
        assertTrue(Rules.hasStraightFlush(player.getHand()));
    }

    @Test
    public void shouldHaveRoyalStraightFlush(){
        Player player = new Player("Test");
        player.addCard(new Card(Suits.CLOVER,10));
        player.addCard(new Card(Suits.CLOVER,11));
        player.addCard(new Card(Suits.CLOVER,12));
        player.addCard(new Card(Suits.CLOVER,13));
        player.addCard(new Card(Suits.CLOVER,14));
        assertTrue(Rules.hasRoyalStraightFlush(player.getHand()));
    }
}
