package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.tuple.Pair;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class MontyHallGame {

    private static final int NUM_SIMULATIONS = 1000;
    private static final int NUM_DOORS = 3;

    public static void main(String[] args) {
        Map<String, Integer> results = simulateMontyHallGame(NUM_SIMULATIONS, false);
        printStatistics(results);

        log.info("\n\nNow running simulations with switching:");
        results = simulateMontyHallGame(NUM_SIMULATIONS, true);
        printStatistics(results);
    }

    private static Map<String, Integer> simulateMontyHallGame(int numSimulations, boolean switchChoice) {
        Map<String, Integer> results = new HashMap<>();
        results.put("wins", 0);
        results.put("losses", 0);

        Random random = new Random(System.currentTimeMillis());

        for (int i = 0; i < numSimulations; i++) {
            Pair<Integer, Boolean> gameResult = playGame(random, switchChoice);
            if (gameResult.getRight()) {
                results.put("wins", results.get("wins") + 1);
            } else {
                results.put("losses", results.get("losses") + 1);
            }
        }

        return results;
    }

    private static Pair<Integer, Boolean> playGame(Random random, boolean switchChoice) {
        // Generate winning door
        int winningDoor = random.nextInt(NUM_DOORS) + 1;

        // Player makes initial choice
        int playerChoice = random.nextInt(NUM_DOORS) + 1;

        // Host opens a non-winning door
        int hostOpens = getHostOpens(winningDoor, playerChoice);

        // If switching, change player's choice
        if (switchChoice) {
            playerChoice = getOtherDoor(playerChoice, hostOpens);
        }

        // Check if player wins
        return Pair.of(playerChoice, winningDoor == playerChoice);
    }

    private static int getHostOpens(int winningDoor, int playerChoice) {
        Random random = new Random(System.currentTimeMillis());
        int hostOpens;
        do {
            hostOpens = random.nextInt(NUM_DOORS) + 1;
        } while (hostOpens == winningDoor || hostOpens == playerChoice);
        return hostOpens;
    }

    private static int getOtherDoor(int playerChoice, int hostOpens) {
        for (int i = 1; i <= NUM_DOORS; i++) {
            if (i != playerChoice && i != hostOpens) {
                return i;
            }
        }
        throw new IllegalStateException("Should not reach here");
    }

    private static void printStatistics(Map<String, Integer> results) {
        double winPercentage = ((double) results.get("wins") / NUM_SIMULATIONS) * 100;
        log.info("Simulations: {}", NUM_SIMULATIONS);
        log.info("Wins: {}", results.get("wins"), winPercentage);
        log.info("Losses: {}", results.get("losses"), 100 - winPercentage);
    }
}