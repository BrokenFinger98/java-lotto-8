package lotto.presentation;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InputViewTest {

    private static PrintStream originalOut;
    private static InputStream originalIn;

    private static ByteArrayOutputStream outContent;

    @BeforeAll
    static void beforeAll() {
        originalOut = System.out;
        originalIn = System.in;

        String allInputs = String.join(System.lineSeparator(),
                "8000",
                "1,2,3,4,5,6",
                "7"
        ) + System.lineSeparator();
        System.setIn(new ByteArrayInputStream(allInputs.getBytes()));

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    static void afterAll() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Order(1)
    @DisplayName("구입 금액 입력 요청 시 안내 문구를 출력한다")
    @Test
    void 구입_금액_입력_요청_시_안내_문구를_출력한다() {
        // when
        InputView.readMoney();

        // then
        String output = outContent.toString();
        assertThat(output).contains("구입금액을 입력해 주세요.");
    }

    @Order(2)
    @DisplayName("당첨 번호 입력 요청 시 안내 문구를 출력한다")
    @Test
    void 당첨_번호_입력_요청_시_안내_문구를_출력한다() {
        // when
        InputView.readWinningNumbers();

        // then
        String output = outContent.toString();
        assertThat(output).contains("당첨 번호를 입력해 주세요.");
    }

    @Order(3)
    @DisplayName("보너스 번호 입력 요청 시 안내 문구를 출력한다")
    @Test
    void 보너스_번호_입력_요청_시_안내_문구를_출력한다() {
        // when
        InputView.readBonus();

        // then
        String output = outContent.toString();
        assertThat(output).contains("보너스 번호를 입력해 주세요.");
    }
}