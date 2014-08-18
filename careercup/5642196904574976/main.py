"""Find the median of two sorted arrays.

Taken from CareerCup problem at
http://www.careercup.com/question?id=5642196904574976.
"""


def median(arr1, arr2):
    """Find the median of two sorted arrays.

    Miguel Oliveira's O(log(N)) time; O(1) space solution.
    """
    if (len(arr1) + len(arr2)) % 2 == 0:
        median1 = nth_term(arr1, arr2, (len(arr1) + len(arr2)) // 2)
        median2 = nth_term(arr1, arr2, (len(arr1) + len(arr2)) // 2 + 1)
        return float(median1 + median2) / 2
    else:
        return nth_term(arr1, arr2, (len(arr1) + len(arr2) + 1) // 2)


def nth_term(arr1, arr2, nth):
    """Find nth term of two sorted arrays."""
    lo1 = 0
    lo2 = 0
    hi1 = nth - 1
    hi2 = nth - 1

    while lo1 <= hi1 and lo2 <= hi2:
        mid1 = (lo1 + hi1) // 2
        mid2 = (lo2 + hi2) // 2
        left_count1 = mid1 - lo1 + 1
        left_count2 = mid2 - lo2 + 1

        # If the total count left of both mid points is less than or equal to
        # N, then the median term can't be less than or equal to the smaller
        # mid point term.
        if left_count1 + left_count2 <= nth:
            if arr1[mid1] < arr2[mid2]:
                lo1 = mid1 + 1
                nth -= left_count1
            else:
                lo2 = mid2 + 1
                nth -= left_count2
        else:
            if arr1[mid1] > arr2[mid2]:
                hi1 = mid1 - 1
            else:
                hi2 = mid2 - 1

    # Check where the answer is
    if lo1 <= hi1:
        return arr1[lo1 + nth - 1]
    else:
        return arr2[lo2 + nth - 1]


def main():
    """Test median function."""
    arr1 = [1, 2, 7, 9]
    arr2 = [3, 4, 5, 6, 8]
    assert median(arr1, arr2) == 5
    arr3 = [3, 4, 5, 6]
    assert median(arr1, arr3) == 4.5


if __name__ == "__main__":
    main()
