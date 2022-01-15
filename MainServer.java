import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) {
	// write your code here

        AviServerSocket aviServerSocket = AviServerSocket.getInstance(12456);
        aviServerSocket.listenToMess(new ServerSocketReadWriteLisener() {
            @Override
            public void sendRes(PrintWriter printWriter) {
                printWriter.println("Hello Client From Server");
                printWriter.flush();
            }

            @Override
            public void onReq(BufferedReader bufferedReader) {
                try {
                    System.out.println(bufferedReader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
