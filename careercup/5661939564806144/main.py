def print_diagonally(matrix):
    for rowColumnSum in xrange(len(matrix) + len(matrix[0])):
        line = []
        for row in xrange(rowColumnSum + 1):
            column = rowColumnSum - row
            if row < len(matrix) and column < len(matrix[0]):
                line.append(str(matrix[row][column]))
        print " ".join(line)


def main():
    matrix = [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]]
    print_diagonally(matrix)


if __name__ == "__main__":
    main()
