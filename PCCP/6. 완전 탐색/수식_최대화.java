import java.util.*;

class Solution {
    // 우선순위 모든 경우의 수
    private static final String[][] precedences = {
        "+-*".split(""),
        "+*-".split(""),
        "-+*".split(""),
        "-*+".split(""),
        "*+-".split(""),
        "*-+".split(""),
    };
    
    private long calculate (List<String> tokens, String[] precedence) {
        for (String op: precedence) { // 우선 순위에 따라 연산
            for (int i = 0; i < tokens.size(); i++) {
                if (tokens.get(i).equals(op)) { 
                    long left = Long.parseLong(tokens.get(i - 1));
                    long right = Long.parseLong(tokens.get(i + 1));
                    long result = calculate(left, right, op);
                    tokens.remove(i - 1);
                    tokens.remove(i - 1);
                    tokens.remove(i - 1);
                    tokens.add(i - 1, String.valueOf(result));
                    i -= 2;
                }
            }
        }
        return Long.parseLong(tokens.get(0));
    }
    
    private long calculate(long left, long right, String op) {
        return switch (op) {
                case "+" -> left + right;
                case "-" -> left - right;
                case "*" -> left * right;
                default -> 0;
        };
    }
    
    public long solution(String expression) {
        StringTokenizer st = new StringTokenizer(expression, "+-*", true);
        List<String> tokens = new ArrayList<>();
        while (st.hasMoreTokens()) {
            tokens.add(st.nextToken());
        }
        long max = 0;
        for (String[] precedence : precedences) {
            long value = Math.abs(calculate(new ArrayList<>(tokens), precedence));
            max = Math.max(max, value);
        }
        return max;
    }
}
