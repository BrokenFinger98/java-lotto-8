package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoBundleTest {

    @DisplayName("로또 묶음은 불변 리스트로 관리된다")
    @Test
    void 로또_묶음은_불변_리스트로_관리된다() {
        // given
        List<Lotto> tickets = new ArrayList<>();
        tickets.add(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        LottoBundle bundle = new LottoBundle(tickets);

        // when
        tickets.add(new Lotto(List.of(7, 8, 9, 10, 11, 12)));

        // then
        assertThat(bundle.size()).isEqualTo(1);
    }

    @DisplayName("로또 묶음의 크기를 size()로 반환한다")
    @Test
    void 로또_묶음의_크기를_size로_반환한다() {
        // given
        List<Lotto> tickets = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(7, 8, 9, 10, 11, 12))
        );

        // when
        LottoBundle bundle = new LottoBundle(tickets);

        // then
        assertThat(bundle.size()).isEqualTo(2);
    }

    @DisplayName("asList()로 반환된 리스트는 불변이어야 한다")
    @Test
    void asList로_반환된_리스트는_불변이어야_한다() {
        // given
        LottoBundle bundle = new LottoBundle(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6))
        ));

        // when & then
        assertThatThrownBy(() -> bundle.asList().add(new Lotto(List.of(7, 8, 9, 10, 11, 12))))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}