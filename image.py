# Import the necessary libraries
from PIL import Image
from numpy import asarray


# load the image and convert into
# numpy array


img = Image.open('1.png')
numpydata = asarray(img)


# data
print(numpydata)
 
print(type(numpydata))
 
#  shape
print(numpydata.shape)
 
# Below is the way of creating Pillow
# image from our numpyarray
pilImage = Image.fromarray(numpydata)
print(type(pilImage))
 
# Let us check  image details
print(pilImage.mode)
print(pilImage.size)

pilImage.save('new.png')