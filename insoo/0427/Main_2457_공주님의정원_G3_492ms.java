package Algorithm.BOJ._0427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2457_공주님의정원_G3_492ms {

    /**
     * 월마다 계산하는 방식으로 하다가 일자 계산을 처리를 고민하다 걍 일자를 배열로 만들자고 전환
     * 100,000 log 100,000 + 12 * 100,000 -> 100,000 log 100,000 + 365 * 100,000 = 대충 100만 +36,500,000이라 통과되지 않을까
     * 하다가 배열로 할 필요성이 없어짐
     */

    enum Days {
        __(0), JAN(31), FEB(28), MAR(31), APR(30), MAY(31), JUN(30), JUL(31), AUG(31), SEP(30), OCT(31), NOV(30), DEC(31);
        private final int value;

        private Days(int value) {
            this.value = value;
        }
    }

    static class Flower implements Comparable<Flower> {

        int sDay;
        int eDay;

        public Flower(int sDay, int aliveDays) {
            this.sDay = sDay;
            this.eDay = aliveDays;
        }

        @Override
        public int compareTo(Flower o) {
            return this.sDay == o.sDay ? o.eDay - this.eDay : this.sDay - o.sDay;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Days[] days = Days.values();
        Flower[] flowers = new Flower[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int sMonth = Integer.parseInt(st.nextToken());
            int sDay = Integer.parseInt(st.nextToken());
            int eMonth = Integer.parseInt(st.nextToken());
            int eDay = Integer.parseInt(st.nextToken());

            flowers[i] = new Flower(monthToDay(days, sMonth, sDay), monthToDay(days, eMonth, eDay));
        }

        // 시작일 기준으로 내림차순 정렬하되 같은 일자면 마지막 날짜가 더 긴 것을 우선적으로 오름차순 정렬
        Arrays.sort(flowers);



        // 지정된 시작일
        int sDay = monthToDay(days, 3, 1);
        // 지정된 종료일
        final int eDay = monthToDay(days, 12, 1);
        // 결과
        int cnt = 0;
        // 탐색한 지점부터 시작
        int idx = 0;
        // 현재 선택한 꽃의 마지막일
        int lastEDay = 0;

        while (sDay < eDay) {
            // 해당 꽃 선택했는지
            boolean isSelected = false;

            // 꽃 탐색
            for (int i = idx; i < N; i++) {
                // 선택한 꽃이 시작일 보다 크면 원하는 결과 못만듦
                if (flowers[i].sDay > sDay) break;

                // 마지막에 선택된 꽃의 끝날짜보다 커야 선택함
                if (lastEDay < flowers[i].eDay) {
                    isSelected = true;
                    lastEDay = flowers[i].eDay;
                    idx = i + 1;
                }

                // 더 탐색해야하는 이유가 선택됐던 꽃의 시작일 보다 작으면서
                /*
                    선택한 꽃의 시작일 - 06/30
                     - 1 1 6 30 - 이미 선택된 꽃
                    1 1 5 31    <- 마지막 보다 짧아 스킵
                    5 15 8 31   <- 선택됨(이 다음 것도 가능성이 존재)
                    6 10 12 10  <- 선택되어야함
                 */
            }

            if (isSelected) {
                sDay = lastEDay;
                cnt++;
            } else break;   // 하나라도 이상 있으면 어짜피 못채움
        }

        if (lastEDay < eDay) System.out.println(0);
        else System.out.println(cnt);
    }


    /*

3
1 1 5 31
1 1 6 9
6 10 12 10

    */

    private static int monthToDay(Days[] days, int sMonth, int sDay) {
        int monthToDay = 0;
        for (int j = 1; j < sMonth; j++) {
            monthToDay += days[j].value;
        }
        return monthToDay += sDay;
    }

}
