import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main_1922_네트워크연결_G4_배인수_400ms {
    /*
        컴퓨터와 컴퓨터를 모두 연결하는 네트워크를 구축
         컴퓨터를 연결하는 비용을 최소
         모든 컴퓨터를 연결할 수 없는 경우는 없다.
    */

    static class Vertex {
        int to;
        int weight;

        public Vertex(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<ArrayList<Vertex>> list = new ArrayList<ArrayList<Vertex>>();
        // 1<= N <= 1_000 컴퓨터 수
        int N = Integer.parseInt(br.readLine());
        // 1<= M <= 100_000 연결할 수 있는 간선 수 (정렬 NlogN -> 대략 100만) 크루스칼도 가능?!
        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < N + 1; i++) {
            list.add(new ArrayList<Vertex>());
        }
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list.get(from).add(new Vertex(to, weight));
            list.get(to).add(new Vertex(from, weight));
        }

        int[] minEdge = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(minEdge, Integer.MAX_VALUE);
        int cost = 0;
        minEdge[1] = 0; // MST 비용

        for (int i = 1; i <= N; i++) { // N개의 정점 모두 연결
            int min = Integer.MAX_VALUE;
            int minVertex = 0;
            for (int j = 1; j <= N; j++) {
                if (!visited[j] && min > minEdge[j]) {
                    minVertex = j;
                    min = minEdge[j];
                }
            }
            visited[minVertex] = true;
            cost += min;

            for (Vertex v :
                    list.get(minVertex)) {
                if (!visited[v.to] && minEdge[v.to] > v.weight) {
                    minEdge[v.to] = v.weight;
                }
            }
        }

        System.out.println(cost);
    } // end of main

} // end of class
