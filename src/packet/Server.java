package packet;

import java.net.*;
import java.io.*;

public class Server {
    final static int serverPort = 45000;
    boolean started = false;

    public Server() throws IOException {
        //Ottengo il proprio indirizzo IP, leggo il contenuto del sito web
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));     //Copio il contenuto della pagina web

        //Indirizzo IP ottenuto tramite stringa e printato a console
        String ip = in.readLine();
        System.out.println(ip);
        started = true;     //Comunico che il server é startato con successo

        //Apertura comunicazione con client
        try (ServerSocket server = new ServerSocket(serverPort)) {
            System.out.printf("Server in ascolto su: %s%n", server.getLocalSocketAddress());    //Scrive a console la porta

            // Il server accetta e serve un client alla volta
            while (true) {
                try (Socket client = server.accept()) {
                    String rem = client.getRemoteSocketAddress().toString();
                    System.out.format("Client (remoto): %s%n", rem);

                    comunicate(client);     //Esecuzione del metodo piú importante della classe
                } catch (IOException e) {
                    System.err.println(String.format("Errore durante la comunicazione con il client: %s", e.getMessage()));     //Scrittura errore nella console
                }
            }
        //Apertura comunicazione non riuscita
        } catch (IOException e) {
            System.err.println(String.format("Errore server: %s", e.getMessage()));     //Scrittura errore nella console
        }
    }
    private static void comunicate(Socket sck) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(sck.getInputStream(), "UTF-8"));

        PrintWriter out = new PrintWriter(new OutputStreamWriter(sck.getOutputStream(), "UTF-8"), true);

        System.out.println("In attesa di ricevere informazioni dal client...");
        String inStr = in.readLine();
        System.out.format("Ricevuto dal client: %s%n", inStr);
        String outStr = "ha funzionato";
        out.println(outStr);
        System.out.format("Inviato al client: %s%n", outStr);
    }
}