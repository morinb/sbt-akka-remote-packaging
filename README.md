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

* Create a trait that will describe the event handler contrat. => [ResponseReceivedEventHandler](src/main/scala/org/bm/scala/akka/remote/packaging/actors/client/ResponseReceivedEventHandler.scala)
* Make your actor take the event handler as parameter.
* Call the event handler when needed
* Make your GUI implements the Event handler methods and update gui on purpose.
* Happy ! !
