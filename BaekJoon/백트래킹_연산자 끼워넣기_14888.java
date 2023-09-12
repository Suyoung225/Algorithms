import java.util.Scanner;

public class Main {

	public static int max = Integer.MIN_VALUE;
	public static int min = Integer.MAX_VALUE;
	public static int[] operator = new int[4];
	public static int[] numbers;
	public static int N;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		N = scanner.nextInt();
		numbers = new int[N];

		// 숫자 입력
		for (int i = 0; i < N; i++) {
			numbers[i] = scanner.nextInt();
		}

		// 연산자 입력
		for (int i = 0; i < 4; i++) {
			operator[i] = scanner.nextInt();
		}

		dfs(numbers[0], 1);

		System.out.println(max);
		System.out.println(min);

	}

	public static void dfs(int num, int idx) {
		if (idx == N) {
			max = Math.max(max, num);
			min = Math.min(min, num);
			return;
		}

		for (int i = 0; i < 4; i++) {
			// 연산자 개수가 0 미만인 경우
			if (operator[i] == 0) continue;

			// 해당 연산자 개수 1 감소
			operator[i]--;

			switch (i) {
				case 0:	dfs(num + numbers[idx], idx + 1); break;
				case 1:	dfs(num - numbers[idx], idx + 1); break;
				case 2:	dfs(num * numbers[idx], idx + 1); break;
				case 3:	dfs(num / numbers[idx], idx + 1); break;

			}
			// 재귀호출이 종료되면 해당 연산자 개수를 복구
			operator[i]++;
		}
	}
}

