package lotto.presentation;

import java.text.NumberFormat;
import java.util.Locale;
import lotto.domain.Lotto;
import lotto.domain.LottoBundle;
import lotto.domain.LottoResultSummary;
import lotto.domain.Rank;
import lotto.domain.RankResult;

public final class OutputView {
    private static final NumberFormat NUM = NumberFormat.getNumberInstance(Locale.KOREA);

    public static void printPurchased(LottoBundle bundle) {
        System.out.println();
        System.out.println(bundle.size() + "개를 구매했습니다.");
        for (Lotto lotto : bundle.asList()) {
            System.out.println(lotto.getSortedNumbers());
        }
    }

    public static void printSummary(LottoResultSummary summary) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        for (RankResult r : summary.results()) {
            if (r.rank() == Rank.SECOND) {
                System.out.printf("5개 일치, 보너스 볼 일치 (%s원) - %d개%n", NUM.format(r.prize()), r.count());
                continue;
            }
            System.out.printf("%d개 일치 (%s원) - %d개%n", r.matchCount(), NUM.format(r.prize()), r.count());
        }
        System.out.printf("총 수익률은 %.1f%%%s%n", summary.profitRate(), "입니다.");
    }
}