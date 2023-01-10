package my.recommendationoftravel.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import my.recommendationoftravel.domain.Country;
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
public class CountryService {

    private ObjectMapper objectMapper = new ObjectMapper();

    public List<Country> requestCountryApi(String fromMonth, String toMonth) throws IOException, InterruptedException {
        StringBuilder urlBuilder = makeUrl(fromMonth, toMonth);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlBuilder.toString()))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> httpResponse = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

//        System.out.println(httpResponse.body());

        JSONObject jsonObject = new JSONObject(httpResponse.body());
        JSONObject response = jsonObject.getJSONObject("response");
        JSONObject body = response.getJSONObject("body");
        JSONArray items = body.getJSONArray("items");

        List<Country> countryList = new LinkedList<>();

        for(int i=0; i< items.length(); i++){
            JSONObject item = items.getJSONObject(i);
            String region = item.getString("region");
            String country = item.getString("country");
            int arrFlight = Integer.parseInt(makeInt(item.getString("arrFlight")));
            int depFlight = Integer.parseInt(makeInt(item.getString("depFlight")));

            Country countries = Country.builder()
                    .region(region)
                    .country(country)
                    .arrFlight(arrFlight)
                    .depFlight(depFlight).build();

            countryList.add(countries);
        }

        return countryList;
    }

    private static String makeInt(String input){
        return input.replace(",", "");
    }

    private static StringBuilder makeUrl(String fromMonth, String toMonth) throws UnsupportedEncodingException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551177/AviationStatsByCountry/getTotalNumberOfFlight"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=XplUqoBtLUwz0itdqZM7MgZSmDXtF7VqrMAd0FmMfwBY9SKGni7Lsq560y3dmegGAcMdR1JYTCrN0fGgOv6z9Q%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("from_month","UTF-8") + "=" + URLEncoder.encode(fromMonth, "UTF-8")); /*YYYYMM*/
        urlBuilder.append("&" + URLEncoder.encode("to_month","UTF-8") + "=" + URLEncoder.encode(toMonth, "UTF-8")); /*YYYYMM*/
        urlBuilder.append("&" + URLEncoder.encode("periodicity","UTF-8") + "=" + URLEncoder.encode("0", "UTF-8")); /*정기=0, 부정기=1, null=전체*/
        urlBuilder.append("&" + URLEncoder.encode("pax_cargo","UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); /*여객기=Y, 화물기=N, null=전체*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답유형 [xml, json] default=xml*/
        return urlBuilder;
    }

}
