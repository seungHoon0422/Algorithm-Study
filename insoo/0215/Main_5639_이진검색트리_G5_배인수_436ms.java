import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main_5639_이진검색트리_G5_배인수_436ms {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BinarySearchTree bst = new BinarySearchTree();
        String input;
        while (true) {
            input = br.readLine();
            if (input == null || input.equals(""))
                break;
            bst.add(Integer.parseInt(input));
        }
        System.out.println(bst.dfsByPostOrder());

    } // end of main


} // end of class

class Node {
    int val;
    // 작은 거 왼쪽 큰거 오른쪽
    Node left, right;

    public Node(int val) {
        this.val = val;
    }

}


class BinarySearchTree {
    Node rootNode = null;
    static StringBuilder sb = new StringBuilder();

    public void add(int n) {
        // 처음 추가하면
        if (rootNode == null) {
            rootNode = new Node(n);
        } else {
            Node curNode = rootNode;

            while (true) {
                // 현재 노드보다 작으면 왼쪽으로 감
                if (curNode.val > n) {
                    // 왼쪽 비어있으면
                    if (curNode.left == null) {
                        curNode.left = new Node(n);
                        break;
                    } else curNode = curNode.left;
                } else {
                    if (curNode.right == null) {
                        curNode.right = new Node(n);
                        break;
                    } else curNode = curNode.right;
                } // end of if-else
            } // end of while
        } // end of if-else
    } // end of add

    public StringBuilder dfsByPostOrder() {
        dfsByPostOrder(rootNode);
        return sb;
    }

    private void dfsByPostOrder(Node node) {
        if (node == null) return;

        dfsByPostOrder(node.left);
        dfsByPostOrder(node.right);
        sb.append(node.val).append('\n');
    }
}
