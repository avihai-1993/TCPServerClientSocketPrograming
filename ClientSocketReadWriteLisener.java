import java.io.BufferedReader;
import java.io.PrintWriter;

public interface ClientSocketReadWriteLisener {
    void send(PrintWriter printWriter);
    void onRes(BufferedReader bufferedReader);
}
