# Card Game Engine

## Overview

A reusable Java card game engine/framework that allows multiple card games to share the same core classes and systems.

The goal is to make future card games easier to develop by reusing existing code.

Examples:

- Blackjack
- Poker
- Hearts
- Solitaire

---

# Core Ideas

## Reusable Classes

Shared systems that can work across different games.

Examples:

- Card
- Deck
- Hand
- Player

---

## Modular Design

Each game should have its own rules while using the same engine components.

Example:

- Blackjack and Poker both use a Deck
- Blackjack and Hearts both use Players

---

# Development Plan

## Phase 1

Build:

- Card
- Deck
- Hand
- Player

---

## Phase 2

Create Blackjack using the shared engine classes.

---

## Phase 3

Expand into other card games.

---

# Main Goal

Create a reusable card game framework that becomes easier to expand the more games are added.