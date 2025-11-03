package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private static final int MIN = 1;
    private static final int MAX = 45;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = List.copyOf(numbers);
    }

    public List<Integer> getSortedNumbers() {
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        return sortedNumbers;
    }

    public int countMatchWith(Lotto other) {
        return (int) numbers.stream().filter(other::contains).count();
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }

        long distinctCount = numbers.stream().distinct().count();
        if (distinctCount != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
        }

        boolean outOfRange = numbers.stream().anyMatch(num -> num < MIN || num > MAX);
        if (outOfRange) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }
}
