package com.chillyfacts.com;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;


public class ttc {

    public static StringBuffer getDirectionInfo(String origin, String destination) throws JSONException, IOException {
        String key = "AIzaSyBhxp9jFOvBz66jwhNqbsVLHttVe65CgyA";
        String url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + origin + "&destination=" + destination + "&mode=transit&key=" + key;
        System.out.println(url);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response;
    }

    public static String[] getDirectionRouteInfo(JSONObject directionInfo) throws JSONException {
        JSONArray routeInfoArray = new JSONArray();
        JSONArray routeLegsArray = new JSONArray();
        JSONArray stepsArray = new JSONArray();
        JSONObject routeInfoObject = new JSONObject();
        JSONObject routeLegsObject = new JSONObject();
        JSONObject departureTimeObject = new JSONObject();
        JSONObject arrivalTimeObject = new JSONObject();
        JSONObject distanceObject = new JSONObject();
        JSONObject durationObject = new JSONObject();
        JSONObject endLocationObject = new JSONObject();
        JSONObject startLocationObject = new JSONObject();
        JSONObject stepDistanceObject = new JSONObject();
        JSONObject stepDurationObject = new JSONObject();
        JSONObject currentStepObject = new JSONObject();

        String durationSecs;
        String departureTimestamp;
        String arrivalTimestamp;
        String departureTime;
        String arriveTime;
        String distanceKm;
        String endLocationAddress;
        String startLocationAddress;

        String[] returnThis = new String[9];
        String[] stepDistance;
        String[] stepDuration;
        String[] instruction;

        routeInfoArray = directionInfo.getJSONArray("routes");
        routeInfoObject = routeInfoArray.getJSONObject(0);
        routeLegsArray = routeInfoObject.getJSONArray("legs");
        routeLegsObject = routeLegsArray.getJSONObject(0);
        departureTimeObject = routeLegsObject.getJSONObject("departure_time");
        arrivalTimeObject = routeLegsObject.getJSONObject("arrival_time");
        distanceObject = routeLegsObject.getJSONObject("distance");
        durationObject = routeLegsObject.getJSONObject("duration");
        endLocationObject = routeLegsObject.getJSONObject("end_location");
        startLocationObject = routeLegsObject.getJSONObject("start_location");
        stepsArray = routeLegsObject.getJSONArray("steps");

        durationSecs = String.valueOf(durationObject.getInt("value"));
        arrivalTimestamp = String.valueOf(arrivalTimeObject.getInt("value"));
        departureTimestamp = String.valueOf(departureTimeObject.getInt("value"));
        arriveTime = arrivalTimeObject.getString("text");
        departureTime = departureTimeObject.getString("text");
        distanceKm = distanceObject.getString("text");
        startLocationAddress = routeLegsObject.getString("start_address");
        endLocationAddress = routeLegsObject.getString("end_address");

        stepsArray = routeLegsObject.getJSONArray("steps");
        stepDistance = new String[stepsArray.length()];
        stepDuration = new String[stepsArray.length()];
        instruction = new String[stepsArray.length()];

        stepDistanceObject = new JSONObject(stepsArray.getJSONObject(0));
        stepDurationObject = new JSONObject(stepsArray.getJSONObject(1));

        for (int i = 0; i < stepsArray.length(); i++) {
            currentStepObject = (stepsArray.getJSONObject(i));
            stepDistanceObject = currentStepObject.getJSONObject("distance");
            stepDistance[i] = stepDistanceObject.getString("text");
        }

        for (int i = 0; i < stepsArray.length(); i++) {
            currentStepObject = (stepsArray.getJSONObject(i));
            stepDurationObject = currentStepObject.getJSONObject("duration");
            stepDuration[i] = stepDurationObject.getString("text");
        }

        for (int i = 0; i < stepsArray.length(); i++) {
            currentStepObject = (stepsArray.getJSONObject(i));
            instruction[i] = currentStepObject.getString("html_instructions");
        }


        returnThis[0] = durationSecs;
        returnThis[1] = arrivalTimestamp;
        returnThis[2] = departureTimestamp;
        returnThis[3] = departureTime;
        returnThis[4] = arriveTime;
        returnThis[5] = distanceKm;
        returnThis[6] = endLocationAddress;
        returnThis[7] = startLocationAddress;
        returnThis[8] = String.valueOf(stepsArray.length());

        return returnThis;
    }

    public static String[][] getDirectionRouteArrayInfo(JSONObject directionInfo) throws JSONException {
        JSONArray routeInfoArray = new JSONArray();
        JSONArray routeLegsArray = new JSONArray();
        JSONArray stepsArray = new JSONArray();
        JSONObject routeInfoObject = new JSONObject();
        JSONObject routeLegsObject = new JSONObject();
        JSONObject departureTimeObject = new JSONObject();
        JSONObject arrivalTimeObject = new JSONObject();
        JSONObject distanceObject = new JSONObject();
        JSONObject durationObject = new JSONObject();
        JSONObject endLocationObject = new JSONObject();
        JSONObject startLocationObject = new JSONObject();
        JSONObject stepDistanceObject = new JSONObject();
        JSONObject stepDurationObject = new JSONObject();
        JSONObject currentStepObject = new JSONObject();

        String durationSecs;
        String departureTimestamp;
        String arrivalTimestamp;
        String departureTime;
        String arriveTime;
        String distanceKm;
        String endLocationAddress;
        String startLocationAddress;

        String[][] returnThis;
        String[] stepDistance;
        String[] stepDuration;
        String[] instruction;

        routeInfoArray = directionInfo.getJSONArray("routes");
        routeInfoObject = routeInfoArray.getJSONObject(0);
        routeLegsArray = routeInfoObject.getJSONArray("legs");
        routeLegsObject = routeLegsArray.getJSONObject(0);
        departureTimeObject = routeLegsObject.getJSONObject("departure_time");
        arrivalTimeObject = routeLegsObject.getJSONObject("arrival_time");
        distanceObject = routeLegsObject.getJSONObject("distance");
        durationObject = routeLegsObject.getJSONObject("duration");
        endLocationObject = routeLegsObject.getJSONObject("end_location");
        startLocationObject = routeLegsObject.getJSONObject("start_location");
        stepsArray = routeLegsObject.getJSONArray("steps");

        durationSecs = String.valueOf(durationObject.getInt("value"));
        arrivalTimestamp = String.valueOf(arrivalTimeObject.getInt("value"));
        departureTimestamp = String.valueOf(departureTimeObject.getInt("value"));
        arriveTime = arrivalTimeObject.getString("text");
        departureTime = departureTimeObject.getString("text");
        distanceKm = distanceObject.getString("text");
        startLocationAddress = routeLegsObject.getString("start_address");
        endLocationAddress = routeLegsObject.getString("end_address");

        stepsArray = routeLegsObject.getJSONArray("steps");
        stepDistance = new String[stepsArray.length()];
        stepDuration = new String[stepsArray.length()];
        instruction = new String[stepsArray.length()];
        returnThis = new String[3][stepsArray.length()];


        stepDistanceObject = new JSONObject(stepsArray.getJSONObject(0));
        stepDurationObject = new JSONObject(stepsArray.getJSONObject(1));

        for (int i = 0; i < stepsArray.length(); i++) {
            currentStepObject = (stepsArray.getJSONObject(i));
            stepDistanceObject = currentStepObject.getJSONObject("distance");
            stepDistance[i] = stepDistanceObject.getString("text");
        }

        for (int i = 0; i < stepsArray.length(); i++) {
            currentStepObject = (stepsArray.getJSONObject(i));
            stepDurationObject = currentStepObject.getJSONObject("duration");
            stepDuration[i] = stepDurationObject.getString("text");
        }

        for (int i = 0; i < stepsArray.length(); i++) {
            currentStepObject = (stepsArray.getJSONObject(i));
            instruction[i] = currentStepObject.getString("html_instructions");
        }

        for (int i = 0; i < stepsArray.length(); i++) {
            returnThis[0][i] = stepDuration[i];
        }

        for (int i = 0; i < stepsArray.length(); i++) {
            returnThis[1][i] = stepDistance[i];
        }

        for (int i = 0; i < stepsArray.length(); i++) {
            returnThis[2][i] = instruction[i];
        }

        return returnThis;
    }

    public static void main(String[] args) throws JSONException, IOException {
        String origin = "bridlewood_mall_toronto";
        String destination = "agincourt_collegiate_institute";
        JSONObject directionInfo = new JSONObject(getDirectionInfo(origin, destination).toString());
        String[] nonArrayDirectionInfo = new String[9];
        nonArrayDirectionInfo = getDirectionRouteInfo(directionInfo);
        String[][] arrayDirectionInfo = new String[3][Integer.parseInt(nonArrayDirectionInfo[8])];
        arrayDirectionInfo = getDirectionRouteArrayInfo(directionInfo);

        String durationInSeconds = nonArrayDirectionInfo[0];
        String arrivalTimeStamp = nonArrayDirectionInfo[1];
        String departureTimeStamp = nonArrayDirectionInfo[2];
        String arrivalTime = nonArrayDirectionInfo[3];
        String departureTime = nonArrayDirectionInfo[4];
        String distanceKm = nonArrayDirectionInfo[5];
        String endLocationAddress = nonArrayDirectionInfo[6];
        String startLocationAddress = nonArrayDirectionInfo[7];

        String[] stepDuration = new String[Integer.parseInt(nonArrayDirectionInfo[8])];
        stepDuration = arrayDirectionInfo[0];
        String[] instructions = new String[Integer.parseInt(nonArrayDirectionInfo[8])];
        instructions = arrayDirectionInfo[2];
        String[] stepDistance = new String[Integer.parseInt(nonArrayDirectionInfo[8])];
        stepDistance = arrayDirectionInfo[1];
    }
}

