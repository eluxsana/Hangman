import java.util.Scanner;

public class Hangman {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		//Intro to the game
		System.out.println("Welcome to the game of Hangman. ");
		System.out.println("Please enter a single lowercase character to represent your guess.");
		System.out.println("You are able to input guesses until full hangman is drawn.");

		//The 2D array containing the words to be guessed
		//First column of the array stores the words
		String [][] words = new String [14][2];

		//Category selection
		System.out.println("Please choose either 1, 2, or 3 to choose topic.");
		System.out.println("Once you choose a category you can change it by re-running the program.");
		System.out.println("However your stats will reset.");
		System.out.println("1 - Canadian Cities");
		System.out.println("2 - Countries");
		System.out.println("3 - Animals");
		int topic = Integer.parseInt(input.nextLine());
		//If player inputs an invalid option
		while(topic < 1 || topic > 3) {
			System.out.println("Error!!! Please select a valid option.");
			System.out.println("1 - Canadian Cities");
			System.out.println("2 - Countries");
			System.out.println("3 - Animals");
			topic = Integer.parseInt(input.nextLine());           
		}
		if(topic == 1) {
			words [0][0] = "toronto";
			words [1][0] = "vancouver";
			words [2][0] = "calgary";
			words [3][0] = "edmonton";
			words [4][0] = "regina";
			words [5][0] = "saskatoon";
			words [6][0] = "london";
			words [7][0] = "hamilton";
			words [8][0] = "windsor";
			words [9][0] = "barrie";
			words [10][0] = "abbotsford";
			words [11][0] = "guelph";
			words [12][0] = "kitchener";
			words [13][0] = "waterloo";
		}
		else if(topic == 2) {
			words [0][0] = "afghanistan";
			words [1][0] = "belgium";
			words [2][0] = "canada";
			words [3][0] = "china";
			words [4][0] = "cuba";
			words [5][0] = "denmark";
			words [6][0] = "france";
			words [7][0] = "india";
			words [8][0] = "lithuania";
			words [9][0] = "netherlands";
			words [10][0] = "mexico";
			words [11][0] = "switzerland";
			words [12][0] = "uganda";
			words [13][0] = "zimbabwe";     
		}
		else {
			words [0][0] = "dog";
			words [1][0] = "bear";
			words [2][0] = "lion";
			words [3][0] = "tiger";
			words [4][0] = "cheetah";
			words [5][0] = "wolf";
			words [6][0] = "horse";
			words [7][0] = "cow";
			words [8][0] = "snake";
			words [9][0] = "rhinoceros";
			words [10][0] = "turtle";
			words [11][0] = "squirrel";
			words [12][0] = "lizard";
			words [13][0] = "giraffe";             
		}

		//Second column stores if the word has been already done
		for(int i = 0; i<words.length; i++){
			words [i][1] = "Not Done";
		}

		//If this variable is true the user wants to play another round
		boolean wantToContinue = true;
		int amountOfGuesses = 6;
		int gamesPlayed = 0; //Counter to keep track of games played
		int gamesWon = 0; //Counter to keep track of wins
		int attempts = 0; //Stores how much total guesses were entered

		do {

			//Where the player selects a city to guess
			System.out.println("Please choose an number from 1 to 14");
			int selection = (Integer.parseInt(input.nextLine()))-1;
			//If the number input is bigger or smaller than the array index it
			//displays an error message and the player is asked again
			while(selection>13 || selection<0) {
				System.out.println("Error!!! Please choose valid number(1-14).");
				selection = (Integer.parseInt(input.nextLine()))-1;                             
			}

			//Checks if the word already has been guessed right
			while(words[selection][1].equals("Done")) {
				System.out.println("You already guessed the word "+words[selection][0]+".");
				//This loop outputs all numbers that have not been done
				System.out.print("Number(s) that have not been answered correctly: ");
				for(int i = 0; i<words.length; i++) {
					if(words[i][1].equals("Not Done")) {
						System.out.print((i+1)+", ");
					}
				}
				//Keeps asking until a valid number input is given
				System.out.println();
				System.out.println("Please enter a valid number.");
				selection = (Integer.parseInt(input.nextLine()))-1;                             
			}

			//Player selects a difficulty to play on
			System.out.println("Please enter a difficulty (1, 2, or 3):");
			System.out.println("1 - Rookie: 9 incorrect guesses");
			System.out.println("2 - Casual: 6 incorrect guesses");
			System.out.println("3 - Extreme: 3 incorrect guesses");
			int level = Integer.parseInt(input.nextLine());
			//Asks again and keeps asking until valid int is entered
			while(level > 3 || level < 1) {
				System.out.println("Please enter a difficulty (1, 2, or 3):");
				System.out.println("1 - Rookie: 9 incorrect guesses");
				System.out.println("2 - Casual: 6 incorrect guesses");
				System.out.println("3 - Extreme: 3 incorrect guesses");
				level = Integer.parseInt(input.nextLine());
			}

			//Array that stores and turns the word to an char array Ex)"bob"--> 'b' 'o' 'b'
			char[] wordSelected = words[selection][0].toCharArray();
			//Stores how many characters long the selected word is
			int wordLength = wordSelected.length;
			//Makes and char array that is the same length as the word selected
			//This array is used to display either the _ or letter when guessed
			char[] playerGuess = new char [wordLength];
			//The maximum incorrect guesses
			if(level == 2) {
				amountOfGuesses = 6;
			}
			else if(level == 1) {
				amountOfGuesses = 9;
			}
			else if(level == 3) {
				amountOfGuesses = 3;
			}

			//Keeps track how much times the player input was incorrect
			int wrongGuesses = 0;

			//Loop that draws the _ to let the user know how long the word is 
			for(int i = 0; i<playerGuess.length; i++) {
				playerGuess[i]='_';
			}

			//This variable stores if the word has been guessed yet or not
			boolean wordGuessed = false;

			//Loop containing the actual game code that is repeated until the word is
			//correctly guessed or hangman is fully drawn
			while(wordGuessed == false && wrongGuesses < amountOfGuesses) {

				//Depending on the number of times an incorrect letter has been inputed it draws
				//a part of the hangman
				if(wrongGuesses == 0) {
					System.out.println("     _____");
					System.out.println("    |");
					System.out.println("    |");
					System.out.println("    |");
					System.out.println("    |");
					System.out.println("    |");
					System.out.println("    |");
					System.out.println("    |");
					System.out.println("    |");
					System.out.println("____|_____");         
				}
				else if((wrongGuesses == 1 && (level == 2 || level == 3)) || (wrongGuesses == 4 && level == 1)) {
					System.out.println("     _____");
					System.out.println("    |  __|__");
					System.out.println("    |  |. .| ");
					System.out.println("    |  |_0_|");
					System.out.println("    |");
					System.out.println("    |");
					System.out.println("    |");
					System.out.println("    |");
					System.out.println("    |");
					System.out.println("____|_____");
				}
				else if((wrongGuesses == 2 && level == 2) || (wrongGuesses == 5 && level == 1)) {
					System.out.println("     _____");
					System.out.println("    |  __|__");
					System.out.println("    |  |. .| ");
					System.out.println("    |  |_0_|");
					System.out.println("    |    |   ");
					System.out.println("    |    |   ");
					System.out.println("    |    |   ");
					System.out.println("    |");
					System.out.println("    |");
					System.out.println("____|_____");
				}
				else if((wrongGuesses == 3 && level == 2) || (wrongGuesses == 6 && level == 1)) {
					System.out.println("     _____");
					System.out.println("    |  __|__");
					System.out.println("    |  |. .| ");
					System.out.println("    |  |_0_|");
					System.out.println("    |    |   ");
					System.out.println("    |   \\|   ");
					System.out.println("    |    |   ");
					System.out.println("    |");
					System.out.println("    |");
					System.out.println("____|_____");
				}
				else if((wrongGuesses == 4 && level == 2) || (wrongGuesses == 7 && level == 1) || (wrongGuesses == 2 && level == 3)) {
					System.out.println("     _____");
					System.out.println("    |  __|__");
					System.out.println("    |  |. .| ");
					System.out.println("    |  |_0_|");
					System.out.println("    |    |   ");
					System.out.println("    |   \\|/   ");
					System.out.println("    |    |   ");
					System.out.println("    |");
					System.out.println("    |");
					System.out.println("____|_____");
				}
				else if((wrongGuesses == 5 && level == 2) || (wrongGuesses == 8 && level == 1)) {
					System.out.println("     _____");
					System.out.println("    |  __|__");
					System.out.println("    |  |. .| ");
					System.out.println("    |  |_0_|");
					System.out.println("    |    |   ");
					System.out.println("    |   \\|/   ");
					System.out.println("    |    |   ");
					System.out.println("    |   /");
					System.out.println("    |");
					System.out.println("____|_____");
				}
				else if(wrongGuesses == 1 && level == 1) {
					System.out.println("     _____");
					System.out.println("    |  __|__");
					System.out.println("    |  |   | ");
					System.out.println("    |  |_ _|");
					System.out.println("    |");
					System.out.println("    |");
					System.out.println("    |");
					System.out.println("    |");
					System.out.println("    |");
					System.out.println("____|_____");
				}
				else if(wrongGuesses == 2 && level == 1) {
					System.out.println("     _____");
					System.out.println("    |  __|__");
					System.out.println("    |  |.  | ");
					System.out.println("    |  |_ _|");
					System.out.println("    |");
					System.out.println("    |");
					System.out.println("    |");
					System.out.println("    |");
					System.out.println("    |");
					System.out.println("____|_____");
				}
				else {
					System.out.println("     _____");
					System.out.println("    |  __|__");
					System.out.println("    |  |. .| ");
					System.out.println("    |  |_ _|");
					System.out.println("    |");
					System.out.println("    |");
					System.out.println("    |");
					System.out.println("    |");
					System.out.println("    |");
					System.out.println("____|_____");
				}

				//The array is displayed 
				System.out.println("Current guesses: ");
				printArray(playerGuess);
				System.out.println();
				//Displays how much chances they have left
				System.out.println("You have "+(amountOfGuesses-wrongGuesses)+" chances left.");
				//User guess
				System.out.println("Enter a single letter to guess...");
				char guess = input.nextLine().charAt(0); //user guess
				attempts++; //Adds one to the variable <attempt> each time the player guesses 

				//The loop will go through each character of the word and try to find a match
				//When it does it will replace the underscore with the letter that should be there
				for(int i = 0; i < wordSelected.length; i++) {
					if(guess == wordSelected[i]) {
						playerGuess[i] = guess;
					}
				}
				//Increases the number of wrong guesses when an incorrect letter is inputed
				int counter = 0;
				for(int i = 0; i < wordSelected.length; i++) {
					if(guess != wordSelected[i]) {
						counter++;
					}
					if(counter == wordSelected.length) {
						wrongGuesses++;
					}
				}

				//When the word has been solved it stores that it has been done right
				//to prevent the user from selecting it again
				int gamesCompleted = 0;
				if(isTheWordFinished(playerGuess)) {
					words[selection][1] = "Done";
					for(int i = 0; i<words.length; i++) {
						if(words[i][1].equals("Done")) {
							gamesCompleted++;
						}
					}
				}

				//Only goes in here if all 14 cities have been guessed correctly
				//Displays a special congratulation message
				if(isTheWordFinished(playerGuess) && gamesCompleted == 14) {
					wordGuessed = true;
					gamesPlayed++;
					gamesWon++;
					if(topic == 1) {
						System.out.println("Congratulations! You have guessed all 14 cities correctly.");
					}
					else if(topic == 2) {
						System.out.println("Congratulations! You have guessed all 14 countries correctly.");
					}
					else {
						System.out.println("Congratulations! You have guessed all 14 animals correctly.");
					}
					System.out.println("You are a real pro, and there is nothing left to solve...");
					System.out.println("So, stay tuned for the next game realeased by A & S Entertainment.");
					wantToContinue = false;

				}
				//Goes here if a word has been done right but all 14 words are not done yet
				else if(isTheWordFinished(playerGuess) && gamesCompleted < 14) {
					wordGuessed = true;
					gamesPlayed++;
					gamesWon++;
					System.out.println("You guessed "+words[selection][0]+" correctly.");

					//Asks if the player wants to play another round, and keeps asking until either
					//a 'y','Y','n', or 'N' is inputed
					System.out.println("Do you want to play another game? (Y/N)");
					char response = input.nextLine().charAt(0);
					if(response == 'y'||response == 'Y') {
						wantToContinue = true;
					}
					else if(response == 'n'||response == 'N') {
						wantToContinue = false;
					}
					else {
						while(response != 'n' && response != 'N' && response != 'y' && response != 'Y') {
							System.out.println("Error!!! Please enter a valid character (Y/N)");
							response = input.nextLine().charAt(0);
						}
					}
				}

				//Goes in here if the word was not done right and asks player if he/she want to try again
				if(wordGuessed == false && ((level == 2 && wrongGuesses == 6) || (level == 1 && wrongGuesses == 9) || (level == 3 && wrongGuesses == 3))) {
					gamesPlayed++;
					System.out.println("     _____");
					System.out.println("    |  __|__");
					System.out.println("    |  |. .| ");
					System.out.println("    |  |_0_|");
					System.out.println("    |    |   ");
					System.out.println("    |   \\|/   ");
					System.out.println("    |    |   ");
					System.out.println("    |   / \\");
					System.out.println("    |");
					System.out.println("____|_____");

					System.out.println("You have "+(amountOfGuesses-wrongGuesses)+" chances left.");
					System.out.println("Too bad so sad! You failed.");

					//Asks if the player wants to play another round, and keeps asking until either
					//a 'y','Y','n', or 'N' is inputed
					System.out.println("Do you want to play another game? (Y/N)");
					char response = input.nextLine().charAt(0);
					if(response == 'y'||response == 'Y') {
						wantToContinue = true;
					}
					else if(response == 'n'||response == 'N') {
						wantToContinue = false;
					}
					else {
						while(response != 'n' && response != 'N' && response != 'y' && response != 'Y') {
							System.out.println("Error!!! Please enter a valid character (Y/N)");
							response = input.nextLine().charAt(0);
						}
					}
				}
			}

		} while(wantToContinue == true);

		//Calculates and rounds the win rate to one decimal
		double winRate = Math.round(((double)gamesWon/(double)gamesPlayed*100)*10)/10.0;
		//Calculates the average number of guesses inputed per game (rounded to one decimal)
		double avgGuesses = Math.round(((double)attempts/(double)gamesPlayed)*10)/10.0;
		//Displays the statistics and a "thank you" message for playing 
		System.out.println("Game Over!");
		System.out.println("Thanks for playing!!! You have entered "+attempts+" guesses today.");
		System.out.println("The average number of guesses you made per word: "+avgGuesses);
		System.out.println("You played "+gamesPlayed+" game(s), and you won "+gamesWon+".");
		System.out.println("Your win rate is "+winRate+"%");

	}

	//This method takes in a array as input and it outputs all the values of the array
	//in one line. This method was used to display the underscores and letters while
	//the user was guessing.
	public static void printArray(char[] array) {
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i]+" ");
		}
	}

	//This method uses an array as a parameter, but it returns a boolean. The method
	//goes through the array and will try to find an underscore, and if it does it will mean
	//the word is incomplete and will return false. However, if it does not find an underscore
	//in the array it will return true because that means all the places are letters and the
	//word has been guessed.
	public static boolean isTheWordFinished(char[] array) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == '_') {
				return false;
			}
		}
		return true;
	}

}