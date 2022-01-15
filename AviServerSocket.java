import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class AviServerSocket {

    private InputStreamReader inputStreamReader;
    private ServerSocket serverSocket ;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private ServerSocketReadWriteLisener lisener;
    public static int singleton = 0;

    private AviServerSocket(int port){
        try {
            this.serverSocket = new ServerSocket(port);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AviServerSocket getInstance(int port){
        if(singleton == 0){
            AviServerSocket cs = new AviServerSocket(port) ;
            singleton=1;
            return cs ;
        }
        return null;
    }

    public void setServerSocketReadWriteLisener(ServerSocketReadWriteLisener lisener) {
        this.lisener = lisener;
    }

    public void listenToMess(ServerSocketReadWriteLisener lisener){
        try {
            while (true) {
                System.out.println("wating for req");
                Socket socket = this.serverSocket.accept();
                this.inputStreamReader = new InputStreamReader(socket.getInputStream());
                this.printWriter = new PrintWriter(socket.getOutputStream());
                this.bufferedReader = new BufferedReader(inputStreamReader);
                lisener.onReq(this.bufferedReader);
                lisener.sendRes(this.printWriter);
                System.out.println("finished prosec for req");
                socket.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
