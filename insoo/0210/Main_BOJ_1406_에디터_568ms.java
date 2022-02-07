import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main_BOJ_1406_에디터_568ms {
    static final int MX = 600_001;
    static char[] dat = new char[MX];
    static int[] pre = new int[MX];
    static int[] nxt = new int[MX];
    static int unused = 1;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Arrays.fill(pre, -1);
        Arrays.fill(nxt, -1);
        String input = bf.readLine();
        int cursor;
        for (cursor = 0; cursor < input.length();) {
            insert(cursor, input.charAt(cursor));
            cursor++;
        }
        int n = Integer.parseInt(bf.readLine());
        StringTokenizer st;
        char in, ch;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            in = st.nextToken().charAt(0);
            if (in == 'P') {   // 문자를 커서 왼쪽에 추가
                ch = st.nextToken().charAt(0);
                insert(cursor, ch);
                cursor = nxt[cursor];
            } else if (in == 'L') {    // 커서 왼쪽 이동
                if (pre[cursor] != -1) {
                    cursor = pre[cursor];
                }
            } else if (in == 'D') {    // 커서 오른쪽 이동
                if (nxt[cursor] != -1) {
                    cursor = nxt[cursor];
                }
            } else {    // 커서 왼쪽 문자 삭제
                if (pre[cursor] != -1) {
                    erase(cursor);
                    cursor = pre[cursor];
                }
            }
        }
        System.out.println(traverse(sb));
        bf.close();
    }

    static void insert(int index, char ch) {
        dat[unused] = ch;
        pre[unused] = index;
        nxt[unused] = nxt[index];
        if (nxt[index] != -1) pre[nxt[index]] = unused;
        nxt[index] = unused;
        unused++;
    }

    static void erase(int index) {
        nxt[pre[index]] = nxt[index];
        if(nxt[index] != -1) pre[nxt[index]] = pre[index];
    }

    static StringBuilder traverse(StringBuilder sb) {
        int cur = nxt[0];
        while (cur != -1) {
            sb.append(dat[cur]);
            cur = nxt[cur];
        }
        return sb;
    }
}
