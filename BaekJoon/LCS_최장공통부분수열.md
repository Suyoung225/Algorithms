# LCS
![스크린샷 2023-08-27 093842](https://github.com/Suyoung225/Algorithms/assets/87157566/b9ff87a4-e7fd-4cf8-b61c-39a52c1591a7)


$$LCS(X_i, Y_j) = LCS(X_{i-1}, Y_{j-1}) + 1, \quad X_i = Y_j $$ 

$$LCS(X_i, Y_j) = max (LCS(X_{i-1}, Y_{j}), LCS(X_{i}, Y_{j-1})), \quad X_i \neq Y_j $$

참고 블로그: https://st-lab.tistory.com/139

https://www.acmicpc.net/problem/9251

```java
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    char[] str1 = sc.nextLine().toCharArray();
    char[] str2 = sc.nextLine().toCharArray();
    ;
    int n = str1.length;
    int m = str2.length;
    int[][] dp = new int[n + 1][m + 1];

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (str1[i - 1] == str2[j - 1]) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    System.out.println(dp[n][m]);
  }

}
```

## LCS 문자열 찾기 
https://www.acmicpc.net/problem/9252
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr1 = br.readLine().toCharArray();
        char[] arr2 = br.readLine().toCharArray();
        int[][] D = new int[arr1.length + 1][arr2.length + 1];
        for (int i = 1; i <= arr1.length; i++) {
            for (int j = 1; j <= arr2.length; j++) {
                if(arr1[i-1] == arr2[j-1])
                    D[i][j] = D[i - 1][j - 1] + 1; // 첫 번째 줄도 점화식으로 구하기 위해 인덱스 1부터
                else
                    D[i][j] = Math.max(D[i-1][j], D[i][j-1]); // 왼쪽, 위쪽 중 큰 수

            }
        }
        System.out.println(D[arr1.length][arr2.length]);

        StringBuilder sb = new StringBuilder();
        int i = arr1.length;
        int j = arr2.length;
        while(i >= 1 && j >= 1){
            if(D[i][j] == D[i-1][j]) // 위와 같을 때 위로 이동
                i--;
            else if(D[i][j] == D[i][j-1]) // 왼쪽과 같을 때 왼쪽으로 이동
                j--;
            else{
                sb.append(arr1[i - 1]);
                i--; j--; // 대각선으로 이동
            }
        }
        System.out.println(sb.reverse());
    }
}

```

## 3차원 LCS 
https://www.acmicpc.net/problem/1958

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str1 = br.readLine();
    String str2 = br.readLine();
    String str3 = br.readLine();
    char[] arr1 = str1.toCharArray();
    char[] arr2 = str2.toCharArray();
    char[] arr3 = str3.toCharArray();
    int n = arr1.length;
    int m = arr2.length;
    int l = arr3.length;
    int[][][] dp = new int[n + 1][m + 1][l + 1];
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        for (int k = 1; k <= l ; k++) {
          if (arr1[i - 1] == arr2[j - 1] && arr2[j - 1] == arr3[k - 1]) {
            dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
          } else {
            dp[i][j][k] = Math.max(dp[i - 1][j][k], Math.max(dp[i][j - 1][k], dp[i][j][k - 1]));
          }
        }
      }
    }
    System.out.println(dp[n][m][l]);
  }

}



```



```
