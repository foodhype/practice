from fractions import Fraction
import sys

for line in sys.stdin:
    graph = {}
    exchange = " ".split(line.rstrip())
    
    if exchange[0] == ".":
        break
    elif exchange[0] == "!":
        name1, quantity1 = exchange[3], exchange[2]
        name2, quantity2 = exchange[6], exchange[5]
        exchange_rate1 = Fraction(quantity1, quantity2)
        exchange_rate2 = Fraction(quantity2, quantity1)

        if name1 in graph:
            graph[name1][name2] = exchange_rate1
        else:
            graph[name1] = {name2: exchange_rate1}

        if name2 in graph:
            graph[name2][name1] = exchange_rate2
        else:
            graph[name2] = {name1: exchange_rate2}
    elif exchange[0] == "?":
        start, end = exchange[1], exchange[3]
        visited = set()
        dfs(graph, start, end, 1.0, visited)


def dfs(graph, start, end, exchange_rate, visited):
    if start in visited:
        return None
    visited.add(start)
    for name, rate in graph[start].items():
        if name not in visited:
            result = dfs(name, end, exchange_rate * rate)
            if result != None:
                return start
    return None
    
