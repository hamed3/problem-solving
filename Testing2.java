import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Testing2 {
    // #region tree methods
    public static String treeNodeToString(TreeNode root) {
        if (root == null) {
            return "[]";
        }

        String output = "";
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (node == null) {
                output += "null, ";
                continue;
            }

            output += String.valueOf(node.val) + ", ";
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + output.substring(0, output.length() - 2) + "]";
    }

    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static void prettyPrintTree(TreeNode node, String prefix, boolean isLeft) {
        if (node == null) {
            System.out.println("Empty tree");
            return;
        }

        if (node.right != null) {
            prettyPrintTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }

        System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.val);

        if (node.left != null) {
            prettyPrintTree(node.left, prefix + (isLeft ? "    " : "│   "), true);
        }
    }

    public static void prettyPrintTree(TreeNode node) {
        prettyPrintTree(node, "", true);
    }
    // #endregion

    static int ans = 0;

    public static void sumOfLeftLeaves(TreeNode root) {
        if (root == null || (root.right == null && root.left == null))
            return;
        if (root.left != null)
            if (root.left.left == null && root.left.right == null) {
                ans += root.left.val;
            }
        sumOfLeftLeaves(root.left);
        sumOfLeftLeaves(root.right);
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p != null && q != null) {
            if (p.val == q.val)
                return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            return false;
        }
        if (p == q)
            return true;
        return false;
    }

    public static int minDepth(TreeNode root) { // depth search
        if (root == null)
            return 0;
        return min((minDepth(root.left) + 1), (minDepth(root.right) + 1));
    }

    static class TempClass {
        TreeNode node;
        int depth;

        TempClass(TreeNode node, int depth) {
            this.depth = depth;
            this.node = node;
        }
    }

    public static int minDepth2(TreeNode root) { // breadth search
        if (root == null)
            return 0;
        Queue<TempClass> q = new LinkedList<>();
        q.add(new TempClass(root, 1));
        while (!q.isEmpty()) {
            TempClass current = q.poll();
            if (current.node.left == null && current.node.right == null)
                return current.depth;
            if (current.node.left != null) {
                q.add(new TempClass(current.node.left, current.depth + 1));
            }
            if (current.node.right != null) {
                q.add(new TempClass(current.node.right, current.depth + 1));
            }
        }

        return 0;
    }

    public static int min(int a, int b) {
        return a < b ? a : b;
    }

    public static int max(int a, int b) {
        return a > b ? a : b;
    }


    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null)
            return null;
        List<List<Integer>> result = new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int currentLevel = 0;
        while (!q.isEmpty()) {
            result.add(new LinkedList<Integer>());
            for(TreeNode i:q){
                result.get(currentLevel).add(i.val);
            }
            currentLevel++;
            q = getNextLevel(q);
        }
        return result;
    }
    public Queue<TreeNode> getNextLevel(Queue<TreeNode> q){
         Queue<TreeNode> nextLevel = new  LinkedList<>();
        while(!q.isEmpty()){
            TreeNode temp = q.poll();
            if(temp.left != null)
                nextLevel.add(temp.left);
            if(temp.right != null)
                nextLevel.add(temp.right);
        }
        return nextLevel;
    }

    public static void main(String[] args) {
        TreeNode root = stringToTreeNode("[1,2]");
        prettyPrintTree(root);
        System.out.println(minDepth2(root));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}