# Introduction to Scala Async IO

This is an introductory workshop to Scala Async IO, inspired by 
[Play Framework: async I/O without the thread pool and callback hell](http://engineering.linkedin.com/play/play-framework-async-io-without-thread-pool-and-callback-hell).

## Async IO - Threaded vs Evented

### A Threaded World - The Problem
In the classic threaded world each incoming request is handled by a distinct thread. This thread is blocked until the reponse is sent back to the requester.

![Waiting Requests](https://strongloop.com/wp-content/uploads/2014/01/threading_java.png)

Blocking IO and a undersized thread pool may slow down incoming requests.

The greatest challenge here is the right scaling of the thread pool, which depends on the actual number of incoming requests and therefore is highly dynamic.

### One possible Answer - Event/Message-Driven with Non-Blocking IO

![NodeJs Event Loop](https://strongloop.com/wp-content/uploads/2014/01/threading_node.png)

Here the incoming request is not handled by the dispatcher thread, but by (possibly another) worker-thread. The dispatcher thread is free to accept new requests.

This is the underlaying concept e.g. of NodeJs and of the [Play-Framework](https://www.playframework.com/) as well.

Play bases on Scala Async IO (Futures/Promises), which is main topic of the following workshop:

- [scala-play-asyncio-ex](scala-play-asyncio-ex)

The solutions for the quizzies and tests of the workshop can be found in:

- [scala-play-asyncio-full](scala-play-asyncio-full)


