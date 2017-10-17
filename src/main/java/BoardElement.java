public enum BoardElement {
    ZERO(0),
    ONE(1),
    TWO(2);

    private final int value;

    BoardElement(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }
}
