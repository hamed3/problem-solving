public class Testing {
    // #region methods
    public static void merge(int[] a, int m, int[] b, int n) {
        int start = m + n - 1;
        m--;
        n--;
        for (int i = start; i >= 0 && m >= 0 && n >= 0; i--) {
            if (a[m] > b[n]) {
                a[i] = a[m];
                m--;
            } else {
                a[i] = b[n];
                n--;
            }
        }
        for (int i = n; i >= 0; i--) {
            a[i] = b[i];
        }
    }

    public static int[] sortedSquares(int[] A) {
        int l = A.length;
        if (l < 1)
            return A;
        for (int i : A) {
            System.out.print(i + " ");
        }
        System.out.println();
        int i = 0; // positive pointer
        int j = 0; // negative pointer
        while (j < l && A[j] < 0)
            j++;
        i = j;
        j--;

        int[] result = new int[l];
        int k = 0;
        while (j >= 0 && i < A.length) {
            int x = A[i] * A[i], y = A[j] * A[j];
            if (x < y) {
                result[k++] = x;
                i++;
            } else {
                result[k++] = y;
                j--;
            }
        }
        while (j >= 0) {
            result[k++] = A[j] * A[j];
            j--;
        }
        while (i < A.length) {
            result[k++] = A[i] * A[i];
            i++;
        }

        for (int q : result) {
            System.out.print(q + " ");
        }
        System.out.println();
        return result;
    }

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for (int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static void prettyPrintLinkedList(ListNode node) {
        while (node != null && node.next != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }

        if (node != null) {
            System.out.println(node.val);
        } else {
            System.out.println("Empty LinkedList");
        }
    }

    public static void reverseLinkedList(ListNode node) {
        prettyPrintLinkedList(node);
        if (node == null || node.next == null) {
            return;
        }
        ListNode prev = null, next;
        while (node != null) {
            next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        if (head.next.next == null) {
            if (head.val == head.next.val)
                return true;
            return false;
        }

        ListNode slow, fast;
        slow = fast = head;
        while (fast.next != null) {
            if (fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            } else {
                fast = fast.next;
            }
        }

        reverseLinkedList(slow.next);
        ListNode p1 = head, p2 = fast;
        while (p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1=p1.next;
            p2=p2.next;
        }
        return true;
    }


    static ListNode ref;
    public static boolean isPalindrome2(ListNode head) {
        ref = head;        
        return check(head);
    }
    
    public static boolean check(ListNode node){
        if(node == null) return true;
        boolean ans = check(node.next);
        boolean isEqual = (ref.val == node.val)? true : false; 
        ref = ref.next;
        return ans & isEqual;
    }

    public static boolean isPalindrome(String s) {
        System.out.println(s);
        if(s == "" || s.length() == 1)
            return true;
        char[] temp = s.toCharArray();
        int i = 0, j = temp.length-1;
        boolean flag;
        while(i<temp.length && j>=0){
            flag = false;
            if(!Character.isLetter(temp[i])){
                flag = true;
                i++;
            }
            if(!Character.isLetter(temp[i])){
                flag = true;
                j--;
            }
            if(flag) continue;
            System.out.print(temp[j]);
            if(Character.toLowerCase(temp[i]) != Character.toLowerCase(temp[j]))
                return false;
            i++;
            j--;
        }
        return true;
    }

    // #endregion

    public static void main(String[] args) {
        System.out.println("\n" + isPalindrome("A man, a plan, a canal: Panama"));
    }
}

// #region ListNode Class
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
// #endregion