## 코딩테스트 연습 2020 KAKAO BLIND RECRUITMENT 블록 이동하기

https://school.programmers.co.kr/learn/courses/30/lessons/60063#qna

dfs로는 최소 깊이를 구하기 어려움

```java
import java.util.LinkedList;
import java.util.Queue;


class Solution {
    public int solution(int[][] board) {
        int n = board.length;
        boolean[][] map = new boolean[n+1][n+1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i+1][j+1] = board[i][j] == 0;
            }
        }
        boolean[][][][] visited = new boolean[n+1][n+1][n+1][n+1];
        Queue<Node> queue = new LinkedList<>();
        int cnt = 0;
        queue.add(new Node(1,1,1,2, 0));
        while(!queue.isEmpty()){
            Node node = queue.poll();
            int i1 = node.i1;
            int i2 = node.i2;
            int j1 = node.j1;
            int j2 = node.j2;
            cnt = node.cnt;
            if(visited[i1][j1][i2][j2]) continue;
            visited[i1][j1][i2][j2] = true;
            if(i2 == n && j2 == n) break;
            if(i1 == i2){ // 로봇이 평행
                if(i1 != 1 && map[i1-1][j2] && map[i1-1][j1]){ // 왼쪽 기준 위로
                    queue.add(new Node( i1-1, j1,i1,j1, cnt+1));
                }if(i1 != n && map[i1+1][j2] && map[i1+1][j1]){ // 왼쪽 기준 아래로
                    queue.add(new Node(i1,j1, i1+1, j1, cnt+1));
                }if(i1 != 1 && map[i1-1][j1] && map[i1-1][j2]){ // 오른쪽 기준 위로
                    queue.add(new Node(i1-1,j2,i2,j2, cnt+1));
                }if(i1 != n && map[i1+1][j1] && map[i1+1][j2]){ // 오른쪽 기준 아래로
                    queue.add(new Node(i2,j2,i1+1,j2, cnt+1));
                }
            }else{ // 로봇 수직
                if(j1 != 1 && map[i2][j1-1] && map[i1][j1-1]){ // 위쪽 기준 왼쪽으로
                    queue.add(new Node(i1,j1-1,i1,j1, cnt+1));
                }if(j1 != n && map[i2][j1+1] && map[i1][j1+1]){ // 위쪽 기준 오른쪽으로
                    queue.add(new Node(i1,j1,i1,j1+1, cnt+1));
                }if(j1 != 1 && map[i1][j1-1] && map[i2][j1-1]){ // 아래쪽 기준 왼쪽으로
                    queue.add(new Node(i2,j1-1,i2,j2, cnt+1));
                }if(j1 != n && map[i1][j1+1] && map[i2][j1+1]){ // 아래쪽 기준 오른쪽으로
                    queue.add(new Node(i2,j2,i2,j1+1, cnt+1));
                }
            }
            if(i1 != 1 && map[i1-1][j1] && map[i2-1][j2]){ // 상
                queue.add(new Node(i1-1,j1,i2-1,j2, cnt+1));
            }
            if(i2 != n && map[i1+1][j1] && map[i2+1][j2]){ // 하
                queue.add(new Node(i1+1,j1,i2+1,j2, cnt+1));
            }
            if(j1 != 1 && map[i1][j1-1] && map[i2][j2-1]){ // 좌
                queue.add(new Node(i1,j1-1,i2,j2-1, cnt+1));
            }
            if(j2 != n && map[i1][j1+1] && map[i2][j2+1]){ // 우
                queue.add(new Node(i1,j1+1,i2,j2+1, cnt+1));
            }

        }
        return cnt;
    }
}

class Node{
    int i1, j1, i2, j2, cnt;

    public Node(int i1, int j1, int i2, int j2, int cnt) {
        this.i1 = i1;
        this.j1 = j1;
        this.i2 = i2;
        this.j2 = j2;
        this.cnt = cnt;
    }
}
```
