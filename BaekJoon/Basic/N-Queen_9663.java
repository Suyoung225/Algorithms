import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] board; // board[i] = j : i번째 행에 있는 queen의 열 번호
	static int ans;
	static int n;

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		board = new int[n];
		backtracking(0, n);
		System.out.println(ans);
	}
	
	static void backtracking(int depth, int n){
		if(depth == n){
			ans++;
		}else{
			for (int i = 0; i < n; i++) {
				board[depth] = i;
				if(isValid(depth)){
					backtracking(depth + 1, n);
				}
			}
		}
	}
	
	static boolean isValid(int row){
		for (int i = 0; i < row; i++) {
			if(board[i] == board[row]) return false;
			if(Math.abs(i-row) == Math.abs(board[i] - board[row])) return false;
		}
		return true;
	}
}

