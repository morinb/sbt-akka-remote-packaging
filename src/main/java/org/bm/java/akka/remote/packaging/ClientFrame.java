package org.bm.java.akka.remote.packaging;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.Creator;
import com.typesafe.config.ConfigFactory;
import org.bm.scala.akka.remote.packaging.actors.actors.common.Message;
import org.bm.scala.akka.remote.packaging.actors.actors.common.Response;
import org.bm.scala.akka.remote.packaging.actors.actors.common.Status$;
import org.bm.scala.akka.remote.packaging.actors.client.ClientActor;
import org.bm.scala.akka.remote.packaging.actors.client.ResponseReceivedEventHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * .
 *
 * @author Baptiste Morin
 */
public class ClientFrame extends JFrame implements ResponseReceivedEventHandler {
    private final ActorRef client;

    private JTextArea jTextArea = new JTextArea();

    public ClientFrame() {
        final ActorSystem system = ActorSystem.create("ClientSystem", ConfigFactory.load("client"));
        final String remotePath = "akka.tcp://ServerSystem@127.0.0.1:2553/user/server";

        this.client = system.actorOf(Props.create(ClientActor.class, new Creator<ClientActor>() {
            @Override
            public ClientActor create() throws Exception {
                return new ClientActor(remotePath, ClientFrame.this);
            }
        }), "client");

        this.setLayout(new BorderLayout());

        this.add(new JScrollPane(jTextArea), BorderLayout.CENTER);

        this.add(new JButton(new AbstractAction("Ask Status") {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.tell(new Message("uir", Status$.MODULE$), ActorRef.noSender());
            }
        }), BorderLayout.SOUTH);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Runtime.getRuntime().addShutdownHook(new ShutdownHook(system, client));

    }

    @Override
    public void handleResponse(final Response r) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jTextArea.append(String.format("Received response to %s, %s : %s\n", r.message().cmd().toString(), r.message().env(), r.response()));
            }
        });
    }
}

