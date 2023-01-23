## 코딩테스트 연습 2020 KAKAO BLIND RECRUITMENT 자물쇠와 열쇠

https://school.programmers.co.kr/learn/courses/30/lessons/60059

#### 비트 연산자 XOR 이용

```java
class Solution {
    static int m,n;
    public boolean solution(int[][] key, int[][] lock) {
        m = key.length;
        n = lock.length;
        int len = n + 2 *(m - 1);
        int[][] board = new int[len][len];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[m+i-1][m+j-1] = lock[i][j]; // board 배열 중앙에 lock 값 저장
            }
        }
        isUnlocked(board);
        
        int rotate = 0; // 회전 총 네 번
        while(rotate < 4){
            for (int i = 0; i < n+m-1; i++) {
                for (int j = 0; j < n+m-1; j++) {

                    for (int k = 0; k < m; k++) {
                        for (int l = 0; l < m; l++) {
                            board[i + k][j + l] ^= key[k][l];
                        }
                    }
                    if(isUnlocked(board)) return true;
                    
                    // board 배열 원상복구 
                    for (int k = 0; k < m; k++) {
                        for (int l = 0; l < m; l++) {
                            board[i + k][j + l] ^= key[k][l];
                        }
                    }
                }
            }
            key = rotate(key);
            rotate++;
        }
        return false;
    }
    
    boolean isUnlocked(int[][] board){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i+m-1][j+m-1] == 0) return false;
            } // xor 연산 후 board 중앙 부분에 0 :
        } // lock 홈이 안 채워졌거나, key와 lock의 홈과 돌기가 충돌된 경우 -> fail
        return true;
    }

    int[][] rotate(int[][] arr) {
        int[][] rotate = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                rotate[i][j] = arr[m-1-j][i];
            }
        }
        return rotate;
    }

}
```

***


#### 첫 풀이

```java
class Solution {
    static int m,n;
    public boolean solution(int[][] key, int[][] lock) {
        m = key.length;
        n = lock.length;
        int[][] arr1 = key;
        int[][] arr2 = rotate(arr1); // 90도
        int[][] arr3 = rotate(arr2); // 180도
        int[][] arr4 = rotate(arr3); // 270도
        int[][] lockNew = new int[m+n][m+n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                lockNew[m+i][m+j] = lock[i][j];
            }
        }
        if(isUnlocked(arr1, lockNew) || isUnlocked(arr2, lockNew) || isUnlocked(arr3, lockNew) || isUnlocked(arr4, lockNew)){
            return true;
        }
        return false;
    }
    boolean isUnlocked(int[][] key, int[][] lock){
        for (int i = 1; i < n+m; i++) { // 밑으로
            for (int j = 1; j < n+m; j++) { // 오른쪽
                if(isPassed(i, j, key, lock)) return true;
            }
        }
        return false;
    }

    boolean isPassed(int i, int j, int[][] key, int[][] lock) {
        for (int k = m; k < m+n; k++) {
            for (int l = m; l < m+n; l++) {
                if(k-i < 0 || k-i >= m || l-j < 0 || l-j >= m){ // lock에 0을 못 채우는 경우
                    if(lock[k][l] == 0) return false;
                }else{
                    if(lock[k][l] == 1){
                        if(key[k-i][l-j] == 1) return false;
                    }else{
                        if(key[k-i][l-j] == 0) return false;
                    }
                }
            }
        }
        return true;
    }

    int[][] rotate(int[][] arr) {
        int[][] rotate = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                rotate[i][j] = arr[m-1-j][i];
            }
        }
        return rotate;
    }
}
```

