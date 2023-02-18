## Climbing the Leaderboard

https://www.hackerrank.com/challenges/climbing-the-leaderboard/problem?isFullScreen=true

```java
public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
    ranked = ranked.stream().distinct().sorted().collect(toList());
    List<Integer> ans = new ArrayList<>(); 
    for (Integer score : player) {
        int idx =  Collections.binarySearch(ranked, score); 
        if(idx < 0){ 
            ranked.add(-idx - 1, score); 
            ans.add(ranked.size() + idx + 1); 
        }else{ // 4
            ans.add(ranked.size() - idx);
        }
    }
   return ans;
}
```
