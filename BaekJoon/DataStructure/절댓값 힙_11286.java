import java.io.*;
import java.util.*;

public class Main{

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		// 조건에 맞게 우선순위 큐 정렬 조건 변경
		PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) ->{
			if(Math.abs(x) != Math.abs(y)){
				return Math.abs(x) - Math.abs(y);
			}else{
				return x - y;
			}
		});

		// 입력 및 연산
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			if(num == 0){
				if(pq.isEmpty()) System.out.println("0");
				else System.out.println(pq.poll());
			}else{
				pq.add(num);
			}
		}

	}

}


