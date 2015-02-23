enum BST<T> {
    Node(T, Box<BST<T>>, Box<BST<T>>),
    Leaf,
}


fn insert<T>(tree: Box<BST<T>>, value: T) -> Box<BST<T>> where T: Ord {
    let temp = *tree; // hack to get around box destructuring bug in Rust

    Box::new(
        match temp {
            BST::Leaf => {
                BST::Node(value, Box::new(BST::Leaf), Box::new(BST::Leaf))
            },
            BST::Node(root_value, left, right) => {
                if value < root_value {
                    BST::Node(root_value, insert(left, value), right)
                } else {
                    BST::Node(root_value, left, insert(right, value))
                }
            }
        }
    )
}


fn main() {
    let tree1 = Box::new(
        BST::Node(5, Box::new(BST::Leaf), Box::new(BST::Leaf)));
    let tree2 = insert(tree1, 3);
    let tree3 = insert(tree2, 7);

    let unboxed_root = *tree3;

    match unboxed_root {
        BST::Node(value, left, right) => {
            println!("Root is {}.", value);

            let unboxed_left = *left;
            let unboxed_right = *right;

            match unboxed_left {
                BST::Node(left_value, _, _) => {
                    println!("Left child is {}.", left_value);
                },
                _ => {}
            };

            match unboxed_right {
                BST::Node(right_value, _, _) => {
                    println!("Right child is {}.", right_value);
                },
                _ => {}
            };
        },
        _ => {}
    }
}
