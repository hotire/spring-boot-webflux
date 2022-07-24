# Logging

## Request/Response Body 
- https://www.baeldung.com/kotlin/spring-webflux-log-request-response-body

## Debug
- https://m.blog.naver.com/sthwin/221953620875

### Hooks.onOperatorDebug() 

- 오퍼레이터가 생성될 때 마다 스택을 캡쳐해서 유지한다

단점은 애플리케이션에서 사용하는 모든 오퍼레이터에 대해서 조립 스택트레이스를 캡처한다는 것이다. 