## 描述
问题：由于缓冲区是一个共享的数据结构，我们必须采用同步机制


Java在Object对象中提供wait()，notify()，和notifyAll() 方法的实现。
一个线程可以在synchronized代码块中调用wait()方法。如果在synchronized代码块外部调用wait()方法，
JVM会抛出IllegalMonitorStateException异常。当线程调用wait()方法，JVM让这个线程睡眠，
并且释放控制 synchronized代码块的对象，这样，虽然它正在执行但允许其他线程执行由该对象保护的其他synchronized代码块。
为了唤醒线程，你必 须在由相同对象保护的synchronized代码块中调用notify()或notifyAll()方法。