import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line = br.readLine();

    StringBuilder sb = new StringBuilder();
    Stack<Character> stack = new Stack<>();

    for (char c : line.toCharArray()) {
      if (c >= 'A' && c <= 'Z') {
        sb.append(c);
      } else if (c == '+' || c == '-' || c == '*' || c == '/') {
        while (!stack.isEmpty() && priority(stack.peek()) >= priority(c)) {
          sb.append(stack.pop());
        }
        stack.push(c);
      } else if (c == '(') {
        stack.push(c);
      } else if (c == ')') {
        while (!stack.isEmpty() && stack.peek() != '(') {
          sb.append(stack.pop());
        }
        stack.pop();
      }
    }

    while(!stack.isEmpty()) {
      sb.append(stack.pop());
    }
    System.out.println(sb.toString());
  }
  
  static int priority(char operator) {
    if (operator == '+' || operator == '-') {
      return 1;
    } else if (operator == '*' || operator == '/') {
      return 2;
    }
    return -1;
  }

}
