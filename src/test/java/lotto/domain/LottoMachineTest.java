package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    static class TestLottoNumbersPicker extends LottoNumbersPicker {
        @Override
        public List<Integer> pickNumbersInRange() {
            return List.of(1, 2, 3, 4, 5, 6);
        }
    }

    @DisplayName("금액에 따라 구매 가능한 개수만큼 로또를 발행한다")
    @Test
    void 금액에_따라_구매_가능한_개수만큼_로또를_발행한다() {
        // given
        LottoMachine machine = new LottoMachine(new TestLottoNumbersPicker());
        Money money = new Money(3000);

        // when
        LottoBundle bundle = machine.buy(money);

        // then
        assertThat(bundle.asList()).hasSize(3);
        assertThat(bundle.asList())
                .allSatisfy(lotto -> assertThat(lotto.getSortedNumbers()).containsExactly(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("Money가 1000원 단위가 아니면 예외가 발생한다")
    @Test
    void 금액이_1000원_단위가_아니면_예외가_발생한다() {
        // given
        LottoMachine machine = new LottoMachine(new TestLottoNumbersPicker());

        // when & then
        assertThatThrownBy(() -> machine.buy(new Money(1500)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}