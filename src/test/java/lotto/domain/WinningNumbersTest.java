package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {

    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    @Test
    void 보너스_번호가_당첨_번호와_중복되면_예외가_발생한다() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 1 미만 또는 45 초과면 예외가 발생한다.")
    @Test
    void 보너스_번호가_1_미만_또는_45_초과면_예외가_발생한다() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 0))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 46))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호와 당첨 번호의 일치 개수를 정확히 계산한다.")
    @Test
    void 로또_번호와_당첨_번호의_일치_개수를_정확히_계산한다() {
        // given
        WinningNumbers winning = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto lotto = new Lotto(List.of(1, 3, 5, 7, 9, 11));

        // when
        int matchCount = winning.matchCount(lotto);

        // then
        assertThat(matchCount).isEqualTo(3);
    }

    @DisplayName("보너스 번호가 로또에 포함되면 bonusMatched는 true를 반환한다.")
    @Test
    void 보너스_번호가_로또에_포함되면_bonusMatched는_true를_반환한다() {
        // given
        WinningNumbers winning = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto lotto = new Lotto(List.of(7, 8, 9, 10, 11, 12));

        // when & then
        assertThat(winning.bonusMatched(lotto)).isTrue();
    }

    @DisplayName("보너스 번호가 로또에 포함되지 않으면 bonusMatched는 false를 반환한다.")
    @Test
    void 보너스_번호가_로또에_포함되지_않으면_bonusMatched는_false를_반환한다() {
        // given
        WinningNumbers winning = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);
        Lotto lotto = new Lotto(List.of(8, 9, 10, 11, 12, 13));

        // when & then
        assertThat(winning.bonusMatched(lotto)).isFalse();
    }
}