import java.util.Random;

public class Card {

    private final int Rank, Suit;
    private static final Random Generator = new Random();
    public static final String[] Ranks= {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};
    public static final String[] Suits= {"♣", "♦", "♥", "♠"};

    public Card(){
        Rank = Generator.nextInt(Ranks.length);
        Suit = Generator.nextInt(Suits.length);
    }

    public String getRank(){
        return Ranks[Rank];

    }

    public String getSuit(){
        return Suits[Suit];
    }

    public int getRankValue(){
        return Rank;
    }

    public int getSuitValue(){
        return Suit;
    }


    public String toString(){
        return getRank() + " of " + getSuit();
    }





}
