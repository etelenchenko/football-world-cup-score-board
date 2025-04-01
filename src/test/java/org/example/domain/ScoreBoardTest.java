package org.example.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardTest {

    private ScoreBoard scoreBoard;

    @BeforeEach
    void setUp() {
        scoreBoard = new ScoreBoard();
    }

    @Test
    void startGameShouldThrowExceptionForDuplicateMatch() {
        scoreBoard.startGame("Brazil", "Germany");

        assertThrows(IllegalArgumentException.class, () -> scoreBoard.startGame("Brazil", "Germany"));
    }

    @Test
    void startGameShouldAddMatchToScoreBoard() {
        Match match = scoreBoard.startGame("Brazil", "Germany");

        assertEquals(1, scoreBoard.getActiveMatchesCount());
        assertEquals("Brazil", match.getHomeTeam());
        assertEquals("Germany", match.getAwayTeam());
        assertEquals(0, match.getHomeScore());
        assertEquals(0, match.getAwayScore());
    }

    @Test
    void startGameShouldThrowExceptionIfHomeTeamIsAlreadyPlaying() {
        scoreBoard.startGame("Brazil", "Germany");

        assertThrows(IllegalArgumentException.class, () -> scoreBoard.startGame("Brazil", "Italy"));
        assertEquals(1, scoreBoard.getActiveMatchesCount());
    }

    @Test
    void startGameShouldThrowExceptionIfAwayTeamIsAlreadyPlaying() {
        scoreBoard.startGame("Brazil", "Germany");

        assertThrows(IllegalArgumentException.class, () -> scoreBoard.startGame("Italy", "Germany"));
        assertEquals(1, scoreBoard.getActiveMatchesCount());
    }

    @Test
    void finishGameShouldRemoveMatchFromScoreBoard() {
        scoreBoard.startGame("Brazil", "Germany");

        boolean result = scoreBoard.finishGame("Brazil", "Germany");

        assertTrue(result);
        assertEquals(0, scoreBoard.getActiveMatchesCount());
    }

    @Test
    void finishGameShouldReturnFalseForNonExistentMatch() {
        boolean result = scoreBoard.finishGame("Brazil", "Germany");

        assertFalse(result);
    }

    @Test
    void teamsCanPlayAfterTheirMatchIsFinished() {
        scoreBoard.startGame("Brazil", "Germany");
        scoreBoard.finishGame("Brazil", "Germany");

        Match match = scoreBoard.startGame("Brazil", "Italy");
        assertEquals("Brazil", match.getHomeTeam());
        assertEquals("Italy", match.getAwayTeam());
        assertEquals(1, scoreBoard.getActiveMatchesCount());
    }

}