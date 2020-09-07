package com.asijit;


import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class API{
    private static HttpURLConnection connection;

    public String fetchDataFromAPI(String newUrl){

        System.out.println(newUrl);
        StringBuffer sb=new StringBuffer();
        try {
            URL url = new URL(newUrl);

            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int status=connection.getResponseCode();
            System.out.println(status);
            if(status==200){
                Scanner fetch=new Scanner(new InputStreamReader(connection.getInputStream()));

                while(fetch.hasNextLine()){
                    sb.append(fetch.nextLine());
                }

                connection.disconnect();

            }else{
                System.out.println("there is problem");
            }
            System.out.println(sb);
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "error";
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "error";
        }
    }
}