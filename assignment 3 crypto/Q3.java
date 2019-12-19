import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Q3 {
    public static Random rand = new Random();

    // not there is an upper bound an the type int any operations with int must be
    // between -2147483648 to 2147483647;
    public static void main(String[] args) {
        int n = 16384 + 1; // this is equal to 2^14. Thus we can start here. Want to be odd
        boolean found = false;
        outer:
        while(!found) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 2; i<n-1; i++){
                list.add(i);
            }
            //randomize list and take first 5
            Collections.shuffle(list);
            for (int t = 0 ; t< 5; t++) {
                int a = list.get(t);
                if (!millerRabinTest(n,a)) { // this number n is composite number
                    n += 2; // next odd number
                    continue outer; // goes to next while loop
                }
            }
            System.out.println("The first probable prime with t = 5 is : " + n);
            System.out.println("The random 'a' values are " + list.get(0) + ", " + list.get(1) + ", "
                    + list.get(2) + ", " + list.get(3) + ", " + list.get(4));
            found = true;
        }


    }



    // true means inconclusive and false means composite
    public static boolean millerRabinTest(int n, int a) {
        //n must be odd
        if (n % 2 == 0) {
            return false;
        }

        // find k, q, k>0 q odd so that n-1 = 2^k*q
        int temp = n - 1;
        int q = 1;
        int k = 0;
        while ((temp % 2) == 0) {
            temp = temp / 2;
            k++;
            if (temp % 2 == 1) {
                q = temp;
                break;
            }
        }


//		System.out.println("k: " + k);
//		System.out.println("q: " + q);
//		System.out.println("a: " + a);
        if (Math.pow(a, q) % n == 1) {
            return true;
        }
        for (int j = 0; j < k; j++) {
            if (BigInteger.valueOf(a).modPow(BigInteger.valueOf(2).pow(j).multiply(BigInteger.valueOf(q)),
                    BigInteger.valueOf(n)).intValue() == n - 1) {
                return true;
            }
        }
        return false;
    }

}
