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
		String returnString = this.getRank().toString() + " of " + this.getSuit().toString();
		return returnString;
	}
	
	public static boolean compareRank(PlayingCard card1, PlayingCard card2) {
		if (card1.getRank().ordinal() != card2.getRank().ordinal()) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public static boolean compareSuit(PlayingCard card1, PlayingCard card2) {
		if (card1.getSuit().ordinal() != card2.getSuit().ordinal()) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public int compareTo(PlayingCard card2) {
		if (this.getRank().ordinal() < card2.getRank().ordinal()) {
			return -1;
		}
		
		else if (this.getRank().ordinal() > card2.getRank().ordinal()) {
			return 1;
		}
		
		else {
			return 0;
		}
	}
}

