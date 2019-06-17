package fun.galesi.studentpicker;

import java.util.ArrayList;

public class Period {
    private final String periodName;
    private final DatabaseHandler databaseHandler;

    private ArrayList<String> studentNames;

    public Period(String periodName) {
        this.periodName = periodName;
        databaseHandler = new DatabaseHandler(periodName);

        studentNames = databaseHandler.readLines();
    }

    @Override
    public String toString() {
        return periodName;
    }

    public ArrayList<String> getStudentNames() {
        return studentNames;
    }
}
