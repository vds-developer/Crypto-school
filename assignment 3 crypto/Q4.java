import java.math.BigInteger;
import java.util.Random;

public class Q4 {
    public static  Random rand = new Random();

    public static void main(String[] args) {
        System.out.println("User A is creating its public/private key ...");
        int q = 71;
        int a = 12;
//      "Step 1 - Generating X_A"
        int x_a = rand.nextInt(q-1) + 2;
        //Step 2 - compute Y^A
        //since 71 is prime we can do a^x_a mod q = ((a mod q)^(x_a mod q-1))
        int y_a = BigInteger.valueOf(a).modPow(BigInteger.valueOf(x_a), BigInteger.valueOf(q)).intValue();
        System.out.println("A's private key is : " + x_a);
        System.out.println("A's public key is : {" + q + ", " + a + ", " + y_a + "}" );

        //String representing message, messages longer than q are sent as blocks
        // Step done by user B
        System.out.println("\nUser B decides to send a message to User A ....");
        int M = rand.nextInt(q);
        // 1 <= k <= q-1
        int k = rand.nextInt(q-1) + 1;
        //Step compute one time key
//        System.out.println("k = " + k);
        int K = BigInteger.valueOf(y_a).modPow(BigInteger.valueOf(k), BigInteger.valueOf(q)).intValue();
//        System.out.println("K = " + K);
        //encrpt integer as (C1, C2)
        int c_1 = BigInteger.valueOf(a).modPow(BigInteger.valueOf(k), BigInteger.valueOf(q)).intValue();
        int c_2 =  (K*M) % q;
        System.out.println("User B sends the message: " + M);
        System.out.println("User B sends the encypted message (" + c_1 + ", " + c_2 + ") to User A");



        System.out.println("\nUser A decides to decrpyt the message sent by B...");
        //User A now needs to decrypt the message and recover plain text
        //Key K computed by A
        int K_A = BigInteger.valueOf(c_1).modPow(BigInteger.valueOf(x_a), BigInteger.valueOf(q)).intValue();
        int K_A_inverse = modInverse(K_A, q);
        if (K_A_inverse == 0) System.out.println("Cannot find K^-1"); // should never happen
        // message decryped by A.
        int M_A = BigInteger.valueOf(c_2 * K_A_inverse).mod(BigInteger.valueOf(q)).intValue();

        System.out.println("User A find that the one time key (computed by B) is " + K_A +
                "\nUser A decrypts the message to find that the message was " + M_A
        );
    }


    static int modInverse(int a, int m)
    {
        for (int x = 1; x < m; x++)
            if ((a * x) % m == 1)
                return x;
        return 0;
    }
}