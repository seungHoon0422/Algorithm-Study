import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main_BOJ_1966_프린터큐_144ms {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Pair> queue = new LinkedList<>();
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int cnt = 0;
            // 문서의 개수
            int n = Integer.parseInt(st.nextToken());
            // 몇 번째로 인쇄되었는지 궁금한 문서 (시작 인덱스 0)
            int m = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine(), " ");

            // 입력
            for (int j = 0; j < n; j++) {
                int priority = Integer.parseInt(st.nextToken());
                queue.offer(new Pair(j, priority));
                priorityQueue.offer(priority);
            }

            while (!queue.isEmpty()) {
                Pair cur = queue.poll();
                if (cur.priority < priorityQueue.peek()) {
                    queue.offer(cur);
                } else {
                    cnt++;
                    priorityQueue.poll();
                    if (cur.idx == m) break;
                }
            } // end of while
            sb.append(cnt).append('\n');
            queue.clear();
            priorityQueue.clear();
        } // end of for test-case
        System.out.println(sb);
    } // end of main
} // end of class

class Pair {
    int idx;
    int priority;

    public Pair(int idx, int priority) {
        this.idx = idx;
        this.priority = priority;
    }
}
