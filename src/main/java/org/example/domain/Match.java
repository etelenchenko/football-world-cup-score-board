package org.example.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Match {
    private final String homeTeam;
    private final String awayTeam;
    private int homeScore;
    private int awayScore;

    public Match(String homeTeam, String awayTeam) {
        validateTeamNames(homeTeam, awayTeam);
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
    }

    public void updateScore(int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Scores cannot be negative");
        }
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public int getTotalScore() {
        return homeScore + awayScore;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(homeTeam.trim().toLowerCase(), match.homeTeam.trim().toLowerCase()) &&
                Objects.equals(awayTeam.trim().toLowerCase(), match.awayTeam.trim().toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam.trim().toLowerCase(), awayTeam.trim().toLowerCase());
    }
}
