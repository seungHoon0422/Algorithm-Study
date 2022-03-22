import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main_1717_집합의표현_G4_392ms {

    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        makeSet(N);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            switch (cmd) {
                case 0: // 합집합
                    union(a, b);
                    break;
                case 1: // 같은 집합에 포함되어 있는지
                    sb.append(findParent(a) == findParent(b) ? "YES" : "NO").append('\n');
                    break;
            }

        }

        System.out.println(sb);
    } // end of main

    static void makeSet(int n) {
        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }
    }

    static int findParent(int x) {
        if (x == parents[x]) return x;
        else return parents[x] = findParent(parents[x]);
    }

    static void union(int x, int y) {
        int xRoot = findParent(x);
        int yRoot = findParent(y);

        if (xRoot == yRoot) return;
        else {
            parents[yRoot] = xRoot;
        }
    }
}
