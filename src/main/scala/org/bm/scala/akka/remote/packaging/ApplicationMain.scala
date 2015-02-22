package org.bm.scala.akka.remote.packaging

import javax.swing.WindowConstants

import akka.actor.{Props, ActorLogging, ActorSystem}
import com.typesafe.config.ConfigFactory
import org.bm.java.akka.remote.packaging.{ShutdownHook, ClientFrame}
import org.bm.scala.akka.remote.packaging.actors.actors.common.Initialize
import org.bm.scala.akka.remote.packaging.actors.client.ClientActor
import org.bm.scala.akka.remote.packaging.actors.server.ServerActor

/**
 * .
 * @author Baptiste Morin
 */
object ApplicationMain {

  def main(args: Array[String]): Unit = {
    if(args.isEmpty || args.head == "server") {
      startServerSide()
    }
    if(args.isEmpty || args.head == "client") {
      startClientSide()
    }
  }

  def startClientSide(): Unit = {
    val frame = new ClientFrame()
    frame.setSize(800, 600)
    frame.setLocationRelativeTo(null)
    frame.setVisible(true)

  }

  def startServerSide(): Unit = {
    val system = ActorSystem("ServerSystem", ConfigFactory.load("server"))
    system.actorOf(Props[ServerActor], "server") ! Initialize
  }

}
