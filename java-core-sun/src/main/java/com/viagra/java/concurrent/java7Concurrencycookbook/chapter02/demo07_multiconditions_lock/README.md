## 在Lock中使用多个条件

一个锁可能伴随着多个条件。这些条件声明在Condition接口中。
这些条件的目的是允许线程拥有锁的控制并且检查条件是否为true，如果是false，那么线程将被阻塞，
直到其他线程唤醒它们。Condition接口提供一种机制，阻塞一个线程和唤醒一个被阻塞的线程。