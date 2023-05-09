package treasureoflife;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.Period;

public class calculateDate {
    public String checkDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        LocalDate present = LocalDate.now();
        LocalDate a100YearsAgo = present.minusYears(100);
        if (date.isBefore(a100YearsAgo)) return "isBefore";
        if(date.isAfter(present)) return "isAfter";
        return ""; // = no error
    }
    public int[] SeperatedDate(String dateStr) throws Exception{
        String inputDate = "03262023";
        int year = Integer.parseInt(inputDate.substring(4));
        int month = Integer.parseInt(inputDate.substring(0, 2));
        int day = Integer.parseInt(inputDate.substring(2, 4));
        
        LocalDate currentDate = LocalDate.now();
        LocalDate inputLocalDate = LocalDate.of(year, month, day);
        
        Period period = Period.between(inputLocalDate, currentDate);
        
        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();
        int date[] = {days , months ,years};
       return date;
    }
    public double yearRemaining (String dateStr){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        LocalDate today = LocalDate.now();
        LocalDate nextAnniversary = date.withYear(today.getYear());

        if (nextAnniversary.isBefore(today) || nextAnniversary.isEqual(today)) {
        nextAnniversary = nextAnniversary.plusYears(1);
        }

        long daysRemaining = ChronoUnit.DAYS.between(today, nextAnniversary);
        long daysInYear = ChronoUnit.DAYS.between(LocalDate.of(today.getYear(), 1, 1), LocalDate.of(today.getYear(), 12, 31));
        double percentRemaining = 100.0 * daysRemaining / daysInYear;
        return percentRemaining ;
    }
    public double[] barsStatus(int[] Date , Double percentRemaining ){//Date = {day , month , year}
        double[] status = new double[10];
        int hasRemaining = 0; //statusbar that not 0 or 100
        for(int i = 0 ; i < Date[2]/*year*//10 ; i++  ){
            status[i] = 100;
            hasRemaining++;
        }
        status[hasRemaining] = percentRemaining;
        return status;
    }
}
