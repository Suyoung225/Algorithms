## 코딩테스트 연습 2023 KAKAO BLIND RECRUITMENT 이모티콘 할인행사


```java
class Solution {
    static int[] result;
    static int[] discount;
    public int[] solution(int[][] users, int[] emoticons) {
        discount = new int[emoticons.length];
        result = new int[2];
        dfs(users, emoticons, 0);
        return result;
    }
    
    void dfs(int[][] users, int[] emoticons, int depth){
        if(depth == emoticons.length) {
            compare(sales(users, emoticons));
            return;
        }

        for (int i = 10; i <= 40; i+=10) {
            discount[depth] = i;
            dfs(users, emoticons,  depth+1);
        }
    }
    
    void compare(int[] temp){
        if(temp[0] > result[0]){
            result[0] = temp[0];
            result[1] = temp[1];
        }else if(temp[0] == result[0] && temp[1] > result[1]){
            result[1] = temp[1];
        }
    }

    int[] sales(int[][] users, int[] emoticons){
        int[] result = new int[2];
        for (int[] user : users) {
            int total = 0;
            for (int j = 0; j < emoticons.length; j++) {
                if (user[0] <= discount[j]) {
                    total += emoticons[j] * (100 - discount[j]) / 100;
                }
            }
            if (user[1] <= total) {
                result[0]++;
            } else {
                result[1] += total;
            }
        }
        return result;
    }
}
```
***

#### 중복 순열

```java
void dfs(int depth){
    if(depth == length) {
        list.add(arr);
        return;
    }

    for (int i = 0; i <= target.length; i++) {
        arr[depth] = target[i];
        dfs(depth+1);
    }
}
```

[조합, 순열](https://velog.io/@cgw0519/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EC%88%9C%EC%97%B4-%EC%A4%91%EB%B3%B5%EC%88%9C%EC%97%B4-%EC%A1%B0%ED%95%A9-%EC%A4%91%EB%B3%B5%EC%A1%B0%ED%95%A9-%EC%B4%9D%EC%A0%95%EB%A6%AC)
