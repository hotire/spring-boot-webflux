package com.kakao.mis.tire.webflux.netty.core.buffer;

import io.netty.buffer.AbstractByteBuf;
import io.netty.buffer.ByteBuf;

/**
 * @see ByteBuf;
 * @see AbstractByteBuf;
 */
public abstract class ByteBufCore {
    int readerIndex;
    int writerIndex;

    /**
     * @see ByteBuf#capacity()
     */
    public abstract int capacity();
}
