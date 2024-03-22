package edu.cnm.deepdive.craps;

import java.util.random.RandomGenerator;

public record Roll(int die1, int die2) {

  private static final int SIDES_PER_DIE = 6;

  public Roll(RandomGenerator rng) {
    this(1 + rng.nextInt(SIDES_PER_DIE), 1 + rng.nextInt(SIDES_PER_DIE));
  }

  public int total() {
    return die1 + die2;
  }

}
