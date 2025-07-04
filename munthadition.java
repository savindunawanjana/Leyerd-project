import java.time.LocalDate;

public class AddMonthsWithDate {
    public static void main(String[] args) {
        // Starting date
        LocalDate date = LocalDate.of(2025, 5, 8); // May 8, 2025

        // Add months
        LocalDate newDate = date.plusMonths(6); // Add 6 months

        System.out.println("Original Date: " + date);
        System.out.println("New Date: " + newDate);
    }
}


//=================out put ==============


Original Date: 2025-05-08
New Date: 2025-11-08


