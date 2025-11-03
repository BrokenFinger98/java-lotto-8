package lotto.presentation;

import java.util.ArrayList;
import java.util.List;

public final class Parser {

    private Parser() {}

    public static long parseMoney(String text) {
        String trimmed = text == null ? "" : text.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 구입 금액을 입력해 주세요.");
        }
        try {
            long money = Long.parseLong(trimmed);
            return money;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다.");
        }
    }

    public static List<Integer> parseWinningNumbers(String csv) {
        String line = csv == null ? "" : csv.trim();
        if (line.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호를 입력해 주세요.");
        }

        String[] tokens = line.split(",");
        if (tokens.length != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 쉼표(,)로 구분된 6개 숫자여야 합니다.");
        }

        List<Integer> numbers = new ArrayList<>(6);
        for (String token : tokens) {
            numbers.add(parseIntOrThrow(token));
        }
        return numbers;
    }

    public static int parseBonus(String text) {
        return parseIntOrThrow(text);
    }

    private static int parseIntOrThrow(String token) {
        String t = token == null ? "" : token.trim();
        if (t.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 숫자 형식이 올바르지 않습니다.");
        }
        try {
            return Integer.parseInt(t);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자 형식이 올바르지 않습니다.");
        }
    }
}