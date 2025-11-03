package lotto.application;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoServiceTest {

    static class TestLottoNumbersPicker extends LottoNumbersPicker {
        @Override
        public List<Integer> pickNumbersInRange() {
            return List.of(1, 2, 3, 4, 5, 6);
        }
    }

    @DisplayName("buyTickets: 주어진 금액만큼 로또를 구매한다")
    @Test
    void buyTickets_주어진_금액만큼_로또를_구매한다() {
        // given
        LottoService service = new LottoService(new TestLottoNumbersPicker());
        Money money = new Money(3_000);

        // when
        LottoBundle bundle = service.buyTickets(money);

        // then
        assertThat(bundle.asList()).hasSize(3);
        assertThat(bundle.asList())
                .allSatisfy(lotto -> assertThat(lotto.getSortedNumbers()).containsExactly(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("summarize: 구매한 로또의 당첨 결과를 요약한다")
    @Test
    void summarize_구매한_로또의_당첨_결과를_요약한다() {
        // given
        LottoService service = new LottoService(new TestLottoNumbersPicker());

        LottoBundle bundle = new LottoBundle(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                new Lotto(List.of(1, 2, 3, 4, 5, 8))
        ));

        WinningNumbers winning = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), new BonusNumber(7));
        Money invested = new Money(3_000);

        // when
        LottoResultSummary summary = service.summarize(bundle, winning, invested);

        // then
        assertThat(summary.results()).hasSize(5);
        assertThat(summary.totalPrize()).isEqualTo(2_031_500_000L);
        assertThat(summary.profitRate()).isGreaterThan(0.0);
    }
}