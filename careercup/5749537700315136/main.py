class Step(object):
    def __init__(self, start, finish):
        self.start = start
        self.finish = finish

    def __str__(self):
        return "Step(%s, %s)" % (self.start, self.finish)


class Node(object):
    def __init__(self, value, next_node):
        self.value = value
        self.next_node = next_node


def find_path(steps):
    nodes = {}
    in_degree_nodes = set()
    for step in steps:
        node = None
        if step.start not in nodes:
            node = Node(step.start, None)
            nodes[step.start] = node
        else:
            node = nodes[step.start]

        next_node = None
        if step.finish not in nodes:
            next_node = Node(step.finish, None)
            nodes[step.finish] = next_node
        else:
            next_node = nodes[step.finish]
        
        node.next_node = next_node
        in_degree_nodes.add(next_node)
    
    ordered_steps = []
    for node in nodes.values():
        if node not in in_degree_nodes:
            current = start
            while current is not None:
                step = Step(current.value, None)
                if current.next_node is not None:
                    step.finish = current.next_node.value
                    ordered_steps.append(step)
                current = current.next_node

    return ordered_steps


def main():
    steps = [Step("C", "D"), Step("A", "B"),  Step("B", "C")]
    for step in find_path(steps):
        print step


if __name__ == "__main__":
    main()
