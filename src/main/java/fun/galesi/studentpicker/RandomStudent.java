package fun.galesi.studentpicker;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class RandomStudent {

    private static DatabaseHandler periodDatabaseHandler;
    private static ArrayList<Period> Periods;

    public static ArrayList<String> getPeriodNames() {
        return Periods
                .stream()
                .map(Object::toString).collect(Collectors.toCollection(ArrayList::new));
    }


    public static void main(String[] args) {
        periodDatabaseHandler = new DatabaseHandler("allClasses");

        Periods = periodDatabaseHandler.readLines()
                .stream()
                .map(Period::new).collect(Collectors.toCollection(ArrayList::new));

        addPeriod("3");
        addPeriod("Period 5");

        Periods.get(0).addStudent("Shrey");
        Periods.get(1).addStudent("Not");

        Periods.get(0).addStudent("You");


        Periods.forEach(period -> period.getStudentNames().forEach(System.out::println));
    }

    public static void addPeriod(String periodName) {
        if (!getPeriodNames().contains(periodName))
        {
            periodDatabaseHandler.write(periodName);
            Periods.add(new Period(periodName));
        }
    }

}
