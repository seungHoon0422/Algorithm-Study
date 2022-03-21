import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main_1238_파티_G3_168ms {

    private static int N;
    private static int[][] adjMat;
    private static ListGraph listGraph1;
    private static ListGraph listGraph2;
    private static PriorityQueue<Vertex> pq;

    static class ListGraph {
        private ArrayList<ArrayList<Pair>> listGraph;

        // 그래프 초기화
        public ListGraph(int initSize) {
            this.listGraph = new ArrayList<ArrayList<Pair>>(); // 그래프 생성

            for (int i = 0; i < initSize + 1; i++) {
                listGraph.add(new ArrayList<Pair>());
            }
        }

        // 그래프 return
        public ArrayList<ArrayList<Pair>> getGraph() {
            return this.listGraph;
        }

        // 그래프의 특정 노드 return
        public ArrayList<Pair> getNode(int i) {
            return this.listGraph.get(i);
        }

        // 그래프 추가 (단방향)
        public void putSingle(int x, int y, int weight) {
            listGraph.get(x).add(new Pair(y, weight));
        }

    }

    static class Vertex implements Comparable<Vertex> {
        int no;
        int minDis;

        public Vertex(int no, int minDis) {
            this.no = no;
            this.minDis = minDis;
        }

        @Override
        public int compareTo(Vertex o) {
            return this.minDis - o.minDis;
        }
    }

    static class Pair {
        int nxt;
        int weight;

        public Pair(int nxt, int weight) {
            this.nxt = nxt;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>();

//        adjMat = new int[N + 1][N + 1];
        listGraph1 = new ListGraph(N);
        listGraph2 = new ListGraph(N);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            listGraph1.putSingle(start, end, time);
            listGraph2.putSingle(end, start, time);
        }


        int max = Integer.MIN_VALUE;
        int[] distance1 = fullDijkstra(X,listGraph1);
        int[] distance2 = fullDijkstra(X,listGraph2);
        for (int i = 1; i <= N; i++) {
            int fullDis = distance1[i] + distance2[i];

            max = Math.max(max, fullDis);
        }

        System.out.println(max);
    } // end of main

    static int[] fullDijkstra(int start, ListGraph listGraph) {
        int[] distance = new int[N + 1];    // 출발지에서 자신에게 오는 거리
        boolean[] visited = new boolean[N + 1]; // 최소비용 확정여부
        Arrays.fill(distance, Integer.MAX_VALUE);
        pq.offer(new Vertex(start, 0));
        distance[start] = 0;    // 시작점

        while (!pq.isEmpty()) {
            // 최소비용 확저되지 않은 정점 중 최소비용의 정점 선택
            Vertex cur = pq.poll();
            if (visited[cur.no]) continue;
            visited[cur.no] = true;
            // 선택된 정점을 경유지로 하여 아직 최소비용 확정되지 않은 다른 정점의 최소비용 고려

//            for (int j = 1; j <= N; j++) {
//                if (!visited[j] && adjMat[cur.no][j] != 0 && distance[j] > distance[cur.no] + adjMat[cur.no][j]) {
//                    distance[j] = distance[cur.no] + adjMat[cur.no][j];
//                    pq.offer(new Vertex(j, distance[j]));
//                }
//            }

            for (Pair sel :
                    listGraph.getNode(cur.no)) {
                if (!visited[sel.nxt] && distance[sel.nxt] > distance[cur.no] + sel.weight) {
                    distance[sel.nxt] = distance[cur.no] + sel.weight;
                    pq.offer(new Vertex(sel.nxt, distance[sel.nxt]));
                }
            }
        }

        return distance;
    }

    static int dijkstra(int start, int goal) {
        int[] distance = new int[N + 1];    // 출발지에서 자신에게 오는 거리
        boolean[] visited = new boolean[N + 1]; // 최소비용 확정여부
        Arrays.fill(distance, Integer.MAX_VALUE);
        pq.offer(new Vertex(start, 0));
        distance[start] = 0;    // 시작점

        while (!pq.isEmpty()) {
            // 최소비용 확저되지 않은 정점 중 최소비용의 정점 선택
            Vertex cur = pq.poll();
            if (visited[cur.no]) continue;
            visited[cur.no] = true;
            if (visited[goal]) break;

            // 선택된 정점을 경유지로 하여 아직 최소비용 확정되지 않은 다른 정점의 최소비용 고려
            for (Pair sel :
                    listGraph1.getNode(cur.no)) {
                if (!visited[sel.nxt] && distance[sel.nxt] > distance[cur.no] + sel.weight) {
                    distance[sel.nxt] = distance[cur.no] + sel.weight;
                    pq.offer(new Vertex(sel.nxt, distance[sel.nxt]));
                }
            }
        }
        pq.clear();
        return distance[goal];
    }
}
