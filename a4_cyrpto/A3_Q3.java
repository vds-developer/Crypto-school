
import javax.crypto.*;
import java.nio.ByteBuffer;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Date;

public class A3_Q3 {
    private static String type = "DESede/CBC/NoPadding";

    public static void main(String[] args) throws Exception {


        KeyGenerator keygenerator = KeyGenerator.getInstance("DESede");
        SecretKey key = keygenerator.generateKey();
//        System.out.println(key1.getEncoded().length);
        SecureRandom secureRandom = new SecureRandom();
        byte[] seed = secureRandom.generateSeed(8); // 64bits seed

        //first 5 outputs of 8 bytes
        byte[][] outputs = new byte[5][8];
//        System.out.println(longToBytes(System.currentTimeMillis()).length);
        //first 5
        for(int i =0 ; i < 5; i++) {
            //64 bit representation of current date time
            byte[] currentDateTime = longToBytes(System.currentTimeMillis());

            //compute first triple des with datetime as input
            byte[] DES1 =  tripleDes(currentDateTime, key);

            //xor with v_i
            byte[] XOR1 = xor(DES1, seed);

            //single output
            byte[] r =  tripleDes(XOR1, key);

            //xor des1 with r
            byte[] XOR2 = xor(DES1, r);
            outputs[i] = XOR2;

            seed = tripleDes(XOR2, key);


        }

        for (int i = 0; i<outputs.length; i++) {
            System.out.println("Iteration : " + i);
            for (byte b : outputs[i]) {
                System.out.printf("0x%02X", b);
				System.out.print(" ");
            }
			System.out.println();
            System.out.println("----------------");
        }

    }

    public static byte[] xor(byte[] a, byte[] b) {
        byte[] finalValue = new byte[a.length];
        for(int i =0 ;i<a.length; i++) {
            finalValue[i] = (byte)(a[i] ^ b[i]);
        }
        return finalValue;
    }

    public static byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }

    public static byte[] tripleDes( byte[] input, Key key1) throws Exception {
        Cipher cipher1 = Cipher.getInstance(type);
        cipher1.init(Cipher.ENCRYPT_MODE, key1);
        return cipher1.doFinal(input);

    }
}
