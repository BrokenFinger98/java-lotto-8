package lotto;

import java.util.List;
import lotto.application.LottoService;
import lotto.domain.BonusNumber;
import lotto.domain.LottoBundle;
import lotto.domain.LottoNumbersPicker;
import lotto.domain.LottoResultSummary;
import lotto.domain.Money;
import lotto.domain.WinningNumbers;
import lotto.presentation.InputView;
import lotto.presentation.OutputView;
import lotto.presentation.Parser;

public class Application {
    public static void main(String[] args) {
        LottoService service = new LottoService(new LottoNumbersPicker());

        Money money = readMoneyLoop();
        LottoBundle bundle = service.buyTickets(money);
        OutputView.printPurchased(bundle);

        WinningNumbers winning = readWinningLoop();
        LottoResultSummary summary = service.summarize(bundle, winning, money);
        OutputView.printSummary(summary);
    }

    private static Money readMoneyLoop() {
        while (true) {
            try {
                String line = InputView.readMoney();
                return new Money(Parser.parseMoney(line));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static WinningNumbers readWinningLoop() {
        while (true) {
            try {
                List<Integer> nums = Parser.parseWinningNumbers(InputView.readWinningNumbers());
                BonusNumber bonus = new BonusNumber(Parser.parseBonus(InputView.readBonus()));
                return new WinningNumbers(nums, bonus);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
