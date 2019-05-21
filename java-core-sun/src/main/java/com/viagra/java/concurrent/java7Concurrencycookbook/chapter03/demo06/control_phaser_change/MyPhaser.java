package com.viagra.java.concurrent.java7Concurrencycookbook.chapter03.demo06.control_phaser_change;

import java.util.concurrent.Phaser;

/**
 实现Phaser类的子类.覆盖onAdvance()方法去控制phase的改变.
 **/
public class MyPhaser extends Phaser {

    /**
     * 这个方法会在arriveAndAwaitAdvance()方法中被调用.
     * 根据phase属性的值,调用不同的辅助方法.
     */
    @Override
    protected boolean onAdvance(int phase,
                                int registeredParties) {
        /*
        // 2. 覆盖 onAdvance() 方法。根据 phase 的属性的值，我们将调用不同的辅助方法。如果 phase 等于 0，调用
    // studentsArrived() 方法；又如果 phase 等于 1，调用 finishFirstExercise() 方法；又如果 phase
    // 等于 2，调用 finishSecondExercise() 方法；再如果 phase 等于 3，调用 finishExam()
    // 方法。否则，返回真值，表示phaser已经终结。
         */
        switch (phase){
            case 0:
                return studentsArrived();
            case 1:
                return finishFirstExercise();
            case 2:
                return finishSecondExercise();
            case 3:
                return finishExam();
                default:
                    return true;
        }

    }

    /**
     * 这个方法在阶段0到阶段1改变的时候调用
     * // 实现辅助方法 studentsArrived()。它在操控台写2条信息，并返回false值来表明phaser将继续执行。
     */
    private boolean studentsArrived() {
        System.out.printf("Phaser: The exam are going to start. The students are ready.\n");
        System.out.printf("Phaser: We have %d students.\n",getRegisteredParties());
        return false;
    }

    /**
     * 这个方法在阶段1到阶段2改变的时候调用
     * // 实现辅助方法 finishFirstExercise()。它在操控台写2条信息，并返回false值来表明phaser将继续执行。
     */
    private boolean finishFirstExercise() {
        System.out.printf("Phaser: All the students has finished the first exercise.\n");
        System.out.printf("Phaser: It's turn for the second one.\n");
        return false;
    }

    /**
     * 这个方法在阶段2到阶段3改变的时候调用
     * // 实现辅助方法 finishSecondExercise()。它在操控台写2条信息，并返回false值来表明phaser将继续执行。
     */
    private boolean finishSecondExercise() {
        System.out.printf("Phaser: All the students has finished the second exercise.\n");
        System.out.printf("Phaser: It's turn for the third one.\n");
        return false;
    }

    /**
     * 这个方法在阶段3到阶段4改变的时候调用
     * 实现辅助方法 finishExam()。它在操控台写2条信息，并返回false值来表明phaser将继续执行。
     */
    private boolean finishExam() {
        System.out.printf("Phaser: All the students has finished the exam.\n");
        System.out.printf("Phaser: Thank you for your time.\n");
        return true;
    }
}
