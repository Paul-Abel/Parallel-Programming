# Parallel Programming 

This is a small practical task for the subject "Parallel Programming" in the first master semester from the universtity of applied science Karlsruhe. For this, several types of parallel programming should be implemented in Java.

The task is to simulate an ice cream store with service staff and customers. A thread should be used for each customer and service employee. The store has 4 service staff and only 12 seats, all other customers have to wait. 

The process is as follows.
1. the customer enters the store and waits
2. the customer places an order with the service staff and waits
3. the customer is served by the service staff and waits for 3-8 seconds
4. the customer leaves the store

- Task 1: Implementation with wait and sleep.
- Task 2: Conversion with executorService.
