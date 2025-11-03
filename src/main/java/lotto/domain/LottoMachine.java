package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public final class LottoMachine {
    private final LottoNumbersPicker picker;

    public LottoMachine(LottoNumbersPicker picker) {
        this.picker = picker;
    }

    public LottoBundle buy(Money money) {
        int count = money.purchasableTicketCount();
        List<Lotto> tickets = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            tickets.add(new Lotto(picker.pickNumbersInRange()));
        }
        return new LottoBundle(tickets);
    }
}