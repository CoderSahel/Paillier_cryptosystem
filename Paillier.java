
import java.math.*;
import java.util.*;



public class Paillier 
{


    private BigInteger p, q, n, nsq, lambda, g;
    private int bitlength;
    
    public Paillier() 
    {
        keygeneration(512, 64);
    }

    public void keygeneration(int bitlengthVal, int certainty) 
    {
        bitlength = bitlengthVal;

        p = new BigInteger(bitlength / 2, certainty, new Random());
        q = new BigInteger(bitlength / 2, certainty, new Random());

        //p=new BigInteger ("17");
        //q=new BigInteger ("19");

        System.out.println("p="+p);
        System.out.println("q="+q);

        n = p.multiply(q);
        nsq = n.multiply(n);

        g = new BigInteger(bitlength, new Random());
        lambda = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)).divide(p.subtract(BigInteger.ONE).gcd(q.subtract(BigInteger.ONE)));
        // check if g is good
        if (g.modPow(lambda, nsq).subtract(BigInteger.ONE).divide(n).gcd(n).intValue() != 1) 
        {
            System.out.println("g is not good. Choose g again.");
            System.exit(1);
        }
        else
        {
            System.out.println("g is good\n");
        }
    }


    public BigInteger encryption(BigInteger m) 
    {
        BigInteger r = new BigInteger(bitlength, new Random());
        System.out.println("\nr="+r+"\ng="+g);
        //BigInteger r = new BigInteger(10, new Random());
        BigInteger cipher=g.modPow(m, nsq).multiply(r.modPow(n, nsq)).mod(nsq);
        return cipher;

    }

    public BigInteger decryption(BigInteger c) 
    {
        BigInteger mu = g.modPow(lambda, nsq).subtract(BigInteger.ONE).divide(n).modInverse(n);
        System.out.println("\nlambda="+lambda+"\nmu="+mu);
        BigInteger decipher=c.modPow(lambda, nsq).subtract(BigInteger.ONE).divide(n).multiply(mu).mod(n);
        return decipher;
    }

    public static void main(String[] str) 
    {

        Paillier en = new Paillier();
        // meassages
        BigInteger m1 = new BigInteger("76576");
        BigInteger m2 = new BigInteger("64656");

        // encryption
        BigInteger cipher1 = en.encryption(m1);
        BigInteger cipher2 = en.encryption(m2);

        //encrypted text
        System.out.println(cipher1);
        System.out.println(cipher2);

        //decrypted text
        System.out.println(en.decryption(cipher1).toString());
        System.out.println(en.decryption(cipher2).toString());

        // D(E(m1)*E(m2) mod n^2) = (m1 + m2) mod n 
        BigInteger product_em1em2 = cipher1.multiply(cipher2).mod(en.nsq);
        BigInteger sum_m1m2 = m1.add(m2).mod(en.n);
        System.out.println("original sum: " + sum_m1m2.toString());
        System.out.println("decrypted sum: " + en.decryption(product_em1em2).toString());

        // D(E(m1)^m2 mod n^2) = (m1*m2) mod n 
        BigInteger expo_em1m2 = cipher1.modPow(m2, en.nsq);
        BigInteger prod_m1m2 = m1.multiply(m2).mod(en.n);
        System.out.println("original product: " + prod_m1m2.toString());
        System.out.println("decrypted product: " + en.decryption(expo_em1m2).toString());

    }
}