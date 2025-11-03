package lotto.domain;

public class Money {
    private static final int LOTTO_PRICE = 1_000;
    private static final int PERCENT = 100;
    private static final double ROUNDING_UNIT = 10.0;

    private final long amount;

    public Money(long amount) {
        validate(amount);
        this.amount = amount;
    }

    public double profitRateComparedTo(Money invested) {
        double ratio = ((double) amount / invested.amount) * PERCENT;
        return Math.round(ratio * ROUNDING_UNIT) / ROUNDING_UNIT;
    }

    public int getPurchasableLottoCount() {
        return (int) (amount / LOTTO_PRICE);
    }

    private void validate(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 0보다 커야 합니다.");
        }
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 1,000원 단위여야 합니다.");
        }
    }
}