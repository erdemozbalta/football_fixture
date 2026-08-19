# Football Fixture

A simple Java console application that simulates a 4-team football league.

## Features

- Add 4 football teams
- Generate a league fixture
- Generate random match scores
- Calculate wins, draws and losses
- Calculate goals scored and conceded
- Calculate goal difference
- Display the league standings

## Technologies

- Java 24
- Scanner
- Random
- Arrays

## League System

The application simulates a 4-team league. Each team plays against every other team twice, once at home and once away. This results in a total of 12 matches.

## Scoring System

| Result | Points |
|--------|--------|
| Win | 3 |
| Draw | 1 |
| Loss | 0 |

## Match Scores

Match scores are randomly generated between 0 and 4 goals for each team.

## League Table

The standings display:

- Played Matches
- Wins
- Draws
- Losses
- Goals Scored
- Goals Conceded
- Goal Difference
- Points

## Project Structure

```text
src/
└── footballfixture/
    └── FootballFixture.java
```

## How to Run

Compile the project:

```bash
javac -d build\classes src\footballfixture\FootballFixture.java
```

Run the application:

```bash
java -cp build\classes footballfixture.FootballFixture
```

## Author

**erdemozbalta**
