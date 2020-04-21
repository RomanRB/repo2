import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

class PrimeFindingThread implements Runnable {
    private int intervalBeginning, intervalEnd;
    private boolean straightToOverallCollection;
    private CountDownLatch countDownLatch;

     PrimeFindingThread(int intervalBeginning, int intervalEnd, boolean straightToOverallCollection, CountDownLatch countDownLatch) {
        this.intervalBeginning = intervalBeginning;
        this.intervalEnd = intervalEnd;
        this.straightToOverallCollection = straightToOverallCollection;
        this.countDownLatch = countDownLatch;
    }
    public void run(){
         ArrayList<Integer> localPrimeNumbers = new ArrayList<>();
         for(int i = this.intervalBeginning; i <= this.intervalEnd; i++){
             if(PrimeNumbers.isPrime(i)){
                 if(this.straightToOverallCollection){
                     PrimeNumbers.primeNumbersSet.add(i);
                 }
                 else {
                     localPrimeNumbers.add(i);
                 }
             }
         }
         if(!localPrimeNumbers.isEmpty()){
             PrimeNumbers.primeNumbersSet.addAll(localPrimeNumbers);
         }
         this.countDownLatch.countDown();
    }
}
