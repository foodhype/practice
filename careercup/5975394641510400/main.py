def verify_sudoku_solution(solution):
    valid = set([1, 2, 3, 4, 5, 6, 7, 8, 9])
    found = [False] * 9
    for row in solution:
        for number in row:
            if found[number - 1] or number not in valid:
                return False
            else:
                found[number - 1] = True
        found[:] = [False] * 9

    for index in xrange(9):
        for row in solution:
            number = row[index]
            if found[number - 1] or number not in valid:
                return False
            else:
                found[number - 1] = True
        found[:] = [False] * 9          

    for row_index in xrange(0, 9, 3):
        for col_index in xrange(0, 9, 3):
            for row_offset in xrange(3):
                for col_offset in xrange(3):
                    number = solution[row_index + row_offset][col_index + col_offset]
                    if found[number - 1] or number not in valid:
                        return False
                    else:
                        found[number - 1] = True
            found[:] = [False] * 9

    return True


def main():
    sudoku_solution = [[2, 4, 8, 3, 9, 5, 7, 1, 6],
            [5, 7, 1, 6, 2, 8, 3, 4, 9],
            [9, 3, 6, 7, 4, 1, 5, 8, 2],
            [6, 8, 2, 5, 3, 9, 1, 7, 4],
            [3, 5, 9, 1, 7, 4, 6, 2, 8],
            [7, 1, 4, 8, 6, 2, 9, 5, 3],
            [8, 6, 3, 4, 1, 7, 2, 9, 5],
            [1, 9, 5, 2, 8, 6, 4, 3, 7],
            [4, 2, 7, 9, 5, 3, 8, 6, 1]]

    print verify_sudoku_solution(sudoku_solution)


if __name__ == "__main__":
    main()
