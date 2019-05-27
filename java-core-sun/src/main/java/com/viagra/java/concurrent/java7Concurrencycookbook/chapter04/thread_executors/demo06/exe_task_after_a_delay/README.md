## 执行者延迟运行一个任务
   
执行者框架提供ThreadPoolExecutor类，使用池中的线程来执行Callable和Runnable任务，

这样可以避免所有线程的创建操作。当你提交一个任务给执行者，会根据执行者的配置尽快执行它。在有些使用情况下，

当你对尽快执行任务不感觉兴趣。你可能想要在一段时间之后执行任务或周期性地执行任务。基于这些目的，


执行者框架提供 ScheduledThreadPoolExecutor类。


在这个示例中，关键的一点是Main类和ScheduledThreadPoolExecutor的管理。正如使用ThreadPoolExecutor类创建预定的执行者，Java建议利用Executors类。在本例中，你使用newScheduledThreadPool()方法。你用1作为参数传给这个方法。这个参数是你想要让线程池创建的线程数。

你必须使用schedule()方法，让执行者在一段时间后执行任务。这个方法接收3个参数，如下：

你想要执行的任务
你想要让任务在执行前等待多长时间
时间单位，指定为TimeUnit类的常数
在本例中，每个任务等待的秒数（TimeUnit.SECONDS）等于它在任务数组中的位置再加1。