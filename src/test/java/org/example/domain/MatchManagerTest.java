package org.example.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        matchManager.startGame("Brazil", "Germany");

        boolean result = matchManager.finishGame("Brazil", "Germany");

        assertTrue(result);
        assertEquals(0, matchManager.getActiveMatchesCount());
    }

    @Test
    void finishGameShouldReturnFalseForNonExistentMatch() {
        boolean result = matchManager.finishGame("Brazil", "Germany");

        assertFalse(result);
    }

    @Test
    void teamsCanPlayAfterTheirMatchIsFinished() {
        matchManager.startGame("Brazil", "Germany");
        matchManager.finishGame("Brazil", "Germany");

        Match match = matchManager.startGame("Brazil", "Italy");
        assertEquals("Brazil", match.getHomeTeam());
        assertEquals("Italy", match.getAwayTeam());
        assertEquals(1, matchManager.getActiveMatchesCount());
    }

}