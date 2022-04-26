package Algorithm.BOJ._0427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2660_회장뽑기_G5_80ms {

    /**
     * 회원 사이에 서로 모르는 사람도 있지만, 몇 사람을 통하면 모두가 서로 알 수 있다.
     * 계층을 dfs로 타면 계산이 이상해져 bfs로 타야한다. 최소 계층을 알아야 하기 때문
     */

    static int[] nodes;

    static class Graph {
        ArrayList<ArrayList<Integer>> graph;

        public Graph(int n) {
            graph = new ArrayList<ArrayList<Integer>>();

            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<Integer>());
            }
        }

        public void bidirectionalConnect(int a, int b) {
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        public int traverseGraph(int i) {

            Queue<Pair> queue = new LinkedList<>();
            queue.offer(new Pair(i,0));
            boolean[] visited = new boolean[graph.size()];
            visited[i] = true;
            int level = 0;

            while (!queue.isEmpty()) {
                Pair cur = queue.poll();
                level = cur.level;
                for (int nxt :
                        graph.get(cur.node)) {

                    if (visited[nxt]) continue;

                    queue.offer(new Pair(nxt,cur.level + 1));
                    visited[nxt] = true;
                }
            }
            return level;
        }
    }

    static class Pair {
        int node;
        int level;

        public Pair(int node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        // 각 노드의 회장 점수
        nodes = new int[N + 1];
        Graph graph = new Graph(N);
        while (true) {
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == -1 && b == -1) break;
            graph.bidirectionalConnect(a, b);
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            nodes[i] = graph.traverseGraph(i);
            if (min > nodes[i]) min = nodes[i];
        }



        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (nodes[i] == min) {
                queue.offer(i);
                cnt++;
            }
        }

        sb.append(min).append(" ").append(cnt).append("\n");
        while (!queue.isEmpty()) {
            sb.append(queue.poll()).append(" ");
        }
        System.out.println(sb);
    }
}
