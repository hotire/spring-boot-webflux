package com.kakao.mis.tire.webflux.reactor;

import java.util.function.Supplier;

import lombok.RequiredArgsConstructor;

/**
 * @see reactor.core.publisher.FluxOnAssembly.AssemblySnapshot
 */
@RequiredArgsConstructor
public class AssemblySnapshotCore {
    private final Supplier<String> assemblyInformationSupplier;
}
