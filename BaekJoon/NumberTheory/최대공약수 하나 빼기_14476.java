import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
    	for(int i = 0; i < N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
		// left[i] : 0번부터 i번까지 수들의 최대공약수
		// right[i] : i번부터 N-1번까지 수들의 최대공약수
		int[] left = new int[N];
		int[] right = new int[N];
		left[0] = arr[0];
		right[N-1] = arr[N-1];
		for (int i = 1; i < N; i++) {
			left[i] = gcd(left[i-1], arr[i]);
		}
		for (int i = N-2; i >= 0; i--) {
			right[i] = gcd(right[i+1], arr[i]);
		}

		int max = -1;
		int changed = -1;
		for (int i = 0; i < N; i++) {
			int gcd = -1;
			if(i == 0){
				gcd = right[1]; // 0번 수 제외 최대공약수
			}else if(i == N-1){
				gcd = left[N-2]; // N-1번 수 제외 최대공약수
			}else{
				gcd = gcd(left[i-1], right[i+1]); // i번 수 제외 최대공약수
			}
			if(arr[i] % gcd != 0 && gcd > max){ // 나머지 수의 최대공약수가 K의 약수가 아니고 이전에 구한 최대공약수보다 큰 경우
				max = gcd;
				changed = arr[i];
			}
		}
		if(max == -1){
			System.out.println("-1");
		}else{
			System.out.println(max + " " + changed);
		}
    	
    }
    
    static int gcd(int a, int b){
        if(b == 0) return a;
        else return gcd(b, a % b);
    }

}

