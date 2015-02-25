use std::cmp::Ordering;


struct BST<T> {
    root: Option<Box<TreeNode<T>>>
}


impl<T: Ord> BST<T> {
    fn new() -> BST<T> {
        BST {
            root: None
        }
    }

    fn contains(&self, value: T) -> bool {
        BST::contains_with(&self.root, value)
    }

    fn contains_with(root: &Option<Box<TreeNode<T>>>, value: T) -> bool {
        let mut current: &Option<Box<TreeNode<T>>> = root;

        loop {
            match *current {
                Some(ref node) => {
                    match value.cmp(&node.value) {
                        Ordering::Less => {
                            current = &node.left;
                        },
                        Ordering::Greater => {
                            current = &node.right;
                        },
                        Ordering::Equal => {
                            return true
                        }
                    }
                },
                None => {
                    return false
                }
            }
        }
    }

    fn insert(&mut self, value: T) {
        BST::insert_with(&mut self.root, value);
    }

    fn insert_with(root: &mut Option<Box<TreeNode<T>>>, value: T) {
        match *root {
            Some(ref mut node) => {
                match value.cmp(&node.value) {
                    Ordering::Less => {
                        BST::insert_with(&mut node.left, value);
                    },
                    Ordering::Greater => {
                        BST::insert_with(&mut node.right, value);
                    },
                    Ordering::Equal => {
                        // TODO: Replace this println with proper error handling.
                        println!("Cannot insert duplicate into binary search tree.");
                    }
                }
            },
            None => {
                *root = Some(Box::new(TreeNode::new(value)));
            }
        }
    }
}


struct TreeNode<T> {
    value: T,
    left: Option<Box<TreeNode<T>>>,
    right: Option<Box<TreeNode<T>>>
}


impl<T: Ord> TreeNode<T> {
    fn new(value: T) -> TreeNode<T> {
        TreeNode {
            value: value,
            left: None,
            right: None
        }
    }
}


fn main() {
    let mut tree = BST::new();
    tree.insert(5);
    tree.insert(3);
    tree.insert(7);

    println!("Contains 5?: {}", tree.contains(5));
    println!("Contains 3?: {}", tree.contains(3));
    println!("Contains 4?: {}", tree.contains(4));
    println!("Contains 7?: {}", tree.contains(7));
}
