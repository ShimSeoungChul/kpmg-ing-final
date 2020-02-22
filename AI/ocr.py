# apt-get install tesseract-ocr-kor
# pip install opencv-python
# pip install pytesseract

import sys

import cv2
import PIL
import pytesseract

img_path = sys.argv[1]
img = cv2.imread(img_path,cv2.IMREAD_COLOR)
img2 = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
img2 = PIL.Image.fromarray(img2)
txt = pytesseract.image_to_string(img2, lang='kor')
print(txt)
