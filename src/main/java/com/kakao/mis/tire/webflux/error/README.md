# Error

ErrorWebFluxAutoConfiguration



## WebExceptionHandler

## ErrorAttributes

~~~java
    @Bean
    @ConditionalOnMissingBean(value = ErrorAttributes.class, search = SearchStrategy.CURRENT)
    public DefaultErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes(this.serverProperties.getError().isIncludeException());
    }
~~~
ErrorAttributes의 구현체는 DefaultErrorAttributes 사용한다.




