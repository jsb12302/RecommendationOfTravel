package my.recommendationoftravel.service;

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

@Service
public class ConfusionService {

    public void requestConfusionApi(String terminal, String date) throws IOException, InterruptedException {
        StringBuilder urlBuilder = makeUrl(date);

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

    }

    private static StringBuilder makeUrl(String date) throws UnsupportedEncodingException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551177/StatusOfArrivals/getArrivalsCongestion"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=XplUqoBtLUwz0itdqZM7MgZSmDXtF7VqrMAd0FmMfwBY9SKGni7Lsq560y3dmegGAcMdR1JYTCrN0fGgOv6z9Q%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("selectdate","UTF-8") + "=" + URLEncoder.encode(date, "UTF-8")); /*오늘일자(D) ='0', 내일일자(D+1) ='1', default = 0*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답유형 [xml, json] default=xml*/
        return urlBuilder;
    }
}
