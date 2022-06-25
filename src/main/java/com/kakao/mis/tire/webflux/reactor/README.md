# Reactor 

## Reactive Stream

- Publisher
- Subscriber
- Subscription
- Processor

reference : https://github.com/reactive-streams/reactive-streams-jvm/tree/v1.0.2


### Mono
Reactive Stream Publisher의 구현체로 0~1개를 전달한다.


### Flux
Reactive Stream Publisher의 구현체로 0~N개를 전달한다.

### Hot vs Cold

- https://blog.naver.com/gngh0101/222787182356
- https://www.vinsguru.com/reactor-hot-publisher-vs-cold-publisher/

Cold는 subcribe 하지 않으면, 아무 일도 일어나지 않는다. 또한 subcribe 할 때마다 매번 독립적인 데이터를 생성하고 동작한다. 
반면 Hot은 subcribe 하지 않더라도 데이터를 생성할 수 있다.  대표적으로 cache, share와 같은 것이 있다. 


굳이 비교하자면 넷플릭스는 내가 보고싶을 떄 여러번 볼수 있고 처음부터 스트리밍한다. 콜드 
반면 핫은 극장에서 개봉하는 영화와 같다. 내가 보러가지 않아도 영화는 개봉 하고 상영한다.

Flux의 경우 ConnectableFlux를 이용해 connect하면 데이터를 publishing한다. 
autoConnect 사용시 해당 구독수가 생기면 pubhsing한다.
refCount는 autoConntect과 유사하지만 재방송해준다.