# Introduction in Scala Futures (as used in Play Framework)

## Preconditions

- IntelliJ CE or UE with SBT-Plugin
  (.sc-Files are IntelliJ-Worksheets)

## Topics

- [blocking vs. non-blocking IO](app/worksheets/a_motivation)
- [Try vs. Future + Error Handling](app/worksheets/b_ws_recovery)
  - exercise:
    - app/worksheets/b_ws_recovery/exercises/b1_recover_ex.sc
    - unit.futures.b.FutureRecoverySpec
- [List -> Future + Combining Futures](app/worksheets/c_from_list_to_future)
  - exercises: 
    - unit.futures.b.FutureCombinationSpec1
    - unit.futures.b.FutureCombinationSpec2 (requires running play: ./sbt run)

# Typesafe Codebase for Play
Bases on the Typesafe Reactor project [Scala Seed](https://www.typesafe.com/activator/template/play-scala-reactive-platform-15v09)
([Sources](https://github.com/playframework/playframework/tree/master/templates/play-scala))

## 1. Basic Skeleton

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

## 2. Additional Controllers

1. [Controllers as Blocking/non blocking Proxy sample](app/controllers/proxySample/): to be shown together with _app/worksheets/a_motivation_
2. [API to be called by unit.futures.b.FutureCombinationSpec2](app/controllers/myserver)


# Links
- [official Scala Future Site](http://docs.scala-lang.org/overviews/core/futures.html)

# TODO
- More Play-Stuff
  - Async in Controllers
  - Testing Controllers ...