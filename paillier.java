import java.math.*;
import java.util.*;



public class paillier 
{
    private BigInteger p, q, n, nsq, lambda, g;
    private int bitlength;

    public paillier() 
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

        System.out.println("\np="+p+"\nq="+q);

        n = p.multiply(q);
        System.out.println("\nn="+n+"\n");
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
        //an object of Paillier cryptosystem
        paillier en = new paillier();
        Scanner in = new Scanner(System.in);

        System.out.println("\nEnter a message:");
        String mess=in.nextLine();

        BigInteger m = new BigInteger(mess);
        System.out.println("\nmessage="+m);
        // encrypted text
        BigInteger cipher = en.encryption(m);
        
        System.out.println("\ncipher="+cipher);
        //decrypted text
        BigInteger decipher=en.decryption(cipher);
        System.out.println("\ndecipher="+decipher);

        in.close();

    }

}