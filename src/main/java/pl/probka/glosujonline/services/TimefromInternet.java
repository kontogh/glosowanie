package pl.probka.glosujonline.services;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.Date;

@Service
public class TimefromInternet {

    String TIME_SERVER = "time-a.nist.gov";
    NTPUDPClient timeClient;
    InetAddress inetAddress;
    TimeInfo timeInfo;
    long returnTime;
    Date time;

    public TimefromInternet() throws Exception{

        timeClient = new NTPUDPClient();
        inetAddress = InetAddress.getByName(TIME_SERVER);
        timeInfo = timeClient.getTime(inetAddress);
        returnTime = timeInfo.getReturnTime();
        time = new Date(returnTime);
    }
    public int getHour(){
        return time.getHours();
    }

    public String  getTime() {
        String tim = ""+ time.getHours() + " : " +time.getMinutes() + " : "+time.getSeconds();
        return tim;
    }
}
