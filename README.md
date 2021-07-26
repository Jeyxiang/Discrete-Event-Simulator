# Discrete-Event-Simulator
NUS CS2030 AY2020/21 S2 Project
Idea of the project is to simulate an event where customers arrive at different timings, with a set number of servers to serve them.
Customers are classified as normal customers or greedy customers (whom will choose to join servers with the shortest queue).Customers has a designated arrival time and serving time.
Servers have attributes of a queue length, which dictates how many customers can wait there, and a unique server ID. Depending on the status of the servers, there are several events
that a customer can go through.
1. Arrives -> Serving -> Done (Customer served immediately when a Server is available)
2. Arrives -> Wait -> Serving -> Done (Customer waits at a server when no Server are available)
3. Arrives -> Leaves (Customer leaves without getting Served as no Server has space for customers to wait at)
On top of that, A server can also enter a Rest state after serving its customers. After all the events, we can determined the average waiting time, the number of customers that
left without being served, and the total customers that were served.

This project is simply my solution/attempt at the project and in no means the model answer to the problem. I am open to any discussions and feedbacks regarding my submission :)