package Algorithm.BOJ._0413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class Main_15685_드래곤커브_G4_84ms {

    /**
     * 그림 그려보니 끝점 기억한 상태로 배열돌려서 만들고 반복후 완성된 드래곤 커브를 원본 배열에 첨가하는 느낌으로 진행
     * 예상 시간 복잡도가 O(n^2)인데 (100 * 100 * 10 * 10 * 20 = 20,000,000) 이천만개의 연산 정도 나올듯
     */

    static boolean[][] res = new boolean[101][101];

    static class Pos {
        int x;
        int y;
        int d;
        int g;

        public Pos(int x, int y, int d, int g) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.g = g;
        }
    }

    // 오른쪽 위 왼쪽 아래
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // 드래곤 커브의 개수 N(1 ≤ N ≤ 20)
        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer> dragonCurve;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            // x는 드래곤 커브의 가로기준 시작 점
            int x = Integer.parseInt(st.nextToken());
            // y는 드래곤 커브의 세로기준 시작 점
            int y = Integer.parseInt(st.nextToken());
            // 드래곤 커브의 d는 시작 방향
            int d = Integer.parseInt(st.nextToken());
            // 드래곤 커브의 g는 세대
            int g = Integer.parseInt(st.nextToken());

            Pos startPos = new Pos(x, y, d, g);
            // 해당 주어진 값대로 드래곤 커브 만들기
            dragonCurve = buildDragonCurve(startPos);
            // 만든 드래곤 커브 원본 배열에 추가
            makeDragonCurve(dragonCurve, startPos);
        }

//        for (int i = 0; i <= 100; i++) {
//            for (int j = 0; j <= 100; j++) {
//                System.out.print(res[i][j] + " ");
//            }
//            System.out.println();
//        }
        // 다 만든 드래곤 커브 사각형 개수 세기
        System.out.println(countSquare());
    }

    private static int countSquare() {
        int cnt = 0;
        for (int i = 0; i <= 99; i++) {
            for (int j = 0; j <= 99; j++) {
                if (res[i][j] && res[i + 1][j] && res[i + 1][j + 1] && res[i][j + 1]) cnt++;
            }
        }

        return cnt;
    }

    private static void makeDragonCurve(List<Integer> dragonCurve, Pos pos) {
        int x = pos.x;
        int y = pos.y;
        Iterator<Integer> iterator = dragonCurve.iterator();
        while (iterator.hasNext()) {
            int dir = iterator.next();
            x += dx[dir];
            y += dy[dir];
            res[y][x] = true;
        }
    }

    private static ArrayList<Integer> buildDragonCurve(Pos pos) {
        int g = pos.g;
        int x = pos.x;
        int y = pos.y;

        res[y][x] = true;
        int dir = pos.d;
        // 0단계 드래곤 커브 만들기
        ArrayList<Integer> dragonCurve = new ArrayList<>();
        dragonCurve.add(dir);

        // 1~g단계
        for (int i = 1; i <= g; i++) {
            int beforeGenDragon = dragonCurve.size();

            for (int j = 0; j < beforeGenDragon; j++) {
                dragonCurve.add((dragonCurve.get(beforeGenDragon - 1 - j) + 1) % 4);
            }
        }

        return dragonCurve;
    }


}
