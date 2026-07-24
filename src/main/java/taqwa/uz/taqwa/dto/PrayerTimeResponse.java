package taqwa.uz.taqwa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrayerTimeResponse {
    private String code;
    private String status;
    private DataDTO data;

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public DataDTO getData() { return data; }
    public void setData(DataDTO data) { this.data = data; }

    public static class DataDTO {
        private TimingsDTO timings;
        private DateDTO date;

        public TimingsDTO getTimings() { return timings; }
        public void setTimings(TimingsDTO timings) { this.timings = timings; }
        public DateDTO getDate() { return date; }
        public void setDate(DateDTO date) { this.date = date; }
    }

    public static class TimingsDTO {
        @JsonProperty("Fajr")
        private String Fajr;
        @JsonProperty("Sunrise")
        private String Sunrise;
        @JsonProperty("Dhuhr")
        private String Dhuhr;
        @JsonProperty("Asr")
        private String Asr;
        @JsonProperty("Sunset")
        private String Sunset;
        @JsonProperty("Maghrib")
        private String Maghrib;
        @JsonProperty("Isha")
        private String Isha;
        @JsonProperty("Imsak")
        private String Imsak;
        @JsonProperty("Midnight")
        private String Midnight;

        public String getFajr() { return Fajr; }
        public void setFajr(String fajr) { Fajr = fajr; }
        public String getSunrise() { return Sunrise; }
        public void setSunrise(String sunrise) { Sunrise = sunrise; }
        public String getDhuhr() { return Dhuhr; }
        public void setDhuhr(String dhuhr) { Dhuhr = dhuhr; }
        public String getAsr() { return Asr; }
        public void setAsr(String asr) { Asr = asr; }
        public String getSunset() { return Sunset; }
        public void setSunset(String sunset) { Sunset = sunset; }
        public String getMaghrib() { return Maghrib; }
        public void setMaghrib(String maghrib) { Maghrib = maghrib; }
        public String getIsha() { return Isha; }
        public void setIsha(String isha) { Isha = isha; }
        public String getImsak() { return Imsak; }
        public void setImsak(String imsak) { Imsak = imsak; }
        public String getMidnight() { return Midnight; }
        public void setMidnight(String midnight) { Midnight = midnight; }
    }

    public static class DateDTO {
        private String readable;
        private HijriDTO hijri;
        private GregorianDTO gregorian;

        public String getReadable() { return readable; }
        public void setReadable(String readable) { this.readable = readable; }
        public HijriDTO getHijri() { return hijri; }
        public void setHijri(HijriDTO hijri) { this.hijri = hijri; }
        public GregorianDTO getGregorian() { return gregorian; }
        public void setGregorian(GregorianDTO gregorian) { this.gregorian = gregorian; }
    }

    public static class GregorianDTO {
        private String date;
        private String day;
        private MonthDTO month;
        private String year;

        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }
        public String getDay() { return day; }
        public void setDay(String day) { this.day = day; }
        public MonthDTO getMonth() { return month; }
        public void setMonth(MonthDTO month) { this.month = month; }
        public String getYear() { return year; }
        public void setYear(String year) { this.year = year; }
    }

    public static class HijriDTO {
        private String date;
        private String day;
        private MonthDTO month;
        private String year;

        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }
        public String getDay() { return day; }
        public void setDay(String day) { this.day = day; }
        public MonthDTO getMonth() { return month; }
        public void setMonth(MonthDTO month) { this.month = month; }
        public String getYear() { return year; }
        public void setYear(String year) { this.year = year; }
    }

    public static class MonthDTO {
        private Integer number;
        private String en;
        private String ar;

        public Integer getNumber() { return number; }
        public void setNumber(Integer number) { this.number = number; }
        public String getEn() { return en; }
        public void setEn(String en) { this.en = en; }
        public String getAr() { return ar; }
        public void setAr(String ar) { this.ar = ar; }
    }
}
