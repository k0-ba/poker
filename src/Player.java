import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Geir
 * Date: 10/17/13
 * Time: 15:46
 * To change this template use File | Settings | File Templates.
 */
public class Player{
    private List<Card> hand;
    private String name;
    private boolean fold;
    private int bet;
    private Card highCard;
    private int score;

    public Player(String name){
        hand = new ArrayList<Card>();
        fold = false;
        bet = 0;
        this.name = name;
    }


    public void addCard(Card card){
        hand.add(card);
    }

    public void removeCard(int index){
        hand.remove(index);
    }

    public void setFold(boolean fold){
        this.fold = fold;
    }

    public boolean isFolded(){
        return fold;
    }

    public void setBet(int bet){
        this.bet = bet;
    }

    public int getBet(){
        return bet;
    }

    public String getName(){
        return name;
    }

    public List<Card> getHand(){
        return hand;
    }

    public void sortHand(){
        Collections.sort(hand);
    }

    public void setScore(){
        if(Rules.hasPair(getHand())){
            score = 1;
        }
        if(Rules.hasTwoPairs(getHand())){
            score = 2;
        }
        if(Rules.hasThreeOfAKind(getHand())){
            score = 3;
        }
        if(Rules.hasStraight(getHand())){
            score = 4;
        }
        if(Rules.hasFlush(getHand())){
            score = 5;
        }
        if(Rules.hasFullHouse(getHand())){
            score = 6;
        }
        if(Rules.hasFourOfAKind(getHand())){
            score = 7;
        }
        if(Rules.hasStraightFlush(getHand())){
            score = 8;
        }
        if(Rules.hasRoyalStraightFlush(getHand())){
            score = 9;
        }
    }

    public int getScore(){
        return score;
    }

    public void setHighCard(){
        if(getScore() > 0){
            switch(getScore()){
                //Has one pair
                case(1):
                    highCard = getHighCardWithPairs(this.getHand());
                //Has two pairs
                case(2):
                    highCard =  getHighCardWithTwoPairs(this.getHand());
                //Has three of a kind
                case(3):
                    highCard = getHighCardWithThreeOfAKind(this.getHand());
                //Has straight
                case(4):
                    highCard = hand.get(hand.size()-1);
                //Has flush
                case(5):
                    highCard = hand.get(hand.size()-1);
                //Has full house
                case(6):
                    highCard = getHighCardWithFullHouse(this.getHand());
                    break;
                //Has four of a kind
                case(7):
                    highCard = getHighCardWithFourOfAKind(this.getHand());
                    break;
                //Has straight flush
                case(8):
                    highCard = hand.get(hand.size()-1);
                    break;
                //Has royal straight flush
                case(9):
                    highCard = hand.get(hand.size()-1);
                    break;
                default:
                    highCard = hand.get(hand.size()-1);
                    break;
            }
        }
    }

    public Card getHighCard(){
        return highCard;
    }

    public static Card getHighCardWithPairs(List<Card> hand){
        int index = 0;
        for(int i = 0; i < hand.size()-1; i++){
            if(hand.get(i).getValue() == hand.get(i+1).getValue()){
                index = i;
            }
        }
        return hand.get(index);
    }

    public static Card getHighCardWithTwoPairs(List<Card> hand){
        int index = 0;
        for(int i = 0; i < hand.size()-3; i++){
            if(hand.get(i).getValue() == hand.get(i+1).getValue()){
                for(int k = i + 1; k < hand.size()-1; k++){
                    if(hand.get(k).getValue() == hand.get(k+1).getValue()){
                        index = k;
                    }
                }
            }
        }
        return hand.get(index);
    }

    public static Card getHighCardWithThreeOfAKind(List<Card> hand){
        int index = 0;
        for(int i = 0; i < hand.size()-2; i++){
            if((hand.get(i).getValue() == hand.get(i+1).getValue()) &&
                    hand.get(i+1).getValue() == hand.get(i+2).getValue()){
                index = i;
            }
        }
        return hand.get(index);
    }

    public static Card getHighCardWithFullHouse(List<Card> hand){
        Card highCardFromPair = getHighCardWithPairs(hand);
        Card highCardFromThreeOfAKind = getHighCardWithThreeOfAKind(hand);
        if(highCardFromPair.getValue() > highCardFromThreeOfAKind.getValue()){
            return highCardFromPair;
        }
        else{
            return highCardFromThreeOfAKind;
        }
    }

    public static Card getHighCardWithFourOfAKind(List<Card> hand){
        int index = 0;
        for(int i = 0; i < hand.size()-3; i++){
            if(hand.get(i).getValue() == hand.get(i+1).getValue() &&
                    hand.get(i+1).getValue() == hand.get(i+2).getValue() &&
                    hand.get(i+2).getValue() == hand.get(i+3).getValue()){
                index = i;
            }
        }
        return hand.get(index);
    }

    public static List<Player> rankPlayers(List<Player> players){
        List<Player> rankedList = new ArrayList<Player>();

        for (Player player: players){
            player.setScore();
            player.setHighCard();

            if(rankedList.isEmpty()){
                rankedList.add(player);
            }
            else{
                for(Player rankedPlayer: rankedList){
                    if(player.getScore() >= rankedPlayer.getScore()){
                        rankedList.add(rankedList.indexOf(rankedPlayer),player);
                        break;
                    }
                }
                rankedList.add(player);
                continue;
            }
        }

        return rankedList;
    }

    public String toString(){
        String tmp = "";
        for(Card card: hand){
            tmp += card + "\n";
        }
        return tmp;
    }
}
