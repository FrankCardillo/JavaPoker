import java.util.*;

public class Hand{
	
	private ArrayList<PlayingCard> hand = new ArrayList<PlayingCard>(5);
	
	public Hand() {
		
	}
	
	public void showCards() {
		for (int i = 0; i < 5; i++) {
			System.out.println(this.hand.get(i));
		}
	}
	
	
	public void emptyHand(Deck deck) {
		for (int i = 0; i < this.hand.size(); i++) {
			deck.deck.add(this.hand.get(i));
		}
		this.hand.clear();
	}
	
	public ArrayList<PlayingCard> getHand() {
		return this.hand;
	}
	
	public ArrayList<PlayingCard> sortHand() {
		Collections.sort(this.getHand());
		return this.hand;
	}
	
	public Boolean isFlush() {
		Boolean flushCase = this.getHand().get(0).getSuit().ordinal() == this.getHand().get(1).getSuit().ordinal() && 
				this.getHand().get(0).getSuit().ordinal() == this.getHand().get(2).getSuit().ordinal() && 
				this.getHand().get(0).getSuit().ordinal() == this.getHand().get(3).getSuit().ordinal() && 
				this.getHand().get(0).getSuit().ordinal() == this.getHand().get(4).getSuit().ordinal();
		
		return flushCase;
	}
	
	public Boolean isStraight() {
		// index 1 = index 0 (+1)
		Boolean firstCheck = this.getHand().get(1).getRank().ordinal() == (this.getHand().get(0).getRank().ordinal() + 1);
		
		// index 2 = index 1 (+1)
		Boolean secondCheck = this.getHand().get(2).getRank().ordinal() == (this.getHand().get(1).getRank().ordinal() + 1);
		
		// index 3 = index 2 (+1)
		Boolean thirdCheck = this.getHand().get(3).getRank().ordinal() == (this.getHand().get(2).getRank().ordinal() + 1);
		
		// index 4 = index 3 (+1)
		Boolean fourthCheck = this.getHand().get(4).getRank().ordinal() == (this.getHand().get(3).getRank().ordinal() + 1);
		
		return (firstCheck && secondCheck && thirdCheck && fourthCheck);
	}
	
	public Boolean isStraightFlush() {
		return (this.isFlush() && this.isStraight());
	}
	
	public Boolean isRoyalFlush() {
		return (this.isFlush() && this.isStraight() && this.getHand().get(4).getRank().ordinal() == 12);
	}
	
	public Boolean isFourOfAKind() {
		// [p, p, p, p, n]
		Boolean firstCase = this.getHand().get(0).getRank().ordinal() == this.getHand().get(1).getRank().ordinal() && 
				this.getHand().get(0).getRank().ordinal() == this.getHand().get(2).getRank().ordinal() && 
				this.getHand().get(0).getRank().ordinal() == this.getHand().get(3).getRank().ordinal() && 
				this.getHand().get(0).getRank().ordinal() != this.getHand().get(4).getRank().ordinal();
		
		// [n, p, p, p, p]
		Boolean secondCase = this.getHand().get(1).getRank().ordinal() == this.getHand().get(2).getRank().ordinal() && 
				this.getHand().get(1).getRank().ordinal() == this.getHand().get(3).getRank().ordinal() && 
				this.getHand().get(1).getRank().ordinal() == this.getHand().get(4).getRank().ordinal() && 
				this.getHand().get(1).getRank().ordinal() != this.getHand().get(0).getRank().ordinal();
		
		return (firstCase || secondCase);
	}
	
	public Boolean isFullHouse() {
		// [p1, p1, p1, p2, p2]
		Boolean firstCase = this.getHand().get(0).getRank().ordinal() == this.getHand().get(1).getRank().ordinal() && 
				this.getHand().get(0).getRank().ordinal() == this.getHand().get(2).getRank().ordinal() && 
				this.getHand().get(0).getRank().ordinal() != this.getHand().get(3).getRank().ordinal() && 
				this.getHand().get(3).getRank().ordinal() == this.getHand().get(4).getRank().ordinal();
		
		// [p2, p2, p1, p1, p1]
		Boolean secondCase = this.getHand().get(0).getRank().ordinal() == this.getHand().get(1).getRank().ordinal() && 
				this.getHand().get(0).getRank().ordinal() != this.getHand().get(2).getRank().ordinal() && 
				this.getHand().get(2).getRank().ordinal() == this.getHand().get(3).getRank().ordinal() && 
				this.getHand().get(2).getRank().ordinal() == this.getHand().get(4).getRank().ordinal();
		
		return (firstCase || secondCase);
	}
	
	public Boolean isThreeOfAKind() {
		// [p, p, p, n, n]
		Boolean firstCase = this.getHand().get(0).getRank().ordinal() == this.getHand().get(1).getRank().ordinal() && 
				this.getHand().get(0).getRank().ordinal() == this.getHand().get(2).getRank().ordinal() && 
				this.getHand().get(0).getRank().ordinal() != this.getHand().get(3).getRank().ordinal() && 
				this.getHand().get(0).getRank().ordinal() != this.getHand().get(4).getRank().ordinal();
		
		// [n, p, p, p, n]
		Boolean secondCase = this.getHand().get(1).getRank().ordinal() == this.getHand().get(2).getRank().ordinal() && 
				this.getHand().get(1).getRank().ordinal() == this.getHand().get(3).getRank().ordinal() && 
				this.getHand().get(1).getRank().ordinal() != this.getHand().get(0).getRank().ordinal() && 
				this.getHand().get(1).getRank().ordinal() != this.getHand().get(4).getRank().ordinal();
		
		// [n, n, p, p, p]
		Boolean thirdCase = this.getHand().get(2).getRank().ordinal() == this.getHand().get(3).getRank().ordinal() && 
				this.getHand().get(2).getRank().ordinal() == this.getHand().get(4).getRank().ordinal() && 
				this.getHand().get(2).getRank().ordinal() != this.getHand().get(0).getRank().ordinal() && 
				this.getHand().get(2).getRank().ordinal() != this.getHand().get(1).getRank().ordinal();
		
		return (firstCase || secondCase || thirdCase);
	}
	
	public Boolean isTwoPair() {
		// [p1, p1, p2, p2, n]
		Boolean firstCase = this.getHand().get(0).getRank().ordinal() == this.getHand().get(1).getRank().ordinal() && 
				this.getHand().get(2).getRank().ordinal() == this.getHand().get(3).getRank().ordinal() && 
				this.getHand().get(0).getRank().ordinal() != this.getHand().get(2).getRank().ordinal() && 
				this.getHand().get(0).getRank().ordinal() != this.getHand().get(4).getRank().ordinal() &&
				this.getHand().get(2).getRank().ordinal() != this.getHand().get(4).getRank().ordinal();
		
		// [p1, p1, n, p2, p2]
		Boolean secondCase = this.getHand().get(0).getRank().ordinal() == this.getHand().get(1).getRank().ordinal() && 
				this.getHand().get(3).getRank().ordinal() == this.getHand().get(4).getRank().ordinal() && 
				this.getHand().get(0).getRank().ordinal() != this.getHand().get(2).getRank().ordinal() && 
				this.getHand().get(0).getRank().ordinal() != this.getHand().get(4).getRank().ordinal() &&
				this.getHand().get(2).getRank().ordinal() != this.getHand().get(4).getRank().ordinal();
		
				
		// [n, p1, p1, p2, p2]
		Boolean thirdCase = this.getHand().get(1).getRank().ordinal() == this.getHand().get(2).getRank().ordinal() && 
				this.getHand().get(3).getRank().ordinal() == this.getHand().get(4).getRank().ordinal() && 
				this.getHand().get(0).getRank().ordinal() != this.getHand().get(2).getRank().ordinal() && 
				this.getHand().get(0).getRank().ordinal() != this.getHand().get(4).getRank().ordinal() &&
				this.getHand().get(1).getRank().ordinal() != this.getHand().get(3).getRank().ordinal();
		
		return (firstCase || secondCase || thirdCase);
	}
	
	public Boolean isPair() {
		// [p, p, n, n, n]
		Boolean firstCase = this.getHand().get(0).getRank().ordinal() == this.getHand().get(1).getRank().ordinal() && 
				this.getHand().get(0).getRank().ordinal() != this.getHand().get(2).getRank().ordinal() && 
				this.getHand().get(0).getRank().ordinal() != this.getHand().get(3).getRank().ordinal() && 
				this.getHand().get(0).getRank().ordinal() != this.getHand().get(4).getRank().ordinal();
		
		// [n, p, p, n, n]
		Boolean secondCase = this.getHand().get(1).getRank().ordinal() == this.getHand().get(2).getRank().ordinal() && 
				this.getHand().get(1).getRank().ordinal() != this.getHand().get(0).getRank().ordinal() && 
				this.getHand().get(1).getRank().ordinal() != this.getHand().get(3).getRank().ordinal() && 
				this.getHand().get(1).getRank().ordinal() != this.getHand().get(4).getRank().ordinal();
		
		// [n, n, p, p, n]
		Boolean thirdCase = this.getHand().get(2).getRank().ordinal() == this.getHand().get(3).getRank().ordinal() && 
				this.getHand().get(2).getRank().ordinal() != this.getHand().get(0).getRank().ordinal() && 
				this.getHand().get(2).getRank().ordinal() != this.getHand().get(1).getRank().ordinal() && 
				this.getHand().get(2).getRank().ordinal() != this.getHand().get(4).getRank().ordinal();
		
		// [n, n, n, p, p]
		Boolean fourthCase = this.getHand().get(3).getRank().ordinal() == this.getHand().get(4).getRank().ordinal() && 
				this.getHand().get(3).getRank().ordinal() != this.getHand().get(0).getRank().ordinal() && 
				this.getHand().get(3).getRank().ordinal() != this.getHand().get(1).getRank().ordinal() && 
				this.getHand().get(3).getRank().ordinal() != this.getHand().get(2).getRank().ordinal();
		
		return (firstCase || secondCase || thirdCase || fourthCase);
	}
	
	public Boolean isHighCard() {
		Boolean checkAllOtherHands = this.isPair() || this.isTwoPair() || this.isThreeOfAKind() || 
				this.isFourOfAKind() || this.isStraight() || this.isFlush() || this.isRoyalFlush() || 
				this.isFullHouse() || this.isStraightFlush();
		
		return checkAllOtherHands;
	}
	
	public int scoreHand() {
		if (this.isRoyalFlush() == true) {
			return 10;
		}
		else if (this.isStraightFlush() == true) {
			return 9;
		}
		else if (this.isFourOfAKind() == true) {
			return 8;
		}
		else if (this.isFullHouse() == true) {
			return 7;
		}
		else if (this.isFlush() == true) {
			return 6;
		}
		else if (this.isStraight() == true) {
			return 5;
		}
		else if (this.isThreeOfAKind() == true) {
			return 4;
		}
		else if (this.isTwoPair() == true) {
			return 3;
		}
		else if (this.isPair() == true) {
			return 2;
		}
		else if (this.isHighCard() == true) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
	public static int declareWinner(Hand hand1, Hand hand2) {
		if (hand1.scoreHand() > hand2.scoreHand()) {
			return 1;
		}
		else if (hand1.scoreHand() < hand2.scoreHand()) {
			return 2;
		}
		else {
			int handOneRankTotal = 0;
			int handTwoRankTotal = 0;
			int handOneSuitTotal = 0;
			int handTwoSuitTotal = 0;
			for (int i = 0; i < hand1.getHand().size(); i++) {
				if (hand1.getHand().get(i).getRank().ordinal() > hand2.getHand().get(i).getRank().ordinal()) {
					handOneRankTotal += 1;
				}
				else if (hand2.getHand().get(i).getRank().ordinal() > hand1.getHand().get(i).getRank().ordinal()) {
					handTwoRankTotal += 1;
				}
				else {
					if (hand1.getHand().get(i).getSuit().ordinal() > hand2.getHand().get(i).getSuit().ordinal()) {
						handOneSuitTotal += 1;
					}
					else {
						handTwoSuitTotal += 1;
					}
				}
			}
			if (handOneRankTotal > handTwoRankTotal) {
				return 1;
			}
			else if (handTwoRankTotal > handOneRankTotal) {
				return 2;
			}
			else {
				if (handOneSuitTotal > handTwoSuitTotal) {
					return 1;
				}
				else {
					return 2;
				}
			}
		}
	}
	
}