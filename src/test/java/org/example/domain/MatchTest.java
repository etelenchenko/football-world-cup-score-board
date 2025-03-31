package org.example.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    @Test
    void constructorShouldThrowExceptionForNullHomeTeam() {
        assertThrows(IllegalArgumentException.class, () -> new Match(null, "Germany"));
    }

    @Test
    void constructorShouldThrowExceptionForEmptyHomeTeam() {
        assertThrows(IllegalArgumentException.class, () -> new Match("", "Germany"));
    }

    @Test
    void constructorShouldThrowExceptionForNullAwayTeam() {
        assertThrows(IllegalArgumentException.class, () -> new Match("Brazil", null));
    }

    @Test
    void constructorShouldThrowExceptionForEmptyAwayTeam() {
        assertThrows(IllegalArgumentException.class, () -> new Match("Brazil", ""));
    }

    @Test
    void constructorShouldThrowExceptionForSameTeams() {
        assertThrows(IllegalArgumentException.class, () -> new Match("Brazil", "Brazil"));
    }

}