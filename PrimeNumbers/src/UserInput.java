import java.io.BufferedReader;
import java.io.IOException;


class UserInput {
    private PrimeNumbers primeNumbers;

    UserInput(PrimeNumbers primeNumbers) {
        this.primeNumbers = primeNumbers;
    }

    int inputIntervalBeginning(BufferedReader reader) throws IOException {
        System.out.println("Введите начало интервала поиска(целое положительное число):");
        return this.getInput(reader);
    }
    int inputIntervalEnd(BufferedReader reader) throws IOException {
        System.out.println("Введите конец интервала поиска(целое положительное число):");
        int end = this.getInput(reader);
        while (this.primeNumbers.intervalBeginning > end){
            System.out.println("Ошибка. Число, обозначающее конец интервала, должно быть больше числа, обозначающего начало интервала");
            end = this.getInput(reader);
        }
        return end;
    }
    int inputThreadCount(BufferedReader reader) throws IOException{
        System.out.println("Введите количество потоков(целое положительное число):");
        int threadCount = this.getInput(reader);
        if(threadCount != Runtime.getRuntime().availableProcessors() - 1){
            threadCount = Runtime.getRuntime().availableProcessors() - 1;
            System.out.println("С учетом системных параметров оптимальное количество потоков - " + threadCount + ".\n" +
                    "Поэтому при обработке указанного интервала будут использованы потоки в количестве не более " + threadCount +" ед.");
        }
        return threadCount;
    }
    private int getInput(BufferedReader reader) throws IOException {
        int i = 0;
        while(i <= 0){
            try{
                i = Integer.parseInt(reader.readLine());
                if (i <= 0) System.out.println("Ошибка. Вы ввели недопустимое значение");
            }
            catch (NumberFormatException e){
                System.out.println("Ошибка. Вы ввели недопустимое значение");
            }

        }
        return i;
    }
}
