Note:

For Question 1 part 1, the straight calculation is faster than calculation using CRT. This is probably due to the fact that the library I'm using to exponential mod is using something in the background. In reality CRT should be faster than straight calculation.

This is similarly for Question 1 part 2. In reality ECDH should be faster than DH. But I predict that in the "modPow" function there is some special technique to speed up modular arithmetic.


In Question 2, the the Bi are the outputs


In Question 3, I am printing it by byte. 