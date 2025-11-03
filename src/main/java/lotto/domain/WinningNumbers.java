package lotto.domain;

import java.util.List;

public class WinningNumbers {
    private final Lotto winningLotto;
    private final BonusNumber bonusNumber;

    public WinningNumbers(List<Integer> winningNumbers, BonusNumber bonusNumber) {
        validate(winningNumbers, bonusNumber);
        this.winningLotto = new Lotto(winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    public int matchCount(Lotto lotto) {
        return lotto.countMatchWith(winningLotto);
    }

    public boolean bonusMatched(Lotto lotto) {
        return lotto.contains(bonusNumber.value());
    }

    private void validate(List<Integer> winningNumbers, BonusNumber bonusNumber) {
        if (winningNumbers.contains(bonusNumber.value())) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
