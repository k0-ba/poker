import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Geir
 * Date: 10/30/13
 * Time: 10:20
 * To change this template use File | Settings | File Templates.
 */
public class GameTest {

    @Test
    public void playerOneShouldWin(){
        List<Player> players = new ArrayList<Player>();

        Player playerOne = new Player("Player One");
        playerOne.addCard(new Card(Suits.CLOVER, 2));
        playerOne.addCard(new Card(Suits.CLOVER, 3));
        playerOne.addCard(new Card(Suits.CLOVER, 4));
        playerOne.addCard(new Card(Suits.CLOVER, 5));
        playerOne.addCard(new Card(Suits.CLOVER, 6));
        players.add(playerOne);

        Player playerTwo = new Player("Player Two");
        playerTwo.addCard(new Card(Suits.CLOVER, 2));
        playerTwo.addCard(new Card(Suits.DIAMOND, 2));
        playerTwo.addCard(new Card(Suits.HEART, 10));
        playerTwo.addCard(new Card(Suits.SPADE, 10));
        playerTwo.addCard(new Card(Suits.CLOVER, 10));
        players.add(playerTwo);

        playerOne.setScore();
        playerOne.setHighCard();

        playerTwo.setScore();
        playerTwo.setHighCard();

        players = Player.rankPlayers(players);

        assertEquals("Player One",players.get(0).getName());
    }

    @Test
    public void playerTwoShouldWin(){
        List<Player> players = new ArrayList<Player>();

        Player playerOne = new Player("Player One");
        playerOne.addCard(new Card(Suits.CLOVER, 2));
        playerOne.addCard(new Card(Suits.DIAMOND, 2));
        playerOne.addCard(new Card(Suits.HEART, 2));
        playerOne.addCard(new Card(Suits.DIAMOND, 9));
        playerOne.addCard(new Card(Suits.DIAMOND, 10));
        players.add(playerOne);

        Player playerTwo = new Player("Player Two");
        playerTwo.addCard(new Card(Suits.CLOVER, 2));
        playerTwo.addCard(new Card(Suits.DIAMOND, 2));
        playerTwo.addCard(new Card(Suits.HEART, 10));
        playerTwo.addCard(new Card(Suits.SPADE, 10));
        playerTwo.addCard(new Card(Suits.CLOVER, 10));
        players.add(playerTwo);

        playerOne.setScore();
        playerOne.setHighCard();

        playerTwo.setScore();
        playerTwo.setHighCard();

        players = Player.rankPlayers(players);

        assertEquals("Player Two",players.get(0).getName());
    }

    @Test
    public void nobodyShouldWin(){
        List<Player> players = new ArrayList<Player>();
        String result = "";

        Player playerOne = new Player("Player One");
        playerOne.addCard(new Card(Suits.CLOVER, 2));
        playerOne.addCard(new Card(Suits.DIAMOND, 2));
        playerOne.addCard(new Card(Suits.HEART, 3));
        playerOne.addCard(new Card(Suits.DIAMOND, 4));
        playerOne.addCard(new Card(Suits.DIAMOND, 10));
        players.add(playerOne);

        Player playerTwo = new Player("Player Two");
        playerTwo.addCard(new Card(Suits.HEART, 2));
        playerTwo.addCard(new Card(Suits.SPADE, 2));
        playerTwo.addCard(new Card(Suits.CLOVER, 3));
        playerTwo.addCard(new Card(Suits.HEART, 4));
        playerTwo.addCard(new Card(Suits.CLOVER, 10));
        players.add(playerTwo);

        playerOne.setScore();
        playerOne.setHighCard();

        playerTwo.setScore();
        playerTwo.setHighCard();

        players = Player.rankPlayers(players);

        if(players.get(0).getScore() == players.get(1).getScore()){
            switch (players.
                    get(0).
                    getHighCard().
                    compareTo(players.
                            get(1).
                            getHighCard()
                    )){
                case(-1):
                    result = ("The winner is " + players.get(1).getName());
                    break;
                case(0):
                    result = ("At least two players share the best score. No winner can be determined.");
                    break;
                case(1):
                    result = ("The winner is " + players.get(0).getName());
                    break;
            }
        }
        else{
            result = ("The winner is " + players.get(0).getName());
        }
        assertEquals("At least two players share the best score. No winner can be determined.", result);
    }
}
