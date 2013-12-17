# http://www.pythonchallenge.com/pc/def/equality.html

import re

f = open("3.in")
text = f.read()
f.close()

pattern = re.compile(r'[^A-Z]+[A-Z]{3}[a-z]{1}[A-Z]{3}[^A-Z]+')
print re.findall(pattern, text)

pattern = re.compile(r'[^A-Z]+[A-Z]{3}([a-z]{1})[A-Z]{3}[^A-Z]+')
print re.findall(pattern, text)
