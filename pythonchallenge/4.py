# http://www.pythonchallenge.com/pc/def/linkedlist.php

import re
import time
import urllib2

base_url = "http://www.pythonchallenge.com/pc/def/linkedlist.php?nothing="
nothing = "12345"

for i in xrange(400):
  conn = urllib2.urlopen("".join((base_url, nothing)))
  html = conn.read()
  conn.close()
  print "current iteration: %d" % (i)
  print "current nothing: %s" % (nothing)
  print "current HTML: %s" % (html)
  nothing_match = re.compile(r'and the next nothing is ([0-9]+)').search(html)
  if nothing_match == None:
    if html == "Yes. Divide by two and keep going.":
      nothing = str(int(nothing) / 2)
    else:
      break
  else:
    nothing = nothing_match.group(1)
  time.sleep(1) # avoid crawling too hard
