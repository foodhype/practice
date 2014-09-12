def can_schedule(task_capacities, server_capacities, used):
    if all(used):
        return True
    
    for (task_index, task_capacity) in enumerate(task_capacities):
        if not used[task_index]:
            used[task_index] = True
            for (server_index, server_capacity) in enumerate(server_capacities):
                if task_capacity <= server_capacity:
                    server_capacities[server_index] -= task_capacity
                    if can_schedule(task_capacities, server_capacities, used):
                        return True
                    server_capacities[server_index] += task_capacity
            used[task_index] = False

    return False


def main():
    task_capacities = [18, 4, 8, 4, 6, 6, 8, 8]
    server_capacities = [8, 16, 8, 32]
    used = [False for _ in task_capacities]

    assert can_schedule(task_capacities, server_capacities, used)

    task_capacities = [4]
    server_capacities = [1, 3]
    used = [False for _ in task_capacities]

    assert not can_schedule(task_capacities, server_capacities, used)


if __name__ == "__main__":
    main()    
