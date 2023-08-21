import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] numbers = new int[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}

		PriorityQueue<Integer> pqBigger = new PriorityQueue<>(); // 큰 수들 중 작은 수가 top
		PriorityQueue<Integer> pqSmaller = new PriorityQueue<>(Comparator.reverseOrder()); // 작은 수들 중 큰 수가 top

		StringBuilder ans = new StringBuilder();
		for (int i = 0; i < N; i++) {
			if(pqBigger.size() == pqSmaller.size()){
				pqSmaller.add(numbers[i]);
			}else{
				pqBigger.add(numbers[i]);
			}
			if(!pqBigger.isEmpty() && !pqSmaller.isEmpty() && pqBigger.peek() < pqSmaller.peek()){
				// 교환
				int temp = pqSmaller.poll();
				pqSmaller.add(pqBigger.poll());
				pqBigger.add(temp);
			}
			ans.append(pqSmaller.peek()).append("\n");
		}
		System.out.println(ans);
	}

}



