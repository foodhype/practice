import Queue

def bfs(graph, start, target):
    if start == target:
        return start

    visited = set()
    visited.add(start)
    queue = Queue.Queue()
    queue.put(start)
    
    while not queue.empty():
        node = queue.get()
        for neighbor in graph[node]:
            if neighbor not in visited:
                if neighbor == target:
                    return neighbor
                visited.add(neighbor)
                queue.put(neighbor)
                
    return None


g = {1: [1, 2, 3], 2: [8], 3: [4, 5], 4: [], 5: [8]}
print bfs(g, 1, 8)
