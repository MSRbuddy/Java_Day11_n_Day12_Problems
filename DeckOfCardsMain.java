import java.util.Arrays;
        import java.util.Random;

class DeckOfCards
{
    private static final String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    private static final int NUM_CARDS = SUITS.length * RANKS.length;

    private String[] deck;
    private Random random;

    public DeckOfCards()
    {
        deck = new String[NUM_CARDS];
        random = new Random();

        // Initialize the deck of cards
        int index = 0;
        for (String suit : SUITS)
        {
            for (String rank : RANKS)
            {
                deck[index] = rank + " of " + suit;
                index++;
            }
        }
    }

    public void shuffle()
    {
        for (int i = NUM_CARDS - 1; i > 0; i--)
        {
            int j = random.nextInt(i + 1);
            String temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }
    }

    public String[][] distributeCards(int numPlayers, int numCardsPerPlayer)
    {
        if (numPlayers * numCardsPerPlayer > NUM_CARDS)
        {
            throw new IllegalArgumentException("Not enough cards in the deck to distribute.");
        }

        String[][] playersCards = new String[numPlayers][numCardsPerPlayer];

        int cardIndex = 0;
        for (int player = 0; player < numPlayers; player++)
        {
            for (int card = 0; card < numCardsPerPlayer; card++)
            {
                playersCards[player][card] = deck[cardIndex];
                cardIndex++;
            }
        }

        return playersCards;
    }
}

public class DeckOfCardsMain
{
    public static void main(String[] args)
    {
        DeckOfCards deckOfCards = new DeckOfCards();

        // Shuffle the deck of cards
        deckOfCards.shuffle();

        // Distribute cards to players
        int numPlayers = 4;
        int numCardsPerPlayer = 9;
        String[][] playersCards = deckOfCards.distributeCards(numPlayers, numCardsPerPlayer);

        // Print the cards received by each player
        for (int player = 0; player < numPlayers; player++)
        {
            System.out.println("Player " + (player + 1) + " Cards:");
            System.out.println(Arrays.toString(playersCards[player]));
            System.out.println();
        }
    }
}