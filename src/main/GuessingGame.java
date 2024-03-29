package main;

import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    private int min;
    private int max;
    private int magicNumber;
    private int amountOfGuesses;

    public GuessingGame(int min, int max) {
    	this.min = min;
    	this.max = max;
        this.magicNumber = this.getRandomNumberInRange();
        this.amountOfGuesses = 0;
    }

    public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public int getMagicNumber() {
		return magicNumber;
	}

	public int getAmountOfGuesses() {
		return amountOfGuesses;
	}

	private int getRandomNumberInRange() {
        Random random = new Random();
        return random.nextInt((this.max - this.min) + 1) + this.min;
    }
	
	public boolean reactToEnteredInteger(int number) {
		this.amountOfGuesses++;
        if (number > this.max || number < this.min) {
            System.out.println(
                    "The number I am thinking of is in the range between " + this.min + " and " + this.max);
        } else if (number == this.magicNumber) {
            System.out.println("Congratulations! You guessed correctly! Amount of tries: " + this.amountOfGuesses);
            return true;
        } else if (number < this.magicNumber) {
            System.out.println("The number I am thinking of is bigger");
        } else if (number > this.magicNumber) {
            System.out.println("The number I am thinking of is smaller");
        }
        return false;
	}

    public void listenToUserInput() {
        Scanner userInputScanner = new Scanner(System.in);
        while (true) {
            if (userInputScanner.hasNextInt()) {
                int userInput = userInputScanner.nextInt();
    
                if (this.reactToEnteredInteger(userInput)) {
                	break;
                };
            } else {
                String userInput = userInputScanner.nextLine();
                if (userInput.equals("exit")) {
                    break;
                } else {
                    System.out.println("You need to enter an integer!");
                }
            }
        }
        userInputScanner.close();
    }

    public void start() {
        System.out.println("I am thinking of a number between 1 and 300");
        System.out.println("Can you guess what it is?");
        System.out.println("Type 'exit' to give up and end the game");
        this.listenToUserInput();
    }

}