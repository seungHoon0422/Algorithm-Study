import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * 아이디어 1
 * 드래곤 커브 입력 받을때마다 찍히는 좌표 boolean배열에 check
 * 90도로 돌리는 부분이 가장 문제!!
 * r,c 좌표계에서 x,y좌표계 쓸라니까 머리 터진다...
 * 
 * 
 * 아이디어 2
 * 좌표를 계산한 후에 list에 담아 관리하지 말고, 어차피 한줄로 연결되어있으니
 * 진행하는 방향만 저장하자!!
 * 그전에 입력되어있는 방향에서 direction 순서를 시계방향으로 저장해서
 * 인덱스만 1증가시키면 다음 방향을 알 수 있다!!
 * 시계방향, 반시계방향 회전하는 문제들에서 유용하게 사용할 스킬
 * 
 * 
 * 
 * @author SeongHoon
 *
 */
public class Main_BOJ_15685_드래곤커브_Gold4_92ms {
    static boolean[][] map = new boolean[101][101];
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());   // 시작 방향
            int g = Integer.parseInt(st.nextToken());   // 세대

            dragonCurve(x, y, d, g);
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) {
                    ans++;
                }
            }
        }

        bw.write(ans + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    public static void dragonCurve(int x, int y, int d, int g) {
        List<Integer> d_list = new ArrayList<>();
        d_list.add(d);

        for (int i = 1; i <= g; i++) {
            for (int j = d_list.size() - 1; j >= 0; j--) {
                d_list.add((d_list.get(j) + 1) % 4);
            }
        }

        map[y][x] = true;
        for (Integer direction : d_list) {
            x += dx[direction];
            y += dy[direction];
            map[y][x] = true;
        }
    }
}
