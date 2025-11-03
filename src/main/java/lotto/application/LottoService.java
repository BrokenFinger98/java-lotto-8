package lotto.application;

import lotto.domain.*;

public final class LottoService {
    private final LottoMachine machine;

    public LottoService(LottoNumbersPicker picker) {
        this.machine = new LottoMachine(picker);
    }

    public LottoBundle buyTickets(Money money) {
        return machine.buy(money);
    }

    public LottoResultSummary summarize(LottoBundle bundle, WinningNumbers winning, Money invested) {
        return new LottoRound(winning).summarize(bundle, invested);
    }
}