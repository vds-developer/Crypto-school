import java.math.BigInteger;
import java.awt.Point;

public class A3_Q1_P2 {
//    static final BigInteger p = 23;
//    static final int a = 9;
//    static final int b = 17;
//    static final BigInteger p = new BigInteger("23");
    static final BigInteger p = new BigInteger("100043");
//    static final BigInteger privateKeyA = new BigInteger("6");
//    static final BigInteger privateKeyB =  new BigInteger("4");

    static final BigInteger privateKeyA = new BigInteger("120");
    static final BigInteger privateKeyB =  new BigInteger("260");

    //fod DH
    // 2 is a primitive root of 100043;
    static final BigInteger alpha = new BigInteger("2");


    //for ECDH
    static final Point generator = new Point(16,5);
    static final BigInteger a = new BigInteger("9");
    static final BigInteger b = new BigInteger("17");

    public static void main(String[] args){

    runDH();
    runECDH();




    }

    public static void runDH(){
        System.out.println("Running DH");
        System.out.println("====================");
        System.out.println("Generating public keys");

        BigInteger publicKeyA = alpha.modPow(privateKeyA, p);
        BigInteger publicKeyB = alpha.modPow(privateKeyB, p);
        System.out.println("A's public key is " + publicKeyA.intValue());
        System.out.println("B's public key is " + publicKeyB.intValue());

        System.out.println("====================");
        System.out.println("Calculating shared secret keys");
        long start = System.nanoTime();
        BigInteger secretKeyA = publicKeyB.modPow(privateKeyA, p);
        BigInteger secretKeyB = publicKeyA.modPow(privateKeyB, p);
        System.out.println("Secret key calculated by A: " + secretKeyA.intValue());
        System.out.println("Secret key calculated by B: " + secretKeyB.intValue());
        System.out.println("Time Spent: " + (System.nanoTime() - start));
        System.out.println("====================");



    }

    public static void runECDH(){
        System.out.println("Running ECDH");
        System.out.println("====================");
        System.out.println("Generating public keys");

        Point publicKeyA = pointMultiplication(generator, privateKeyA.intValue());
        Point publicKeyB = pointMultiplication(generator, privateKeyB.intValue());

//        System.out.println("A's private key is : (" + privateKeyA.intValue() + ")");
        System.out.println("A's public key is : (" + publicKeyA.x + ", " + publicKeyA.y + ")");

//        System.out.println("B's private key is : (" + privateKeyB + ")");
        System.out.println("B's public key is : (" + publicKeyB.x + ", " + publicKeyB.y + ")");
//
        System.out.println("====================");
        System.out.println("Calculating shared secret keys");

        long start = System.nanoTime();
        Point secretKeyA = pointMultiplication(publicKeyB, privateKeyA.intValue());
        Point secretKeyB = pointMultiplication(publicKeyA, privateKeyB.intValue());
        System.out.println(System.nanoTime() - start);

        System.out.println("Secret key calculated by A: (" + secretKeyA.x + ", " + secretKeyA.y + ")");
        System.out.println("Secret key calculated by B: (" + secretKeyB.x + ", " + secretKeyB.y + ")");
        System.out.println("Time Spent: " + (System.nanoTime() - start));
        System.out.println("====================");

    }




    public static Point pointMultiplication(Point generator, int b){
        Point infinity = new Point(0,0);
        char[] b_string = Integer.toBinaryString(b).toCharArray();
//        System.out.println("======");
        for(int i = 0; i < b_string.length; i++) {
//            if (!infinity.equals(new Point(0,0))) infinity = pointDouble(infinity);
            infinity = pointDouble(infinity);
//            System.out.println(b_string[i]);
            if(b_string[i] == '1') {
//                System.out.println("Doing point addition");
                infinity = pointAddition(infinity, generator);
            }
//            System.out.println("index : " + i + " point is: " + infinity.x + " " + infinity.y);
        }
        return infinity;
    }

    public static Point pointDouble(Point point){
        if (point.equals(new Point(0,0))) return point; //doubling infinity point is 0;
        BigInteger x = new BigInteger(String.valueOf(point.x));
        BigInteger y = new BigInteger(String.valueOf(point.y));
//        int y = point.y;
        BigInteger x_new;
        BigInteger y_new;

        BigInteger THREE = new BigInteger("3");
        BigInteger TWO = new BigInteger("2");

        BigInteger delta_numerator =  x.pow(2).multiply(THREE).add(a).mod(p);
        BigInteger delta_denometor = y.multiply(TWO).modInverse(p);
        BigInteger delta = delta_numerator.multiply(delta_denometor).mod(p);

        x_new = delta.pow(2).subtract(x).subtract(x).mod(p);
        y_new = delta.multiply(x.subtract(x_new)).subtract(y).mod(p);

//        int delta = (int)(3*Math.pow(x, 2) + a) / (2*y);
//        x_new = (int)Math.pow(delta,2) - x - x;
//        y_new = (int) delta * (x - x_new) - y;
        return new Point(x_new.intValue(), y_new.intValue());
    }

    public static Point pointAddition(Point a, Point b) {
        if(a.equals(new Point(0,0))) return b; // 0+b = b
        if(b.equals(new Point(0,0))) return a; // 0+a = a

        BigInteger x_a = new BigInteger(String.valueOf(a.x));
        BigInteger y_a = new BigInteger(String.valueOf(a.y));

        BigInteger x_b = new BigInteger(String.valueOf(b.x));
        BigInteger  y_b = new BigInteger(String.valueOf(b.y));

//        int y_a = a.y;
//        int x_b = b.x;
//        int y_b = b.y;
        BigInteger x_new;
        BigInteger y_new;

        BigInteger delta_numerator = y_b.subtract(y_a).mod(p);
        BigInteger delta_denometor = x_b.subtract(x_a).modInverse(p);
        BigInteger delta = delta_numerator.multiply(delta_denometor).mod(p);
        x_new = delta.pow(2).subtract(x_a).subtract(x_b).mod(p);
        y_new = delta.multiply(x_a.subtract(x_new)).subtract(y_a).mod(p);
//        x_new = (int)Math.pow(delta,2) - x_a - x_b;
//        y_new = (int) delta * (x_a - x_new) - y_a;
        return new Point(x_new.intValue(), y_new.intValue());
    }
//
//    private static class Point {
//        private BigInteger x;
//        private BigInteger y;
//        Point(BigInteger x, BigInteger y) {
//            this.x = x;
//            this.y = y;
//        }
//    }
//

    // calculates BG


}
