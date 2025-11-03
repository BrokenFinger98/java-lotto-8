package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @DisplayName("금액이 0 이하이면 예외가 발생한다.")
    @Test
    void 금액이_0_이하이면_예외가_발생한다() {
        assertThatThrownBy(() -> new Money(0))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Money(-1000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("금액이 1,000원 단위가 아니면 예외가 발생한다.")
    @Test
    void 금액이_1_000원_단위가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> new Money(1250))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("현재 금액으로 구매 가능한 로또 개수를 반환한다.")
    @Test
    void 현재_금액으로_구매_가능한_로또_개수를_반환한다() {
        // given
        Money money = new Money(8000);
        Money money2 = new Money(14000);

        // when & then
        assertThat(money.purchasableTicketCount()).isEqualTo(8);
        assertThat(money2.purchasableTicketCount()).isEqualTo(14);
    }

    @DisplayName("투자 금액 대비 수익률을 소수점 한 자리로 반올림하여 계산한다.")
    @Test
    void 투자_금액_대비_수익률을_소수점_한_자리로_반올림하여_계산한다() {
        // given
        Money invested = new Money(8000);
        Money prize = new Money(5000);

        // when & then
        assertThat(prize.profitRateComparedTo(invested)).isEqualTo(62.5);
    }
}