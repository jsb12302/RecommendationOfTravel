package my.recommendationoftravel.controller;

import my.recommendationoftravel.domain.Country;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedList;
import java.util.List;

@Controller
public class testController {

    @GetMapping("/test")
    public void test() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551177/PassengerNoticeKR/getfPassengerNoticeIKR"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=XplUqoBtLUwz0itdqZM7MgZSmDXtF7VqrMAd0FmMfwBY9SKGni7Lsq560y3dmegGAcMdR1JYTCrN0fGgOv6z9Q%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("selectdate","UTF-8") + "=" + URLEncoder.encode("0", "UTF-8")); /*오늘일자(D) ='0', 내일일자(D+1) ='1', default = 0*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답유형 [xml, json] default=xml*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    }

    @GetMapping("/congestion")
    public void congestion() throws IOException, InterruptedException {
        StringBuilder urlBuilder = makeUrl();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlBuilder.toString()))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> httpResponse = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(httpResponse.body());

//        JSONObject jsonObject = new JSONObject(httpResponse.body());
//        JSONObject response = jsonObject.getJSONObject("response");
//        JSONObject body = response.getJSONObject("body");
//        JSONArray items = body.getJSONArray("items");
//
//        List<Country> countryList = new LinkedList<>();
    }

    private static StringBuilder makeUrl() throws UnsupportedEncodingException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551177/StatusOfArrivals/getArrivalsCongestion"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=XplUqoBtLUwz0itdqZM7MgZSmDXtF7VqrMAd0FmMfwBY9SKGni7Lsq560y3dmegGAcMdR1JYTCrN0fGgOv6z9Q%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*데이터 행*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*데이터 표출 페이지 수*/
        urlBuilder.append("&" + URLEncoder.encode("terno","UTF-8") + "=" + URLEncoder.encode("T1", "UTF-8")); /*터미널 구분값 T1: 제1여객터미널, T2: 제2여객터미널*/
        urlBuilder.append("&" + URLEncoder.encode("airport","UTF-8") + "=" + URLEncoder.encode("FUK", "UTF-8")); /*출발지공항(IATA) 코드*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답유형 [xml, json] default=xml*/
        return urlBuilder;
    }

}
