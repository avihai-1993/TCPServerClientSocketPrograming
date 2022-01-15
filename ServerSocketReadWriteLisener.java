import java.io.BufferedReader;
import java.io.PrintWriter;

public interface ServerSocketReadWriteLisener {

    void sendRes(PrintWriter printWriter);
    void onReq(BufferedReader bufferedReader);
}
