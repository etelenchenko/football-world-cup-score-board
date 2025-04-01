package org.example.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ScoreBoard {
    private final Set<Match> matches;

    public ScoreBoard() {
        this.matches = new LinkedHashSet<>();
    }

    /**
     * Gets a summary of games ordered by total score (descending)
     * Games with the same total score are ordered by most recently added
     *
     * @return List of matches ordered according to the requirements
     */
    public List<Match> getSummary() {
        List<Match> orderedMatches = new ArrayList<>(matches);
        return orderedMatches.stream()
                .sorted(Comparator
                        .comparing(Match::getTotalScore, Comparator.reverseOrder())
                        .thenComparing(orderedMatches::indexOf, Comparator.reverseOrder())
                )
                .toList();
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
