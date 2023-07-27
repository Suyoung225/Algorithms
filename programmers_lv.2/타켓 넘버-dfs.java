class Solution {
    static int total, len; 
    public int solution(int[] numbers, int target) {
        len = numbers.length;
        dfs(0, 0, numbers, target);
        return total;
    }
    void dfs(int sum, int idx, int[] numbers, int target){
        if(idx == len){
            if(sum == target) total++;
        }
        if(idx < len){
            dfs(sum + numbers[idx], idx+1, numbers, target);
            dfs(sum - numbers[idx], idx+1, numbers, target);
        }
    }
}
