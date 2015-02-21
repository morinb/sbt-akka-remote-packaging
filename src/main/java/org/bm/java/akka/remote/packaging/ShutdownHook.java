package org.bm.java.akka.remote.packaging;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.bm.scala.akka.remote.packaging.actors.actors.common.ClientTerminate;
import org.bm.scala.akka.remote.packaging.actors.actors.common.ClientTerminate$;

/**
 * .
 *
 * @author Baptiste Morin
 */
public class ShutdownHook extends Thread {

    private final ActorSystem system;
    private final ActorRef client;

    public ShutdownHook(ActorSystem system, ActorRef client) {
        this.system = system;
        this.client = client;
    }

    @Override
    public void run() {
        System.out.println("Shutdown hook called");
        client.tell(ClientTerminate$.MODULE$, ActorRef.noSender());
        system.shutdown();
    }
}
