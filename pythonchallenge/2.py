# http://www.pythonchallenge.com/pc/def/ocr.html

from collections import Counter
import re

f = open("2.in")
text = f.read()
f.close()
histogram = Counter(text).most_common(256)
histogram.reverse()
print histogram
pattern = re.compile(r'([a-z])')
print re.findall(pattern, text)
