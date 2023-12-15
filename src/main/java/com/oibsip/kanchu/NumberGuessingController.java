package com.oibsip.kanchu;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("gameData")
public class NumberGuessingController {

    private static final int MAX_ATTEMPTS = 3;
    private static final int MAX_ROUNDS = 5;

    private int generateRandomNumber() {
        return (int) (Math.random() * 100) + 1;
    }

    private void initializeModel(Model model) {
        GameData gameData = new GameData();
        gameData.setRoundsPlayed(0);
        gameData.setTotalScore(0);
        gameData.setSecretNumber(generateRandomNumber());

        model.addAttribute("message", "Welcome! Can you guess the secret number?");
        model.addAttribute("gameData", gameData);
    }

    @GetMapping("/new-game")
    public String startNewGame(Model model) {
        GameData gameData = (GameData) model.getAttribute("gameData");

        if (isGameOver(gameData)) {
            return "game-over";
        }

        gameData.setCurrentRoundScore(0);
        gameData.setAttemptsLeft(MAX_ATTEMPTS);
        initializeModel(model);

        return "index";
    }

    @GetMapping("/exit")
    public String exit() {
        return "exit";
    }

    @PostMapping("/guess")
    public String handleGuess(int guess, Model model) {
        GameData gameData = (GameData) model.getAttribute("gameData");

        if (gameData.getAttemptsLeft() > 0) {
            gameData.setAttemptsLeft(gameData.getAttemptsLeft() - 1);

            if (guess == gameData.getSecretNumber()) {
                int roundScore = MAX_ATTEMPTS - gameData.getAttemptsLeft();
                gameData.setTotalScore(gameData.getTotalScore() + roundScore);
                gameData.setCurrentRoundScore(roundScore);

                if (isGameOver(gameData)) {
                    return "game-over";
                }

                gameData.setRoundsPlayed(gameData.getRoundsPlayed() + 1);
                return "win";
            } else {
                model.addAttribute("message", guess < gameData.getSecretNumber() ? "Too low!" : "Too high!");
            }

            if (gameData.getAttemptsLeft() == 0) {
                model.addAttribute("message", "Sorry, you lost. The correct number was " + gameData.getSecretNumber());
                return "lose";
            } else {
                return "index";
            }
        } else {
            model.addAttribute("message", "Sorry, you lost. The correct number was " + gameData.getSecretNumber());
            return "lose";
        }
    }

    private boolean isGameOver(GameData gameData) {
        return gameData.getRoundsPlayed() >= MAX_ROUNDS;
    }
}
