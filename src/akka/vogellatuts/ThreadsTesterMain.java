package akka.vogellatuts;

/**
 * User: mayukh
 * Date: 11/16/13
 * Time: 11:31 PM
 */

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.lang.InterruptedException;
import java.lang.RuntimeException;

public class ThreadsTesterMain {

    private static final int THREADS = 16;
    private static final int CORES = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {

//        testThreads();
//        testThreadPools();
//        testCallableFutures();
//        testNonBlocking();
        testForkJoin();
    }


    public static void testForkJoin() {
        IntListGen myList = new IntListGen();
        System.out.println("this fork-join will run on " + CORES + " threads");

        ForkJoinSolver solver = new ForkJoinSolver(myList.getList());
        ForkJoinPool pool = new ForkJoinPool(CORES);
        pool.invoke(solver);
        long result = solver.result;
        System.out.println("Parallel Sum: " + result);

//        long seqSum = 0;
//        for (int i = 0; i < myList.getList().length; i++) {
//            seqSum += myList.getList()[i];
//        }
//        System.out.println("Sequential Sum: " + seqSum);
//        System.out.println("Correct!");
    }


    public static void testNonBlocking() {
        final CounterAtomic counter = new CounterAtomic();
//        System.out.println("init value of atomic int: " + counter.getValue());

        List<Future<Integer>> results = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(THREADS);

        for (int i = 0; i < 500; i++) {
            Callable<Integer> task = new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int num = counter.increment();
                    System.out.println(num);
                    return num;
                }
            };
            Future<Integer> submit = executor.submit(task);
            results.add(submit); // thats it
        }

        executor.shutdown();
        while (!executor.isTerminated());

        List<Integer> check = new ArrayList<>();
        for (Future<Integer> result : results) {
            try {
                check.add(result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        if (results.size() != check.size())
            throw new RuntimeException("Missing/ Double entries");
        else
            System.out.println("Success");
    }


    public static void testCallableFutures() {
        ExecutorService executor = Executors.newFixedThreadPool(THREADS);
        List<Future<Long>> returns = new ArrayList<>();

        for (int i = 0; i < 500; i++) {
            Callable<Long> task = new MyCallableAdder(1000000);
            Future<Long> submit = executor.submit(task);
            returns.add(submit); // thats it
        }

        for (Future<Long> future : returns) {
            try {
                System.out.println("sum: " + future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("All workers finished");
        executor.shutdown();
    }


    public static void testThreadPools() {
        try {
            ExecutorService executor = Executors.newFixedThreadPool(THREADS);
            for (int i = 0; i < 500; i++) {
                Runnable worker = new MyRunnableAdder(100 + i);
                executor.execute(worker); // thats it
            }
//          controlling the executor
            executor.shutdown(); // make it accept no new threads
            executor.awaitTermination(20000, TimeUnit.MILLISECONDS); //wait untill all threads are finished. JDK 7 needs these args
            System.out.println("All workers finished");
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }


    public static void testThreads() {
        List<Thread> workers = new ArrayList<>();
        System.out.println("My first threaded application");

        for (int i = 0; i < 500; i++) { // 500 is the number of workers desired in which case each worker could have just one task, but actual number of threads will be allocated by JVM (M for manager?)
//          create task
            Runnable task = new MyRunnableAdder (1000 + i);
//          create worker with that task
            Thread worker = new Thread (task);
//          set worker properties
            worker.setName(String.valueOf(i));
//          start worker, do not call run()
            worker.start();
//          optionally, add worker to a list for attendance
            workers.add(worker);
        }

        int num_working = 0;
        do {
            for (Thread worker: workers) {
                if (worker.isAlive()) // check a worker property for analysis
                    num_working++;
            }
            System.out.println("# workers working: "+num_working);
        } while (num_working > 0);
    }
}
