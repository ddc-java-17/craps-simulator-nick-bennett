package edu.cnm.deepdive.craps;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.random.RandomGenerator;

public final class Round {

  private final List<Roll> rolls;
  private final RandomGenerator rng;

  private State state;
  private int point;

  public Round(RandomGenerator rng) {
    this.rng = rng;
    rolls = new LinkedList<>();
    reset();
  }

  public void reset() {
    rolls.clear();
    state = State.initialState();
    point = 0;
  }

  public List<Roll> getRolls() {
    return Collections.unmodifiableList(rolls);
  }

  public State getState() {
    return state;
  }

  @SuppressWarnings("UnusedReturnValue")
  public State roll() {
    Roll roll = new Roll(rng);
    if (point == 0) {
      point = roll.total();
    }
    state = state.next(point, roll);
    rolls.add(roll);
    return state;
  }

  public State play() {
    while (!state.isTerminal()) {
      roll();
    }
    return state;
  }

}
