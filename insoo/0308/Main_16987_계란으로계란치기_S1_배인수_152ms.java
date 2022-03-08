import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main_16987_계란으로계란치기_S1_배인수_152ms {
    private static int N;

    /*
        각 계란에는 내구도와 무게가 정해져있다
        계란으로 계란을 치게 되면 각 계란의 내구도는 상대 계란의 무게만큼 깎이게 된다.
        그리고 내구도가 0 이하가 되는 순간 계란은 깨지게 된다.

        예를 들어 계란 1의 내구도가 7, 무게가 5이고 계란 2의 내구도가 3, 무게가 4라고 해보자.
        계란 1으로 계란 2를 치게 되면 계란 1의 내구도는 4만큼 감소해 3이 되고 계란 2의 내구도는 5만큼 감소해 -2가 된다.
        충돌 결과 계란 1은 아직 깨지지 않았고 계란 2는 깨졌다.

        1.가장 왼쪽의 계란을 든다.

        2.
        손에 들고 있는 계란으로 깨지지 않은 다른 계란 중에서 하나를 친다.
        단, 손에 든 계란이 깨졌거나 깨지지 않은 다른 계란이 없으면 치지 않고 넘어간다.
        이후 손에 든 계란을 원래 자리에 내려놓고 3번 과정을 진행한다.

        3.
        가장 최근에 든 계란의 한 칸 오른쪽 계란을 손에 들고 2번 과정을 다시 진행한다.
        단, 가장 최근에 든 계란이 가장 오른쪽에 위치한 계란일 경우 계란을 치는 과정을 종료한다.
     */

    static class Egg {
        int durability;
        int weight;

        public Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }
    }

    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        Egg[] eggs = new Egg[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            eggs[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        permutation(eggs, 0, 0);
        System.out.println(max);
    } // end of main

    static void permutation(Egg[] eggs, int pos, int broken) {

        if (pos == N) {
            max = Math.max(max, broken);
            return;
        }

        // 현재 집은 계란이 부서졌거나 1개 빼고 다 깨졌으면 다음으로 넘김
        if (eggs[pos].durability <= 0 || broken == N - 1) {
            permutation(eggs, pos + 1, broken);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (i == pos || eggs[i].durability <= 0) continue;
            eggs[i].durability -= eggs[pos].weight;
            eggs[pos].durability -= eggs[i].weight;
            if (eggs[i].durability <= 0) broken++;
            if (eggs[pos].durability <= 0) broken++;

            permutation(eggs, pos + 1, broken);

            if (eggs[i].durability <= 0) broken--;
            if (eggs[pos].durability <= 0) broken--;
            eggs[i].durability += eggs[pos].weight;
            eggs[pos].durability += eggs[i].weight;
        }
    }
} // end of class
