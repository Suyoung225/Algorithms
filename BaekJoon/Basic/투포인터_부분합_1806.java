import java.util.*;

public class Main {

    public static void main(String[] args) {
        // 스캐너 객체 생성
        Scanner sc = new Scanner(System.in);

        // 배열의 크기 및 목표 합 입력 받기
        int N = sc.nextInt();
        int S = sc.nextInt();

        // 배열 선언 및 입력 받기
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        // 최소 길이 변수 및 인덱스 초기화
        int len = Integer.MAX_VALUE;
        int h = 0;
        int l = 0;
        int sum = arr[0];

        // 투 포인터 알고리즘을 사용한 배열 탐색
        while (l <= h && h < N) {
            if (sum < S) {
                // 현재 부분합이 목표 합 미만이면 우측 포인터 이동
                if (h == N - 1) {
                    h++;
                } else {
                    sum += arr[++h];
                }
            } else {
                // 현재 부분합이 목표 합 이상이면 최소 길이 갱신 및 좌측 포인터 이동
                len = Math.min(len, h - l + 1);
                sum -= arr[l++];
            }
        }

        // 만약 최소 길이가 갱신되지 않았으면 0으로 설정
        if (len == Integer.MAX_VALUE) {
            len = 0;
        }

        // 결과 출력
        System.out.println(len);
    }
}
