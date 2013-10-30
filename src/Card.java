/**
 * Created with IntelliJ IDEA.
 * User: Geir
 * Date: 10/17/13
 * Time: 15:45
 * To change this template use File | Settings | File Templates.
 */
public class Card implements Comparable<Card>{
    private Suits suit;
    private int value;

    public Card(Suits suit, int value){
        this.suit = suit;
        this.value = value;
    }

    public Suits getSuit(){
        return this.suit;
    }

    public int getValue(){
        return this.value;
    }

    public String toString(){
        switch(value){
            case(11):
                return "JACK of " + this.suit + "S";
            case(12):
                return "QUEEN of " + this.suit + "S";
            case(13):
                return "KING of " + this.suit + "S";
            case(14):
                return "ACE of " + this.suit + "S";
            default:
                break;
        }
        return this.value + " of " + this.suit + "S";
    }

    @Override
    public int compareTo(Card card){
        if(this.value < card.value){
            return -1;
        }
        else if(this.value == card.value){
            return 0;
        }
        else{
            return 1;
        }
    }
}
