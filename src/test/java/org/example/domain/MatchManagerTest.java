package org.example.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MatchManagerTest {

    private MatchManager matchManager;

    @BeforeEach
    void setUp() {
        ScoreBoard scoreBoard = new ScoreBoard();
        matchManager = new MatchManager(scoreBoard);
    }

    @Test
    void startGameShouldThrowExceptionForDuplicateMatch() {
        matchManager.startGame("Brazil", "Germany");

        assertThrows(IllegalArgumentException.class, () -> matchManager.startGame("Brazil", "Germany"));
    }

    @Test
    void startGameShouldAddMatchToScoreBoard() {
        Match match = matchManager.startGame("Brazil", "Germany");

        assertEquals(1, matchManager.getActiveMatchesCount());
        assertEquals("Brazil", match.getHomeTeam());
        assertEquals("Germany", match.getAwayTeam());
        assertEquals(0, match.getHomeScore());
        assertEquals(0, match.getAwayScore());
    }

    @Test
    void startGameShouldThrowExceptionIfHomeTeamIsAlreadyPlaying() {
        matchManager.startGame("Brazil", "Germany");

        assertThrows(IllegalArgumentException.class, () -> matchManager.startGame("Brazil", "Italy"));
        assertEquals(1, matchManager.getActiveMatchesCount());
    }

    @Test
    void startGameShouldThrowExceptionIfAwayTeamIsAlreadyPlaying() {
        matchManager.startGame("Brazil", "Germany");

        assertThrows(IllegalArgumentException.class, () -> matchManager.startGame("Italy", "Germany"));
        assertEquals(1, matchManager.getActiveMatchesCount());
    }

    @Test
    void finishGameShouldRemoveMatchFromScoreBoard() {
        Match brazilGermany = matchManager.startGame("Brazil", "Germany");

        boolean result = matchManager.finishGame(brazilGermany);

        assertTrue(result);
        assertEquals(0, matchManager.getActiveMatchesCount());
    }

    @Test
    void finishGameShouldReturnFalseForNonExistentMatch() {
        Match brazilGermany = new Match("Brazil", "Germany");
        boolean result = matchManager.finishGame(brazilGermany);

        assertFalse(result);
    }

    @Test
    void teamsCanPlayAfterTheirMatchIsFinished() {
        Match brazilGermany = matchManager.startGame("Brazil", "Germany");
        matchManager.finishGame(brazilGermany);

        Match match = matchManager.startGame("Brazil", "Italy");
        assertEquals("Brazil", match.getHomeTeam());
        assertEquals("Italy", match.getAwayTeam());
        assertEquals(1, matchManager.getActiveMatchesCount());
    }

    @Test
    void updateScoreShouldUpdateMatchScore() {
        Match match = matchManager.startGame("Brazil", "Germany");

        matchManager.updateScore(match, 3, 2);

        assertEquals(3, match.getHomeScore());
        assertEquals(2, match.getAwayScore());
    }

    @Test
    void updateScoreShouldThrowExceptionForNonExistentMatch() {
        Match match = new Match("Brazil", "Germany");
        assertThrows(IllegalArgumentException.class,
                () -> matchManager.updateScore(match, 3, 2));
    }

    @Test
    void getSummaryShouldReturnMatchesOrderedByTotalScore() {
        setupExampleMatches();

        List<Match> summary = matchManager.getSummary();

        assertEquals(5, summary.size());

        assertEquals("Uruguay", summary.get(0).getHomeTeam());
        assertEquals("Italy", summary.get(0).getAwayTeam());
        assertEquals(12, summary.get(0).getTotalScore());

        assertEquals("Spain", summary.get(1).getHomeTeam());
        assertEquals("Brazil", summary.get(1).getAwayTeam());
        assertEquals(12, summary.get(1).getTotalScore());

        assertEquals("Mexico", summary.get(2).getHomeTeam());
        assertEquals("Canada", summary.get(2).getAwayTeam());
        assertEquals(5, summary.get(2).getTotalScore());

        assertEquals("Argentina", summary.get(3).getHomeTeam());
        assertEquals("Australia", summary.get(3).getAwayTeam());
        assertEquals(4, summary.get(3).getTotalScore());

        assertEquals("Germany", summary.get(4).getHomeTeam());
        assertEquals("France", summary.get(4).getAwayTeam());
        assertEquals(4, summary.get(4).getTotalScore());
    }

    @Test
    void matchesShouldBeOrderedByMostRecentlyAddedForSameTotalScore() {
        Match match = matchManager.startGame("Germany", "France");
        matchManager.updateScore(match, 2, 2);

        match = matchManager.startGame("Argentina", "Australia");
        matchManager.updateScore(match, 3, 1);

        List<Match> summary = matchManager.getSummary();

        assertEquals(2, summary.size());
        assertEquals("Argentina", summary.get(0).getHomeTeam());
        assertEquals("Australia", summary.get(0).getAwayTeam());
        assertEquals("Germany", summary.get(1).getHomeTeam());
        assertEquals("France", summary.get(1).getAwayTeam());
    }

    private void setupExampleMatches() {
        Match mexicoCanada = matchManager.startGame("Mexico", "Canada");
        Match spainBrazil = matchManager.startGame("Spain", "Brazil");
        Match germanyFrance = matchManager.startGame("Germany", "France");
        Match uruguayItaly = matchManager.startGame("Uruguay", "Italy");
        Match argentinaAustralia = matchManager.startGame("Argentina", "Australia");

        matchManager.updateScore(mexicoCanada, 0, 5);
        matchManager.updateScore(spainBrazil, 10, 2);
        matchManager.updateScore(germanyFrance, 2, 2);
        matchManager.updateScore(uruguayItaly, 6, 6);
        matchManager.updateScore(argentinaAustralia, 3, 1);
    }

}