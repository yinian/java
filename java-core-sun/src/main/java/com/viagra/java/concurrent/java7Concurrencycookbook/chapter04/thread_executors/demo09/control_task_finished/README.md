##执行者控制一个任务完成

FutureTask类提供一个done()方法，允许你在执行者执行任务完成后执行一些代码。

你可以用来做一些后处理操作，生成一个报告，通过e-mail发送结果，或释放一些资源。

当执行的任务由FutureTask来控制完成，FutureTask会内部调用这个方法。

这个方法在任务的结果设置和它的状态变成isDone状态之后被调用，不管任务是否已经被取消或正常完成


当控制任务执行完成后，FutureTask类调用done()方法。在这个示例中，你已经实现一个Callable对象，ExecutableTask类，

然后一个FutureTask类的子类用来控制ExecutableTask对象的执行。

在建立返回值和改变任务的状态为isDone状态后，done()方法被FutureTask类内部调用。

你不能改变任务的结果值和它的状态，但你可以关闭任务使用的资源，写日志消息，或发送通知。