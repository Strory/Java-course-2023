package edu.hw1;

public class Task5 {
    private Task5() {
    }

    //    public static void main(String[] args) {
//        System.out.println(isPalindromeDescendant(221));
//    }
    @SuppressWarnings({"ParameterAssignment"})
    public static boolean isPalindromeDescendant(int numb) {
        while (countDigits(numb) > 1) {
            if (checkPalindrome((numb))) {
                return true;
            }
            numb = createChild((numb));
        }
        return false;
    }

    @SuppressWarnings({"MagicNumber", "ParameterAssignment"})
    public static boolean checkPalindrome(int numb) {
        int order = countDigits(numb);
        int tenPow = order - 1;
        for (int i = 0; i < (order) / 2; ++i) {
            if ((numb / (myPow(10, tenPow))) != (numb % 10)) {
                return false;
            }
            numb %= myPow(10, tenPow);
            numb /= 10;
            tenPow -= 2;
        }
        return true;
    }

    @SuppressWarnings({"MagicNumber", "ParameterAssignment"})
    public static int createChild(int numb) {
        int order = countDigits(numb);
        int tenPow = order - 1;
        StringBuilder child = new StringBuilder();
        for (int i = 0; i < order / 2; ++i) {
            int numb1 = numb / myPow(10, tenPow);
            numb %= myPow(10, tenPow);
            --tenPow;
            int numb2 = numb / myPow(10, tenPow);
            numb %= myPow(10, tenPow);
            --tenPow;
            int pairSum = numb1 + numb2;
            child.append(pairSum);
        }
        if (tenPow == 0) {
            child.append(numb);
        }
        return Integer.parseInt(child.toString());
    }

    @SuppressWarnings({"MagicNumber", "ParameterAssignment"})
    public static int countDigits(int n) {
        int count = 1;
        while (n > 9) {
            n /= 10;
            ++count;
        }
        return count;
    }

    public static int myPow(int n, int pow) {
        int res = 1;
        for (int i = 0; i < pow; ++i) {
            res *= n;
        }
        return res;
    }
}
