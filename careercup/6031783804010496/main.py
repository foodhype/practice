def product(matrix, index=0):
    row = matrix[index]
    for element in row:
        if index + 1 < len(matrix):
            for sub_product in product(matrix, index + 1):
                yield [element] + sub_product
        else:
            yield [element]


def string_concat_product(matrix):
    return ["".join(prod) for prod in product(matrix, 0)]


def main():
    matrix = [["abc", "def", "gh"], ["f", "g"], ["qrt", "xyz", "pqr"]]
    print string_concat_product(matrix)


if __name__ == "__main__":
    main()

