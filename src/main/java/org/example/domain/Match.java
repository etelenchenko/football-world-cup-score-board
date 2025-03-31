package org.example.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Match {
    private final String homeTeam;
    private final String awayTeam;

    public Match(String homeTeam, String awayTeam) {
        validateTeamNames(homeTeam, awayTeam);
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    private void validateTeamNames(String homeTeam, String awayTeam) {
        if (homeTeam == null || homeTeam.trim().isEmpty()) {
            throw new IllegalArgumentException("Home team name cannot be null or empty");
        }
        if (awayTeam == null || awayTeam.trim().isEmpty()) {
            throw new IllegalArgumentException("Away team name cannot be null or empty");
        }
        if (homeTeam.trim().equalsIgnoreCase(awayTeam.trim())) {
            throw new IllegalArgumentException("Home team and away team cannot be the same");
        }
    }
}
