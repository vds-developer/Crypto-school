To run Q3.java
1. Open terminal
2. Compile the code javac Q3.java
3. To run do the following java Q3

Assumptions:
We start at 16384 since this is 2^14 thus has 14 bits. We then start checking for primes after 16384.
 We will not need 15 bits since I know 16481 is a prime that is between 2^14 and 2^15.
 
 
 To run Q4.java
1. Open terminal
2. Compile the code javac Q4.java
3. To run do the following java Q4

Assumptions/Notes:
The Encyption/decyption works because of the following
   K = Y_a^k mod q
-> K = (a^x_a mod q)^ k mod q, since Y_a = a^x_a mod q (How we generated the public key)
-> K = a^(k*x_a) mod q, by rules of arithmetic
-> K = c_1^x_a mod q, since cq = a^k mod q

Thus by using the private key we can find K (the one time key of the sender).
Once we have K it is easy to decrypt the message
c_2 = KM mod q
-> c_2*K^-1 mod q = KMK^-1 mod q = M mod q = M. Since M was assumed to be less than q.