import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main_1941_소문난칠공주_G3_배인수_260ms {

    /*
        문제 보니까 25C7개 중 맞는 경우를 찾아야 하는 것 같다.
        5x5에서 7개 탐색만 이걸 잘 처리하면 될듯?

        이름이 이름인 만큼, 7명의 여학생들로 구성되어야 한다.
        강한 결속력을 위해, 7명의 자리는 서로 가로나 세로로 반드시 인접해 있어야 한다.
        화합과 번영을 위해, 반드시 ‘이다솜파’의 학생들로만 구성될 필요는 없다.
        그러나 생존을 위해, ‘이다솜파’가 반드시 우위를 점해야 한다.
        따라서 7명의 학생 중 ‘이다솜파’의 학생이 적어도 4명 이상은 반드시 포함되어 있어야 한다.
     */

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 'S'(이다‘솜’파의 학생을 나타냄) 또는 'Y'(임도‘연’파의 학생을 나타냄)

        char[][] mat = new char[5][5];
        for (int i = 0; i < 5; i++) {
            mat[i] = br.readLine().toCharArray();
        }

        combination(0, 0, 0, mat, 0);

        System.out.println(res);
    } // end of main

    static int[] arr = new int[7];

    static void combination(int pos, int start, int yCnt, char[][] mat, int flag) {
        if (pos == 7) {
//            System.out.println(Arrays.toString(arr));

            // 해당 조합으로 뽑은 좌표들이 서로 이어지는지 확인
            if (bfs(flag)) {
                res++;
            }
            return;
        }

        for (int i = start; i < 25; i++) {
            int y = i / 5;
            int x = i % 5;
            arr[pos] = i;
            if (mat[y][x] == 'Y') {
                // 임도연파 학생이 4명 이상이 될 수 없음
                if (yCnt < 3) combination(pos + 1, i + 1, yCnt + 1, mat, flag | 1 << i);
            } else {    // 이다솜파 학생
                combination(pos + 1, i + 1, yCnt, mat, flag | 1 << i);
            }
        }
    }

    static boolean bfs(int flag) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(arr[0]);
        int checkFlag = 0;
        checkFlag = checkFlag | 1 << arr[0];
        int cnt = 1;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int y = cur / 5;
            int x = cur % 5;

            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nxt = ny * 5 + nx;
                // 범위 나가는 것 이미 방문했던것 조합에 나온 곳에 방문 안하면 스킵함
                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || (checkFlag & 1 << nxt) != 0 || (flag & 1 << nxt) == 0) continue;

                checkFlag = checkFlag | 1 << nxt;
                queue.offer(nxt);
                cnt++;
            }
        }
        if (cnt == 7) return true;
        else return false;
    }
} // end of class



