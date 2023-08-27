# LCS
![스크린샷 2023-08-27 093842](https://github.com/Suyoung225/Algorithms/assets/87157566/b9ff87a4-e7fd-4cf8-b61c-39a52c1591a7)


$$LCS(X_i, Y_j) = LCS(X_{i-1}, Y_{j-1}) + 1, \quad X_i = Y_j $$ 

$$LCS(X_i, Y_j) = max (LCS(X_{i-1}, Y_{j}), LCS(X_{i}, Y_{j-1})), \quad X_i \neq Y_j $$

참고 블로그: https://st-lab.tistory.com/139

https://www.acmicpc.net/problem/9251

```java
import java.util.Scanner;

public class Main{

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] str1 = sc.nextLine().toCharArray();
		char[] str2 = sc.nextLine().toCharArray();;
		int n = str1.length;
		int m = str2.length;
		int[][] dp = new int[n+1][m+1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if(str1[i-1] == str2[j-1]){
					dp[i][j] = dp[i-1][j-1] + 1;
				}else{
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}

		System.out.println(dp[n][m]);
	}

}




```
