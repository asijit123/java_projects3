package com.asijit;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        API api=new API();
        Scanner input = new Scanner(System.in);
        System.out.println("Hi! Welcome to MeraIRCTC\n"+
                "1. Enter 1 to find train route\n" +
                "2. Enter 2 to find trains between 2 stations\n" +
                "3. Enter anything to to exit");

        int userInput = input.nextInt();

        if(userInput==1)
        {//fetch the train route
            System.out.println("Enter the train number");
            int trainNO=input.nextInt();

            String response=api.fetchDataFromAPI("https://indianrailapi.com/api/v2/TrainSchedule/apikey/9c0bd43ed1ff3834ac1f709d1aed99bc/TrainNumber/" + trainNO + "/");

            JSONObject parentObject= new JSONObject(response);
            JSONArray jsonArray= parentObject.getJSONArray("Route");
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject childObject= jsonArray.getJSONObject(i);
                System.out.println(childObject.get("StationName")+ "| "+ childObject.get("ArrivalTime")+"| "+ childObject.get("DepartureTime"));
                System.out.println("-------------------------------------------------------");

            }
        }else if(userInput==2){
            //train between stations
            System.out.println("Enter the 1st Station code");
            input.nextLine();
            String station1=input.nextLine();
            System.out.println("Enter the 2nd station code");
            String station2=input.nextLine();
            String url="https://indianrailapi.com/api/v2/TrainBetweenStation/apikey/9c0bd43ed1ff3834ac1f709d1aed99bc/From/" + station1 +"/To/"+ station2;
            System.out.println(url);
            String response=api.fetchDataFromAPI(url);
            System.out.println(response);
            JSONObject parentObject= new JSONObject(response);
            JSONArray jsonArray= parentObject.getJSONArray("Trains");
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject childObject= jsonArray.getJSONObject(i);
                System.out.println(childObject.get("TrainNo")+ "| "+ childObject.get("TrainName")+"| "+ childObject.get("ArrivalTime")+ "| " + childObject.get("DepartureTime"));
                System.out.println("-------------------------------------------------------");

            }


        }
        else
        {
            System.exit(0);
        }



    }
}
