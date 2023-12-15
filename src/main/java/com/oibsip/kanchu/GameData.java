package com.oibsip.kanchu;

public class GameData {

	private int secretNumber;
	private int attemptsLeft;
	private int roundsPlayed;
	private int currentRoundScore;
	private int totalScore;

	public int getSecretNumber() {
		return secretNumber;
	}

	public void setSecretNumber(int secretNumber) {
		this.secretNumber = secretNumber;
	}

	public int getAttemptsLeft() {
		return attemptsLeft;
	}

	public void setAttemptsLeft(int attemptsLeft) {
		this.attemptsLeft = attemptsLeft;
	}

	public int getRoundsPlayed() {
		return roundsPlayed;
	}

	public void setRoundsPlayed(int roundsPlayed) {
		this.roundsPlayed = roundsPlayed;
	}

	public int getCurrentRoundScore() {
		return currentRoundScore;
	}

	public void setCurrentRoundScore(int currentRoundScore) {
		this.currentRoundScore = currentRoundScore;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}


	@Override
	public String toString() {
		return "GameData [secretNumber=" + secretNumber + ", attemptsLeft=" + attemptsLeft + ", roundsPlayed="
				+ roundsPlayed + ", currentRoundScore=" + currentRoundScore + ", totalScore=" + totalScore + "]";
	}

}
