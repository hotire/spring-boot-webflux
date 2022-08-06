package com.kakao.mis.tire.webflux.netty.core.buffer;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

import static org.assertj.core.api.Assertions.assertThat;

class ByteBufferTest {

    @Test
    void allocatePutGet() {
        // given
        final ByteBuffer byteBuffer = ByteBuffer.allocate(3);
        System.out.println(byteBuffer);
        byteBuffer.put((byte) 1);
        System.out.println(byteBuffer);

        // when
        final byte result = byteBuffer.get();

        // then
        assertThat(result).isEqualTo((byte) 0); // 쓰기 인덱스와 읽기 인덱스를 pos라는 하나의 변수로 관리하고 있기 때문이다.
    }

    @Test
    void allocatePutFlipGet() {
        // given
        final ByteBuffer byteBuffer = ByteBuffer.allocate(3);
        System.out.println(byteBuffer);
        byteBuffer.put((byte) 1);
        System.out.println(byteBuffer);
        byteBuffer.flip(); // 읽기 모드로 변경, flip을 써서 읽기 모드로 변경해서 pos를 0으로 초기화하고 lim을 현재 포지션인 1로 바꾼다.
        System.out.println(byteBuffer);

        // when
        final byte result = byteBuffer.get();

        // then
        assertThat(result).isEqualTo((byte) 1);
    }

    @Test
    void allocatePutFlipFlip() {
        // no assert
        final ByteBuffer byteBuffer = ByteBuffer.allocate(3);
        System.out.println(byteBuffer);
        byteBuffer.put((byte) 1);
        System.out.println(byteBuffer);
        byteBuffer.flip(); // 읽기 모드로 변경, flip을 써서 읽기 모드로 변경해서 pos를 0으로 초기화하고 lim을 현재 포지션인 1로 바꾼다.
        System.out.println(byteBuffer);
        byteBuffer.flip();
        System.out.println(byteBuffer);
    }

    @Test
    void rewind() {
        // given
        final ByteBuffer byteBuffer = ByteBuffer.allocate(3);
        System.out.println(byteBuffer);
        byteBuffer.put((byte) 1);
        System.out.println(byteBuffer);
        byteBuffer.rewind();
        System.out.println(byteBuffer);

        // when
        final byte result = byteBuffer.get();

        // then
        assertThat(result).isEqualTo((byte) 1);
    }
}