# Netty Core

## 핵심 컴포넌트 

### 1. Channel 

하나 이상의 입출력 작업을 수행할 수 있는 하드웨어 장치, 파일, 네트워크 소켓, 프로그램 컴포넌트와 같은 엔티티에 대한 "연결"

Inbound, Outbound 데이터를 위한 운송수단이라고 볼 수 있다. 

### 2. Callback

함수가 끝나고 실행 되는 함수 

### 3. Future

작업이 완료되면 알려주는 클래스 

### 4. Event, Handler

작업의 상태를 알리기 위해 이벤트를 이용하며 발생한 이벤트를 핸들러를 통해 처리한다.

- 로깅
- 데이터 변환
- 흐름 제어
- 비즈니스 논리

inbound 
- 연결 활성화 또는 비활성화
- 데이터 읽기
- 사용자 이벤트
- 오류 이벤트

outbound
- 원격 연결 열기 또는 닫기
- 소켓으로 데이터 쓰기 또는 플러스

## EventLoop

연결의 수명주기 중 발생하는 이벤트를 처리하는 네티의 핵심 추상화 정의한 인터페이스이다.

- 한 EventLoopGroup 은 하나 이상의 EventLoop 를 포함한다.
- 한 EventLoop 는 수명주기 동안 한 Thread 로 바인딩된다.
- 한 EventLoop 에서 처리되는 모든 입출력 이벤트는 바인딩된 Thread 에서 처리된다.
- 한 Channel 수명주기 동안 한 EventLoop 에 등록할 수 있다. 
- 한 EventLoop 하나 이상의 Channel 로 할당할 수 없다.

[eventLoop](doc/eventLoop.jpeg)

Netty는 Channel에서 발생하는 이벤트들을 EventLoop가 처리하는 구조로 이벤트를 실행하기 위한 스레드를 말한다.

연결의 수명 기간 동안 발생하는 이벤트를 처리하는 작업을 실행하는 것은 네트워킹 프레임워크의 기본 기능이다.

이러한 프로그래밍 구조를 이벤트 루프라고 한다.

동시성, 네트워킹의 두 가지 기본 API 를 공동으로 활용해 설계되었다.

- io.netty.util.concurrent 패키지는 JDK 패키지인 java.util.concurrent 에 기반을 두는 스레드 실행자를 제공한다.
- io.netty.channel 패키지의 클래스는 Channel 이벤트와 인터페이스를 수행하기 위해 확장한다.

EventLoop 는 변경되지 않는 Thread 하나로 움직이며 작업을 즉시 실행, 예약 실행할 수 있다.

EventLoop 는 ScheduledExecutorService 를 확장하며 parent() 라는 메서드 하나만 정의한다.

현재 EventLoop 구현 인스턴스가 속한 EventLoopGroup 참조을 반환한다.

- 이전 릴리스의 스레딩 모델에서는 인바운드(업스트립) 이벤트만 Event Loop 에서 실행되고 아웃바운드 이벤트는 Event Loop 이거나 다른 스레드에서 실행되었다.
- 이젠 릴리스의 모델은 스레드 동기화 과정이 필요하기 때문에 (여러 다른 스레드에서 아웃바운드 이벤트 접근 제한) 최근 모델에서는 동일한 스레드에서 처리하는 방법으로 변경했다.

### 스레드 관리

현재 실행 중인 Thread 의 ID 를 확인하는 기능, 즉 Thread 가 현재 Channel 과 해당 Event Loop 에 할당된 것인가 확인하는 기능이 중요하다. 

호출 Thread 가 EventLoop 에 속하는 경우 해당 코드 블럭을 실행하고, 그렇지 않으면 EventLoop 나중에 실행하기 위해 작업을 예약히기 위해 내부 큐에 넣는다.

### EventLoopGroup

Channel 에 이벤트와 입출력을 지원하는 EventLoop 는 EventLoopGroup 에 포함된다. 

EventLoopGroup 은 새로 생성된 각 Channel 에 EventLoop 를 할당한다. 

균형을 위해 라운드 로빈 방식을 이용하여 동일한 EventLoop 가 여러 Channel 에 할당될 수 있다.

## ChannelFuture

입출력 비동기 처리를 위해 추상화 정의한 인터페이스이다.


## ChannelHandler

인바운드, 아웃바운드 데이터의 처리에 적용되는 논리적인 컨테이너 역할을 한다.

ChannelHandler 의 메서드가 네트워크 이벤트(광범위한 의미)에 의해 트리거 된다.

- 데이터를 한 포맷에더 다른 포맷으로 변환 
- 예외에 대한 알림 제공
- Channel 활성화 또는 비활성화에 따른 알람 제공
- Channel EventLoop 에 등록할 때 또는 해제할 때 알림 제공
- 사용자 정의 이벤트에 대한 알림 제공 

### ChannelHandlerContext

핸들러 메서드에 제공되는 ChannelHandlerContext 를 통해 다음 핸들러로 이벤트를 전달할 수 있으며 

관심없는 이벤트는 무시할 수 있다. 

- Channel 가져오거나 아웃바운드 데이터를 기록할 떄 사용 


### 인코더, 디코더 

메시지를 전송, 수신할 때 데이터를 변환해야 한다. 

인바운드 메시지는 바이트에서 다른 포맷(주로 자바객체)으로 변환되는 디코딩을 거친다. 

아웃바운드 메시지는 현재 포맷에서 바이트로 인코딩된다.




## Selector 

셀렉터의 기본 개념은 Channel 의 상태가 변경되면 요청이 알림을 받을 수 있는 레지스트리의 역할을 하는 것이다.

- 새로운 Channel 수락되고 준비됨
- Channel 연결이 완료됨
- Channel 읽을 데이터가 있음
- Channel 이용해 데이터를 기록함


### 제로 카피 (Zero-copy)

NIO 와 Epoll 전송에서만 이용가능한 기능으로 파일 시스템의 데이터를 커널 공간에서 사용자 공간으로 복사하는 과정을 생략하여

빠르고 효율적으로 네트워크로 이동할 수 있게 해준다. 

## ByteBuf 

테티의 데이터 컨테이너이다.

자바 NIO ByteBuffer 자체 바이트 컨테이너를 제공하지만, 너무 복잡해 사용하기 부담스럽다.

네티에서는 ByteBuffer 를 대신해 ByteBuf 제공한다.

읽기 / 쓰기 를 위한 고유한 두 인덱스를 유지한다. ByteBuf 에서 데이터를 읽으면 ByteBuf 의 readerIndex 가 읽은 바이트 수만큼 증가한다.
비슷하게 ByteBuf 에 데이터를 기록하면 writerIndex 가 증가한다.

read / write 로 시작하는 메서드들은 인덱스를 증가시키지만, get, set 으로 시작하는 메서드는 증가시키지 않는다.


### References

- https://perfectacle.github.io/2021/02/28/netty-byte-buf/


## ChannelPipeline

ChannelHandler 체이닝을 위한 역할로 체인상에서 인바운드오와 아웃바운드 이벤트를 전파한다.

- ChannelInitializer 구현은 ServerBootstrap 에 등록된다.
- ChannelInitializer.initChannel() 호출되면 ChannelInitializer 의 커스텀 집합을 파이프라인에 설치한다.
- ChannelInitializer 는 ChannelPipeline 에서 자신을 제거한다.

인바운드, 아웃바운드가 동일한 ChannelPipeline 안에 두 가지 핸들러가 같이 존재한다.

인바운드, 아웃바운드 모두 ChannelHandler 를 확장하지만 네티는 ChannelInboundHandler, ChannelOutboundHandler 구현을 구분하여

핸들러 간의 데이터 전달이 동일한 방향으로 수행되도록 보장한다.

ChannelHandler 를 ChannelPipeline 에 추가할 때 ChannelHandler 및 ChannelPipeline 간의 바인딩을 나타내는 ChannelHandlerContext 하나가 할당된다.

네티에서 메시지를 보내는 방법은 Channel 에 직접 기록하거나 ChannelHandler 와 연결된 ChannelHandlerContext 객체에 기록하는 방법이 있다.

전자가 ChannelPipeline 의 뒤쪽에서 시작되며 후자의 방법은 ChannelPipeline 의 다음 핸들러에서 시작된다.

### Inbound Event

- fireChannelRegistered : ChannelPipeline 다음 ChannelInboundHandler 에서 channelRegistered() 를 호출한다.
- fireChannelUnRegistered : ChannelPipeline 다음 ChannelInboundHandler 에서 channelUnRegistered() 를 호출한다. 
- fireChannelActive : ChannelPipeline 다음 ChannelInboundHandler 에서 channelActive() 를 호출한다.
- fireChannelRead : ChannelPipeline 다음 ChannelInboundHandler 에서 channelRead() 를 호출한다.

.... fire 똑같은 형식으로 이루어짐

### Outbound Event
