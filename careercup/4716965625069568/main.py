from collections import defaultdict
from collections import deque


def contains(matrix, neighbor, symbol):
    return matrix[neighbor[0]][neighbor[1]] == symbol


def in_bounds(matrix, row, col):
    return row >= 0 and col >= 0 and row < len(matrix) and col < len(matrix[0])


def bfs(matrix):
    neighbors = defaultdict(set)
    guard_positions = []

    for row in xrange(len(matrix)):
        for col in xrange(len(matrix[0])):
            current = matrix[row][col]
            if current == "G":
                guard_positions.append((row, col))
            elif current == "0":
                matrix[row][col] = str(float("inf"))
            if in_bounds(matrix, row - 1, col):
                neighbors[(row, col)].add((row - 1, col))
            if in_bounds(matrix, row, col - 1):
                neighbors[(row, col)].add((row, col - 1))
            if in_bounds(matrix, row + 1, col):
                neighbors[(row, col)].add((row + 1, col))
            if in_bounds(matrix, row, col + 1):
                neighbors[(row, col)].add((row, col + 1))

    for guard_pos in guard_positions:
        visited = set()
        root = guard_pos
        visited.add(root)
        queue = deque()
        queue.appendleft(root)

        distance = 1
        while queue:
            current = queue.pop()
            for neighbor in neighbors[current]:
                if (neighbor not in visited and
                        not contains(matrix, neighbor, "B") and
                        not contains(matrix, neighbor, "G")):

                    visited.add(neighbor)
                    queue.appendleft(neighbor)
                    if float(distance) < float(matrix[neighbor[0]][neighbor[1]]):
                        matrix[neighbor[0]][neighbor[1]] = str(distance)
            distance += 1

    return matrix


def main():
    matrix = [["0", "0", "0"], ["B", "G", "G"], ["B", "0", "0"]]
    print bfs(matrix)


if __name__ == "__main__":
    main()
