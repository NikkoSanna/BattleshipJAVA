package packet;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    final static String nomeServer = "localhost";
    final static int portaServer = 4000;
    boolean started = false;

    public Client() throws IOException {
        //Ottengo il proprio indirizzo IP, leggo il contenuto del sito web
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));     //Copio il contenuto della pagina web

        //Indirizzo IP ottenuto tramite stringa e printato a console
        String ip = in.readLine();
        System.out.println(ip);
        started = true;     //Comunico che il client é startato con successo

        //Tentativo di connessione col server
        System.out.println("Connessione al server in corso...");
        try (Socket sck = new Socket(nomeServer, portaServer)) {
            String rem = sck.getRemoteSocketAddress().toString();
            String loc = sck.getLocalSocketAddress().toString();
            System.out.printf("Server (remoto): %s%n", rem);
            System.out.printf("Client (client): %s%n", loc);

            comunicate(sck);    //Esecuzione del metodo piú importante della classe
        } catch (UnknownHostException e) {
            System.err.printf("Nome di server non valido: %s%n", e.getMessage());    //Scrittura errore nella console
        } catch (IOException e) {
            System.err.printf("Errore durante la comunicazione con il server: %s%n", e.getMessage());   //Scrittura errore nella console
        }
    }
    private static void comunicate(Socket sck) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(sck.getInputStream(), "UTF-8"));

        PrintWriter out = new PrintWriter(new OutputStreamWriter(sck.getOutputStream(), "UTF-8"), true);

        Scanner s = new Scanner(System.in, "UTF-8");
        System.out.print("Frase: ");
        String frase = s.nextLine();
        System.out.printf("Invio al server: %s%n", frase);
        out.println(frase);
        System.out.println("In attesa di risposta dal server...");
        String risposta = in.readLine();
        System.out.printf("Risposta dal server: %s%n", risposta);
    }
}
