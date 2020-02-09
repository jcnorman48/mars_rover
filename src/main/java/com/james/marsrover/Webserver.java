package com.james.marsrover;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Webserver {

    static final String INPUT = "5 5 1 2 N LMLMLMLMM 3 3 E MMRMMRMRRM";

    /*
    A very quick webserver, and nothing close to usable beyond this code challenge
     */
    public static void main(String[] args) throws Exception {

        ServerSocket ss = new ServerSocket(8085);
        System.out.println("Server started, listening on port 8085\n http://127.0.0.1:8085");

        while(true){
            Socket s = ss.accept();
            new Thread(() -> {

                try{
                    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    PrintWriter out = new PrintWriter(s.getOutputStream());
                    in.readLine();

                    out.println("HTTP/1.1");
                    out.println("Content-type: text/html");
                    out.println(); // blank line between headers and content, very important !
                    out.println("Input: " + INPUT + "<br/>");
                    out.println("Final Rover Positions: <b>" + Run.run(INPUT) + "</b>");
                    out.flush();
                    out.close();
                }catch(IOException ioEx){
                    ioEx.printStackTrace();
                }
            }).start();
        }
    }
}
