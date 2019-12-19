import java.math.BigInteger;

public class A3_Q1_P1 {
	public static void main(String[] args) {
		// int p = 773;

		// we need to pick p q so that p*q is as large as the message else it
		//would be impossible to decrypt the message.
		BigInteger p = new BigInteger("10001801");
		BigInteger q = new BigInteger("100001929");
		BigInteger one = new BigInteger("1");

		// n = p*q
		BigInteger n = p.multiply(q);

		//euler = (p-1)(q-1)
		BigInteger eulerFunction = (p.subtract(one)).multiply(q.subtract(one));

		System.out.println("p : " + p.toString());
		System.out.println("q : " + q.toString());
		System.out.println("euler: " + eulerFunction.toString());
		System.out.println("=====================\n=====================");
		// note gcd(e, euler) = 1 since e is prime
		// note 1 < e < euler
		BigInteger e = new BigInteger("65537");

		//d inverse of e
		BigInteger d = e.modInverse(eulerFunction);

		// public key is (e, n)
		//private key is(d)

		System.out.println("Public key is (" + e.toString() + ", " + n.toString() + ")");
		System.out.println("Private key is (" + d.toString() + ")");

		System.out.println("=====================\n=====================");

//encrpt
		BigInteger m = new BigInteger("466921883457309");
		BigInteger cipherText = m.modPow(e, n);

		System.out.println("Encrypting message m " + m.toString());
		System.out.println("The CipherText is " + cipherText.toString());

//decrpt
System.out.println("=====================\n=====================");
	System.out.println("Decryption: Straight Calcuation");
	long start = System.nanoTime();
	BigInteger decrpyted_m = cipherText.modPow(d, n);
	System.out.println("Decrypting cipherText " + cipherText.toString());
	System.out.println("The decrypted message was : " + decrpyted_m.toString());
	long time =  System.nanoTime() - start;
	System.out.println("Time " + time );

	//decrpt using crt
	System.out.println("=====================\n=====================");
		System.out.println("DecryptionCRT Calcution");
		start = System.nanoTime();
		BigInteger m_1 = p;
		BigInteger m_2 = q;

		BigInteger M_1 = q;
		BigInteger M_2 = p;
		BigInteger M_1_inverse = M_1.modInverse(m_1);
		BigInteger M_2_inverse = M_2.modInverse(m_2);

		BigInteger a1 = (cipherText.mod(m_1)).modPow(d.mod(m_1.subtract(one)), m_1);
		BigInteger a2 = (cipherText.mod(m_2)).modPow(d.mod(m_2.subtract(one)), m_2);

		BigInteger decrpyted_m_crt = (a1.multiply(M_1).multiply(M_1_inverse))
		.add(a2.multiply(M_2).multiply(M_2_inverse)).mod(n);
		System.out.println("Decrypting cipherText " + cipherText.toString());
		System.out.println("The decrypted message was : " + decrpyted_m_crt.toString());
		time =  System.nanoTime() - start;
		System.out.println("Time " + time );
 // String s = new BigInteger("2").multiply(new Big Integer("5")).add(new BigInteger("2")).toString();
		// System.out.print.ln(s);





	}
	}
