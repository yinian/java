## 在任务中抛出异常
   
   在Java中有两种异常：
   
   已检查异常（Checked exceptions）：这些异常必须在一个方法的throws从句中指定或在内部捕捉它们。比如：IOException或ClassNotFoundException。
   未检查异常（Unchecked exceptions）：这些异常不必指定或捕捉。比如：NumberFormatException。
   在ForkJoinTask类的compute()方法中，你不能抛出任何已检查异常，因为在这个方法的实现中，它没有包含任何抛出（异常）声明。你必须包含必要的代码来处理异常。但是，你可以抛出（或者它可以被任何方法或使用内部方法的对象抛出）一个未检查异常。ForkJoinTask和ForkJoinPool类的行为与你可能的期望不同。程序不会结束执行，并且你将不会在控制台看到任何关于异常的信息。它只是被吞没，好像它没抛出（异常）。你可以使用ForkJoinTask类的一些方法，得知一个任务是否抛出异常及其异常种类。在这个指南中，你将学习如何获取这些信息。