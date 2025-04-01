package org.example.domain;

public class MatchManager {
    private final ScoreBoard scoreBoard;

    public MatchManager(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
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

        boolean teamAlreadyPlaying = scoreBoard.isTeamAlreadyPlaying(match);

        if (teamAlreadyPlaying) {
            throw new IllegalArgumentException("One or both teams are already playing in another match");
        }
        scoreBoard.addMatch(match);

        return match;
    }

    /**
     * Get the current number of active matches
     */
    public int getActiveMatchesCount() {
        return scoreBoard.getActiveMatchesCount();
    }

    /**
     * Finishes a game and removes it from the scoreboard
     *
     * @param homeTeam Home team name
     * @param awayTeam Away team name
     * @return true if the match was removed, false if no match was found
     */
    public boolean finishGame(String homeTeam, String awayTeam) {
        return scoreBoard.removeMatch(new Match(homeTeam, awayTeam));
    }

    /**
     * Updates the score of an existing match
     *
     * @param homeTeam Home team name
     * @param awayTeam Away team name
     * @param homeScore New home team score
     * @param awayScore New away team score
     * @throws IllegalArgumentException if the match does not exist
     */
    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        Match matchToUpdate = findMatch(homeTeam, awayTeam);
        if (matchToUpdate == null) {
            throw new IllegalArgumentException("Match not found");
        }
        matchToUpdate.updateScore(homeScore, awayScore);
    }

    /**
     * Helper method to find a match by team names
     */
    private Match findMatch(String homeTeam, String awayTeam) {
        Match matchToFind = new Match(homeTeam, awayTeam);
        return scoreBoard.findMatch(matchToFind);
    }
}
