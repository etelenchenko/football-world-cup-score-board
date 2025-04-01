package org.example.domain;

import java.util.HashSet;
import java.util.Set;

public class ScoreBoard {
    private final Set<Match> matches;

    public ScoreBoard() {
        this.matches = new HashSet<>();
    }

    public void addMatch(Match match) {
        matches.add(match);
    }

    public int getActiveMatchesCount() {
        return matches.size();
    }

    public boolean removeMatch(Match match) {
        return matches.remove(match);
    }

    public Match findMatch(Match matchToFind) {
        return matches.stream()
                .filter(match -> match.equals(matchToFind))
                .findFirst()
                .orElse(null);
    }

    public boolean isTeamAlreadyPlaying(Match match) {
        return matches.stream()
                .anyMatch(m -> m != match &&
                        (isTeamPlaying(m, match.getHomeTeam()) || isTeamPlaying(m, match.getAwayTeam())));
    }

    private boolean isTeamPlaying(Match match, String team) {
        String normalizedTeam = team.trim().toLowerCase();
        return match.getHomeTeam().trim().toLowerCase().equals(normalizedTeam) ||
                match.getAwayTeam().trim().toLowerCase().equals(normalizedTeam);
    }
}
