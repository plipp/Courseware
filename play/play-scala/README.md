# Introduction in the Playframework -Scala Flavor

Bases on the Typesafe Reactor project [Scala Seed](https://www.typesafe.com/activator/template/play-scala-reactive-platform-15v01)
([Sources](https://github.com/playframework/playframework/tree/master/templates/play-scala))

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

1. _controllers.a_ws.BlockingProxyController_
2. _controllers.a_ws.NonBlockingProxyController_

### 3. From List (Monad) to Future

0. _controllers/b_list_to_future/b0_future_creation.sc_
1. _controllers/b_list_to_future/b1_map.sc_
2. _controllers/b_list_to_future/b1_flatMap.sc_
3. _controllers/b_list_to_future/b3_for_comprehension.sc_
4. optional: _controllers/b_list_to_future/b4_fold_opt.sc_

#### Key difference:

- A **_List_** calls the function, passed to _map_, _flatMap_, ... **immediately**,
- a **_Future_** calls it when the I/O has completed and **data is available**.

### 4. Error handling

1. _controllers.a_ws.NonBlockingProxyController_
2. _controllers.c_ws_recovery.NonBlockingRecoveredProxyController_

3. Future-Errorhandling-Worksheets
    - _controllers/c_ws_recovery/c2_recover.sc_ 
    - _controllers/c_ws_recovery/c3_onSuccess.sc_ 
    - _controllers/c_ws_recovery/c4_onComplete.sc_ 
    - _controllers/c_ws_recovery/c4_onFallback.sc_ 
    - _controllers/c_ws_recovery/c4_try_vs_recover.sc_ 

### 5. Parallel vs. Sequential WS-Calls

TODO

### 6. Composition

TODO timeout-handling, authorization...


### X. Playing around

# Links

- [official Scala Future Site](http://docs.scala-lang.org/overviews/core/futures.html)

# TODOs

- Exercises 
  - for the single topics
  - 1 for the whole workshop

- check Threads in the PUI - Tomcat vs. Play



