import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 * User: Geir
 * Date: 10/17/13
 * Time: 15:45
 * To change this template use File | Settings | File Templates.
 */
public class Game {

    public static void main(String[] args) throws IOException{
        ArrayList<Player> players = new ArrayList<Player>();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        Pile pile = new Pile();
        pile.shuffle();

        setPlayers(players, input);
        dealCards(players, pile);

        runTradeRound(players, pile);
        runBetRound(players, pile);

        players = Player.rankPlayers(players);

        announceWinner(players);

        input.close();
    }

    private static void announceWinner(ArrayList<Player> players) {
        if(players.get(0).getScore() == players.get(1).getScore()){
            switch (players.
                    get(0).
                    getHighCard().
                    compareTo(players.
                            get(1).
                            getHighCard()
                    )){
                case(-1):
                    System.out.println("The winner is " + players.get(1).getName());
                    break;
                case(0):
                    System.out.println("At least two players share the best score. No winner can be determined.");
                    break;
                case(1):
                    System.out.println("The winner is " + players.get(0).getName());
                    break;
            }
        }
        else{
            System.out.println("The winner is " + players.get(0).getName());
        }
    }

    private static void dealCards(ArrayList<Player> players, Pile pile){
        for(int j = 0; j < 5; j++){
            for(Player player: players){
                player.addCard(pile.drawCard());
                player.sortHand();
            }
        }
    }

    private static void setPlayers(ArrayList<Player> players, BufferedReader input) throws IOException{
        for(int i = 0; i < 4; i++){
            System.out.println("Input desired name: ");
            players.add(new Player(input.readLine()));
        }
    }

    private static void runTradeRound(ArrayList<Player> players, Pile pile) throws IOException{
        for(Player player: players){
            int numberToSwap;
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            ArrayList<Integer> cardToSwap = new ArrayList<Integer>();
            System.out.println("Turn: " + player.getName());
            System.out.println("Your current hand:");
            System.out.println(player);
            System.out.println("How many cards to swap out?");
            numberToSwap = Integer.parseInt(input.readLine());

            for(int i = 0; i < numberToSwap; i++){
                System.out.println("Using 1-5, select card to swap:");
                cardToSwap.add(Integer.parseInt(input.readLine()));
            }

            for(int i = 0; i < numberToSwap; i++){
                System.out.println("Removing card " + player.getHand().get(Collections.max(cardToSwap)-1));
                player.removeCard(Collections.max(cardToSwap)-1);
                System.out.println("Card removed");
                cardToSwap.remove(Collections.max(cardToSwap));
            }
            for(int i = 0; i < numberToSwap; i++){
                player.addCard(pile.drawCard());
            }
            player.sortHand();
            System.out.println("Current hand is now: ");
            System.out.println(player);
        }
    }

    private static void runBetRound(ArrayList<Player> players, Pile pile) throws IOException {
        boolean newBet = true;
        int playerBet = 0;
        int currentBet = 0;
        int choice;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        while(newBet){
            for(Player player: players){
                if(player.isFolded() != true){
                    printDefaultHeader(currentBet, player);
                    choice = Integer.parseInt(input.readLine());

                    switch(choice){
                        case(-1):
                            player.setFold(true);
                            break;
                        case(0):
                            if(player.getBet() == currentBet){
                                newBet = false;
                                break;
                            }
                            else{
                                System.out.println("You cannot check when your current bet does not match the table bet");
                                System.out.println("Rulebreaker. You automatically folded. Problem?");
                                player.setFold(true);
                                break;
                            }
                        case(1):
                            System.out.println("Write your bet. Must be at least " + currentBet + " or higher");
                            playerBet = Integer.parseInt(input.readLine());
                            if(playerBet < currentBet){
                                System.out.println("Bet not high enough. Rulebreaker. You automatically folded. Problem?");
                                player.setFold(true);
                                break;
                            }
                            else{
                                currentBet = playerBet;
                                player.setBet(playerBet);
                                newBet = true;
                            }
                            break;
                    }
                }
            }
        }
    }





    private static void printDefaultHeader(int currentBet, Player player) {
        System.out.println("Turn: " + player.getName());
        System.out.println(player);
        System.out.println("Current bet is: " + currentBet);
        System.out.println("Your current bet is: " + player.getBet());
        System.out.println("Will you: Check(0), See/Raise(1) or fold(-1)?");
    }
}
