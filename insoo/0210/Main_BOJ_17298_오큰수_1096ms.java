import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main_BOJ_17298_오큰수_1096ms {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Queue<Pair> queue = new LinkedList<>();
        // 현재 지정된 수 + 지정된 수 보다 작은 수들
        Stack<Pair> stack = new Stack<>();

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            queue.offer(new Pair(Integer.parseInt(st.nextToken()), i));
        } // end of for


        while (!queue.isEmpty()) {
            // 스택에 암것도 없으면
            if (stack.isEmpty()) {
                stack.push(queue.poll());
            }
            // 현재 수 보다 작으면 집어 넣어버려서 다음 오큰수 찾으러...
            while (!stack.isEmpty() && !queue.isEmpty() && stack.peek().val >= queue.peek().val) {
                stack.push(queue.poll());
            }
            // 현재 수 보다 크면 오큰수
            while (!stack.isEmpty() && !queue.isEmpty() && stack.peek().val < queue.peek().val) {
                arr[stack.pop().idx] = queue.peek().val;
            }
        } // end of while
        // 스택 남았는데도 없으면 // 7,6,5,4,3,2,1
        while (!stack.isEmpty()) {
            arr[stack.pop().idx] = -1;
        }
        for (int i = 0; i < n; i++) {
            sb.append(arr[i]).append(' ');
        }
        System.out.println(sb);
    } // end of main
} // end of class

class Pair {
    int val;
    int idx;

    public Pair(int val, int idx) {
        this.val = val;
        this.idx = idx;
    }
}
