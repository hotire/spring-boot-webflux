package com.kakao.mis.tire.webflux.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BlockingRepository<T> extends JpaRepository<T, Long> {

}
