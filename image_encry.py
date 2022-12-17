from PIL import Image
from numpy import asarray
from random import randint
import libnum
import sys

def gcd(a,b):
    """Compute the greatest common divisor of a and b"""
    while b > 0:
        a, b = b, a % b
    return a
    
def lcm(a, b):
    """Compute the lowest common multiple of a and b"""
    return a * b // gcd(a, b)



def L(x,n):
	return ((x-1)//n)

p=17
q=19

n = p*q

gLambda = lcm(p-1,q-1)


#g = randint(20,150)
g=112

r = randint(20,150)

l = (pow(g, gLambda, n*n)-1)//n
#gMu=323
gMu = libnum.invmod(l, n)

img = Image.open('2.jpg')
a = asarray(img)
b = asarray(img)
c = asarray(img)

#p=vector(b)

#my = asarray.array(list(map(asarray.int_, b)))

print(a)

col1 = len(a)
col2 = len(a[0])
row = len(a[0][0])


for i in range(0,col1):
    for j in range(0,col2):
        for k in range(0,row):
            #print(a[i][j][k])
            u=int(a[i][j][k])
            k1 = pow(g, u, n*n)
            k2 = pow(r, n, n*n)
            cipher = (k1 * k2) % (n*n)
            b[i][j][k]=cipher
            
print(b)
            
for i in range(0,col1):
    for j in range(0,col2):
        for k in range(0,row):
            #print(a[i][j][k])
            cipher=int(b[i][j][k])
            l = (pow(cipher, gLambda, n*n)-1) // n
            mess= (l * gMu) % n
            c[i][j][k]=mess
            
pilImage = Image.fromarray(b)
pilImage.save('encypted.jpg')


         
pilImage = Image.fromarray(c)
pilImage.save('deciphered.jpg')