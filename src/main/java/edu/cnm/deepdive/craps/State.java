package edu.cnm.deepdive.craps;

public enum State {

  COME_OUT {
    @Override
    public State next(int point, Roll roll) {
      return switch (roll.total()) {
        case 7, 11 -> WIN;
        case 2, 3, 12 -> LOSS;
        default -> POINT;
      };
    }
  },
  POINT {
    @Override
    public State next(int point, Roll roll) {
      return (roll.total() == 7)
          ? LOSS
          : ((roll.total() == point) ? WIN : POINT);
    }
  },
  WIN {
    @Override
    public boolean isTerminal() {
      return true;
    }
  },
  LOSS {
    @Override
    public boolean isTerminal() {
      return true;
    }
  };

  public static State initialState() {
    return COME_OUT;
  }

  public boolean isTerminal() {
    return false;
  }

  public State next(int point, Roll roll) {
    throw new IllegalStateException();
  }
}
