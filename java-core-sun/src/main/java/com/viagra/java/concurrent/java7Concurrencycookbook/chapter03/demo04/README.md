我们有一个很大的随机的整数矩阵，然后你想知道这矩阵里面某个数字出现的次数。

为了更好的执行，我们使用了 divide 和 conquer 技术。我们 divide 矩阵成5个子集，

然后在每个子集里使用一个线程来查找数字。这些线程是 Searcher 类的对象。

我们使用 CyclicBarrier 对象来同步5个线程的完成，并执行 Grouper 任务处理个别结果，最后计算最终结果。

如我们之前提到的，CyclicBarrier 类有一个内部计数器控制到达同步点的线程数量。

每次线程到达同步点，它调用 await() 方法告知 CyclicBarrier 对象到达同步点了。

CyclicBarrier 把线程放入睡眠状态直到全部的线程都到达他们的同步点。

当全部的线程都到达他们的同步点，CyclicBarrier 对象叫醒全部正在 await() 方法中等待的线程们，

然后，选择性的，为CyclicBarrier的构造函数 传递的 Runnable 对象（例子里，是 Grouper 对象）

创建新的线程执行外加任务。



####重置 CyclicBarrier 对象
CyclicBarrier 类与CountDownLatch有一些共同点，但是也有一些不同。最主要的不同是，CyclicBarrier对象可以重置到它的初始状态，重新分配新的值给内部计数器，即使它已经被初始过了。

可以使用 CyclicBarrier的reset() 方法来进行重置操作。当这个方法被调用后，全部的正在await() 方法里等待的线程接收到一个 BrokenBarrierException 异常。此异常在例子中已经用打印stack trace处理了，但是在一个更复制的应用，它可以执行一些其他操作，例如重新开始执行或者在中断点恢复操作。

#### 破坏 CyclicBarrier 对象 
CyclicBarrier 对象可能处于一个特殊的状态，称为 broken。当多个线程正在 await() 方法中等待时，其中一个被中断了，此线程会收到 InterruptedException 异常，但是其他正在等待的线程将收到 BrokenBarrierException 异常，并且 CyclicBarrier 会被置于broken 状态中。

CyclicBarrier 类提供了isBroken() 方法，如果对象在 broken 状态，返回true，否则返回false。