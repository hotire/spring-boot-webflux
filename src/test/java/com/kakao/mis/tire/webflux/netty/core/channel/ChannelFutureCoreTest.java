package com.kakao.mis.tire.webflux.netty.core.channel;

import io.netty.util.concurrent.CompleteFuture;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.BDDMockito.given;

@Slf4j
class ChannelFutureCoreTest {

    @Test
    void completeChannelFutureAddListener() {
        // given
        final EventExecutor executor = Mockito.mock(EventExecutor.class);
        final CompleteFuture<String> future = new CompleteFuture<>(executor) {

            @Override
            public boolean isSuccess() {
                return false;
            }

            @Override
            public Throwable cause() {
                return null;
            }

            @Override
            public String getNow() {
                return null;
            }
        };

        given(executor.inEventLoop()).willReturn(true);

        // when
        future.addListener(new GenericFutureListener<Future<? super String>>() {
            @Override
            public void operationComplete(Future<? super String> future) throws Exception {
                log.info("operationComplete, isSuccess : {}", future.isSuccess());
            }
        });

        // no assert
    }

}