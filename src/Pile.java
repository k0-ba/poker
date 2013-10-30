import java.util.ArrayList;
import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 * User: Geir
 * Date: 10/17/13
 * Time: 15:46
 * To change this template use File | Settings | File Templates.
 */
public class Pile {
    private ArrayList<Card> pile;

    public Pile(){
        pile = new ArrayList<Card>();
        for(Suits suit: Suits.values()){
            for(int i = 2; i <= 14; i++){
                pile.add(new Card(suit,i));
            }
        }
    }

    public ArrayList<Card> getPile(){
        return this.pile;
    }

    public Card drawCard(){
        return pile.remove(0);
    }

    public void shuffle(){
        Collections.shuffle(pile);
    }

    public String toString(){
        String tmp = "";
        for(Card card: pile){
            tmp += card.toString() + "\n";
        }
        return tmp;
    }
}
