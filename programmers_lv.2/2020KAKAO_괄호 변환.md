## 코딩테스트 연습 2020 KAKAO BLIND RECRUITMENT 괄호 변환


```java
import java.util.Stack;

class Solution {
    public String solution(String p) {
        if(p == null || isRight(p)) return p;
        String u = p.substring(0,balanced(p));
        String v = p.substring(balanced(p));
        StringBuilder ans = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        if(isRight(u)){
            return u + solution(v);
        }
        else{
            sb.append("(").append(solution(v)).append(")");
            for (int i = 1; i < u.length()-1; i++) {
                sb.append((u.charAt(i)=='(')? ')':'(' );
            }
        }
        ans.append(sb);
        return ans.toString();
    }

    int balanced(String str){
        int left = 0;
        int right = 0;
        for (char c : str.toCharArray()) {
            switch (c) {
                case '(' -> left++;
                case ')' -> right++;
            }
            if(left == right) return 2 * left;
        }
        return 0;
    }
    boolean isRight(String str){
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if(c == '('){
                stack.push(c);
            }else if(stack.isEmpty()){
                return false;
            }else{
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
```
