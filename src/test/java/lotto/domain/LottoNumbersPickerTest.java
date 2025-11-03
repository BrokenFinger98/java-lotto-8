package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumbersPickerTest {

    private LottoNumbersPicker lottoNumbersPicker = new LottoNumbersPicker();

    @Test
    @DisplayName("최소값 이상 최대값 이하의 숫자를 정해진 개수만큼 뽑는다.")
    void 최소값_이상_최대값_이하의_숫자를_정해진_개수만큼_뽑는다() {
        // given
        int min = 1;
        int max = 45;
        int count = 6;

        // when
        List<Integer> numbers = lottoNumbersPicker.pickNumbersInRange();

        // then
        assertThat(numbers).hasSize(count);
        assertThat(numbers).allMatch(num -> num >= min && num <= max);
        Set<Integer> unique = new HashSet<>(numbers);
        assertThat(unique).hasSameSizeAs(numbers);
    }
}