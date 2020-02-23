package com.kakao.mis.tire.webflux.example;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockingRepository<T> extends JpaRepository<T, Long> {

}
