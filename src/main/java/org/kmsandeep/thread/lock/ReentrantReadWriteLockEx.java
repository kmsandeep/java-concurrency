package org.kmsandeep.thread.lock;


import java.time.LocalTime;
import java.util.Stack;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockEx {

    ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private int counter = 0;

    public void readCount() {
        int i = 10;
        Stack<Integer> stack  = new Stack<Integer>();
        while (i-- > 0) {
            try {
                readWriteLock.readLock().lock();
                Thread.sleep(1000);
                System.out.println("Reading Count:" + counter + " " + Thread.currentThread().getName() + " at time: "+ LocalTime.now());

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                readWriteLock.readLock().unlock();
            }
        }

    }

    public void updateCounter() {
        int i = 5;
        while (i-- > 0) {
            try {

                readWriteLock.writeLock().lock();
                counter++;
                System.out.println("------------------------------Writing Count:" + counter + " " + Thread.currentThread().getName()+ " at time: "+ LocalTime.now());
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                readWriteLock.writeLock().unlock();
            }
        }

    }

    public static void main(String[] args) {

        ReentrantReadWriteLockEx reentrantLockEx = new ReentrantReadWriteLockEx();
        new Thread(reentrantLockEx::readCount).start();
        new Thread(reentrantLockEx::readCount).start();
        new Thread(reentrantLockEx::readCount).start();
        new Thread(reentrantLockEx::updateCounter).start();
        new Thread(reentrantLockEx::updateCounter).start();
        new Thread(reentrantLockEx::updateCounter).start();

    }
}

