package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoRoundTest {

    @DisplayName("구매한 로또들을 등수별로 집계하고 총 상금 및 수익률을 요약한다")
    @Test
    void 구매한_로또들을_등수별로_집계하고_총_상금_및_수익률을_요약한다() {
        // given
        WinningNumbers winning = new WinningNumbers(
                List.of(1, 2, 3, 4, 5, 6),
                new BonusNumber(7)
        );

        Lotto t1_first  = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto t2_second = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        Lotto t3_third  = new Lotto(List.of(1, 2, 3, 4, 5, 8));
        Lotto t4_fourth = new Lotto(List.of(1, 2, 3, 4, 9, 10));
        Lotto t5_fifth  = new Lotto(List.of(1, 2, 3, 11, 12, 13));

        LottoBundle bundle = new LottoBundle(List.of(
                t1_first, t2_second, t3_third, t4_fourth, t5_fifth
        ));

        Money invested = new Money(5_000);
        LottoRound round = new LottoRound(winning);

        // when
        LottoResultSummary summary = round.summarize(bundle, invested);

        // then
        List<RankResult> results = summary.results();
        assertThat(results).hasSize(5);

        assertThat(results.get(0).rank()).isEqualTo(Rank.FIFTH);
        assertThat(results.get(0).matchCount()).isEqualTo(3);
        assertThat(results.get(0).count()).isEqualTo(1);
        assertThat(results.get(0).prize()).isEqualTo(Rank.FIFTH.getPrize());

        assertThat(results.get(1).rank()).isEqualTo(Rank.FOURTH);
        assertThat(results.get(1).matchCount()).isEqualTo(4);
        assertThat(results.get(1).count()).isEqualTo(1);
        assertThat(results.get(1).prize()).isEqualTo(Rank.FOURTH.getPrize());

        assertThat(results.get(2).rank()).isEqualTo(Rank.THIRD);
        assertThat(results.get(2).matchCount()).isEqualTo(5);
        assertThat(results.get(2).count()).isEqualTo(1);
        assertThat(results.get(2).prize()).isEqualTo(Rank.THIRD.getPrize());

        assertThat(results.get(3).rank()).isEqualTo(Rank.SECOND);
        assertThat(results.get(3).matchCount()).isEqualTo(5);
        assertThat(results.get(3).count()).isEqualTo(1);
        assertThat(results.get(3).prize()).isEqualTo(Rank.SECOND.getPrize());

        assertThat(results.get(4).rank()).isEqualTo(Rank.FIRST);
        assertThat(results.get(4).matchCount()).isEqualTo(6);
        assertThat(results.get(4).count()).isEqualTo(1);
        assertThat(results.get(4).prize()).isEqualTo(Rank.FIRST.getPrize());

        assertThat(summary.totalPrize()).isEqualTo(2_031_555_000L);

        assertThat(summary.profitRate()).isEqualTo(40_631_100.0);
    }
}