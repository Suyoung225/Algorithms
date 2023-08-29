import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        // 절댓값의 오름차순으로 정렬되는 우선순위 큐(pq) 생성
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> {
            if (Math.abs(x) != Math.abs(y)) {
                return Math.abs(x) - Math.abs(y); // 절댓값이 다르면 절댓값으로 비교
            } else {
                return x - y; // 절댓값이 같으면 원래 값으로 비교
            }
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            if (num == 0) {
                if (pq.isEmpty()) {
                    System.out.println("0"); // 큐가 비어있을 때
                } else {
                    System.out.println(pq.poll()); // 최솟값 출력 및 제거
                }
            } else {
                pq.add(num); // 0이 아닌 수를 큐에 추가
            }
        }
    }
}
