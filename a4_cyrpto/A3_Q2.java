import java.math.BigInteger;

public class A3_Q2 {
    //note that p = q = 3 mod 4;
    private final static BigInteger p = new BigInteger("30000000091");
    private final static BigInteger q = new BigInteger("40000000003");
    private final static BigInteger n = p.multiply(q);
    //note that p nor q is a factor of s thus s and n are coprime;
    private final static BigInteger s = new BigInteger("7");


    public static void main(String[] args){
        //first 15 outputs
        BigInteger TWO = new BigInteger("2");

        BigInteger[][] b = new BigInteger[15][3];
        BigInteger x0 = s.pow(2).mod(n);
        BigInteger prevX = x0;

        BigInteger currentX;
        for (int i =0; i < 15; i++) {
            currentX = prevX.pow(2).mod(n);
            b[i][0] = new BigInteger(String.valueOf(i));
            b[i][1] = currentX;
            b[i][2] = currentX.mod(TWO);
            prevX = currentX;
        }

        System.out.println("The first 15 outputs are");
        for (BigInteger[] bigIntegers : b) {
            System.out.println("i: " + bigIntegers[0].intValue());
            System.out.println("xi: " + bigIntegers[1].intValue());
            System.out.println("bi: " + bigIntegers[2].intValue());
            System.out.println("-----------------------------------");
        }
    }
}
