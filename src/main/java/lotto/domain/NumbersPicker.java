package lotto.domain;

import java.util.List;

public interface NumbersPicker {
    List<Integer> pickNumbersInRange(int min, int max, int count);
}
