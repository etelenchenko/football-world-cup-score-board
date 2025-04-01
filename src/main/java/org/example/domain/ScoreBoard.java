package org.example.domain;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ScoreBoard {
    private final Set<Match> matches;

    public ScoreBoard() {
        this.matches = new HashSet<>();
    }
}
