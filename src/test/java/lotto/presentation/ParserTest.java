package lotto.presentation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParserTest {

    @DisplayName("구입 금액: 숫자 문자열을 long으로 파싱한다")
    @Test
    void 구입_금액_숫자_문자열을_long으로_파싱한다() {
        // given
        long money = Parser.parseMoney("8000");

        // when & then
        assertThat(money).isEqualTo(8000L);
    }

    @DisplayName("구입 금액: 공백 또는 빈 문자열이면 예외가 발생한다")
    @Test
    void 구입_금액_빈값이면_예외() {
        assertThatThrownBy(() -> Parser.parseMoney(""))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> Parser.parseMoney("   "))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> Parser.parseMoney(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액: 숫자가 아니면 예외가 발생한다")
    @Test
    void 구입_금액_숫자가_아니면_예외() {
        assertThatThrownBy(() -> Parser.parseMoney("1000j"))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> Parser.parseMoney("8,000"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호: 쉼표로 구분된 6개의 숫자를 파싱한다")
    @Test
    void 당첨_번호_정상_파싱() {
        // given
        List<Integer> nums = Parser.parseWinningNumbers("1,2,3,4,5,6");

        // when & then
        assertThat(nums).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("당첨 번호: 공백 또는 빈 문자열이면 예외가 발생한다")
    @Test
    void 당첨_번호_빈값이면_예외() {
        assertThatThrownBy(() -> Parser.parseWinningNumbers(""))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> Parser.parseWinningNumbers("   "))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> Parser.parseWinningNumbers(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호: 6개가 아니면 예외가 발생한다")
    @Test
    void 당첨_번호_개수_6개_아니면_예외() {
        assertThatThrownBy(() -> Parser.parseWinningNumbers("1,2,3,4,5"))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> Parser.parseWinningNumbers("1,2,3,4,5,6,7"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호: 숫자 형식이 아닌 토큰이 있으면 예외가 발생한다")
    @Test
    void 당첨_번호_비숫자_토큰_예외() {
        assertThatThrownBy(() -> Parser.parseWinningNumbers("1,2,3,4,5,a"))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> Parser.parseWinningNumbers("1, 2, 3, 4, 5, a"))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @DisplayName("보너스 번호: 빈 문자열 또는 비숫자면 예외가 발생한다")
    @Test
    void 보너스_번호_비정상_입력_예외() {
        assertThatThrownBy(() -> Parser.parseBonus(""))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> Parser.parseBonus("   "))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> Parser.parseBonus("x7"))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> Parser.parseBonus(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}