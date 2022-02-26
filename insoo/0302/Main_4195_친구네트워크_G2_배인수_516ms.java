import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main_4195_친구네트워크_G2_배인수_516ms {

    static int[] parents;
    static int[] parentsCnt;

    /*
        처음엔 그냥 Union-Find했는데 시간초과가 나와서 랭크를 써야한다는 소리를 듣고 찾아봄
        각 트리의 높이가 다를 때 높은 쪽으로 낮은 것이 붙어 그대로, 높이가 같으면 높이가 +1 된다.
        결론적으로 어느 루트를 이용하는 것이 이득인지 구분하는 것.

        근데 수업때 랭크랑 패스 압축을 같이 이용하면 성능이 안좋다고 했는데 그 이유가
        해당 루트 노드의 자식이 패스 압축이 일어날 때 다른 자식들이 압축이 안되었을 수 있어서
        해당 루트 노드의 랭크를 갱신하려면 다른 자식 또한 탐색해서 해야한다.
        그래서 랭크를 어떻게 쓰라는 건지 이해가 안되어서 동률님 코드 염탐

        보니까 랭크의 개념을 응용한 느낌이었다. 코드에선 랭크라고 하셨는데 랭크보단 해당 집합의 개수를 센 개념이 맞는 것 같다.
        왜냐하면 깊이끼리 더해서 사실상 개수를 센거다.
       
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        StringBuilder sb = new StringBuilder();
        // 테스트 케이스의 개수
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            // 친구 관계의 수 F 100,000을 넘지 않는다
            int F = Integer.parseInt(br.readLine());

            makeSet(F);

            // 다음 F개의 줄에는 친구 관계가 생긴 순서대로 주어진다
            // 두 사용자의 아이디로 이루어져 있으며, 알파벳 대문자 또는 소문자로만 이루어진 길이 20 이하의 문자열
            StringTokenizer st;
            int idx = 0;
            for (int j = 0; j < F; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                String a = st.nextToken();
                String b = st.nextToken();

                if (!hashMap.containsKey(a)) {
                    hashMap.put(a, idx++);
                }
                if (!hashMap.containsKey(b)) {
                    hashMap.put(b, idx++);
                }

                // 유니온 코드 상 왼쪽에 있는 매개변수가 부모가 됨
                sb.append(union(hashMap.get(a), hashMap.get(b))).append('\n');
            }
            hashMap.clear();
        }
        System.out.println(sb);
    } // end of main

    static void makeSet(int f) {
        // 최악의 경우 각자 다 다른 친구가 나올 수 있음
        parents = new int[f * 2];
        parentsCnt = new int[f * 2];

        for (int i = 0; i < f * 2; i++) {
            parents[i] = i;
            parentsCnt[i] = 1;
        }
    }

    static int findSet(int a) {
        if (a == parents[a]) return a;
        else return parents[a] = findSet(parents[a]);
    }

    static int union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if (aRoot == bRoot) return parentsCnt[aRoot];
        else parents[bRoot] = aRoot;
        return parentsCnt[aRoot] += parentsCnt[bRoot];
    }

} // end of class
