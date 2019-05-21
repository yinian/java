这个例子的关键是PrintQueue类的printJob()方法。此方法展示了3个你必须遵守的步骤当你使用semaphore来实现critical section时，并保护共享资源的访问：

1. 首先， 你要调用acquire()方法获得semaphore。

2. 然后， 对共享资源做出必要的操作。

3. 最后， 调用release()方法来释放semaphore。

另一个重点是PrintQueue类的构造方法和初始化Semaphore对象。你传递值1作为此构造方法的参数，

那么你就创建了一个binary semaphore。初始值为1，就保护了访问一个共享资源，

在例子中是print queue。

当你开始10个threads，当你开始10个threads时，

那么第一个获得semaphore的得到critical section的访问权。

剩下的线程都会被semaphore阻塞直到那个获得semaphore的线程释放它。

当这情况发生，semaphore在等待的线程中选择一个并给予它访问critical section的访问权。

全部的任务都会打印文档，只是一个接一个的执行。

Semaphore类有另2个版本的 acquire() 方法：

acquireUninterruptibly()：acquire()方法是当semaphore的内部计数器的值为0时，

阻塞线程直到semaphore被释放。在阻塞期间，线程可能会被中断，

然后此方法抛出InterruptedException异常。而此版本的acquire方法会忽略线程的中断而且不会抛出任何异常。
tryAcquire()：此方法会尝试获取semaphore。如果成功，返回true。如果不成功，

返回false值，并不会被阻塞和等待semaphore的释放。接下来是你的任务用返回的值执行正确的行动。
Semaphores的公平性

fairness的内容是指全java语言的所有类中，那些可以阻塞多个线程并等待同步资源释放的类（例如，semaphore)。默认情况下是非公平模式。在这个模式中，当同步资源释放，就会从等待的线程中任意选择一个获得资源，但是这种选择没有任何标准。

而公平模式可以改变这个行为并强制选择等待最久时间的线程。

随着其他类的出现，Semaphore类的构造函数容许第二个参数。这个参数必需是 Boolean 值。

如果你给的是 false 值，那么创建的semaphore就会在非公平模式下运行。如果你不使用这个参数，

是跟给false值一样的结果。如果你给的是true值，那么你创建的semaphore就会在公平模式下运行。
=================

##信号量可以分为几类：    
  - 二进制信号量(binary semaphore)：只允许信号量取0或1值，其同时只能被一个线程获取。    
    
  - 整型信号量（integer semaphore)：信号量取值是整数，它可以被多个线程同时获得，直到信号量的值变为0。    
    
  - 记录型信号量（record semaphore)：每个信号量s除一个整数值value（计数）外，还有一个等待队列List，其中是阻塞在该信号量的各个线程的标识。当信号量被释放一个，值被加一后，系统自动从等待队列中唤醒一个等待中的线程，让其获得信号量，同时信号量再减一。    

