from collections import namedtuple
import heapq


def distance(point1, point2):
    return ((point1[0] - point2[0])**2 + (point1[1] - point2[1])**2)**0.5


def nearest_hotels(user, hotels, k):
    return heapq.nsmallest(k, hotels, key=lambda hotel: distance(user, hotel))


def nearest_hotels2(user, hotels, k):
    hotel_node = namedtuple("node", "priority hotel distance")
    nearby_hotels = []

    for hotel in hotels:
        hotel_distance = distance(user, hotel)
        priority = 1.0 / hotel_distance if hotel_distance > 0 else float("inf")

        if len(nearby_hotels) == k:
            farthest_nearby_hotel = nearby_hotels[0]
            if hotel_distance < farthest_nearby_hotel.distance:
                node = hotel_node(priority, hotel, hotel_distance)
                heapq.heappushpop(nearby_hotels, node)
        else:
            node = hotel_node(priority, hotel, hotel_distance)
            heapq.heappush(nearby_hotels, node)

    nearest = []
    while nearby_hotels:
        nearest.append(heapq.heappop(nearby_hotels).hotel)
    nearest.reverse()

    return nearest


def main():
    user = (5, 5)

    hotels = []
    for i in xrange(10):
        for j in xrange(10):
            hotels.append((i, j))

    print nearest_hotels(user, hotels, 10)
    print nearest_hotels2(user, hotels, 10)


if __name__ == "__main__":
    main()
