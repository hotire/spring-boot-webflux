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

### ChannelPipeline 

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

### ChannelHandlerContext

핸들러 메서드에 제공되는 ChannelHandlerContext 를 통해 다음 핸들러로 이벤트를 전달할 수 있으며 

관심없는 이벤트는 무시할 수 있다. 

- Channel 가져오거나 아웃바운드 데이터를 기록할 떄 사용 








