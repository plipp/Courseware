# Introduction in the Playframework

This is an introductory workshop into the [Play Framework](https://www.playframework.com/), inspired by [Play Framework: async I/O without the thread pool and callback hell](http://engineering.linkedin.com/play/play-framework-async-io-without-thread-pool-and-callback-hell).

It puts the main emphasis on how Play handles async IO.

## Async IO - Threaded vs Evented

### A Threaded World
In the classic threaded world each incoming request is handled by a distinct thread. This thread is blocked until the reponse is sent back to the requester.

![Waiting Requests](https://strongloop.com/wp-content/uploads/2014/01/threading_java.png)

Blocking IO and a undersized thread pool may slow down incoming requests.<br>
The greatest challenge here is the right scaling of the thread pool, which depends on the actual number of incoming requests and therefore is highly dynamic.

### One possible Answer - Event/Message-Driven with Non-Blocking IO

![NodeJs Event Loop](https://strongloop.com/wp-content/uploads/2014/01/threading_node.png)

Here the incoming request is not handled by the dispatcher thread, but by (possibly another) worker-thread. The dispatcher thread is free to accept new requests.

This is the underlaying concept e.g. of NodeJs and of the [Play-Framework](https://www.playframework.com/) as well.

## Basic Play Samples

- [Scala-Sample](./play-scala): Work in Progress
- [Java-Sample](./play-java): TBD

The samples start from the Typesafe Reactor project seeds:

- [Scala Seed](https://www.typesafe.com/activator/template/play-scala-reactive-platform-15v01)
    - [Sources](https://github.com/playframework/playframework/tree/master/templates/play-scala)
- [Java Seed](https://www.typesafe.com/activator/template/play-java-reactive-platform-15v01)
    - [Sources](https://github.com/playframework/playframework/tree/master/templates/play-java)

They show/demonstrate

- The basic seeds/skeletons
- WebService Calls
    - synchronous/blocking vs.
    - asynchronous (Futures/Promises)
- the relation between Lists and Futures
    - map, flatMap ...
- Future Errorhandling
...

# Ideas for further Workshops

- Testing in Play
- ...

