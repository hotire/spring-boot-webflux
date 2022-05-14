package com.kakao.mis.tire.webflux.io.buffer;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.lang.Nullable;

/**
 * @see DataBufferUtils
 */
public class DataBufferUtilsCore {

    /**
     * @see DataBufferUtils#retain(DataBuffer)
     */
    public static <T extends DataBuffer> T retain(final T dataBuffer) {
        return null;
    }

    /**
     * @see DataBufferUtils#release(DataBuffer)
     *
     */
    public static boolean release(@Nullable final DataBuffer dataBuffer) {
        return true;
    }
}
