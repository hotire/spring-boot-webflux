package com.kakao.mis.tire.webflux.core.nio.ex;

import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

public class Handler implements Runnable {
    private final SocketChannel socket;
    private final SelectionKey sk;

    private final Map<HandlerState, Runnable> handlerMap;

    private final HandlerState state = HandlerState.READING;

    public Handler(final Selector selector, final SocketChannel socket) throws ClosedChannelException {
        this.socket = socket;
        this.sk = socket.register(selector, 0);
        this.sk.attach(this);
        this.sk.interestOps(SelectionKey.OP_READ);
        selector.wakeup();

        handlerMap = new HashMap<>();
        handlerMap.put(HandlerState.READING, this::read);
        handlerMap.put(HandlerState.SENDING, this::send);
    }

    @Override
    public void run() {
        handlerMap.get(state).run();
    }

    public void read() {
    }

    public void send() {
    }


}
