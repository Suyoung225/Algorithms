## Coding Test Practice Summer/Winter Coding(~2018) - 영어 끝말잇기

https://school.programmers.co.kr/learn/courses/30/lessons/12981

```java
import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        List<String> wordList = new ArrayList<>();
        wordList.add(words[0]);
        for (int i = 1; i < words.length; i++) {
            if(words[i-1].charAt(words[i-1].length()-1)!=words[i].charAt(0) || wordList.contains(words[i])){
                return new int[]{i%n+1, i/n+1};
            }
            wordList.add(words[i]);
        }
        return new int[]{0, 0};
    }
}
```

### HashSet 이용

```java
import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        HashSet<String> wordSet = new HashSet<>();
        wordSet.add(words[0]);
        for (int i = 1; i < words.length; i++) {
            wordSet.add(words[i]);
            if(words[i-1].charAt(words[i-1].length()-1)!=words[i].charAt(0) || wordSet.size()!=i+1){
                return new int[]{i%n+1, i/n+1};
            }
        }
        return new int[]{0, 0};
    }
}
```
