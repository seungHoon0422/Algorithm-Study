import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_5430_AC_G5_배인수_852ms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 덱으로 양방향 pop 가능하니 R함수가 와도 리버스 대신 방향만 바꿔주면 된다.
        Deque<String> deque = new ArrayDeque<>();;
        StringTokenizer arrValue;
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            // 입력 받기
            String RDFunc = br.readLine();
            int arrLength = Integer.parseInt(br.readLine());

            arrValue = new StringTokenizer(br.readLine(), "[],");
       
            while (arrValue.hasMoreTokens()) {
                deque.add(arrValue.nextToken());
            }

            boolean isError = false;
            boolean isReverse = false;

            for (char func :
                    RDFunc.toCharArray()) {
                if (func == 'R') {
                    isReverse = !isReverse;
                } else {
                    // 덱 비었으면 멈추고 에러 표시

                    if (deque.isEmpty()) {
                        isError = true;
                        break;
                    }

                    // 뒤에서 pop
                    if (isReverse) {
                        deque.pollLast();
                        // 앞에서 pop
                    } else {
                        deque.pollFirst();
                    }
                } // end of if else
            } // end of for

            // 출력문
            if (isError) {
                sb.append("error\n");
            } else {

                sb.append('[');
                // , 를 잘 찍기 위해 미리 한개 출력
                if (!deque.isEmpty()) {
                    if (isReverse) {
                        sb.append(deque.pollLast());
                    } else {
                        sb.append(deque.pollFirst());
                    }
                }

                while (!deque.isEmpty()) {
                    if (isReverse) {
                        sb.append(',').append(deque.pollLast());
                    } else {
                        sb.append(',').append(deque.pollFirst());
                    }
                } // end of while
                sb.append(']').append('\n');
            } // end of if else
        } // end of for 테스트 케이스 반복
        System.out.print(sb);
    }

}
