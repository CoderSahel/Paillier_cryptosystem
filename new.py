import libnum
import sys
from Crypto.Util.number import getPrime
from Crypto.Random import get_random_bytes
from random import randint
import libnum

def gcd(a,b):
    """Compute the greatest common divisor of a and b"""
    while b > 0:
        a, b = b, a % b
    return a
    
def lcm(a, b):
    """Compute the lowest common multiple of a and b"""
    return a * b // gcd(a, b)

def genparams():
    p = getPrime(512, randfunc=get_random_bytes)
    q = getPrime(512, randfunc=get_random_bytes)
    n = p*q
    g=n
    while (gcd(g,n*n)!=1):
      g = randint(2000,3000)

    gLambda = lcm(p-1,q-1)
    l = (pow(g, gLambda, n*n)-1)//n
    gMu = libnum.invmod(l, n)
    return gLambda, n, g, gMu,primebits

def encrypt(k):
  return pow(g, k, n*n)

def decrypt(cipher):
  l = (pow(cipher, gLambda, n*n)-1) // n
  mess= (l * gMu) % n
  return mess

def add(cipher,cipher2):
  return (cipher* cipher2) % (n*n)

def sub(cipher,cipher2):
  v1=(cipher* (libnum.invmod(cipher2,n*n)) % (n*n))
  v2=(cipher2* (libnum.invmod(cipher,n*n)) % (n*n))
  return v1,v2

def L(x,n):
	return ((x-1)//n)

primebits=60
a=9
b=3

if (len(sys.argv)>1):
  a=int(sys.argv[1])
if (len(sys.argv)>2):
  b=int(sys.argv[2])
if (len(sys.argv)>3):
  primebits=int(sys.argv[3])

######## Encrypting
gLambda, n, g, gMu, primesize = genparams()



enc_a = encrypt(a)
enc_b = encrypt(b)

print (f"Public key: g={g}, n={n}\n")
print (f"Private key: lambda={gLambda}\nMu={gMu}\n")
print (f"\nEncrypted a={enc_a}")
print (f"Encrypted b={enc_b}")

cipher2_1,cipher2_2 = sub(enc_a,enc_b)
mess1= decrypt(cipher2_1)
mess2= decrypt(cipher2_2)
res=mess1
if (mess1>n/8): res=mess2

print(f"\nVal1={a},Val2={b}. Diff: {res}")