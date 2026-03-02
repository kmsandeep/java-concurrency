package org.kmsandeep.thread.synchronize;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadOrderWithReentrantLock {

    static class OrderedPrinter {
        private final Lock lock = new ReentrantLock();
        private final Condition firstDone = lock.newCondition();
        private final Condition secondDone = lock.newCondition();

        private boolean isFirstPrinted = false;
        private boolean isSecondPrinted = false;

        public void printFirst() {
            lock.lock();
            try {
                System.out.println("First");
                isFirstPrinted = true;
                firstDone.signal(); // Signal Thread 2
            } finally {
                lock.unlock();
            }
        }

        public void printSecond() {
            lock.lock();
            try {
                while (!isFirstPrinted) {
                    firstDone.await(); // Wait for Thread 1
                }
                System.out.println("Second");
                isSecondPrinted = true;
                secondDone.signal(); // Signal Thread 3
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }

        public void printThird() {
            lock.lock();
            try {
                while (!isSecondPrinted) {
                    secondDone.await(); // Wait for Thread 2
                }
                System.out.println("Third");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {

        OrderedPrinter printer = new OrderedPrinter();

        Thread t1 = new Thread(printer::printFirst);
        Thread t2 = new Thread(printer::printSecond);
        Thread t3 = new Thread(printer::printThird);

        // Threads can start in any order
        t3.start();
        t2.start();
        t1.start();
    }
}
