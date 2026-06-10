/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class MainApp {
    
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Create Login object
        Login login = new Login();

        System.out.println("=== USER REGISTRATION ===");
        
        System.out.println("Enter your firstname:");
        String firstname;
        firstname = input.nextLine();
        
        System.out.println("Enter your lastname:");
        String lastname;
        lastname = input.nextLine();
        
        String username;
        while (true) {
            System.out.print("Enter a username: ");
            username = input.nextLine();

            if (login.checkUserName(username)) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println("Invalid username. Must contain '_' and be <= 5 characters.");
            }
        }

        String password;
        while (true) {
            System.out.print("Enter a password: ");
            password = input.nextLine();

            if (login.checkPasswordComplexity(password)) {
                System.out.println("Password captured successfully.");
                break;
            } else {
                System.out.println("Password must be 8+ chars, include capital, number, and special char.");
            }
        }

        String phone;
        while (true) {
            System.out.print("Enter SA phone (+27...): ");
            phone = input.nextLine();

            if (login.checkCellPhoneNumber(phone)) {
                System.out.println("Phone number captured successfully.");
                break;
            } else {
                System.out.println("Invalid phone number format.");
            }
        }

        // Register user
        String response = login.registerUser(username, password, phone);
        System.out.println(response);

        // ===== LOGIN =====
        System.out.println("\n=== USER LOGIN ===");

        System.out.print("Enter username: ");
        String loginUsername = input.nextLine();

        System.out.print("Enter password: ");
        String loginPassword = input.nextLine();

        boolean loggedIn = login.loginUser(loginUsername, loginPassword);

        System.out.println(login.returnLoginStatus(loggedIn));

        // ===== PART 2 =====
        if (loggedIn) {
            System.out.println("\n=== WELCOME TO QUICKCHAT ===");

            boolean running = true;

            while (running) {
                System.out.println("\n== CHAT MENU ==");
                System.out.println("1. Send messages");
                System.out.println("2. Show recently sent messages");
                System.out.println("3. Quit");
                System.out.println("4. Store message");

                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {

                    case 1:
                        System.out.print("How many messages do you want to send: ");
                        int numMessages = input.nextInt();
                        input.nextLine();

                        for (int i = 0; i < numMessages; i++) {
                            int messageNumber = i + 1;
                            System.out.println("=== Message " + messageNumber + " ===");
                            System.out.print("Enter recipient number: ");
                            String recipient = input.nextLine();

                            System.out.print("Enter your message: ");
                            String messageTextInput = input.nextLine();

                            Message currentMessage = new Message();

                            System.out.println(currentMessage.checkRecipientCell(recipient));

                            if (messageTextInput.length() > 250) {
                                System.out.println("You have exceeded your limit of 250 characters");
                                continue;
                            }

                            // populate message object
                            currentMessage.setRecipient(recipient);
                            currentMessage.setMessageText(messageTextInput);
                            currentMessage.setNumMessages(messageNumber);

                            // generate ID and hash
                            currentMessage.generateMessageID();
                            String messageHash = currentMessage.createMessageHash();

                            // ask user how to handle the message and send
                            System.out.println("Choose option: 1=Send, 2=Delete, 3=Store");
                            int sendChoice = input.nextInt();
                            input.nextLine();

                            String result = currentMessage.sentMessage(sendChoice);

                            System.out.println(result);
                            System.out.println("Message ID: " + currentMessage.getMessageID());
                            System.out.println("Message Hash: " + messageHash);
                            System.out.println("Recipient: " + currentMessage.getRecipient());
                            System.out.println("Message Text: " + currentMessage.getMessageText());

                        }
                        break;

                    case 2:
                        System.out.println("Coming soon...");
                        break;

                    case 3:
                        System.out.println("Goodbye!");
                        running = false;
                        break;
                        
                    case 4:
                        System.out.println("4) Stored Messages");
                        System.out.println("a) Display all stored messages");
                        System.out.println("b) Display the longest message");
                        System.out.println("c) Search by messageID");
                        System.out.println("d) Search by recipient");
                        System.out.println("e) Delete by message Hash");
                        System.out.println("f) Display full report/feedback");
                        
                      
                        
                        String rawChoice = input.hasNextLine() ? input.nextLine().trim().toLowerCase() : "";
                        char subChoice = rawChoice.isEmpty() ? ' ' : rawChoice.charAt(0);
                        
                        switch(subChoice) {
                            case 'a':
                                System.out.println("a) Display all stored messages");
                                Message.displayStoredMessages(); // return all the " stored messages" in a form a list
                               
                                
                            case 'b':
                                System.out.println("b) Display the longest message");
                                Message.diplayLongestMessage();
                                
                            case 'c':
                                System.out.println("c) Search by messageID");
                                String id = input.nextLine();
                                Message.searchByMessageID(id);

                                
                            case 'd':
                                System.out.println("d) Search by recipient");
                                String recipient = input.nextLine();
                                Message.searchByRecipient(recipient);

                                
                            case 'e':
                                System.out.println("e) Delete by message Hash");
                                String Hash = input.nextLine();
                                Message.deleteByHash(Hash);

                            case 'f':  
                                System.out.println("f) Display full report/feedback");
                                Message.displayFullReport();
                                break;

                        }

                    default:
                        System.out.println("Invalid choice. Please try again.");
                    break;
                }
            }

        } else {
            System.out.println("successfully logged in.");
        }

        input.close();
        
                    
    }              
      
}
    

