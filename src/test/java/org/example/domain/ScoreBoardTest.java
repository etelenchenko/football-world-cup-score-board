package org.example.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardTest {

    @Test
    void startGameShouldThrowExceptionForDuplicateMatch() {
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.startGame("Brazil", "Germany");

        assertThrows(IllegalArgumentException.class, () -> scoreBoard.startGame("Brazil", "Germany"));
    }

}