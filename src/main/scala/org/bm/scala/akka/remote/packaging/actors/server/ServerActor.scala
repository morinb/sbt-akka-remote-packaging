package org.bm.scala.akka.remote.packaging.actors.server

import akka.actor.{Actor, ActorLogging}
import org.bm.scala.akka.remote.packaging.actors.actors.common.{Response, Message, Initialize}

/**
 * .
 * @author Baptiste Morin
 */
class ServerActor extends Actor with ActorLogging {
  override def receive: Receive = {
    case Initialize => log.info("Server actor initialized.")
    case Message(env, cmd) => log.info(s"Asking ${cmd.getClass.getSimpleName} $env" )
      log.info(s"$env is running")
      sender ! Response(Message(env, cmd), s"$env is Running")
  }
}
