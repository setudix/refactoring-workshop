package workshop;

import java.util.ArrayList;
import java.util.LinkedList;

public class TriviaGame {
    ArrayList players = new ArrayList();
    int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public TriviaGame() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast(createRockQuestion(i));
        }
    }

    public String createRockQuestion(int index) {
        return "Rock Question " + index;
    }


    public boolean add(String playerName) {


        players.add(playerName);
        places[howManyPlayers()] = 0;
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;

        announce(playerName + " was added");
        announce("They are player number " + players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        announce(players.get(currentPlayer) + " is the current player");
        announce("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
            if (isRollOdd(roll)) {
                isGettingOutOfPenaltyBox = true;

                announce(players.get(currentPlayer) + " is getting out of the penalty box");
                incrementPlaces(roll);
                if (needToReducePlaces(currentPlayer))
                    reducePlaces();

                announce(players.get(currentPlayer)
                        + "'s new location is "
                        + places[currentPlayer]);
                announce("The category is " + currentCategory());
                askQuestion();
            } else {
                announce(players.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            incrementPlaces(roll);
            if (needToReducePlaces(currentPlayer))
                reducePlaces();

            announce(players.get(currentPlayer)
                    + "'s new location is "
                    + places[currentPlayer]);
            announce("The category is " + currentCategory());
            askQuestion();
        }

    }
    private boolean isRollOdd(int roll){
        if (roll % 2 != 0)
            return true;
        else
            return false;
    }
    private boolean needToReducePlaces(int currentPlayer){
        return places[currentPlayer] > 11;
    }
    private void incrementPlaces(int increment){
        places[currentPlayer] += increment;
    }
    private void reducePlaces(){
        places[currentPlayer] = places[currentPlayer] - 12;
    }
    private void askQuestion() {
        if (currentCategory() == "Pop")
            announce(popQuestions.removeFirst());
        if (currentCategory() == "Science")
            announce(scienceQuestions.removeFirst());
        if (currentCategory() == "Sports")
            announce(sportsQuestions.removeFirst());
        if (currentCategory() == "Rock")
            announce(rockQuestions.removeFirst());
    }


    private String currentCategory() {
        if (isPop())
            return "Pop";
        else if (isScience())
            return "Science";
        else if (isSports())
            return "Sports";
        else
            return "Rock";
    }
    private boolean isPop(){
        if (places[currentPlayer] % 4 == 0)
            return true;
        else
            return false;
    }
    private boolean isScience(){
        if (places[currentPlayer] % 4 == 1)
            return true;
        else
            return false;
    }
    private boolean isSports(){
        if (places[currentPlayer] % 4 == 2)
            return true;
        else
            return false;
    }


    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                announce("Answer was correct!!!!");
                purses[currentPlayer]++;
                announce(players.get(currentPlayer)
                        + " now has "
                        + purses[currentPlayer]
                        + " Gold Coins.");

                boolean winner = didPlayerWin();
                incrementCurrentPlayer();
                if (currentPlayer == players.size()) currentPlayer = 0;

                return winner;
            } else {
                incrementCurrentPlayer();
                if (currentPlayer == players.size()) currentPlayer = 0;
                return true;
            }


        } else {

            announce("Answer was correct!!!!");
            purses[currentPlayer]++;
            announce(players.get(currentPlayer)
                    + " now has "
                    + purses[currentPlayer]
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            incrementCurrentPlayer();
            if (currentPlayer == players.size()) currentPlayer = 0;

            return winner;
        }
    }

    public boolean wrongAnswer() {
        announce("Question was incorrectly answered");
        announce(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        incrementCurrentPlayer();
        if (isPlayerSizedReached()) currentPlayer = 0;
        return true;
    }
    private boolean isPlayerSizedReached(){
        return currentPlayer == players.size();
    }
    private void incrementCurrentPlayer(){
        currentPlayer++;
    }
    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == 6);
    }

    protected void announce(Object message) {
        System.out.println(message);
    }
}