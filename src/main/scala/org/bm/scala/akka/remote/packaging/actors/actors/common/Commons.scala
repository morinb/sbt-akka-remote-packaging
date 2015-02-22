package org.bm.scala.akka.remote.packaging.actors.actors.common

trait Command extends Serializable {
  def toString: String
}

object Status extends Command {
  override val toString = "Status"
}

object ClientTerminate extends Command {
  override val toString = "ClientTerminate"
}

object ServerTerminate extends Command {
  override val toString = "ServerTerminate"
}

case class Message(env: String, cmd: Command) extends Serializable

case class Response(message: Message, response: String) extends Serializable

object Initialize extends Command {
  override val toString = "Initialize"
}