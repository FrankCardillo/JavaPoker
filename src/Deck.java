import java.util.*;

public class Deck {
	
	ArrayList<PlayingCard> deck = new ArrayList<PlayingCard>();
	
	public Deck() {
        int i = 0;
        
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(i++, new PlayingCard(suit, rank));
            }
        }
        this.shuffleDeck();
	}
    
	public boolean isEmpty() {
        
		return this.deck.isEmpty();
    }
	
	public void shuffleDeck() {
		Collections.shuffle(this.deck);
	}
	
	public void dealHand(Hand ourHand) {
		for (int i = 0; i < 5; i++) {
			ourHand.getHand().add(this.deck.get(i));
			this.deck.remove(i);
		}
	}
	
	public ArrayList<PlayingCard> getDeck() {
		return this.deck;
	}

}