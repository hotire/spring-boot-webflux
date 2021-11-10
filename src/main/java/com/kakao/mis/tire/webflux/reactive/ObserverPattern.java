package com.kakao.mis.tire.webflux.reactive;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * 기존의 Observable / Observer 패턴
 */
@SuppressWarnings("deprecation")
public class ObserverPattern {

    static class TestObservable extends Observable implements Runnable {

        @Override
        public void run() {
            IntStream.range(1, 10).forEach(integer -> {
                setChanged();
                notifyObservers(integer);
            });
        }
    }

    static class TestObserver implements Observer {

        @Override
        public void update(Observable o, Object arg) {
            System.out.println(Thread.currentThread().getName() + ", " + arg);
        }
    }

    public static void main(String args[]) {
        TestObservable testObservable = new TestObservable();
        testObservable.addObserver(new TestObserver());

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(testObservable);
        System.out.println(Thread.currentThread().getName());
        executorService.shutdown();
    }
}
