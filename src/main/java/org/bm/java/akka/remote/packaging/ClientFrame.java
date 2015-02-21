package org.bm.java.akka.remote.packaging;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;
import org.bm.scala.akka.remote.packaging.actors.actors.common.Message;
import org.bm.scala.akka.remote.packaging.actors.actors.common.Status$;
import org.bm.scala.akka.remote.packaging.actors.client.ClientActor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * .
 *
 * @author Baptiste Morin
 */
public class ClientFrame extends JFrame {
    private final ActorSystem system;
    private final ActorRef client;

    private JButton sendStatusCommand = new JButton(new AbstractAction("Ask Status") {
        @Override
        public void actionPerformed(ActionEvent e) {
            client.tell(new Message("uir", Status$.MODULE$), ActorRef.noSender());
        }
    });

    public ClientFrame(ActorSystem system, ActorRef client) {
        this.system = system;
        this.client = client;

        this.setLayout(new BorderLayout());

        this.add(sendStatusCommand, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

}

