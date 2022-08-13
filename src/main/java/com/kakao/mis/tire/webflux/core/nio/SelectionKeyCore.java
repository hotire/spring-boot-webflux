package com.kakao.mis.tire.webflux.core.nio;

/**
 * @see java.nio.channels.SelectionKey
 */
public class SelectionKeyCore {

    /**
     * Channel 에서 데이터를 읽을 수 있으면 알린다.
     */
    public static final int OP_READ = 1 << 0;

    /**
     * Channel 로 데이터를 기록할수 있으면 알린다. 소켓 버퍼가 완전히 차는 상황을 처리한다. 이러한 상황은 원격 피어의 처리 능력보다 데이터가 더 자주 전송될 때 흔히 발생한다.
     */
    public static final int OP_WRITE = 1 << 2;

    /**
     *  연결되면 알린다.
     */
    public static final int OP_CONNECT = 1 << 3;

    /**
     * 새로운 연결을 수락되고 Channel 생성되면 알린다.
     */
    public static final int OP_ACCEPT = 1 << 4;
}
