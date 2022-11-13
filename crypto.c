#include<stdio.h>
#include<stdlib.h>
#include <math.h>

long long int p=17,q=19;

long long int gcd(long long int a,long long int b)
{
    long long int result = (a<b)?a:b; // Finding minimum of a nd b
    while (result > 0) 
    {
        if (a % result == 0 && b % result == 0) {
            break;
        }
        result--;
    }
    return result;
}

long long int lcm(long long int a, long long int b)
{
    return (a / gcd(a, b)) * b;
}
long long int invmod(long long int A, long long int M)
{
    for (long long int X = 1; X < M; X++)
        if (((A % M) * (X % M)) % M == 1)
            return X;
}

long long int encrypt(long long int n, long long int g, long long int m)
{
    long long int r,k1,k2,cipher,mess;
    

    if (gcd(g,n*n)==1){
	    printf("g is relatively prime to n*n");
    }
    else{
	    printf("WARNING: g is NOT relatively prime to n*n. Will not work!!!");
    }

    r=rand() % n;
    printf(" r=%lld ",r);

    

    k1 = (long long int)pow(g, m)%(n*n);
    k2 = (long long int)pow(r, n)%(n*n);

    cipher = (k1 * k2) % (n*n);


    return cipher;
}

long long int decrypt(long long int cipher, long long int gLambda, long long int gMu,long long int g)
{
    long long int l,n;
    n=p*q;
    //l = (long long int)((((long long int)pow(cipher, gLambda)%(n*n))-1)/n);
    l = 175;
    printf("n=%lld \n",n);
    printf("lambda=%lld \n",gLambda);
    printf("mu=%lld \n",gMu);
    printf("l=%lld \n",l);
    long long int decipher=(l * gMu) % n;
    return decipher;
}

long long int main()
{
    long long int m=22,n,g=112,gMu,l1;
    n = p*q;
    long long int gLambda = lcm(p-1,q-1);
    //l1 = (long long int)((((long long int)pow(g, gLambda)%(n*n))-1)/n);
    l1=52;
    gMu = invmod(l1,n);
    long long int cipher=encrypt(n,g,m);
    printf("cipher=%lld\n",cipher);
    long long int decipher=decrypt(cipher, gLambda, gMu,g);
    printf("decipher=%lld\n",decipher);
    return 0;
    
}
