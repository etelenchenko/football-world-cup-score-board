# Football World Cup Score Board
A simple Java library that implements a Football World Cup Score Board as specified in the requirements.

## Overview
The library provides functionality to:

- Start a game (capturing home team and away team, with initial score 0-0)
- Finish a game (removing it from the scoreboard)
- Update a game's score
- Get a summary of games sorted by total score (with same-score games ordered by most recently added)

## Design Decisions
### Architecture

The solution follows Object-Oriented design principles with three main classes:

- Match: Represents a football match with teams and scores
- ScoreBoard: Represents the collection of active matches
- MatchManager: Manages matches

### Data Structure

Used LinkedHashSet to store matches, which:

- Preserves insertion order (needed for sorting by most recently added)
- Provides efficient add/remove operations

## Edge Cases Handled

- Duplicate match prevention
- Null or empty team names
- Same team playing against itself
- Negative scores
- Case-insensitive team name comparison
- Whitespace in team names
- Teams playing in multiple matches simultaneously (prevented)

## Testing
The implementation follows Test-Driven Development practices:

- Unit tests for all Match, ScoreBoard and MatchManager classes
- Edge case testing

## Assumptions

- Team names are case-insensitive for matching purposes (e.g., "Brazil" and "BRAZIL" are considered the same team)
- Whitespace is trimmed from team names for comparison
- A match is uniquely identified by its home and away teams
- Once a match is finished, it cannot be restarted with the same teams (would need to create a new match)
- There is no persistence - all data is stored in memory
- A team cannot play in multiple matches simultaneously (real-world constraint)

## How to Use
```agsl
// Create a new scoreboard
ScoreBoard scoreBoard = new ScoreBoard();
MatchManager matchManager = new MatchManager(scoreBoard);

// Start games
Match mexicoCanada = matchManager.startGame("Mexico", "Canada");
Match spainBrazil = matchManager.startGame("Spain", "Brazil");

// Update scores
matchManager.updateScore(mexicoCanada, 0, 5);
matchManager.updateScore(spainBrazil, 10, 2);

// Get summary of games sorted by total score
List<Match> summary = matchManager.getSummary();
for (Match match : summary) {
    System.out.println(match);
}

// Finish a game
matchManager.finishGame(match);

// Now the team can play in a new match
matchManager.startGame("Mexico", "Argentina");
```
