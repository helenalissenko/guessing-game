package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.GuessingGame;

public class GuessingGameTest { 
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	
	private GuessingGame guessingGame;
	private int min;
	private int max;
	@Before 
	public void setup() {
		this.min = 1;
		this.max = 300;
		this.guessingGame = new GuessingGame(min, max);
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
	
	@Test
	public void testGuessingGame() {
		assertEquals(this.guessingGame.getAmountOfGuesses(), 0);
		assertEquals(this.guessingGame.getMax(), this.max);
		assertEquals(this.guessingGame.getMin(), this.min);
	}

	@Test
	public void testListenToUserInputTooBig() {
		this.guessingGame.reactToEnteredInteger(this.max + 1);
		assertEquals("The number I am thinking of is in the range between " + this.min + " and " + this.max, outContent.toString().trim());
	}

	@Test
	public void testListenToUserInputTooSmall() {
		this.guessingGame.reactToEnteredInteger(this.min - 1);
		assertEquals("The number I am thinking of is in the range between " + this.min + " and " + this.max, outContent.toString().trim());
	}
	
	@Test
	public void testListenToUserInputSmaller() {
		int guess = this.guessingGame.getMagicNumber() - 1;
		this.guessingGame.reactToEnteredInteger(guess);
		assertEquals("The number I am thinking of is bigger", outContent.toString().trim());
	}
	
	@Test
	public void testListenToUserInputBigger() {
		int guess = this.guessingGame.getMagicNumber() + 1;
		this.guessingGame.reactToEnteredInteger(guess);
		assertEquals("The number I am thinking of is smaller", outContent.toString().trim());
	}
	
	@Test
	public void testListenToUserInputCorrect() {
		this.guessingGame.reactToEnteredInteger(this.guessingGame.getMagicNumber());
		assertEquals("Congratulations! You guessed correctly! Amount of tries: 1", outContent.toString().trim());
	}
	
	@After
	public void cleanup() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}
}
