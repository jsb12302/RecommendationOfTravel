package my.recommendationoftravel.service;

import my.recommendationoftravel.domain.Country;
import my.recommendationoftravel.domain.Parking;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedList;
import java.util.List;

@Service
public class ParkingService {

    private static String parkingTime = "";
    public List<Parking> requestParkingApi() throws IOException, InterruptedException {
        StringBuilder urlBuilder = parkingUrl();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlBuilder.toString()))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> httpResponse = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return returnParkingList(httpResponse.body());
    }

    public List<Parking> returnParkingList(String parkingString){
        JSONObject jsonObject = new JSONObject(parkingString);
        JSONObject response = jsonObject.getJSONObject("response");
        JSONObject body = response.getJSONObject("body");
        JSONArray items = body.getJSONArray("items");

        List<Parking> parkingList = new LinkedList<>();

        for(int i=0; i< items.length(); i++){
            JSONObject item = items.getJSONObject(i);
            String floor = item.getString("floor");
            int parking = Integer.parseInt(item.getString("parking"));
            int parkingArea = Integer.parseInt((item.getString("parkingarea")));
            String dateTime = (item.getString("datetm"));

            Parking parkings = Parking.builder()
                    .floor(floor)
                    .parking(parking)
                    .parkingArea(parkingArea)
                    .dateTime(dateTime).build();
            parkingList.add(parkings);
        }
        parkingTime = parkingList.get(0).getDateTime();
        return parkingList;
    }

    private static StringBuilder parkingUrl() throws UnsupportedEncodingException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551177/StatusOfParking/getTrackingParking"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=XplUqoBtLUwz0itdqZM7MgZSmDXtF7VqrMAd0FmMfwBY9SKGni7Lsq560y3dmegGAcMdR1JYTCrN0fGgOv6z9Q%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("20", "UTF-8")); /*데이터 행*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*데이터 표출 페이지 수*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답유형 [xml, json] default=xml*/
        return urlBuilder;
    }

    public String getParkingTime() {
        String year = parkingTime.substring(0,4);
        String month = parkingTime.substring(4,6);
        String day = parkingTime.substring(6, 8);
        String hour = parkingTime.substring(8, 10);
        String minutes = parkingTime.substring(10,12);
        String standardParkingTime = year + "년 " + month + "월 " + day + "일 " + hour + "시 " + minutes + "분";
        return standardParkingTime;
    }
}
