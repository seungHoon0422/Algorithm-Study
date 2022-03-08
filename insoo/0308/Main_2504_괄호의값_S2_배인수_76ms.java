import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;


class Main_2504_괄호의값_S2_배인수_76ms {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> stack = new Stack<>();

        char[] bracket = br.readLine().toCharArray();

        char before = ' ';
        int mul = 1;
        int result = 0;
        boolean isBalance = true;
        for (char c :
                bracket) {

            // 여는 괄호에서 이전 것이 여는 괄호면 mul에 곱 축적해줌
            if (c == '(' || c == '[') {
                switch (before) {
                    case '(':
                        mul *= 2;
                        break;
                    case '[':
                        mul *= 3;
                        break;
                } // end of switch
                stack.add(c);
                before = c;
            // 닫는 괄호면
            } else {
                // 닫는데 비어 있으면 입력 올바르지 않음
                if (stack.isEmpty()) {
                    isBalance = false;
                    break;
                // 이전 것이 닫는 괄호였다면 mul 나눠줌
                // 이전 것이 여는 괄호였다면 result에 더해줌
                } else if (c == ')') {
                    if (stack.peek() != '(') {
                        isBalance = false;
                        break;
                    } else {
                        if (before == ']' || before == ')') mul /= 2;
                        else result += 2 * mul;
                    }
                } else if (c == ']') {
                    if (stack.peek() != '[') {
                        isBalance = false;
                        break;
                    } else {
                        if (before == ']' || before == ')') mul /= 3;
                        else result += 3 * mul;
                    }
                } // end of if-else

                stack.pop();
                before = c;
            } // end of if-else
        } // end of foreach
        if (!isBalance) {
            System.out.println(0);
        } else {
            if (stack.isEmpty()) {
                System.out.println(result);
            } else {
                System.out.println(0);
            }
        }
    } // end of main

}
