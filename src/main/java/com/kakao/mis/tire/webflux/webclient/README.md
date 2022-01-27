# WebClient

## exchange

##


## Release

- https://giters.com/reactor/reactor-netty/issues/1874

BodyExtractors.toMono

~~~
	private static <T> BodyExtractor<Mono<T>, ReactiveHttpInputMessage> toMono(ResolvableType elementType) {
		return (inputMessage, context) ->
				readWithMessageReaders(inputMessage, context, elementType,
						(HttpMessageReader<T> reader) -> readToMono(inputMessage, context, elementType, reader),
						ex -> Mono.from(unsupportedErrorHandler(inputMessage, ex)),
						skipBodyAsMono(inputMessage));
	}
~~~

BodyExtractors.readToMono

DecoderHttpMessageReader.readMono

AbstractDataBufferDecoder.decodeToMono

DataBufferUtils.release

~~~
public static boolean release(@Nullable DataBuffer dataBuffer) {
}
~~
