import java.util.Random;

class Card {
    private String suit;
    private String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public String toString() {
        return rank + " of " + suit;
    }
}

class Node {
    public Card card;
    public Node next;

    public Node(Card card) {
        this.card = card;
        this.next = null;
    }

    public Card getCard() {
        return card;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

class LinkedListQueue {
    private Node front;
    private Node rear;

    public LinkedListQueue() {
        front = null;
        rear = null;
    }

    public void enqueue(Card card) {
        Node newNode = new Node(card);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
    }

    public Card dequeue() {
        if (isEmpty()) {
            return null;
        }
        Card card = front.getCard();
        front = front.getNext();
        if (front == null) {
            rear = null;
        }
        return card;
    }

    public boolean isEmpty() {
        return front == null;
    }
}

class DeckOfCards2 {
    private static final String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    private static final int NUM_CARDS = SUITS.length * RANKS.length;

    private Card[] deck;
    private Random random;

    public DeckOfCards2() {
        deck = new Card[NUM_CARDS];
        random = new Random();

        // Initialize the deck of cards
        int index = 0;
        for (String suit : SUITS) {
            for (String rank : RANKS) {
                deck[index] = new Card(suit, rank);
                index++;
            }
        }
    }

    public void shuffle() {
        for (int i = NUM_CARDS - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Card temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }
    }

    public Card[][] distributeCards(int numPlayers, int numCardsPerPlayer) {
        if (numPlayers * numCardsPerPlayer > NUM_CARDS) {
            throw new IllegalArgumentException("Not enough cards in the deck to distribute.");
        }

        Card[][] playersCards = new Card[numPlayers][numCardsPerPlayer];

        int cardIndex = 0;
        for (int player = 0; player < numPlayers; player++) {
            for (int card = 0; card < numCardsPerPlayer; card++) {
                playersCards[player][card] = deck[cardIndex];
                cardIndex++;
            }
        }

        return playersCards;
    }
}

class Player {
    private String name;
    private LinkedListQueue cards;

    public Player(String name) {
        this.name = name;
        this.cards = new LinkedListQueue();
    }

    public void receiveCards(Card[] playerCards) {
        for (Card card : playerCards) {
            cards.enqueue(card);
        }
    }

    public void sortByRank() {
        if (cards.isEmpty()) {
            return;
        }

        Node current = cards.dequeue();
        Node tail = current;
        while (current != null) {
            Node next = current.getNext();
            current.setNext(null);
            if (current.getCard().getRank().equals("Ace")) {
                tail.setNext(current);
                tail = current;
            } else {
                Node temp = cards.dequeue();
                Node prev = null;
                while (temp != null && temp.getCard().getRank().equals("Ace")) {
                    if (prev == null) {
                        cards.enqueue(temp.getCard());
                    } else {
                        prev.setNext(temp);
                    }
                    prev = temp;
                    temp = temp.getNext();
                    prev.setNext(null);
                }
                tail.setNext(current);
                tail = current;
                current = temp;
            }
        }
    }

    public void printCards() {
        System.out.println("Player: " + name);
        System.out.println("Cards: ");
        Node current = cards.dequeue();
        while (current != null) {
            System.out.println(current.getCard());
            cards.enqueue(current.getCard());
            current = current.getNext();
        }
        System.out.println();
    }
}

public class DeckOfCardsMain2 {
    private static final int NUM_PLAYERS = 4;
    private static final int NUM_CARDS_PER_PLAYER = 9;

    public static void main(String[] args) {
        DeckOfCards2 deckOfCards = new DeckOfCards2();

        // Shuffle the deck of cards
        deckOfCards.shuffle();

        // Distribute cards to players
        Card[][] playersCards = deckOfCards.distributeCards(NUM_PLAYERS, NUM_CARDS_PER_PLAYER);

        // Create players
        Player[] players = new Player[NUM_PLAYERS];
        for (int i = 0; i < NUM_PLAYERS; i++) {
            players[i] = new Player("Player " + (i + 1));
        }

        // Assign cards to players
        for (int player = 0; player < NUM_PLAYERS; player++) {
            players[player].receiveCards(playersCards[player]);
        }

        // Sort cards by rank for each player
        for (Player player : players) {
            player.sortByRank();
        }

        // Print the player names and cards
        for (Player player : players) {
            player.printCards();
        }
    }
}