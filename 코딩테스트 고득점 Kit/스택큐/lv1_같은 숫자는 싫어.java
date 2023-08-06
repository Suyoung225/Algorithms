import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Stack<Integer> stack = new Stack<>();
        for(int i : arr){
            while(!stack.isEmpty() && stack.peek() == i){
                stack.pop();
            }
            stack.push(i);
        }
        int[] ans = new int[stack.size()];
        for(int i = ans.length - 1; i >= 0; i--){
            ans[i] = stack.pop();
        }
        return ans;
    }
}
