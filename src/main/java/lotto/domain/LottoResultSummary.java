package lotto.domain;

import java.util.List;

public record LottoResultSummary(
        List<RankResult> results,
        long totalPrize,
        double profitRate
) {
}
