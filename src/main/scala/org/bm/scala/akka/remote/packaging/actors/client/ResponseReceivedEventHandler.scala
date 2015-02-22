package org.bm.scala.akka.remote.packaging.actors.client

import org.bm.scala.akka.remote.packaging.actors.actors.common.Response

/**
 * .
 * @author Baptiste Morin
 */
trait ResponseReceivedEventHandler {
  def handleResponse(r: Response)
}
