package lotto.presentation;

import camp.nextstep.edu.missionutils.Console;

public final class InputView {

    private InputView() {}

    public static String readMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return Console.readLine();
    }

    public static String readWinningNumbers() {
        System.out.println();
        System.out.println("당첨 번호를 입력해 주세요.");
        return Console.readLine();
    }

    public static String readBonus() {
        System.out.println();
        System.out.println("보너스 번호를 입력해 주세요.");
        return Console.readLine();
    }
}