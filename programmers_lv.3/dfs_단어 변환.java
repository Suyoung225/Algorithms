class Solution {
    static int ans = Integer.MAX_VALUE;
    static boolean[] visited;
  
    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];
        dfs(begin, target, words, 0);
        return (ans == Integer.MAX_VALUE) ? 0 : ans;
    }
    
    void dfs(String word, String target, String[] words, int cnt){
        if(word.equals(target)){
            ans = Math.min(ans, cnt);
            return;
        }
        for(int i = 0; i < words.length; i++){
            if(!visited[i] && changeable(word, words[i])){
                visited[i] = true;
                dfs(words[i], target, words, cnt + 1);
                visited[i] = false;
            }
        }
    
    }
    
    boolean changeable(String word, String targetWord){
        int diffCnt = 0;
        for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) != targetWord.charAt(i)) diffCnt++;
            if(diffCnt > 1) return false;
        }
        return true;
    }
    
}
