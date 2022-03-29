import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
/**
 * 
 * 경로를 출력하는 부분에서 처음엔 List로 각 지점에서 거쳐온 경로를 모두 저장하는 식으로 했는데, 시간 초과로 실패했다.
 * 대신, 각 지점별로 모든 경로를 저장하는게 아니라, 자신의 부모 위치만 저장한다.
 *
 */
public class Main_BOJ_13913_숨바꼭질4_Gold4_952ms {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, K;
    private static int[] visited = new int[100001];
    private static int[] parent = new int[100001];
 
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
 
        // bfs 탐색 시작
        bfs(N, K);
        System.out.println(visited[K] - 1);
 
        
        // 부모부터 알수있기 때문에 시작위치부터 출력을 위한 stack 사용
        Stack<Integer> s = new Stack<>();
        int idx = K;
        while (idx != N) {
            s.push(idx);
            idx = parent[idx];
        }
        s.push(idx);
 
        while (!s.isEmpty()) {
            System.out.print(s.pop() + " ");
        }
 
        br.close();
    }
 
    private static void bfs(int start, int end) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = 1;
 
        while (!q.isEmpty()) {
            int now = q.poll();
 
            if (now + 1 <= 100000 && visited[now + 1] == 0) {
                visited[now + 1] = visited[now] + 1;
                parent[now + 1] = now;
                q.add(now + 1);
            }
            if (now - 1 >= 0 && visited[now - 1] == 0) {
                visited[now - 1] = visited[now] + 1;
                parent[now - 1] = now;
                q.add(now - 1);
            }
            if (now * 2 <= 100000 && visited[now * 2] == 0) {
                visited[now * 2] = visited[now] + 1;
                parent[now * 2] = now;
                q.add(now * 2);
            }
 
            if (visited[end] != 0) return;
        }
    }
}


