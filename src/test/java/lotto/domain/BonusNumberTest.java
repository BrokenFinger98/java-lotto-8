package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusNumberTest {
    @DisplayName("보너스 번호가 1 미만이면 예외가 발생한다.")
    @Test
    void 보너스_번호가_1_미만이면_예외가_발생한다() {
        assertThatThrownBy(() -> new BonusNumber(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 45 초과면 예외가 발생한다.")
    @Test
    void 보너스_번호가_45_초과면_예외가_발생한다() {
        assertThatThrownBy(() -> new BonusNumber(46))
                .isInstanceOf(IllegalArgumentException.class);
    }
}