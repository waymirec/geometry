package io.nuvalence.geometry.util;

import java.util.function.Consumer;

public class TestCase<TIn> {

    public static <TOut> TestCase<TOut> given(TOut receivedObj){
        return new TestCase<>(receivedObj);
    }

    private TestCase(TIn received){
        this.received = received;
    }

    public <TOut> TestCase<TOut> when(CheckedFunction<TIn, TOut> whenFunction) throws Exception {
        return new TestCase<>(whenFunction.apply(received));
    }

    public void then(Consumer<TIn> thenFunction){
        thenFunction.accept(received);
    }

    private TIn received;

    @FunctionalInterface
    public interface CheckedFunction<TIn, TOut> {
        TOut apply(TIn input) throws Exception ;
    }
}