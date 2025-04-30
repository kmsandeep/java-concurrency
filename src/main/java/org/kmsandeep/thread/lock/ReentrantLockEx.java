package org.kmsandeep.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockEx {

    ReentrantLock lock = new ReentrantLock();

    public void printMessage() {
        try {
            lock.lock();
            Thread.sleep(1000);

            System.out.println("printMessage: " + lock.getHoldCount()+" "+Thread.currentThread().getName());

            sayHi();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    private void sayHi() {

        try {
            lock.lock();
            System.out.println("sayHi: " + lock.getHoldCount()+" "+Thread.currentThread().getName());

            Thread.sleep(1000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        ReentrantLockEx reentrantLockEx = new ReentrantLockEx();
        new Thread(reentrantLockEx::printMessage).start();
        new Thread(reentrantLockEx::printMessage).start();
        new Thread(reentrantLockEx::printMessage).start();


    }
}
