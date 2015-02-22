package org.bm.scala.akka.remote.packaging.actors.actors.common

trait Command extends Serializable
object Status extends Command
object ClientTerminate extends Command
object ServerTerminate extends Command

case class Message(env: String, cmd: Command) extends Serializable
case class Response(m: Message, response: String) extends Serializable

object Initialize extends Command