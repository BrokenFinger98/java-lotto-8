package lotto.domain;

public final class BonusNumber {
    private static final int MIN = 1;
    private static final int MAX = 45;

    private final int number;

    public BonusNumber(int number) {
        validate(number);
        this.number = number;
    }

    public int value() {
        return number;
    }

    private void validate(int number) {
        if (number < MIN || number > MAX) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }
}