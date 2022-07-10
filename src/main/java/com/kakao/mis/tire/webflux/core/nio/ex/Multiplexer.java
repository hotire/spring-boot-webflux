package com.kakao.mis.tire.webflux.core.nio.ex;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * https://blog.naver.com/gngh0101/221612343445
 */
public class Multiplexer {

    private final Selector selector;

    private final ServerSocketChannel serverSocket;

    public Multiplexer(final int port) throws IOException {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(
                new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        final SelectionKey sk = serverSocket.register(selector,SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor());
    }

    class Acceptor implements Runnable { // inner
        @Override
        public void run() {
            try {
                final SocketChannel c = serverSocket.accept();
                if (c != null) {
                    System.out.println("new client connected");
                    new Handler(selector, c);
                }
            } catch (final IOException ex) { /* ... */ }
        }
    }
}
