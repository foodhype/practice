# www.pythonchallenge.com/pc/def/274877906944.html

import string

f = open("1.in")
text = f.read()
f.close()
url = "http://www.pythonchallenge.com/pc/def/map.html"
trans = lambda text: text.translate(string.maketrans(
    "abcdefghijklmnopqrstuvwxyz","cdefghijklmnopqrstuvwxyzab"))
print trans(text)
print trans(url)
