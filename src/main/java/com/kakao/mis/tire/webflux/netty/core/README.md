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
