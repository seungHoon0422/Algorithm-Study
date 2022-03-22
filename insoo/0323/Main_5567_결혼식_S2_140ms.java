import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main_5567_결혼식_실버2_140ms {

    static boolean[] vis;

    static class ListGraph {
        private ArrayList<ArrayList<Integer>> listGraph;

        // 그래프 초기화
        public ListGraph(int initSize) {
            this.listGraph = new ArrayList<ArrayList<Integer>>(); // 그래프 생성

            // 그래프 초기화
            for (int i = 0; i < initSize + 1; i++) {
                listGraph.add(new ArrayList<Integer>());
            }
        }

        // 그래프 추가 (양방향)
        public void put(int x, int y) {
            listGraph.get(x).add(y);
            listGraph.get(y).add(x);
        }


        // 그래프 추가 (단방향)
        public void putSingle(int x, int y) {
            listGraph.get(x).add(y);
        }


        public void calcInviteWedding(int index, int cnt) {
            if (cnt == 2) {
                return;
            }
            ArrayList<Integer> cur = listGraph.get(index);
            int size = cur.size();
            for (int j = 0; j < size; j++) {
                vis[cur.get(j)] = true;
                calcInviteWedding(cur.get(j), cnt + 1);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        ListGraph listGraph = new ListGraph(N);
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            listGraph.put(x, y);
        }

        vis = new boolean[N + 1];
        vis[1] = true;
        listGraph.calcInviteWedding(1, 0);

        int cnt = 0;
        
        for (int i = 2; i <= N; i++) {
            if (vis[i]) cnt++;
        }
        System.out.println(cnt);
    } // end of main


}
