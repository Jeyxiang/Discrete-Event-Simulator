# Discrete-Event-Simulator

Idea of the project is to simulate an event where customers arrive at different timings, with a set number of servers to serve them.

Here are the conditions:

- Customers can be classified as either normal customers or greedy customers (whom will choose to join servers with the shortest queue)
- Customers have a designated arrival time and serving time.
- Servers can rest after finishing serving a customer.
- Arrival times and serving times of customers will be randomly generated.
- the probability at which a server rests and probability that a customer is greedy is randomly generated.
- Rest times of servers will be randomly generated only when it finishes serving a customer.
- Servers have an attribute of a queue length, which dictates how many customers can wait there
- All randomly generated numbers are initialised by a seed, so that the sequeunce of random numbers produced will be the same.

Depending on the status of the servers and type of customers, there are several events that a customer can go through.

1. Arrives -> Serving -> Done (Customer served immediately when a Server is available)
2. Arrives -> Wait -> Serving -> Done (Customer waits at a server when no Server are available)
3. Arrives -> Leaves (Customer leaves without getting Served as no Server has space for customers to wait at)
A server can then enter a Rest state after serving its customers. After all the events, we can determined the average waiting time, the number of customers that
left without being served, and the total customers that were served.

This project is simply my solution/attempt at the project and in no means the model answer to the problem. I am open to any discussions and feedbacks regarding my submission :)
