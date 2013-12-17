# http://www.pythonchallenge.com/pc/def/peak.html

import pickle

f = open("5.in")
unpickled = pickle.Unpickler(f).load()
f.close()

for pair_list in unpickled:
  buf = []
  for pair in pair_list:
    char = pair[0]
    reps = pair[1]
    for i in xrange(reps):
      buf.append(char)
  print ''.join(buf)
