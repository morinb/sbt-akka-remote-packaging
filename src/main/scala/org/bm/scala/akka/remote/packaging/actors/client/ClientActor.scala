package org.bm.scala.akka.remote.packaging.actors.client

import akka.actor._
import org.bm.scala.akka.remote.packaging.actors.actors.common.{ClientTerminate, Message, Response}

import scala.concurrent.duration._

/**
 * .
 * @author Baptiste Morin
 */
class ClientActor(serverActorPath: String, responseHandler : ResponseReceivedEventHandler) extends Actor with ActorLogging {
  sendIdentifyRequest()

  def sendIdentifyRequest(): Unit = {
    log.info("Sending Identify request")
    context.actorSelection(serverActorPath) ! Identify(serverActorPath)
    import context.dispatcher
    context.system.scheduler.scheduleOnce(3.seconds, self, ReceiveTimeout)
  }

  def active(server: ActorRef): Receive = {
    case Terminated(`server`) =>
      log.info("Server terminated.")
      sendIdentifyRequest()
      context.become(identifying) // switch back to identifying

    case m: Message =>
      log.info(s"Sending $m")
      server ! m

    case Response(m, r) =>
      log.info(s"Received answer : $r")
      responseHandler.handleResponse(Response(m, r))

    case ClientTerminate =>
      log.info("Receive ClientTerminate signal, stopping...")
      context.stop(self)


    case m: Any =>
      log.info(s"Received $m")


  }

  def identifying: Receive = {
    case ActorIdentity(`serverActorPath`, Some(actor)) =>
      context.watch(actor) // Listen to Terminated message from actor
      context.become(active(actor)) // redirect receive method to active method
    case ActorIdentity(`serverActorPath`, None) => log.error(s"Server actor not available: $serverActorPath")
    case ReceiveTimeout => sendIdentifyRequest()
    case m: Any => log.warning(s"Message type ${m.getClass.getSimpleName} not handled !")
  }

  override def receive: Actor.Receive = identifying
}
