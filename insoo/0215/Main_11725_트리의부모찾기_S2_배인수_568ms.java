import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main_11725_트리의부모찾기_S2_배인수_568ms {

    static ArrayList<ArrayList<Integer>> adjacencyList;
    static int[] parent; // 각 노드마다 부모 노드 저장
    static int N;
    static boolean[] vis; // 방문했는지 확인(양방향이라 필요)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        vis = new boolean[N + 1];
        // 인접 리스트 초기화
        adjacencyList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < N + 1; i++) {
            adjacencyList.add(new ArrayList<Integer>());
        } // end of for

        StringTokenizer st;
        // 그래프 연결
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 양방향 그래프
            adjacencyList.get(a).add(b);
            adjacencyList.get(b).add(a);
        } // end of for

        // 루트노드부터 시작
        findParent(1);

        for (int i = 2; i <= N; i++) {
            sb.append(parent[i]).append('\n');
        } // end of for

        System.out.println(sb);
    } // end of main

    static void findParent(int n) {

        // 해당 노드 방문함
        vis[n] = true;

        // 해당 노드가 연결된 길이
        int listSize = adjacencyList.get(n).size();
        for (int i = 0; i < listSize; i++) {

            // 다음 노드
            int nxt = adjacencyList.get(n).get(i);

            // 방문 안했으면 감
            if (!vis[nxt]) {
                // 자식 노드의 부모 저장하고 감
                parent[nxt] = n;
                findParent(nxt);
            } // end of if
        } // end of for
    }

} // end of class
