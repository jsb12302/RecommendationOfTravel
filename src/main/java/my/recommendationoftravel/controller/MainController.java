package my.recommendationoftravel.controller;


import my.recommendationoftravel.domain.RequestAviationDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

@Controller
public class MainController {
    @GetMapping("/")
    public String mainPage(){
        return "index";
    }

    @GetMapping("/aviationByCountry")
    public String aviationPage(RequestAviationDTO requestAviationDTO){
        return "aviation/aviation";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() throws IOException {

        String key = "XplUqoBtLUwz0itdqZM7MgZSmDXtF7VqrMAd0FmMfwBY9SKGni7Lsq560y3dmegGAcMdR1JYTCrN0fGgOv6z9Q%3D%3D";
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551177/PaxFltSched/getPaxFltSchedArrivals"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+key); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*데이터 행*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("2", "UTF-8")); /*데이터 표출 페이지 수*/
        urlBuilder.append("&" + URLEncoder.encode("lang","UTF-8") + "=" + URLEncoder.encode("K", "UTF-8")); /*언어 구분값 K: 국문, E: 영문, C: 중문, J: 일문*/
        urlBuilder.append("&" + URLEncoder.encode("airport","UTF-8") + "=" + URLEncoder.encode("NRT", "UTF-8")); /*출발지공항(IATA) 코드*/
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
        return "ok";
    }

    @GetMapping("/park")
    @ResponseBody
    public String park() throws IOException {

        String key = "XplUqoBtLUwz0itdqZM7MgZSmDXtF7VqrMAd0FmMfwBY9SKGni7Lsq560y3dmegGAcMdR1JYTCrN0fGgOv6z9Q%3D%3D";
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551177/StatusOfParking/getTrackingParking"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + key); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*데이터 행*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*데이터 표출 페이지 수*/
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
        return "ok";
    }
}
