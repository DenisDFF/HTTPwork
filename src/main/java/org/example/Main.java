package org.example;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Scanner;

class Main {

    private static final String TEST_URL = "https://jsonplaceholder.typicode.com/users";
    private static final String UPDATE_URL = "https://jsonplaceholder.typicode.com/users/1";
    private static final String DELETE_URL = "https://jsonplaceholder.typicode.com/users/2";
//private static final String TEST_URL = " http://[::1]:3000/posts";
//    private static final String UPDATE_URL = "http://[::1]:3000/posts/1";

    public static void main(String[] args) throws Exception {
        try {
            sendPost();
            sendGET();
            sendDelete();
            sendSearch();
            sendPut();
            sendSearchUserName();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    private static void sendGET() throws IOException {
        URL url = new URL(TEST_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        System.out.println("GET response code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }
    }

    private static void sendPost () throws IOException {

        byte[] request = Files.readAllBytes(new File("user.json").toPath());
        System.out.println(new String(request, StandardCharsets.UTF_8));
        URL url = new URL(TEST_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        OutputStream os = connection.getOutputStream();
        os.write(request);
        os.flush();
        os.close();

        int responseCode = connection.getResponseCode();
        System.out.println("POST response code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_CREATED) {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
        } else {
            System.out.println("POST request not worked");
        }
    }

    private static void sendPut() throws IOException {
        byte[] request = Files.readAllBytes(new File("user.json").toPath());
        System.out.println(new String(request, StandardCharsets.UTF_8));
        URL url = new URL(UPDATE_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setDoOutput(true);
        OutputStream os = connection.getOutputStream();
        os.write(request);
        os.flush();
        os.close();

        int responseCode = connection.getResponseCode();
        System.out.println("PUT response code: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println("Updated object response: " + response.toString());
        } else {
            System.out.println("PUT request not worked");
        }
    }

    private static void sendDelete() throws IOException {
        URL url = new URL(DELETE_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");

        int responseCode = connection.getResponseCode();
        System.out.println("DELETE response code: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
            System.out.println("Resource deleted successfully");
        }
    }

    private static void sendSearch() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Сhoose ID:" );
        int chuseId = scanner.nextInt();

        URL url = new URL("https://jsonplaceholder.typicode.com/users/" + chuseId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        System.out.println("GET response code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
        } else {
            System.out.println("There is no such ID");
        }
    }

    private static void sendSearchUserName() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Сhoose User Name: ");
        String userName = scanner.nextLine();

        URL url = new URL("https://jsonplaceholder.typicode.com/users?username=" + userName);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        System.out.println("GET response code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
        } else {
            System.out.println("There is no such ID");
        }
    }
    }