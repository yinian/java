## 在此章节，我们将学习怎样使用高等级的机制来达到多线程的同步。这些高等级机制有：

- Semaphores: 控制访问多个共享资源的计数器。此机制是并发编程的最基本的工具之一，而且大部分编程语言都会提供此机制。
- CountDownLatch: CountDownLatch 类是Java语言提供的一个机制，它允许线程等待多个操作的完结。
- CyclicBarrier: CyclicBarrier 类是又一个java语言提供的机制，它允许多个线程在同一个点同步。
- Phaser: Phaser类是又一个java语言提供的机制，它控制并发任务分成段落来执行。全部的线程在继续执行下一个段之前必须等到之前的段执行结束。这是Java 7 API的一个新特性。
- Exchanger: Exchanger类也是java语言提供的又一个机制，它提供2个线程间的数据交换点。