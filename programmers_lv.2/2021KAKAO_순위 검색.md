## 코딩테스트 연습 2021 KAKAO BLIND RECRUITMENT 순위 검색

https://school.programmers.co.kr/learn/courses/30/lessons/72412

#### 효율성 100 (블로그 참고)

```java
import java.util.*;

class Solution {
    static HashMap<String, ArrayList<Integer>> infoMap;
    public int[] solution(String[] info, String[] query) {
        int[] ans = new int[query.length];
        
        // info 모든 경우의 수 저장
        infoMap = new HashMap<>();
        for(int i = 0; i< info.length; i++) {
            dfs("",0, info[i].split(" "));
        }
        
        // map에 저장된 점수 list 오름차순 정렬
        for (ArrayList<Integer> value : infoMap.values()) {
            Collections.sort(value);
        }
        
        // 이분 탐색
        for (int i = 0; i < query.length; i++) {
            query[i] = query[i].replaceAll(" and ", "");
            String[] str = query[i].split(" ");
            int score = Integer.parseInt(str[1]);
            ans[i] = search(str[0], score);
        }
        return ans;
    }

    int search(String str, int score){
        if(!infoMap.containsKey(str)) return 0;
        List<Integer> scoreList = infoMap.get(str);
        int s = 0;
        int e = scoreList.size() - 1;
        while(s <= e){
            int mid = (s + e)/2;
            if(scoreList.get(mid) < score) {
                s = mid + 1;
            }
            else{
                e = mid - 1;
            }
        }
        return scoreList.size() - s;
    }
    
    void dfs(String key, int depth, String[] info) {
        if(depth == 4) {
            if(!infoMap.containsKey(key)) {
                ArrayList<Integer> score = new ArrayList<>();
                score.add(Integer.parseInt(info[4]));
                infoMap.put(key, score);
            }else {
                infoMap.get(key).add(Integer.parseInt(info[4]));
            }
            return;
        }
        dfs(key + "-", depth + 1, info);
        dfs(key + info[depth], depth + 1, info);
    }
}
```

***

#### 첫 풀이: 정확성 100, 효율성 0

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int[] solution(String[] info, String[] query) {
        
        // info 저장
        ArrayList<String>[] candidates = new ArrayList[info.length];
        for (int i = 0; i < info.length; i++) {
            candidates[i] = new ArrayList<>(Arrays.asList(info[i].split(" ")));
        }
        Arrays.sort(candidates, new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                return Integer.parseInt(o2.get(4)) - Integer.parseInt(o1.get(4));
            }
        });

        // query 저장
        ArrayList<String>[] requirements = new ArrayList[query.length];
        for (int i = 0; i < query.length; i++) {
            requirements[i] = new ArrayList<>(Arrays.asList(query[i].split(" and | ")));
        }

        int[] ans = new int[query.length];
        int idx = 0;
        for (ArrayList<String> requirement : requirements) {
            for (ArrayList<String> candidate : candidates) {
                boolean right = true;
                if(Integer.parseInt(requirement.get(4)) <= Integer.parseInt(candidate.get(4))){
                    for (int i = 0; i < 4; i++) {
                        if(!requirement.get(i).equals("-") && !requirement.get(i).equals(candidate.get(i))){
                            right = false;
                            break;
                        }
                    }
                }else{
                    right = false;
                }
                if(right) ans[idx]++;
            }
            idx++;
        }

        return ans;
    }
}
```
