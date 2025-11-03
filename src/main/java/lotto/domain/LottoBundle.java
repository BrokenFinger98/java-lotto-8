package lotto.domain;

import java.util.List;

public final class LottoBundle {
    private final List<Lotto> tickets;

    public LottoBundle(List<Lotto> tickets) {
        this.tickets = List.copyOf(tickets);
    }

    public int size() {
        return tickets.size();
    }

    public List<Lotto> asList() {
        return tickets;
    }
}