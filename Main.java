import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {
	// write your code here
      Scanner s = new Scanner(System.in);
      ClientSocket clientSocket = ClientSocket.getInstance("localhost",12456);

      while (true){
          System.out.println("write a mesgge : ");
          String str = s.nextLine();

          clientSocket.sendMess(new ClientSocketReadWriteLisener() {
              @Override
              public void send(PrintWriter printWriter) {

                  printWriter.println(str);
                  printWriter.flush();
                  
              }

              @Override
              public void onRes(BufferedReader bufferedReader) {
                  try {
                      System.out.println(bufferedReader.readLine());
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
          });
      }


    }
}
