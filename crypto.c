#include<stdio.h>
#include<stdlib.h>
#include <math.h>

int p=17,q=19;

int gcd(int a,int b)
{
    int result = (a<b)?a:b; // Finding minimum of a nd b
    while (result > 0) 
    {
        if (a % result == 0 && b % result == 0) {
            break;
        }
        result--;
    }
    return result;
}

int lcm(int a, int b)
{
    return (a / gcd(a, b)) * b;
}
int invmod(int A, int M)
{
    for (int X = 1; X < M; X++)
        if (((A % M) * (X % M)) % M == 1)
            return X;
}

int encrypt(int n, int g, int m)
{
    int r,k1,k2,cipher,mess;
    

    if (gcd(g,n*n)==1){
	    printf("g is relatively prime to n*n");
    }
    else{
	    printf("WARNING: g is NOT relatively prime to n*n. Will not work!!!");
    }

    r=rand() % n;
    printf(" r=%d ",r);

    

    k1 = (int)pow(g, m)%(n*n);
    k2 = (int)pow(r, n)%(n*n);

    cipher = (k1 * k2) % (n*n);


    return cipher;
}

int decrypt(int cipher, int gLambda, int gMu,int g)
{
    int l,n;
    n=p*q;
    //l = (int)((((int)pow(cipher, gLambda)%(n*n))-1)/n);
    l = 175;
    printf("n=%d \n",n);
    printf("lambda=%d \n",gLambda);
    printf("mu=%d \n",gMu);
    printf("l=%d \n",l);
    int decipher=(l * gMu) % n;
    return decipher;
}

int main()
{
    int m=22,n,g=112,gMu,l1;
    n = p*q;
    int gLambda = lcm(p-1,q-1);
    //l1 = (int)((((int)pow(g, gLambda)%(n*n))-1)/n);
    l1=52;
    gMu = invmod(l1,n);
    int cipher=encrypt(n,g,m);
    printf("cipher=%d\n",cipher);
    int decipher=decrypt(cipher, gLambda, gMu,g);
    printf("decipher=%d\n",decipher);
    return 0;
    
}
