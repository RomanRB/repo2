class Finder implements Runnable {

    private String content;
    private static boolean flag;


    void setContent(String content) {
        this.content = content;
    }

    static void setFlag(boolean flag) {
        Finder.flag = flag;
    }


    @Override
    public void run() {
        while (true) {
            while (!flag) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lookForLongest();
            searchResultOutput();
            Sequence.switcher = -1;
            setFlag(false);
        }

    }
    private void lookForLongest(){
        String result = "";
        int n = this.content.length();
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                String temp = largestCommonPrefix(this.content.substring(i,n),this.content.substring(j,n));
                if(temp.length() > result.length()) {
                    result = temp;
                    Sequence.longestSequence = result;
                    Sequence.switcher = 1;
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    private String largestCommonPrefix(String s, String t){
        int n = Math.min(s.length(),t.length());
        for(int i = 0; i < n; i++){
            if(s.charAt(i) != t.charAt(i)){
                return s.substring(0,i);
            }
        }
        return s.substring(0,n);
    }
    private void searchResultOutput(){
        int firstIndex = content.indexOf(Sequence.longestSequence);
        String temp = content.replaceFirst(Sequence.longestSequence, "1");
        int secondIndex = temp.indexOf(Sequence.longestSequence);
        System.out.println(String.format("Длина максимальной последовательности - %d, 1-й индекс - %d, 2-й индекс - %d", Sequence.longestSequence.length(), firstIndex, secondIndex));

    }
}
