package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class LottoNumbersPicker {
    private static final int MIN = 1;
    private static final int MAX = 45;
    private static final int COUNT = 6;

    public List<Integer> pickNumbersInRange() {
        return Randoms.pickUniqueNumbersInRange(MIN, MAX, COUNT);
    }
}
