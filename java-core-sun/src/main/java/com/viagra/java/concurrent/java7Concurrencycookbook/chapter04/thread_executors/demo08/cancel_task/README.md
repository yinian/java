## 执行者取消一个任务

当你使用执行者工作时，你不得不管理线程。你只实现Runnable或 Callable任务和把它们提交给执行者。

执行者负责创建线程，在线程池中管理它们，当它们不需要时，结束它们。有时候，你想要取消已经提交给执行者的任务。

在这种情况下，你可以使用Future的cancel()方法，它允许你做取消操作。



当你想要取消你已提交给执行者的任务，使用Future接口的cancel()方法。根据cancel()方法参数和任务的状态不同，这个方法的行为将不同：

如果这个任务已经完成或之前的已被取消或由于其他原因不能被取消，那么这个方法将会返回false并且这个任务不会被取消。

如果这个任务正在等待执行者获取执行它的线程，那么这个任务将被取消而且不会开始它的执行。如果这个任务已经正在运行，

则视方法的参数情况而定。 cancel()方法接收一个Boolean值参数。如果参数为true并且任务正在运行，

那么这个任务将被取消。如果参数为false并且任务正在运行，那么这个任务将不会被取消。

如果你使用Future对象的get()方法来控制一个已被取消的任务，这个get()方法将抛出CancellationException异常。

