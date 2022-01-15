import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket {

    private InputStreamReader inputStreamReader;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private ClientSocketReadWriteLisener lisener;
    private Socket socket;
    private String host;
    private int port;
    public static int singleton = 0;

    private ClientSocket(String host,int port){

            this.host = host;
            this.port = port;

    }

    public static ClientSocket getInstance(String host,int port){
        if(singleton == 0){
            ClientSocket cs = new ClientSocket(host,port) ;
            singleton = 1;
            return cs ;
        }
        return null;
    }

    public void setClientSocketReadWriteLisener(ClientSocketReadWriteLisener lisener) {
        this.lisener = lisener;
    }

    public void sendMess(ClientSocketReadWriteLisener lisener){
        try {
            this.socket = new Socket(host,port);
            this.inputStreamReader = new InputStreamReader(socket.getInputStream());
            this.printWriter = new PrintWriter(socket.getOutputStream());
            this.bufferedReader = new BufferedReader(inputStreamReader);
            lisener.send(this.printWriter);
            lisener.onRes(this.bufferedReader);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
