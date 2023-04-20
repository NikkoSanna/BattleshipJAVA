package packet;

import java.net.*;
import java.io.*;

public class Server {
    boolean started = false;

    public Server() throws IOException {
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(
                whatismyip.openStream()));

        String ip = in.readLine(); //you get the IP as a String
        System.out.println(ip);
        started = true;
    }
}