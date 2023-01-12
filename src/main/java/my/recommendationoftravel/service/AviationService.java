package my.recommendationoftravel.service;

import lombok.extern.slf4j.Slf4j;
import my.recommendationoftravel.domain.Country;
import my.recommendationoftravel.domain.RequestAviationDTO;
import my.recommendationoftravel.service.page.PageResultDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@Service
@Slf4j
public class AviationService {

    static int totalPage = 0;
    public List<Country> requestCountryApi(RequestAviationDTO requestAviationDTO) throws IOException, InterruptedException {
        StringBuilder urlBuilder = makeUrl(requestAviationDTO.getFromMonth(), requestAviationDTO.getToMonth());

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

            if(!(requestAviationDTO.getKeyword().isBlank())){
                if(countries.getCountry().equals(requestAviationDTO.getKeyword())){
                    countryList.add(countries);
                }
                if(countries.getRegion().equals(requestAviationDTO.getKeyword())){
                    countryList.add(countries);
                }
            }
            else{
                countryList.add(countries);
            }

        }
        totalPage = countryList.size();
        List<Country> countries = returnOrder(countryList, requestAviationDTO.getOrder());
        return getPaging(countries, requestAviationDTO.getPage());
    }

    public List<Country> getPaging(List<Country> countries, int pageNum){
        Pageable pageable = PageRequest.of(pageNum - 1,10);
        int pageSize = 0;
        int currentPage = pageable.getPageNumber();
        if(pageable.getPageNumber()==totalPage / pageable.getPageSize()){
            pageSize = totalPage % pageable.getPageSize();
        }
        else{
            pageSize = pageable.getPageSize();
        }
        int startItem = currentPage * 10;
        return countries.subList(startItem, startItem + pageSize);
    }

    public List<Country> returnOrder(List<Country> countries, String order){
        Collections.sort(countries, new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                if(order.equals("ASC")){
                    return o1.getArrFlight() - o2.getArrFlight();
                }
                else{
                    return o2.getArrFlight() - o1.getArrFlight();
                }
            }
        });
        return countries;
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

    public PageResultDTO responsePage(RequestAviationDTO requestAviationDTO) {
        Pageable pageable = PageRequest.of(requestAviationDTO.getPage() - 1,10);
        return new PageResultDTO(pageable, totalPage);
    }
}
