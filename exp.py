from PIL import Image
from numpy import asarray
from random import randint
import libnum
import sys
import pprint

img = Image.open('2.jpg')
a = asarray(img)
b = asarray(img)
c = asarray(img)

col1 = len(a)
col2 = len(a[0])
row = len(a[0][0])

a[col1-1][col2-1][row-1]=-255

print(a)