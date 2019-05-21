
这程序开始创建的 Phaser 对象是用来在每个phase的末端控制线程的同步。

Phaser的构造函数接收参与者的数量作为参数。在这里，Phaser有3个参与者。

这个数向Phaser表示 Phaser改变phase之前执行 arriveAndAwaitAdvance() 方法的线程数，

并叫醒正在休眠的线程。

Phaser 对象可能是在这2中状态：

Active: 当 Phaser 接受新的参与者注册，它进入这个状态，

并且在每个phase的末端同步。 在此状态，Phaser像在这个指南里解释的那样工作。

此状态不在Java 并发 API中。
Termination: 默认状态，当Phaser里全部的参与者都取消注册，它进入这个状态，

所以这时 Phaser 有0个参与者。更具体的说，当onAdvance() 方法返回真值时，
Phaser 是在这个状态里。如果你覆盖那个方法,你可以改变它的默认行为。

当 Phaser 在这个状态，同步方法 arriveAndAwaitAdvance()会 立刻返回，不会做任何同步。

Phaser 类的一个显著特点是你不需要控制任何与phaser相关的方法的异常。不像其他同步应用，

线程们在phaser休眠不会响应任何中断也不会抛出 InterruptedException 异常。

只有一个异常会在下面的‘更多’里解释。