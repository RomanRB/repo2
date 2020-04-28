import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class PrimeNumbers implements Finder{
    static final ConcurrentSkipListSet<Integer> primeNumbersSet = new ConcurrentSkipListSet<>();
    int intervalBeginning, intervalEnd, threadCount;

    public static void main(String[] args) throws IOException, InterruptedException {
        PrimeNumbers primeNumbers = new PrimeNumbers();
        primeNumbers.setValues(new UserInput(primeNumbers));

        primeNumbers.startThreads(primeNumbers, true);
        primeNumbersSet.clear();
        primeNumbers.startThreads(primeNumbers, false);

    }
    private void setValues(UserInput userInput) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        this.intervalBeginning = userInput.inputIntervalBeginning(reader);
        this.intervalEnd = userInput.inputIntervalEnd(reader);
        this.threadCount = userInput.inputThreadCount(reader);
        reader.close();
    }
    static boolean isPrime(int number){
        if(number == 1) return false;
        else if(number == 2) return true;
        else {
            for(int i = 2; i <= number/2; i++){
                if(number%i == 0) return false;
            }
        }
        return true;
    }
    @Override
    public String toString(){
        return "" + intervalBeginning + " " + intervalEnd + " " + threadCount;
    }

}
