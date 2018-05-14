package io.spring.workshop.reactornetty.udp;

import org.junit.Test;
import reactor.ipc.netty.Connection;
import reactor.ipc.netty.resources.LoopResources;
import reactor.ipc.netty.udp.UdpServer;

import static org.junit.Assert.assertNotNull;

/**
 * Learn how to create a UDP server and client
 *
 * @author Violeta Georgieva
 * @see <a href="http://next.projectreactor.io/docs/netty/snapshot/api/reactor/ipc/netty/udp/UdpServer.html">UdpServer javadoc</a>
 * @see <a href="http://next.projectreactor.io/docs/netty/snapshot/api/reactor/ipc/netty/udp/UdpClient.html">UdpClient javadoc</a>
 */
public class UdpEchoTests {

    @Test
    public void echoTest() {
        LoopResources loopResources = LoopResources.create("echo-test-udp");
        Connection server =
                UdpServer.create() // Prepares a UDP server for configuration.
                         .port(0)  // Configures the port number as zero, this will let the system pick up
                                   // an ephemeral port when binding the server.
                         .runOn(loopResources) // Configures the UDP server to run on a specific LoopResources.
                         .wiretap()            // Applies a wire logger configuration.
                         .bind()   // Binds the UDP server and returns a Mono<Connection>.
                         .block(); // Blocks and waits the server to finish initialising.

        assertNotNull(server);

        server.disposeNow();       // Stops the server and releases the resources.
    }
}
