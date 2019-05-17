## 使用Lock同步代码块
Java提供另外的机制用来同步代码块。它比synchronized关键字更加强大、灵活。它是基于Lock接口和实现它的类（如ReentrantLock）。这种机制有如下优势：

它允许以一种更灵活的方式来构建synchronized块。

使用synchronized关键字，你必须以结构化方式得到释放synchronized代码块的控制权。

Lock接口允许你获得更复杂的结构来实现你的临界区。
Lock 接口比synchronized关键字提供更多额外的功能。新功能之一是实现的tryLock()方法。

这种方法试图获取锁的控制权并且如果它不能获取该锁，是因为其他线程在使用这个锁，它将返回这个锁。

使用synchronized关键字，当线程A试图执行synchronized代码块，如果线程B正在执行它，

那么线程A将阻塞直到线程B执行完synchronized代码块。使用锁，你可以执行tryLock()方法，

这个方法返回一个 Boolean值表示，是否有其他线程正在运行这个锁所保护的代码。

当有多个读者和一个写者时，Lock接口允许读写操作分离。

Lock接口比synchronized关键字提供更好的性能。