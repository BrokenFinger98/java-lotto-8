package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class LottoRound {
    private final WinningNumbers winning;

    public LottoRound(WinningNumbers winning) {
        this.winning = winning;
    }

    public LottoResultSummary summarize(LottoBundle bundle, Money invested) {
        Map<Rank, Integer> tally = new EnumMap<>(Rank.class);
        for (Rank r : Rank.values()) {
            tally.put(r, 0);
        }

        for (Lotto lotto : bundle.asList()) {
            Rank rank = Rank.valueOf(winning.matchCount(lotto), winning.bonusMatched(lotto));
            tally.put(rank, tally.get(rank) + 1);
        }

        List<RankResult> ordered = List.of(
                new RankResult(Rank.FIFTH, 3, tally.get(Rank.FIFTH), Rank.FIFTH.getPrize()),
                new RankResult(Rank.FOURTH, 4, tally.get(Rank.FOURTH), Rank.FOURTH.getPrize()),
                new RankResult(Rank.THIRD, 5, tally.get(Rank.THIRD), Rank.THIRD.getPrize()),
                new RankResult(Rank.SECOND, 5, tally.get(Rank.SECOND), Rank.SECOND.getPrize()),
                new RankResult(Rank.FIRST, 6, tally.get(Rank.FIRST), Rank.FIRST.getPrize())
        );

        long totalPrize = ordered.stream().mapToLong(r -> (long) r.prize() * r.count()).sum();
        double profitRate = new Money(totalPrize).profitRateComparedTo(invested);

        return new LottoResultSummary(ordered, totalPrize, profitRate);
    }
}