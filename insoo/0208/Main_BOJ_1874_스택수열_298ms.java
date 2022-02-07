import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main_BOJ_1874_스택수열_298ms {
    static int[] stack;
    static int pos = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(bf.readLine());
        int[] arr = new int[n];
        stack = new int[n];
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(bf.readLine());
            arr[i] = a;
        }
        int k = 0;
        for (int i = 1; k != n; ) {
            if (arr[k] >= i) {
                push(i++);
                sb.append('+').append('\n');
            } else if (arr[k] == top()) {
                pop();
                k++;
                sb.append('-').append('\n');
            } else {
                System.out.println("NO");
                return;
            } // end of if-else
        } // end of for

        System.out.println(sb);
        bf.close();
    }

    static void push(int x) {
        stack[pos++] = x;
    }

    static void pop() {
        pos--;
    }

    static int top() {
        return stack[pos - 1];
    }
}
