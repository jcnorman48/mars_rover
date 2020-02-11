package com.james.marsrover;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Webserver {


    /*
    A very quick webserver, and nothing close to usable beyond this code challenge
    Note: this does not validate any input whatsoever
     */
    public static void main(String[] args) throws Exception {

        ServerSocket ss = new ServerSocket(8085);
        System.out.println("Server started, listening on port 8085\n http://127.0.0.1:8085?input=" +
                "5 5 1 2 N LMLMLMLMM 3 3 E MMRMMRMRRM".replaceAll(" ", "_"));

        while(true){
            Socket s = ss.accept();
            new Thread(() -> {

                try{
                    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    PrintWriter out = new PrintWriter(s.getOutputStream());

                    try{
                        String input = in.readLine().split(" ")[1]
                            .split("=")[1]
                            .replaceAll("\\?","")
                            .replaceAll("_", " ");

                        String response =  "Input: " + input + "<br/>" +
                                "Final Rover Positions: <b>" + Run.run(input) + "</b>";

                        respond(response, out);
                    }
                    //exceptions thrown from Run
                    catch(IllegalArgumentException e){
                        respond(e.getMessage(), out);
                    }
                    //way to much can go wrong above this
                    catch(Exception e ){
                        respond("Invalid URL", out);
                    }
                }catch(IOException ioEx){
                    ioEx.printStackTrace();
                }
            }).start();
        }
    }

    static void respond(String response, PrintWriter out) {
        out.println("HTTP/1.1");
        out.println("Content-type: text/html");
        out.println(); // blank line between headers and content, very important !
        out.println(response);
        out.flush();
        out.close();
    }

}
