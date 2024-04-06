// https://www.acmicpc.net/problem/16637
import java.util.Scanner;

class Main
{
    static int maxNum = Integer.MIN_VALUE;
    static char[] operators;
    static int[] num;
    static int N;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
        char[] chars = sc.next().toCharArray();
        num = new int[T/2 + 1];
        operators = new char[T/2];
        N = num.length;
        for (int i = 0; i < T; i++) {
            if (i % 2 == 0) { // 숫자
                num[i / 2] = chars[i] - '0';
            } else {
                operators[i / 2] = chars[i];
            }
        }
        getMaxNum(num[0], 1);

        System.out.println(maxNum);
    }

    static void getMaxNum(int total, int nextIdx) {
        if (nextIdx == N) {
            maxNum = Math.max(maxNum, total);
            return;
        }

        int totalValue = calculate(total, num[nextIdx], operators[nextIdx - 1]);
        getMaxNum(totalValue, nextIdx + 1);

        if(nextIdx + 1 < N) {
            int calculatedValue = calculate(num[nextIdx], num[nextIdx+1], operators[nextIdx]);
            totalValue = calculate(total, calculatedValue, operators[nextIdx - 1]);
            getMaxNum(totalValue, nextIdx + 2);
        }

    }

    static int calculate(int left, int right, char operator) {
        switch(operator) {
            case '+' : return left + right;
            case '-' : return left - right;
            case '*' : return left * right;
        }
        return 0;
    }
}
