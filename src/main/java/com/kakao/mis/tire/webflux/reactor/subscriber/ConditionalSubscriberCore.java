package com.kakao.mis.tire.webflux.reactor.subscriber;

/**
 * @see reactor.core.Fuseable.ConditionalSubscriber
 *
 * 소비 여부를 알 수 있는 구독자 변형으로 소비하고 성공하면 true를 반환한다.
 * 만약 값을 버리고 새로운 값을 원하는 경우 false를 반환한다.
 * 이렇게 하는 이유는 request(1) 에 대한 반복 사용을 방지한다.
 */
public class ConditionalSubscriberCore {
}
