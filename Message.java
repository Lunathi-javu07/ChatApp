/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.Random;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;

import org.json.JSONObject;


import java.util.Scanner;


/**
 *
 * @author Student
 */
public class Message {
    
    // =========================
    // Part 3:Arrays
    // ========================
    private static ArrayList<String>sentMessages = new ArrayList<>();
    private static ArrayList<String>storedMessages = new ArrayList<>();
    private static ArrayList<String>disregardedMessages = new ArrayList<>();
    private static ArrayList<String>messageHashes = new ArrayList<>();
    private static ArrayList<String>messageIDs = new ArrayList<>();
    private static ArrayList<String>recipients = new ArrayList<>();
    static void displayStoredMessages() {
        if (storedMessages.isEmpty()) {
            System.out.println("No stored messages.");
            return;
        }
        System.out.println("Stored messages:");
        for (String msg : storedMessages) {
            System.out.println(msg);
        }
    }

    static void diplayLongestMessage() {
        String longest = displayLongestMessage();
        if (longest == null || longest.isEmpty()) {
            System.out.println("No stored messages.");
        } else {
            System.out.println("Longest message: " + longest);
        }
    }

    static void displayFullReport() {
        System.out.println(printMessages());
    }
    


    // ========================
    // Part 2:Variables
    // ========================
    private String messageID;
    private String messageHash;
    private String messageText;
    private int numMessages;
    private String recipient;
    
   
    

    // Getters and Setters
    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public int getNumMessages() {
        return numMessages;
    }

    public void setNumMessages(int numMessages) {
        this.numMessages = numMessages;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    // Generate Message ID (10 digits)
    public final void generateMessageID() {
        Random rand = new Random();
        StringBuilder id = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            id.append(rand.nextInt(10));
        }

        this.messageID = id.toString();
    }

    // Check message length
    public String checkMessageLength(String messageText) {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            return "Message exceeds 250 characters";
        }
    }

    // Check recipient format
    public String checkRecipientCell(String recipient) {
        if (recipient != null && recipient.startsWith("+27") && recipient.length() == 12) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted.";
        }
    }

    // Create Message Hash
    public String createMessageHash() {

        String[] words = messageText.split(" ");

        String firstWord = words[0].replaceAll("[^a-zA-Z]", "");
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z]", "");

        String hash = messageID.substring(0, 2) + ":" +
                      numMessages + ":" +
                      (firstWord + lastWord).toUpperCase();

        return hash;
    }

    // Send message options
    public String sentMessage(int option) {
        switch (option) {
            case 1:
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                return "Message successfully stored.";
            default:
                return "Invalid option.";
        }
    }

    // =======================================
    // Part 3: Methods
    // =======================================
     
    // Longest Message
    public static String displayLongestMessage(){
         
         String longest = "";
         
         for(String msg : storedMessages){
             if(msg.length() > longest.length()){
                 longest = msg;
             }
         }
          return longest;
        }
    
    // Search by id
        public static String searchByMessageID(String id){
            for(int i = 0;i<messageIDs.size();i++){
                if(messageIDs.get(i).equals(id)){
                    return sentMessages.get(i);
                }
            }
            
            return"Message not found.";
        }
    //  Search by recipient
        public static String searchByRecipient(String number){
            String result = "";
            for(int i = 0;i<recipients.size();i++){
                if(recipients.get(i).equals(number)){
                    result = sentMessages.get(i);
                }
            }
            
            return result;
        }
    // Delete by hash
        public static String deleteByHash(String hash){
            for(int i = 0;i<messageHashes.size();i++){
                if(messageHashes.get(i).equals(hash)){
                    String msg = sentMessages.get(i);
                    
                    messageHashes.remove(i);
                    sentMessages.remove(i);
                    messageIDs.remove(i);
                    recipients.remove(i);
                    
                    return "Message:" + msg + "successfully deleted.";
                }
            }
            
            return"Hash not found.";
        }
    // Display Report or feedback
        public static String printMessages(){
            String report = "===Message Report===";
            for(int i = 0;i<sentMessages.size();i++){
                report = "Hash:" + messageHashes.get(i)+ "";
                report = "Recipient:"+ recipients.get(i)+ "";
                report ="Message:"+ sentMessages.get(i)+ "";
                report = "";
            }
            return report;
        }

            
        }
         
    
    

