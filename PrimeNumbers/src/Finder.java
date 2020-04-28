import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public interface Finder {
    default void startThreads(PrimeNumbers primeNumbers, boolean straightToOverallCollection) throws InterruptedException {
        long start = System.nanoTime();
        CountDownLatch countDownLatch = new CountDownLatch(primeNumbers.threadCount);
        doThreads(primeNumbers.intervalBeginning, primeNumbers.intervalEnd, primeNumbers.threadCount, straightToOverallCollection, countDownLatch);
        countDownLatch.await();
        long finish = System.nanoTime();
        if(straightToOverallCollection){
            System.out.println("Время работы при непосредственном заполнении общей коллекции: " + (finish - start)/1000000000.0 + " сек.");
        }
        else{
            System.out.println("Время работы при заполнении промежуточных коллекций: " + (finish - start)/1000000000.0 + " сек.");
        }
    }
    default void startTasks(PrimeNumbers primeNumbers, boolean straightToOverallCollection) throws InterruptedException {
        long start = System.nanoTime();
        CountDownLatch countDownLatch = new CountDownLatch(primeNumbers.threadCount);
        doTasks(primeNumbers.intervalBeginning, primeNumbers.intervalEnd, primeNumbers.threadCount, straightToOverallCollection, countDownLatch);
        countDownLatch.await();
        long finish = System.nanoTime();
        if(straightToOverallCollection){
            System.out.println("Время работы при непосредственном заполнении общей коллекции : " + (finish - start)/1000000000.0 + " сек.");
        }
        else{
            System.out.println("Время работы при заполнении промежуточных коллекций : " + (finish - start)/1000000000.0 + " сек.");
        }
    }
    default void doThreads(int intervalBeginning, int intervalEnd, int threadCount, boolean straightToOverallCollection, CountDownLatch countDownLatch) {
        int intervalLength = intervalEnd - intervalBeginning + 1;
        int tempBeginning = intervalBeginning;
        int tempEnd;
        if (threadCount >= intervalLength){
            new Thread(new PrimeFindingThread(intervalBeginning, intervalEnd, straightToOverallCollection, countDownLatch)).start();
        }
        else{
            for(int i = 1; i <= threadCount; i++){
                if(tempBeginning < intervalEnd){
                    tempEnd = tempBeginning + intervalLength/threadCount;
                    if(tempEnd <= intervalEnd){
                        if(tempEnd < intervalEnd && i == threadCount){
                            new Thread(new PrimeFindingThread(tempBeginning, intervalEnd, straightToOverallCollection, countDownLatch)).start();
                        }
                        else{
                            new Thread(new PrimeFindingThread(tempBeginning, tempEnd, straightToOverallCollection, countDownLatch)).start();
                            tempBeginning = tempEnd + 1;
                        }
                    }
                    else{
                        new Thread(new PrimeFindingThread(tempBeginning, intervalEnd, straightToOverallCollection, countDownLatch)).start();
                        break;
                    }
                }
                else if(tempBeginning == intervalEnd){
                    new Thread(new PrimeFindingThread(tempBeginning, tempBeginning, straightToOverallCollection, countDownLatch)).start();
                    break;
                }
                else break;
            }
        }
    }

    default void doTasks(int intervalBeginning, int intervalEnd, int threadCount, boolean straightToOverallCollection, CountDownLatch countDownLatch) {
        int intervalLength = intervalEnd - intervalBeginning + 1;
        int tempBeginning = intervalBeginning;
        int tempEnd;
        ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);
        if (threadCount >= intervalLength){
            threadPool.execute(new PrimeFindingThread(intervalBeginning, intervalEnd, straightToOverallCollection, countDownLatch));
        }
        else{
            for(int i = 1; i <= threadCount; i++){
                if(tempBeginning < intervalEnd){
                    tempEnd = tempBeginning + intervalLength/threadCount;
                    if(tempEnd <= intervalEnd){
                        if(tempEnd < intervalEnd && i == threadCount){
                            threadPool.execute(new PrimeFindingThread(tempBeginning, intervalEnd, straightToOverallCollection, countDownLatch));
                        }
                        else{
                            threadPool.execute(new PrimeFindingThread(tempBeginning, tempEnd, straightToOverallCollection, countDownLatch));
                            tempBeginning = tempEnd + 1;
                        }
                    }
                    else{
                        threadPool.execute(new PrimeFindingThread(tempBeginning, intervalEnd, straightToOverallCollection, countDownLatch));
                        break;
                    }
                }
                else if(tempBeginning == intervalEnd){
                    threadPool.execute(new PrimeFindingThread(tempBeginning, tempBeginning, straightToOverallCollection, countDownLatch));
                    break;
                }
                else break;
            }
        }
        threadPool.shutdown();
    }
}
