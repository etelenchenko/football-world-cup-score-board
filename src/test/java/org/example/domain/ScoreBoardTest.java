package org.example.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardTest {
    private ScoreBoard scoreBoard;

    @BeforeEach
    void setUp() {
        scoreBoard = new ScoreBoard();
    }

    @Test
    void getSummaryShouldReturnEmptyListForEmptyScoreBoard() {
        List<Match> summary = scoreBoard.getSummary();

        assertTrue(summary.isEmpty());
    }

}