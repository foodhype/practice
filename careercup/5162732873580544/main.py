class Node:
    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None


def print_inorder(root):
    if root is not None:
        print_inorder(root.left)
        print root.value
        print_inorder(root.right)


def build_bst_helper(preorder, index, lo, hi):
    if index >= len(preorder):
        return (None, index)

    root = None
    value = preorder[index]

    if value > lo and value <= hi:
        root = Node(value)
        index += 1
        if index < len(preorder):
            root.left, index = build_bst_helper(preorder, index, lo, value)
            root.right, index = build_bst_helper(preorder, index, value, hi)

    return root, index


def build_bst(preorder):
    return build_bst_helper(preorder, 0, float("-inf"), float("inf"))[0]


def main():
    preorder = [5, 1, 7, 40, 50]
    print_inorder(build_bst(preorder))


if __name__ == "__main__":
    main()
