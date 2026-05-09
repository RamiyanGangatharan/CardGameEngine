# Card Game Engine (CGE)

A Java framework for building card games — designed so every new game added makes the next one easier to build.

---

## What It Is

CGE is a personal long-term project with two goals running in parallel:

1. **Build playable card games** with a clean Swing UI
2. **Extract a reusable engine** from the patterns that emerge across those games

Rather than designing the engine upfront and guessing what's needed, the architecture grows bottom-up: each game reveals what belongs in the shared core.

---

## Games Planned

| #  | Game                              | Category     | Difficulty | Why                                                                                               |
|----|-----------------------------------|--------------|------------|---------------------------------------------------------------------------------------------------|
| 1  | War                               | Comparison   | *          | Minimal implementation — deck handling, card comparison, round resolution, and win conditions     |
| 2  | Crazy Eights                      | Shedding     | *          | Introduces turn rotation, discard piles, and basic rule validation                                |
| 3  | Go Fish                           | Matching     | **         | First hidden-information game with player requests, hand tracking, and set collection             |
| 4  | Blackjack                         | Casino       | **         | Simple finite-state flow with scoring logic, dealer AI, and flexible card values                  |
| 5  | Uno                               | Shedding     | **         | Expands Crazy Eights with action cards, direction changes, penalties, and edge-case handling      |
| 6  | Klondike Solitaire                | Solitaire    | **         | Single-player architecture focused on pile movement, legality checks, and tableau management      |
| 7  | Old Maid                          | Matching     | **         | Adds pair detection and special-card handling with hidden hand interactions                       |
| 8  | Snap                              | Speed        | **         | First real-time mechanics test with reaction windows and simultaneous player input                |
| 9  | Hearts                            | Trick-taking | ***        | Introduces trick resolution, suit-following rules, passing phases, and complex scoring            |
| 10 | FreeCell                          | Solitaire    | ***        | Fully deterministic gameplay with advanced move legality and undo/history requirements            |
| 11 | Spades                            | Trick-taking | ***        | Builds on Hearts with bidding systems, partnerships, trump rules, and nil bids                    |
| 12 | Cribbage                          | Counting     | ***        | Multi-phase scoring system with pegging mechanics, crib logic, and combination scoring            |
| 13 | Gin Rummy                         | Rummy        | ***        | Meld detection, deadwood calculation, knock conditions, and hand optimization                     |
| 14 | Rummy 500                         | Rummy        | ***        | Expands Gin Rummy with shared melds, laying off, and long-term score tracking                     |
| 15 | Spider Solitaire                  | Solitaire    | ***        | More advanced solitaire logic with multi-suit sequencing and stack-completion systems             |
| 16 | Poker (5-Card Draw)               | Casino       | ****       | First major hidden-information architecture with betting rounds and hand evaluation               |
| 17 | Texas Hold'em                     | Casino       | ****       | Adds community cards, multiple betting phases, side pots, and advanced state management           |
| 18 | Canasta                           | Rummy        | ****       | Partnership gameplay with wild cards, meld thresholds, and layered scoring systems                |
| 19 | Bridge                            | Trick-taking | ****       | Full auction/bidding engine, contract resolution, partnership logic, and sophisticated AI         |
| 20 | Magic: The Gathering (simplified) | Collectible  | *****      | Full engine-level architecture with triggers, stack resolution, card scripting, and event systems |

---

## Engine Architecture

### Core Primitives
Stateless, game-agnostic building blocks shared by every game.

- **`Card`** — immutable value object (Suit + Rank)
- **`Deck`** — ordered collection of cards; supports shuffle, draw, reset
- **`Hand`** — a player's held cards; supports add, remove, query
- **`Pile`** / **`Zone`** — named card zones with configurable movement rules (tableau, discard, stock, etc.)

### Game Contract
Every game implements a shared `Game` interface so the main menu can launch anything without knowing what it is.

```java
public interface Game {
    void launch(JFrame frame);
    String getName();
}
```

### Systems (extracted as patterns emerge)
| System | Extracted From | Purpose |
|---|---|---|
| Turn & Phase Manager | War → Crazy Eights | FSM for game flow |
| Rule Condition Engine | Crazy Eights → Uno | "Can I play this card?" validation |
| Per-Player View | Go Fish | Hiding information between players |
| Zone / Pile System | Klondike Solitaire | Named card zones with movement rules |
| Hand Evaluator | Poker | Card combination ranking |
| Scoring Pipeline | Hearts | Accumulating score across rounds/tricks |
| Event / Trigger System | Spades → MtG | Reacting to game events |
| Keyword Component System | MtG | Composable card abilities |

---

## Project Structure

```
src/main/java/org/example/
├── mainmenu/           # Main menu UI and game registry
│   ├── Main.java
│   ├── MenuPanel.java
│   ├── GameInformation.java
│   └── GameRepository.java
├── engine/             # Shared engine classes (grows over time)
│   ├── card/
│   ├── deck/
│   ├── hand/
│   └── player/
└── games/              # Individual game implementations
    ├── blackjack/
    ├── poker/
    └── ...
```

---

## Development Phases

### ✅ Phase 0 — Main Menu
Swing UI shell with game registry, selection list, and description panel. FlatLaf for theming.

### 🔄 Phase 1 — Core Primitives
`Card`, `Deck`, `Hand`, `Player` — the foundation everything else builds on.

### 📋 Phase 2 — War
First full game. Dead simple rules — the entire focus is wiring the engine contract, verifying the primitives work end-to-end, and getting the menu launch flow right.

### 📋 Phase 3 — Crazy Eights
Introduces turn rotation, a discard pile, and the first real play-validation rule. The point where a basic rule condition system starts to emerge.

### 📋 Phase 4+ — Expand
Follow the progression table. Each new game targets specific engine gaps; the engine is refactored when real patterns emerge, not speculatively.

---

## Design Principles

- **Bottom-up engine extraction** — no speculative abstractions; the engine earns its components
- **Games are isolated** — each game package owns its rules; the engine owns no game logic
- **Cards are immutable** — a `Card` never changes its suit or rank after creation
- **One interface to rule them all** — the menu only knows about `Game`; it doesn't care what's behind it

---

## Tech Stack

- **Language:** Java
- **UI:** Java Swing + [FlatLaf](https://www.formdev.com/flatlaf/) (light theme)
- **Build:** Maven

---

*By Ramiyan Gangatharan*