package my.recommendationoftravel.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Confusion {

    private String adate;
    private String atime;
    private String t1sum1;
    private String t1sum2;
    private String t1sum3;
    private String t1sum4;
    private String t1sumset1;
    private String t1sum5;
    private String t1sum6;
    private String t1sum7;
    private String t1sum8;
    private String t1sumset2;

    private String t2sum1;
    private String t2sum2;
    private String t2sumset1;
    private String t2sum3;
    private String t2sum4;
    private String t2sumset2;

//    public Confusion(){
//        this.adate = IntToString(adate);
//    }
//
//    public String IntToString(String input){
//        StringBuilder builder = new StringBuilder(input);
//        return builder.substring(0,builder.length()-2);
//    }
}
