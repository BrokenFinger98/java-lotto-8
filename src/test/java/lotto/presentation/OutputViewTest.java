package lotto.presentation;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import lotto.domain.LottoResultSummary;
import lotto.domain.Rank;
import lotto.domain.RankResult;
import org.junit.jupiter.api.*;

class OutputViewTest {

    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    private String output() {
        return outContent.toString();
    }

    @DisplayName("printSummary: 당첨 통계와 수익률을 형식에 맞게 출력한다")
    @Test
    void printSummary_당첨_통계와_수익률을_형식에_맞게_출력한다() {
        // given
        List<RankResult> results = List.of(
                new RankResult(Rank.FIFTH, 3, 1, Rank.FIFTH.getPrize()),
                new RankResult(Rank.FOURTH, 4, 0, Rank.FOURTH.getPrize()),
                new RankResult(Rank.THIRD, 5, 0, Rank.THIRD.getPrize()),
                new RankResult(Rank.SECOND, 5, 0, Rank.SECOND.getPrize()),
                new RankResult(Rank.FIRST, 6, 0, Rank.FIRST.getPrize())
        );
        LottoResultSummary summary = new LottoResultSummary(results, 5_000L, 62.5);

        // when
        OutputView.printSummary(summary);

        // then
        String printed = output();
        assertThat(printed)
                .contains("당첨 통계")
                .contains("---")
                .contains("3개 일치 (5,000원) - 1개")
                .contains("4개 일치 (50,000원) - 0개")
                .contains("5개 일치 (1,500,000원) - 0개")
                .contains("5개 일치, 보너스 볼 일치 (30,000,000원) - 0개")
                .contains("6개 일치 (2,000,000,000원) - 0개")
                .contains("총 수익률은 62.5%입니다.");
    }
}