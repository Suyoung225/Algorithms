## Coding Test Practice 2022 KAKAO BLIND RECRUITMENT 신고 결과 받기

https://school.programmers.co.kr/learn/courses/30/lessons/92334


```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {

        ArrayList<String> id_arrayList = new ArrayList<>(List.of(id_list));
        int[][] matrix = new int[id_list.length][id_list.length];
        int idx1; 
        int idx2;
        for (String s : report) {
            idx1 = id_arrayList.indexOf(s.split(" ")[0]); // 신고한 사람
            idx2 = id_arrayList.indexOf(s.split(" ")[1]); // 신고 당한 사람
            matrix[idx1][idx2] = 1; // 중복 신고 안 됨
        }
        int sum = 0;
        boolean [] isReported = new boolean[id_list.length]; // 신고 당한 횟수 k 이상인 경우: true        
        for (int i = 0; i < id_list.length; i++) {
            for (int j = 0; j < id_list.length; j++) {
                sum += matrix[j][i];
            }
            if(sum >= k) isReported[i] = true;
            sum = 0;
        }

        int [] result = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            for (int j = 0; j < id_list.length; j++) {
                if(matrix[i][j]==1 & isReported[j]){
                    result[i]++;
                }
            }
        }
        return result;

    }
}
```
