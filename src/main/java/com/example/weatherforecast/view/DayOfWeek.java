package com.example.weatherforecast.view;

import java.time.LocalDate;

public class DayOfWeek {

    public static String getDayName(LocalDate date){

        switch(date.getDayOfWeek().toString()){
            case "MONDAY":
                return "Poniedziałek";

            case "TUESDAY":
                return "Wtorek";

            case "WEDNESDAY":
                return "Środa";

            case "THURSDAY":
                return "Czwartek";

            case "FRIDAY":
                return "Piątek";

            case "SATURDAY":
                return "Sobota";

            case "SUNDAY":
                return "Niedziela";

            default:
                return "";
        }
    }

}
