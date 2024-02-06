import java.util.*;

class Solution {
    private char[] chars = {'A', 'E', 'I', 'O', 'U'};
    public int solution(String word) {
        List<String> words = new ArrayList<>();
        generate("", words);
        return words.indexOf(word);
    }
    
    private void generate(String word, List<String> words) {
        words.add(word);
        if(word.length() == 5) return;
        for (char c : chars) {
            generate(word + c, words);
        }
    }
}
