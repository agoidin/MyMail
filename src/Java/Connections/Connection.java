package Java.Connections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

abstract class Connection {
    protected Socket socket;
    protected PrintWriter writer;
    protected BufferedReader reader;

    protected Connection(String host, int port) throws UnknownHostException, IOException {          
        socket = new Socket(host, port);

        InputStream input = socket.getInputStream();

        OutputStream output = socket.getOutputStream();
        writer = new PrintWriter(output, true);

        reader = new BufferedReader(new InputStreamReader(input));
    }

    /**
     * Executes the command and prints all responses to System log
     * @param command user command to be executed
     * @throws IOException
     */
    protected abstract String executeCommand(String command) throws IOException;

    /**
     * Once called closes the connection and closes the socket
     * @throws IOException
     */
    public void closeConnection(String command) throws IOException {
        executeCommand(command);
        socket.close();
    }
}
