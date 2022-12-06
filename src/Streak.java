import java.util.Scanner;

public class Streak {
    static Scanner Input = new Scanner(System.in);
    static String Answer;
    static int Score;
    static int CardAmount;
    static String[] Players = new String[10];
    static int[] FinalScores = new int[10];
    static String Replay;
    static int p = 0;
    static int Replacing = 0;


    public static void Menu() {
        System.out.println("------------------Streak------------------");

        System.out.println("Please select an option from below");
        System.out.println("1. One Player");
        System.out.println("2. Two Player");
        System.out.println("3. High scores");
        System.out.println("4. Exit");
        Answer = Input.nextLine();
        if ("1".equals(Answer)) {
            System.out.println("You have selected 1 player");
            One_Player();
        }
        if ("2".equals(Answer)) {
            System.out.println("You have selected 2 player");
            Two_Player();
        }
        if ("3".equals(Answer)) {
            High_Scores();
        }
        if ("4".equals(Answer)) {
            System.exit(0);
        } else {
            System.out.println("Error, please enter an option from above");
            Menu();
        }
    }


    public static void Play() {
        Card myCards[] = new Card[CardAmount];
        for (int i = 0; i < CardAmount; i++) {
            myCards[i] = new Card();
        }
        Replay = "Your starting cards were:\n\n";
        Sort(myCards);
        for (int i = 0; i < myCards.length; i++) {
            for (int j = 1; j < myCards.length - 1; j++) {
                if (i == j) {
                    j++;
                } else if (myCards[i].getRankValue() == myCards[j].getRankValue()) {
                    myCards[j] = new Card();
                    j = 0;
                }
            }
        }

        Sort(myCards);
        for (int i = 0; i < myCards.length; i++) {
            System.out.println((i + 1) + " : " + myCards[i]);
            Replay += myCards[i] + "\n";
        }

        Scoring(myCards);
        System.out.println("Your score is: " + Score);

        Replay += "\nyour score here was " + Score + "\n";


        for (int i = 0; i < myCards.length; i++) {
            System.out.println(myCards.length - i + " amount of changes remaining. Would you like to change any of your cards? (Y/N)");
            Answer = Input.nextLine();
            if ("y".equals(Answer) | "Y".equals(Answer)) {
                replaceQuestion(myCards);
            } else if ("n".equals(Answer) | "N".equals(Answer)) {
                i = myCards.length + 1;
            } else {
                System.out.println("Please enter y/n");
                i = i - 1;
            }
            if (i == myCards.length) {
                System.out.println("your out of changes");
            }

        }


    }

    public static void One_Player() {
        System.out.print("Enter Player name >");
        String Name;
        Name = Input.nextLine();


        System.out.println("How many cards would you like to play, between 5 and 10?");
        Answer = Input.nextLine();
        int check = 0;
        while (check < 1) {
            if (!Answer.matches("[0-9]+")) {
                System.out.println("Invalid Please enter a number between 5 and 10");
                System.out.println("How many cards would you like to play, between 5 and 10?");
                Answer = Input.nextLine();
            }
            else {
                check=1;
            }
        }
        if ((Integer.parseInt(Answer) >= 5) && (Integer.parseInt(Answer) <= 10)) {
            CardAmount = Integer.parseInt(Answer);
        } else {
            System.out.println("Please enter between 5 and 10");
            One_Player();
        }
        Play();

        Players[p] = Name;
        FinalScores[p] = Score;
        p++;
        Sort_Scores();


        System.out.println("would you like to see a replay?(Y/N) >");
        Answer = Input.nextLine();
        if ("y".equals(Answer)) {
            System.out.println("---------------Replay---------------");

            System.out.println(Replay);
        }
        if ("n".equals(Answer)) {
            Menu();
        }
    }

    public static void Two_Player() {
        String Name1, Name2;
        System.out.print("Enter Player 1 name >");
        Name1 = Input.nextLine();
        System.out.print("Enter Player 2 name >");
        Name2 = Input.nextLine();
        int[] p1Score = new int[3];
        int[] p2Score = new int[3];


        System.out.println("How many cards would you like to play?(5-10)");
        Answer = Input.nextLine();

        if ((Integer.parseInt(Answer) >= 5) && (Integer.parseInt(Answer) <= 10)) {
            CardAmount = Integer.parseInt(Answer);
        } else {
            System.out.println("Please enter between 5 and 10");
            Two_Player();
        }
        for (int i = 0; i < 3; i++) {
            System.out.println("-------------" + Name1 + "-Hand-" + (i + 1) + "-------------");
            Play();
            Players[p] = Name1;
            FinalScores[p] = Score;
            p++;
            Sort_Scores();
            System.out.println("Player one final score is: " + Score);
            p1Score[i] = Score;
            System.out.println("-------------" + Name2 + "-Hand-" + (i + 1) + "-------------");
            Play();
            Players[p] = Name2;
            FinalScores[p] = Score;
            p++;
            Sort_Scores();
            System.out.println("Player two final score is: " + Score);
            p2Score[i] = Score;

        }
        System.out.println("-------------Final-Score--------------");
        int p1Final = 0;
        int p2Final = 0;
        for (int value : p1Score) {
            p1Final += value;
        }
        for (int value : p2Score) {
            p2Final += value;
        }
        System.out.println(Name1 + "final score is: " + p1Final);
        System.out.println(Name2 + "final score is: " + p2Final);
        if (p1Final > p2Final) {
            System.out.println("\n" + Name1 + "wins!");
        } else if (p1Final == p2Final) {
            System.out.println("it's a tie!");
        } else {
            System.out.println(Name2 + "wins!");
        }

        Menu();

    }

    public static void Sort_Scores() {
        int lastPos = Players.length - 1;
        int innerLastPos = lastPos;
        String tempPlayer;
        int tempScore;
        for (int i = 0; i < lastPos; i++) {
            for (int j = 0; j < innerLastPos; j++) {

                if (FinalScores[j] < FinalScores[j + 1]) {
                    tempScore = FinalScores[j];
                    FinalScores[j] = FinalScores[j + 1];
                    FinalScores[j + 1] = tempScore;
                    tempPlayer = Players[j];
                    Players[j] = Players[j + 1];
                    Players[j + 1] = tempPlayer;
                }
            }
            innerLastPos--;
        }
        for (int i = 5; i < 10; i++) {
            Players[i] = "";
            FinalScores[i] = 0;
        }
        p = 5;
    }

    public static void High_Scores() {
        Sort_Scores();
        System.out.println("------------High-scores------------");

        System.out.println("Player\t\tScore");
        for (int i = 0; i < 5; i++) {
            if (i < Players.length) {
                System.out.println(Players[i] + "\t\t" + FinalScores[i]);
            }
        }

        Menu();

    }

    public static Card[] Sort(Card[] myCards) {
        int lastPos = myCards.length - 1;
        int innerLastPos = lastPos;
        Card tempCard;
        for (int i = 0; i < lastPos; i++) {
            for (int j = 0; j < innerLastPos; j++) {

                if (myCards[j].getRankValue() > myCards[j + 1].getRankValue()) {
                    tempCard = myCards[j];
                    myCards[j] = myCards[j + 1];
                    myCards[j + 1] = tempCard;
                }
            }
            innerLastPos--;
        }
        return myCards;
    }

    public static Card[] replaceQuestion(Card[] myCards) {
        System.out.print("Please enter the number of the card you wish to change >");
        Answer = Input.nextLine();
        if ("1".equals(Answer)) {
            Replacing = 0;
            Replace(myCards);
        } else if ("2".equals(Answer)) {
            Replacing = 1;
            Replace(myCards);
        } else if ("3".equals(Answer)) {
            Replacing = 2;
            Replace(myCards);
        } else if ("4".equals(Answer)) {
            Replacing = 3;
            Replace(myCards);
        } else if ("5".equals(Answer)) {
            Replacing = 4;
            Replace(myCards);
        } else if ("6".equals(Answer)) {
            if (myCards.length >= 6) {
                Replacing = 5;
                Replace(myCards);
            } else {
                System.out.println("Error, please enter a valid card number");
                replaceQuestion(myCards);
            }
        } else if ("7".equals(Answer)) {
            if (myCards.length >= 7) {
                Replacing = 6;
                Replace(myCards);
            } else {
                System.out.println("Error, please enter a valid card number");
                replaceQuestion(myCards);
            }
        } else if ("8".equals(Answer)) {
            if (myCards.length >= 8) {
                Replacing = 7;
                Replace(myCards);
            } else {
                System.out.println("Error, please enter a valid card number");
                replaceQuestion(myCards);
            }
        } else if ("9".equals(Answer)) {
            if (myCards.length >= 9) {
                Replacing = 8;
                Replace(myCards);
            } else {
                System.out.println("Error, please enter a valid card number");
                replaceQuestion(myCards);
            }
        } else if ("10".equals(Answer)) {
            if (myCards.length >= 10) {
                Replacing = 9;
                Replace(myCards);
            } else {
                System.out.println("Error, please enter a valid card number");
                replaceQuestion(myCards);
            }
        } else {
            System.out.println("Error, please enter a valid card number");
            replaceQuestion(myCards);
        }
        return myCards;
    }

    public static Card[] Replace(Card[] myCards) {


        Card replacement = new Card();

        for (int i = 0; i < myCards.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (myCards[i].getRankValue() == replacement.getRankValue()) {
                    replacement = new Card();
                    j = 0;
                } else {
                    j = 1;
                }
            }

        }
        Replay += "\n" + myCards[Replacing] + " was replaced with " + replacement + "\n";
        myCards[Replacing] = replacement;
        Sort(myCards);
        Replay += "\nYour new cards were:\n";
        System.out.println("Your new cards are:");
        for (int i = 0; i < myCards.length; i++) {
            System.out.println((i + 1) + " : " + myCards[i]);
            Replay += myCards[i] + "\n";
        }
        Scoring(myCards);
        System.out.println("Your score is: " + Score);
        Replay += "with a new score of " + Score + "\n";

        return myCards;

    }

    public static int Scoring(Card[] myCards) {
        Score = 0;

        for (int i = 0; i < myCards.length; i++) {
            int Scoring = 1;
            int Counter = 0;
            int Same_Suit;
            int Red;
            int Black;

            Card Suit_Checker[] = new Card[myCards.length];
            for (int j = i; j <= myCards.length; j++) {
                int k = j;
                if (j == myCards.length - 1) {
                    k = j - 1;
                }
                Suit_Checker[Counter] = myCards[j];
                if (myCards[j].getRankValue() == myCards[k + 1].getRankValue() - 1) {
                    Scoring++;
                    Counter++;
                }
                if ((myCards[j].getRankValue() != myCards[k + 1].getRankValue() - 1) | j == myCards.length - 1) {
                    if (Counter >= 1) {
                        Red = 1;
                        Black = 1;
                        Same_Suit = 2;
                        for (int l = 0; l < Counter; l++) {

                            if (Suit_Checker[l].getSuitValue() != Suit_Checker[l + 1].getSuitValue()) {
                                Same_Suit = 0;
                            }
                            if (Suit_Checker[l].getSuitValue() == Suit_Checker[l + 1].getSuitValue()) {
                                Red = 0;
                                Black = 0;
                            }
                        }
                        for (int m = 0; m <= Counter; m++) {
                            if (Suit_Checker[m].getSuit() == "♥" | Suit_Checker[m].getSuit() == "♦") {
                                Black = 0;
                            }
                            if (Suit_Checker[m].getSuit() == "♣" | Suit_Checker[m].getSuit() == "♠") {
                                Red = 0;
                            }
                        }

                        int fScore = Scoring + Same_Suit + Red + Black;


                        if (fScore > Score) {
                            Score = fScore;
                        }

                    }
                    j = myCards.length + 1;

                }


            }

        }
        if (Score == 1) {
            Score = 0;
        }
        return Score;
    }

    public static void main(String[] args) {

        Menu();


    }
}
