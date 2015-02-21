sbt-akka-remote-packaging
=========================

This project is a small template of a
sbt application that mixes Java and Scala with Akka.

The application is packaged with [sbt-onejar](https://github.com/sbt/sbt-onejar) plugin.

To create the jar just run on the project command line : `sbt one-jar`

The minimalist GUI (a button in a frame) is coded in java with swing.
The button sends a message to the client actor, which forward it to the server actor.

The server answer to the client actor that display the received result in the logs.

For the gui to be updated, the client actor should communicate with the ClientFrame class :

* make ClientFrame be the client actor, thus it will be easy to update its field when a message is received.
  And there will be no need to forward message to server, but just send it.
* Make the ClientFrame listen to ClientActor changes, but in this case, the client actor is just a gateway
  that will forward message from gui to server.

