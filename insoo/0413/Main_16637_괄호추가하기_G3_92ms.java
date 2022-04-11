package Algorithm.BOJ._0413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_16637_괄호추가하기_G3_92ms {

    /**
     * 19 -> 괄호 최대 5개
     * 15,17 -> 괄호 최대 4개
     * 11,13 -> 괄호 최대 3개
     * 7,9 -> 괄호 최대 2개
     * 5 -> 괄호 1개
     * 1,3 -> 괄호 0개
     */

    static char[] formula;
    private static int N;
    private static Queue<String> queue;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        formula = br.readLine().toCharArray();

        // 괄호 만들수 있는 개수는 연산자 개수랑 동일(중복 안돼서)
        int bracketCnt = N / 2;
        int maxBracket = 0;
        queue = new LinkedList<>();
        switch (N) {
            case 5:
                maxBracket = 1;
                break;
            case 7:
            case 9:
                maxBracket = 2;
                break;
            case 11:
            case 13:
                maxBracket = 3;
                break;
            case 15:
            case 17:
                maxBracket = 4;
                break;
            case 19:
                maxBracket = 5;
                break;
        }

        // 0개의 괄호 계산
        for (int i = 0; i < N; i++) {
            queue.offer(Character.toString(formula[i]));
        }
        max = calcFormula();


        // 1개 부터 maxBracket괄호 만들어서 값 비교
        for (int i = 1; i <= maxBracket; i++) {
            // 괄호 만들 수 있는 조합
            combination(0, 0, 0, i, bracketCnt);
        }

        System.out.println(max);
    }

    private static void combination(int pos, int start, int flag, int needBracket, int bracketCnt) {
        // 뽑아야할 괄호 수
        if (needBracket == pos) {
            // 뽑은 괄호 미리 계산하기
            calcBracket(flag, bracketCnt);
            // 큐에 넣어둔 계산식 계산
            max = Math.max(max, calcFormula());
            return;
        }

        // 괄호 선택
        for (int i = start; i < bracketCnt; i++) {
            // 고른 괄호 다음에 괄호면 중복괄호여서 안됨 -> i + 2
            combination(pos + 1, i + 2, flag | 1 << i, needBracket, bracketCnt);
        }
    }

    private static int calcFormula() {
        int sum = 0;
        if (!queue.isEmpty()) {
            sum = Integer.parseInt(queue.poll());
        }

        while (!queue.isEmpty()) {
            sum = operation(queue.poll().charAt(0), sum, Integer.parseInt(queue.poll()));
        }

        return sum;
    }

    private static void calcBracket(int flag, int bracketCnt) {

        for (int i = 0; i < bracketCnt; i++) {
            int operatorIdx = (i * 2) + 1;
            int lOperandIdx = operatorIdx - 1;
            int rOperandIdx = operatorIdx + 1;
            // 선택된 괄호면 미리 계산
            if ((flag & 1 << i) != 0) {
                // 1, 3, 5, 7 -> 괄호는 2n + 1 인덱스의 위치함

                queue.offer(Integer.toString(operation(formula[operatorIdx], formula[lOperandIdx] - '0', formula[rOperandIdx] - '0')));
                // 마지막 꺼가 아니면 다음 연산자 추가해주고 마지막 숫자가 있으면 넣어줌
                if (i != bracketCnt - 1) {
                    // 다음 연산자 추가해줌
                    queue.offer(Character.toString(formula[operatorIdx + 2]));
                    // 다음 연산자 생략되어서 끝이 아니면 추가해줌
                    if (operatorIdx + 3 == N - 1) {
                        queue.offer(Character.toString(formula[operatorIdx + 3]));
                    }
                    i++;
                }
            } else {
                queue.offer(Character.toString(formula[lOperandIdx]));
                queue.offer(Character.toString(formula[operatorIdx]));

                // 마지막 꺼면
                if (rOperandIdx == N - 1) {
                    queue.offer(Character.toString(formula[rOperandIdx]));
                }
            }
        }
    }

    private static int operation(char operator, int lOperand, int rOperand) {
        switch (operator) {
            case '+':
                return lOperand + rOperand;
            case '-':
                return lOperand - rOperand;
            case '*':
                return lOperand * rOperand;
        }

        // 나오면 안됨
        return -1;
    }
}
