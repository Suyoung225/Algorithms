import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] line = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();
        char before = line[0];
        int sum = 0;
        int value = 1;
        for (char c : line) {
            if(c == '('){
                stack.push(c);
                value *= 2;
            }else if(c == '['){
                stack.push(c);
                value *= 3;
            }else if(c == ')'){
                if(stack.isEmpty() || stack.peek() != '('){
                    System.out.println("0");
                    return;
                }else if(before == '('){
                    sum += value;
                }
                stack.pop();
                value /= 2;
            }else if(c == ']'){
                if(stack.isEmpty() || stack.peek() != '['){
                    System.out.println("0");
                    return;
                }else if(before == '['){
                    sum += value;
                }
                stack.pop();
                value /= 3;
            }
            before = c;
        }
        if(!stack.isEmpty()) System.out.println("0");
        else System.out.println(sum);

    }

}

