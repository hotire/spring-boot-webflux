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


