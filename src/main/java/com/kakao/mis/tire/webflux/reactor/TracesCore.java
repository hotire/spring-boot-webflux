package com.kakao.mis.tire.webflux.reactor;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @see reactor.core.publisher.Traces
 */
public class TracesCore {

    static Supplier<Supplier<String>> callSiteSupplierFactory;

    static {
        final String[] strategyClasses = {
                TracesCore.class.getName() + "$StackWalkerCallSiteSupplierFactory",
                TracesCore.class.getName() + "$SharedSecretsCallSiteSupplierFactory",
                TracesCore.class.getName() + "$ExceptionCallSiteSupplierFactory",
                };
        // find one available call-site supplier w.r.t. the jdk version to provide
        // linkage-compatibility between jdk 8 and 9+
        callSiteSupplierFactory = Stream
                .of(strategyClasses)
                .flatMap(className -> {
                    try {
                        final Class<?> clazz = Class.forName(className);
                        final Supplier<Supplier<String>> function = (Supplier) clazz.getDeclaredConstructor()
                                                                              .newInstance();
                        return Stream.of(function);
                    }
                    // explicitly catch LinkageError to support static code analysis
                    // tools detect the attempt at finding out jdk environment
                    catch (final LinkageError e) {
                        return Stream.empty();
                    }
                    catch (final Throwable e) {
                        return Stream.empty();
                    }
                })
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Valid strategy not found"));
    }
}
