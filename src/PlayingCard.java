public class PlayingCard implements Comparable<PlayingCard>{
	
	private Suit suit;
	private Rank rank;
	
	public PlayingCard(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	public Suit getSuit() {
		return this.suit;
	}
	
	public Rank getRank() {
		return this.rank;
	}
	
	// @override
	public String toString() {
		String yourCard = this.getRank().toString() + " of " + this.getSuit().toString();
		return yourCard;
	}
	
	public int compareTo(PlayingCard card2) {
		if (this.getRank().ordinal() < card2.getRank().ordinal()) {
			return -1;
		}
		
		else if (this.getRank().ordinal() > card2.getRank().ordinal()) {
			return 1;
		}
		
		else {
			if (this.getSuit().ordinal() > card2.getSuit().ordinal()) {
				return 1;
			}
			else if (this.getSuit().ordinal() < card2.getSuit().ordinal()) {
				return -1;
			}
			else {
				return 0;
			}
		}
	}
}

