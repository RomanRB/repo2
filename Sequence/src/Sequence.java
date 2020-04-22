import java.io.*;

public class Sequence {

    static String longestSequence;
    static int switcher = 0;

    public static void main(String[] args) throws IOException, InterruptedException {
        Sequence sequence = new Sequence();
        sequence.startSearch();
    }
    private void startSearch() throws IOException, InterruptedException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Finder finder = new Finder();
        new  Thread(finder).start();
        while (true) {
            switcher = 0;
            longestSequence = "";
            String content = getFileContent(bufferedReader);
            finder.setContent(content);
            Finder.setFlag(true);
            while (true) {
                if (switcher == 0) Thread.sleep(500);
                else if (switcher == 1) {
                    System.out.println("Текущая длина последовательности: " + longestSequence.length());
                    switcher = 0;
                } else if (switcher == -1) break;
            }
        }
    }

    private String getFileName(BufferedReader bufferedReader) throws IOException {

        System.out.println("Укажите путь к файлу:");
        String fileName = bufferedReader.readLine();
        while (!(new File(fileName).exists())){
            System.out.println("Ошибка. Указанный файл не существует");
            System.out.println("Укажите путь к файлу:");
            fileName = bufferedReader.readLine();}

        return fileName;
    }
    private String getFileContent(BufferedReader bufferedReader) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(getFileName(bufferedReader)));
        StringBuilder entry = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null){
            entry.append(line);
        }
        reader.close();
        return entry.toString();
    }

}
