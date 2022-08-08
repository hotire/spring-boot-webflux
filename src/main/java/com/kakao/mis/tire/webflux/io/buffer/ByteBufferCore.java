package com.kakao.mis.tire.webflux.io.buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * @see ByteBuffer
 * @see Buffer
 */
public interface ByteBufferCore {

    /**
     * @see Buffer#rewind()
     */
    ByteBuffer rewind();

    /**
     * @see Buffer#flip()
     */
    ByteBuffer flip();

}
