# Netty

네티는 비동기 이벤트 기반 네트워크 어플리케이션 프레임워크로써 유지보수를 고려한 고성능 프로토콜 서버와 클라이언트를 빠르게 개발할 수 있다.

## Bootstrap

네티로 작성한 네트워크 어플리케이션의 동작 방식과 환경을 설정하는 도우미 클래스, 주로 클라이언트 어플리케이션에 사용된다.

### ServerBootstrap

Bootstrap 중에 서버의 설정을 돕기 위한 클래스, 주로 서버 어플리케이션에 사용된다.

- 전송 계층 (소켓 모드 및 I/O 종류)
- 이벤트 루프 (단일 스레드, 다중 스레드)
    - 서버 소켓 채널 이벤트 루프
    - 소켓 채널 이벤트 루프
- 채널 파이프라인 설정
    - 서버 소켓 채널 파이프라인 설정
    - 소켓 채널 파이프라인 설정
- 소켓 주소와 포트
- 소켓 옵션

### EventLoopGroup

EventLoop를 그룹핑한 것이다.

지정한 thread 개수 만큼 EventLoop를 생성한다.


### EventLoop

Netty는 Channel에서 발생하는 이벤트들을 EventLoop가 처리하는 구조로

이벤트를 실행하기 위한 스레드를 말합니다.



## References

- https://perfectacle.github.io/2021/02/28/netty-glossary/
- https://woooongs.tistory.com/73
- https://effectivesquid.tistory.com/65