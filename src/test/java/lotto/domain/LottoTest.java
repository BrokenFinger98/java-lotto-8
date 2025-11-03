package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 1 미만이거나 45 초과이면 예외가 발생한다.")
    @Test
    void 로또_번호가_범위를_벗어나면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호를 오름차순으로 정렬된 상태로 반환한다.")
    void 로또_번호를_오름차순으로_정렬된_상태로_반환한다() {
        // given
        Lotto lotto = new Lotto(List.of(6, 5, 4, 3, 2, 1));
        List<Integer> expectedNumbers = List.of(1, 2, 3, 4, 5, 6);

        // when
        List<Integer> sortedNumbers = lotto.getSortedNumbers();

        // then
        assertThat(sortedNumbers).isEqualTo(expectedNumbers);
    }

    @DisplayName("두 Lotto 간 일치하는 번호 개수를 반환한다.")
    @Test
    void 두_Lotto_간_일치하는_번호_개수를_반환한다() {
        // given
        Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = new Lotto(List.of(3, 4, 5, 6, 7, 8));

        // when
        int matchCount = lotto1.countMatchWith(lotto2);

        // then
        assertThat(matchCount).isEqualTo(4);
    }

    @DisplayName("Lotto가 특정 번호를 포함하면 true를 반환한다.")
    @Test
    void Lotto가_특정_번호를_포함하면_true를_반환한다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when & then
        assertThat(lotto.contains(3)).isTrue();
    }

    @DisplayName("Lotto가 특정 번호를 포함하지 않으면 false를 반환한다.")
    @Test
    void Lotto가_특정_번호를_포함하지_않으면_false를_반환한다() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when & then
        assertThat(lotto.contains(7)).isFalse();
    }
}
