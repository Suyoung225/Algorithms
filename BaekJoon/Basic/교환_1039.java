import java.util.*;

public class Main{

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String N = sc.next();
		int K = sc.nextInt();
		// N 자릿수
		int M = N.length();

		Queue<String> queue = new LinkedList<>();
		queue.add(N);

		for (int k = 0; k < K; k++) { // K번 교환
			// 같은 레벨에서 중복 제거
			Set<String> set = new HashSet<>();
			int qSize = queue.size();
			for (int q = 0; q < qSize; q++) { // while 아님!!
				String qNum = queue.poll();
				if(set.contains(qNum)) continue; // 중복 연산 제거
				set.add(qNum);

				for (int i = 0; i < M-1; i++) {
					for (int j = i + 1; j < M; j++) {
						if(i == 0 && qNum.charAt(j) == '0') // 바꿨을 때 앞이 0이면 안됨
							continue;
						queue.add(swap(qNum, i, j));
					}
				}
			}
		}
    
		int maxValue = 0;
		while(!queue.isEmpty()){
			maxValue = Math.max(Integer.parseInt(queue.poll()), maxValue);
		}
    
		if(maxValue == 0) System.out.println("-1");
		else System.out.println(maxValue);
    
	}
  
	static String swap(String qNum, int i, int j){
		char[] charN = qNum.toCharArray();
		char temp = charN[i];
		charN[i] = charN[j];
		charN[j] = temp;
		return String.valueOf(charN);
	}


}

