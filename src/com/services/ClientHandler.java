/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author khwon
 */
public class ClientHandler implements Runnable{
    private static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;
    public ClientHandler(Socket socket){
        try{
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername = bufferedReader.readLine();
            clientHandlers.add(this);
            
            
        }catch(IOException e){
            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }
    
    public void run(){
        String messageFromClient;
        while(socket.isConnected()){
            try{
                messageFromClient = bufferedReader.readLine();
                broadcastMessage(messageFromClient);
            }catch(IOException e){
                closeEverything(socket,bufferedReader,bufferedWriter);
                break;
            }
        }
    }
    
    public void broadcastMessage(String messageToSend){
        for (ClientHandler clientHandler:clientHandlers){
            try{
                if(!clientHandler.clientUsername.equals(clientUsername)){
                    clientHandler.bufferedWriter.write(messageToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            }
            catch(IOException e){
                closeEverything(socket,bufferedReader,bufferedWriter);
            }
        }
    }
    
    public void removeClientHandler(){
        clientHandlers.remove(this);
        broadcastMessage("Server: "+clientUsername+" has left the chat!");
    }
    public void closeEverything(Socket socket, BufferedReader bufferedReader,BufferedWriter bufferedWriter){
        removeClientHandler();
        try{
            if (bufferedReader != null){
                bufferedReader.close();
            }
            if (bufferedWriter != null){
                bufferedWriter.close();
            }
            if(socket != null){
                socket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
