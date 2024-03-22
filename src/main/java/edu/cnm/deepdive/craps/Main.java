package edu.cnm.deepdive.craps;

import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    RandomGenerator rng = RandomGenerator.of("Xoshiro256PlusPlus");
    Round round = new Round(rng);
    Map<State, Long> tally = new EnumMap<>(State.class);
    for (int i = 0; i < 1_000_000_000; i++) {
      State state = round.play();
      tally.put(state, 1 + tally.getOrDefault(state, 0L));
      round.reset();
    }
    double total = tally.values()
        .stream()
        .mapToLong(Long::longValue)
        .sum();
    Map<State, Double> frequencyTally = tally.entrySet()
        .stream()
        .collect(Collectors.toMap(Entry::getKey, (entry) -> entry.getValue() / total));
    System.out.println(frequencyTally);
  }

}
