import java.util.*;

public class GameBoard {
	private static Deck gameDeck = new Deck();
	private static int pot = 0;
	private static Boolean gameFinished = false;
	private static Player player1 = new Player();
	private static Player player2 = new Player();
	private static int currentPlayer = 1;
	private static int currentRound = 1;
	private static int lastBet = 0;
	private static Boolean playerOneReady = false;
	
	private static Scanner name = new Scanner(System.in);
	private static Scanner name2 = new Scanner(System.in);
	private static Scanner input = new Scanner(System.in);
	private static Scanner cards = new Scanner(System.in);
	private static Scanner numbers = new Scanner(System.in);
	
	public static void addToPot(int bet) {
		pot += bet;
	}
	
	public static int getPot() {
		return pot;
	}
	
	public static void awardPotToWinner(Player winner) {
		winner.availableMoney += pot;
		pot = 0;
	}
	
	public static void startNewGame() {
		pot = 0;
		player1.playerHand.emptyHand(gameDeck);
		player2.playerHand.emptyHand(gameDeck);
		gameDeck.shuffleDeck();
		gameDeck.dealHand(player1.playerHand);
		gameDeck.dealHand(player2.playerHand);
	}
	
	public static void switchPlayer() {
		if (currentPlayer == 1) {
			currentPlayer += 1;
		}
		else if (currentPlayer == 2) {
			currentPlayer -= 1;
		}
	}
	
	public static void namePlayers() {
		System.out.println("Please enter your name, player 1.");
		String NAME = name.nextLine();
		player1.setName(NAME);
		System.out.println("Please enter your name, player 2.");
		String NAME2 = name2.nextLine();
		player2.setName(NAME2);
	}
	
	public static void roundOneP1() {
		System.out.println("Okay " + player1.getName() + " and " + player2.getName() + " it's time for the first round of betting.");
		System.out.println(player1.getName() + " these are the available commands. Please choose one now.");
		System.out.println("show hand, show last bet, call, check, raise, show pot, show available money, fold, show name");
		String option = input.nextLine().toLowerCase();
		switch(option) {
		case "show hand":
			player1.playerHand.showCards();
			break;
		case "show last bet":
			System.out.println("The last bet made was " + lastBet);
			break;
		case "call":
			player1.placeBet(lastBet);
			pot += lastBet;
			playerOneReady = true;
			GameBoard.switchPlayer();
			break;
		case "check":
			playerOneReady = true;
			GameBoard.switchPlayer();
			break;
		case "raise":
			System.out.println("How much would you like to raise by?");
			int option3 = Integer.parseInt(numbers.nextLine());
			if (option3 > lastBet) {
			player1.placeBet(option3);
			pot += option3;
			lastBet = option3;
			playerOneReady = false;
			GameBoard.switchPlayer();
			}
			else {
				System.out.println("That bet is not high enough. Please make a new bet.");
				playerOneReady = false;
			}
			break;
		case "show pot":
			System.out.println(GameBoard.getPot());
			break;
		case "show available money":
			System.out.println(player1.getAvailableMoney());
			break;
		case "fold":
			awardPotToWinner(player2);
			System.out.println(player2.getName() + " wins!");
			if (player1.availableMoney > 0) {
				currentRound = 1;
			}
			else {
				gameFinished = true;
			}
			break;
		case "show name":
			System.out.println(player1.getName());
			break;
		default:
			System.out.println("That is not a valid command. Please enter a valid command.");
			System.out.println("show hand, show last bet, call, check, raise, show pot, show available money, fold, show name");
			break;
		}
	}
	
	public static void roundOneP2() {
		System.out.println(player2.getName() + " these are the available commands. Please choose one now.");
		System.out.println("show hand, show last bet, call, check, raise, show pot, show available money, fold, show name");
		String option = input.nextLine().toLowerCase();
		switch(option) {
		case "show hand":
			player2.playerHand.showCards();
			break;
		case "show last bet":
			System.out.println("The last bet made was " + lastBet);
			break;
		case "call":
			player2.placeBet(lastBet);
			pot += lastBet;
			
			if (playerOneReady == true) {
				currentRound += 1;
			}
			GameBoard.switchPlayer();
			break;
		case "check":
			
			if (playerOneReady == true) {
				currentRound += 1;
			}
			GameBoard.switchPlayer();
			
			break;
		case "raise":
			System.out.println("How much would you like to raise by?");
			int option3 = Integer.parseInt(numbers.nextLine());
			if (option3 > lastBet) {
			player2.placeBet(option3);
			pot += option3;
			lastBet = option3;
			
			GameBoard.switchPlayer();
			}
			else {
				System.out.println("That bet is not high enough. Please make a new bet.");
				
			}
			break;
		case "show pot":
			System.out.println(GameBoard.getPot());
			break;
		case "show available money":
			System.out.println(player2.getAvailableMoney());
			break;
		case "fold":
			awardPotToWinner(player1);
			System.out.println(player1.getName() + " wins!");
			if (player2.availableMoney > 0) {
				currentRound = 1;
			}
			else {
				gameFinished = true;
			}
			break;
		case "show name":
			System.out.println(player2.getName());
			break;
		default:
			System.out.println("That is not a valid command. Please enter a valid command.");
			System.out.println("show hand, show last bet, call, check, raise, show pot, show available money, fold, show name");
			break;
		}
	}
	
	public static void roundTwoP1() {
		System.out.println("Okay " + player1.getName() + " and " + player2.getName() + " it's time to choose the cards you wish to discard.");
		System.out.println(player1.getName() + " these are the available commands. Please choose one now.");
		System.out.println("show hand, show last bet, discard cards, show pot, show available money, fold, show name");
		String option = input.nextLine().toLowerCase();
		switch(option) {
			case "discard cards":
				System.out.println("Which cards would you like to discard? Please refer to them as first, second, third, fourth, and fifth. Alternatively you can say none.");
				String option2 = cards.nextLine().toLowerCase();
				if (option2.contains("first")) {
					player1.playerHand.getHand().set(0, gameDeck.getDeck().get(0));
					gameDeck.getDeck().remove(0);
				}
				if (option2.contains("second")) {
					player1.playerHand.getHand().set(1, gameDeck.getDeck().get(0));
					gameDeck.getDeck().remove(0);
				}
				if (option2.contains("third")) {
					player1.playerHand.getHand().set(2, gameDeck.getDeck().get(0));
					gameDeck.getDeck().remove(0);
				}
				if (option2.contains("fourth")) {
					player1.playerHand.getHand().set(3, gameDeck.getDeck().get(0));
					gameDeck.getDeck().remove(0);
				}
				if (option2.contains("fifth")) {
					player1.playerHand.getHand().set(4, gameDeck.getDeck().get(0));
					gameDeck.getDeck().remove(0);
				}
				if (option2.contains("none")) {
				}
				playerOneReady = true;
				GameBoard.switchPlayer();
				break;
			case "show hand":
				player1.playerHand.showCards();
				break;
			case "show last bet":
				System.out.println("The last bet made was " + lastBet);
				break;
			case "show pot":
				System.out.println(GameBoard.getPot());
				break;
			case "show available money":
				System.out.println(player1.getAvailableMoney());
				break;
			case "fold":
				awardPotToWinner(player2);
				System.out.println(player2.getName() + " wins!");
				if (player1.availableMoney > 0) {
					currentRound = 1;
				}
				else {
					gameFinished = true;
				}
				break;
			case "show name":
				System.out.println(player1.getName());
				break;
			default:
				System.out.println("That is not a valid command. Please enter a valid command.");
				System.out.println("show hand, discard cards, show last bet, show pot, show available money, fold, show name");
				break;
		}
	}
	
	public static void roundTwoP2() {
		System.out.println(player2.getName() + " these are the available commands. Please choose one now.");
		System.out.println("show hand, show last bet, discard cards, show pot, show available money, fold, show name");
		String option = input.nextLine().toLowerCase();
		switch(option) {
			case "discard cards":
				System.out.println("Which cards would you like to discard? Please refer to them as first, second, third, fourth, and fifth. Alternatively you can say none.");
				String option2 = cards.nextLine().toLowerCase();
				if (option2.contains("first")) {
					player2.playerHand.getHand().set(0, gameDeck.getDeck().get(0));
					gameDeck.getDeck().remove(0);
				}
				if (option2.contains("second")) {
					player2.playerHand.getHand().set(1, gameDeck.getDeck().get(0));
					gameDeck.getDeck().remove(0);
				}
				if (option2.contains("third")) {
					player2.playerHand.getHand().set(2, gameDeck.getDeck().get(0));
					gameDeck.getDeck().remove(0);
				}
				if (option2.contains("fourth")) {
					player2.playerHand.getHand().set(3, gameDeck.getDeck().get(0));
					gameDeck.getDeck().remove(0);
				}
				if (option2.contains("fifth")) {
					player2.playerHand.getHand().set(4, gameDeck.getDeck().get(0));
					gameDeck.getDeck().remove(0);
				}
				if (option2.contains("none")) {
				}
				
				if (playerOneReady == true) {
					currentRound += 1;
				}
				GameBoard.switchPlayer();
				break;
			case "show hand":
				player2.playerHand.showCards();
				break;
			case "show last bet":
				System.out.println("The last bet made was " + lastBet);
				break;
			case "show pot":
				System.out.println(GameBoard.getPot());
				break;
			case "show available money":
				System.out.println(player2.getAvailableMoney());
				break;
			case "fold":
				awardPotToWinner(player1);
				System.out.println(player1.getName() + " wins!");
				if (player2.availableMoney > 0) {
					currentRound = 1;
				}
				else {
					gameFinished = true;
				}
				break;
			case "show name":
				System.out.println(player2.getName());
				break;
			default:
				System.out.println("That is not a valid command. Please enter a valid command.");
				System.out.println("show hand, discard cards, show last bet, show pot, show available money, fold, show name");
				break;
		}
	}
	
	public static void roundThreeP1() {
		System.out.println("Okay " + player1.getName() + " and " + player2.getName() + " it's time for the final round of betting.");
		System.out.println(player1.getName() + " these are the available commands. Please choose one now.");
		System.out.println("show hand, show last bet, call, check, raise, show pot, show available money, fold, show name");
		String option = input.nextLine().toLowerCase();
		switch(option) {
		case "show hand":
			player1.playerHand.showCards();
			break;
		case "show last bet":
			System.out.println("The last bet made was " + lastBet);
			break;
		case "call":
			player1.placeBet(lastBet);
			pot += lastBet;
			playerOneReady = true;
			GameBoard.switchPlayer();
			break;
		case "check":
			GameBoard.switchPlayer();
			playerOneReady = true;
			break;
		case "raise":
			System.out.println("How much would you like to raise by?");
			int option3 = Integer.parseInt(numbers.nextLine());
			if (option3 > lastBet) {
			player1.placeBet(option3);
			pot += option3;
			lastBet = option3;
			playerOneReady = false;
			GameBoard.switchPlayer();
			}
			else {
				System.out.println("That bet is not high enough. Please make a new bet.");
				playerOneReady = false;
			}
			break;
		case "show pot":
			System.out.println(GameBoard.getPot());
			break;
		case "show available money":
			System.out.println(player1.getAvailableMoney());
			break;
		case "fold":
			awardPotToWinner(player2);
			System.out.println(player2.getName() + " wins!");
			if (player1.availableMoney > 0) {
				currentRound = 1;
			}
			else {
				gameFinished = true;
			}
			break;
		case "show name":
			System.out.println(player1.getName());
			break;
		default:
			System.out.println("That is not a valid command. Please enter a valid command.");
			System.out.println("show hand, discard cards, show last bet, call, check, raise, show pot, show available money, fold, show name");
			break;
		}
	}
	
	public static void roundThreeP2() {
		System.out.println(player2.getName() + " these are the available commands. Please choose one now.");
		System.out.println("show hand, show last bet, call, check, raise, show pot, show available money, fold, show name");
		String option = input.nextLine().toLowerCase();
		switch(option) {
		case "show hand":
			player2.playerHand.showCards();
			break;
		case "show last bet":
			System.out.println("The last bet made was " + lastBet);
			break;
		case "call":
			player2.placeBet(lastBet);
			pot += lastBet;
			if (playerOneReady == true) {
				currentRound += 1;
			}
			GameBoard.switchPlayer();
			break;
		case "check":
			GameBoard.switchPlayer();
			if (playerOneReady == true) {
				currentRound += 1;
			}
			GameBoard.switchPlayer();
			break;
		case "raise":
			System.out.println("How much would you like to raise by?");
			int option3 = Integer.parseInt(numbers.nextLine());
			if (option3 > lastBet) {
			player2.placeBet(option3);
			pot += option3;
			lastBet = option3;
			GameBoard.switchPlayer();
			}
			else {
				System.out.println("That bet is not high enough. Please make a new bet.");
			}
			break;
		case "show pot":
			System.out.println(GameBoard.getPot());
			break;
		case "show available money":
			System.out.println(player2.getAvailableMoney());
			break;
		case "fold":
			awardPotToWinner(player1);
			System.out.println(player1.getName() + " wins!");
			if (player2.availableMoney > 0) {
				currentRound = 1;
			}
			else {
				gameFinished = true;
			}
			break;
		case "show name":
			System.out.println(player2.getName());
			break;
		default:
			System.out.println("That is not a valid command. Please enter a valid command.");
			System.out.println("show hand, discard cards, show last bet, call, check, raise, show pot, show available money, fold, show name");
			break;
		}
	}
	
	public static void roundFour() {
		if (Hand.declareWinner(player1.playerHand, player2.playerHand) == 1) {
			awardPotToWinner(player1);
			System.out.println(player1.getName() + " wins!");
			if (player2.availableMoney > 0) {
				startNewGame();
				currentRound = 1;
			}
			else {
				gameFinished = true;
			}
		}
		else {
			awardPotToWinner(player2);
			System.out.println(player2.getName() + " wins!");
			if (player1.availableMoney > 0) {
				startNewGame();
				currentRound = 1;
			}
			else {
				gameFinished = true;
			}
		}
	}
	
	public static void gameOver() {
		if (gameFinished == true) {
			int continuePlaying = 1;
			while(continuePlaying == 1) {
				// input
				System.out.println("Would you like to start a new game? Please type yes or no.");
				String option = input.nextLine().toLowerCase();
				switch(option) {
				case "yes":
					gameFinished = false;
					break;
				case "no":
					System.out.println("Thanks for playing! Goodbye!");
					continuePlaying -= 1;
					break;
				default:
					System.out.println("That is not a valid command. Please type yes or no.");
					break;
				}	
				input.close();
			}
		}
	name.close();
	name2.close();
	input.close();
	cards.close();
	numbers.close();
	}
	
	public static void playGame() {
		startNewGame();
		namePlayers();
		while (gameFinished == false) {
			if (currentRound == 1 && currentPlayer == 1) {
				roundOneP1();
			}
			else if (currentRound == 1 && currentPlayer == 2) {
				roundOneP2();
			}
			else if (currentRound == 2 && currentPlayer == 1) {
				roundTwoP1();
			}
			else if (currentRound == 2 && currentPlayer == 2) {
				roundTwoP2();
			}
			else if (currentRound == 3 && currentPlayer == 1) {
				roundThreeP1();
			}
			else if (currentRound == 3 && currentPlayer == 2) {
				roundThreeP2();
			}
			else {
				roundFour();
			}
		}
		gameOver();
	}
}