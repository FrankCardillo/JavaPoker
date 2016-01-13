public class Player {
	
	Hand playerHand = new Hand();
	int availableMoney;
	String playerName;
	
	Player() {
		this.availableMoney = 100;
	}
	
	public String getName() {
		return this.playerName;
	}
	
	public void setName(String name) {
		this.playerName = name;
	}
	
	public int getAvailableMoney() {
		return this.availableMoney;
	}
	
	public Hand getPlayerHand() {
		return this.playerHand;
	}
	
	public void placeBet(int wager) {
		int newAmount = this.availableMoney - wager;
		if (newAmount >= 0) {
			this.availableMoney = newAmount;
		}
		else {
			System.out.println("You don't have enough money to place that bet. Make a smaller bet.");
		}
	}
	
}