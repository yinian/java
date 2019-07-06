## 线程执行
通常，当你在Java中开发一个简单的并发编程应用程序，你会创建一些Runnable对象并创建相应的Thread对象来运行它们。如果你开发一个运行多个并发任务的程序，这种途径的缺点如下：

你必须要实现很多相关代码来管理Thread对象（创建，结束，获得的结果）。

你必须给每个任务创建一个Thread对象。如果你执行一个大数据量的任务，那么这可能影响应用程序的吞吐量。
你必须有效地控制和管理计算机资源。如果你创建太多线程，会使系统饱和。

## Executor framework

从Java5开始JDK并发API提供一种机制。这个机制被称为Executor framework，接口核心是Executor，Executor的子接口是ExecutorService，而ThreadPoolExecutor类则实现了这两个接口。

这个机制将任务的创建与执行分离。使用执行者，你只要实现Runnable对象并将它们提交给执行者。执行者负责执行，实例化和运行这些线程。除了这些，它还可以使用线程池提高了性能。

当你提交一个任务给这个执行者，它试图使用线程池中的线程来执行任务，从而避免继续创建线程。

Callable接口是Executor framework的另一个重要优点。

- 这个接口中主要的方法叫call()，可以返回结果。
- 当你提交Callable对象到执行者，你可以获取一个实现Future接口的对象，你可以用这个对象来控制Callable对象的状态和结果。