package Algorithm.BOJ._0420;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_16437_양구출작전_G2_884ms {
    /**
     * 트리 구조를 루트부터 후위순회? 그런 느낌으로 돌아올때 처리해보기?
     */

    static class Tree {
        ArrayList<ArrayList<Island>> tree;
        int aliveSheep;

        public Tree(int size) {
            tree = new ArrayList<ArrayList<Island>>();
            aliveSheep = 0;
            for (int i = 0; i <= size; i++) {
                tree.add(new ArrayList<Island>());
            }
        }

        public long dfsTraverse(int n) {
            ArrayList<Island> branch = tree.get(n);
            int size = branch.size();
            long sum = 0;
            for (int i = 0; i < size; i++) {
                long remain = dfsTraverse(branch.get(i).islandNum);
//                System.out.println(n + " " + remain);
                if (branch.get(i).animal == 'S') remain += branch.get(i).cnt;
                else {
                    if (remain - branch.get(i).cnt < 0) remain = 0;
                    else remain -= branch.get(i).cnt;
                }
                sum += remain;
//                System.out.println(n + " " + remain);
            }
            return sum;
        }

        public void addIsland(int curIsland, char animal, int cnt, int preIsland) {
            tree.get(preIsland).add(new Island(curIsland, animal, cnt));
        }

    }

    static class Island {
        int islandNum;
        char animal;
        int cnt;

        public Island(int islandNum, char animal, int cnt) {
            this.islandNum = islandNum;
            this.animal = animal;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        Tree tree = new Tree(N);
        for (int curIsland = 2; curIsland <= N; curIsland++) {
            st = new StringTokenizer(br.readLine(), " ");
            char animal = st.nextToken().charAt(0);
            int cnt = Integer.parseInt(st.nextToken());
            int preIsland = Integer.parseInt(st.nextToken());

            tree.addIsland(curIsland, animal, cnt, preIsland);
        }

        System.out.println(tree.dfsTraverse(1));
    }
}
