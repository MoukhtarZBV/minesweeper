package minesweeper;

public enum State {

	UNDISCOVERED(-1), EMPTY(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), MINE(9), FLAG(10), LAST(11);
	
	private int value;
	
	private State(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public static State getState(int value) {
		for (State state : State.values()) {
			if (state.getValue() == value) {
				return state;
			}
		}
		return null;
	}
}
