package org.example.domain;

import java.util.HashSet;
import java.util.Set;

public class ScoreBoard {
    private final Set<Match> matches;

    public ScoreBoard() {
        this.matches = new HashSet<>();
    }

    /**
     * Starts a game with initial score 0-0
     *
     * @param homeTeam Home team name
     * @param awayTeam Away team name
     * @return The created match
     * @throws IllegalArgumentException if a match with the same teams already exists
     */
    public Match startGame(String homeTeam, String awayTeam) {
        Match match = new Match(homeTeam, awayTeam);
        if (!matches.add(match)) {
            throw new IllegalArgumentException("A match between these teams already exists");
        }
        return match;
    }
}
