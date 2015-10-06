# Introduction in the Playframework -Scala Flavor

Bases on the Typesafe Reactor project [Scala Seed](https://www.typesafe.com/activator/template/play-scala-reactive-platform-15v09)
([Sources](https://github.com/playframework/playframework/tree/master/templates/play-scala))

## Preconditions

- IntelliJ CE or UE with SBT-Plugin
  (.sc-Files are IntelliJ-Worksheets)

## Topics

Inspired by [Play Framework: async I/O without the thread pool and callback hell](http://engineering.linkedin.com/play/play-framework-async-io-without-thread-pool-and-callback-hell)

### 1. Basic Skeleton

- _application.conf_
- routes
   -> Basic Controller
      - with simple controller/action (no async): _controllers.SimpleApplication_
      -> HTML-Result for
          -  _index.scala.html_
          -> _main.scala.html_
    
- Initial Tests
    - _ApplicationSpec_ with _FakeApplication/Request_...
    - _IntegrationSpec_ with running Application (Selenium)
      - Starts play

- Running Play in IntelliJ: Play2 Run Configuration (or: `sbt ~run`)

### 2. WebService Calls

0. _worksheets/a_motivation/*

Play Controllers

1. _controllers.proxySample.BlockingProxyController_
2. _controllers.proxySample.NonBlockingProxyController_
3. _controllers.proxySample.NonBlockingRecoveredProxyController_

### 3. Error handling

Future-Errorhandling-Worksheets

- _controllers/b_ws_recovery/*_ 
  
Controller    

- _controllers.proxySample.NonBlockingRecoveredProxyController_

### 4. From List (Monad) to Future

- _controllers/c_from_list_to_future/*_

#### Key difference:

- A **_List_** calls the function, passed to _map_, _flatMap_, ... **immediately**,
- a **_Future_** calls it when the I/O has completed and **data is available**.

### 6. Composition

TODO loan-pattern, timeout-handling, authorization...


# Links

- [official Scala Future Site](http://docs.scala-lang.org/overviews/core/futures.html)

# TODOs
- own blocking controller
- Tests (instead of WS) for exercises