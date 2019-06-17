package fun.galesi.studentpicker;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Period
{
	private final String periodName;
	private final DatabaseHandler databaseHandler;

	private ArrayList<String> studentNames;

	public Period(String periodName)
	{
		this.periodName = periodName;
		databaseHandler = new DatabaseHandler(periodName);

		studentNames = databaseHandler.readLines();
	}

	@Override
	public String toString()
	{
		return periodName;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof String)
		{
			return this.periodName.equals(obj);
		} else if (obj instanceof Period)
		{
			return this.periodName.equals(((Period) obj).periodName);
		} else
		{
			return false;
		}
	}

	@Override
	public int hashCode() {
		return periodName.hashCode();
	}

	public ArrayList<String> getStudentNames()
	{
		return studentNames;
	}

	public void addStudent(String studentName)
	{
		databaseHandler.write(studentName);
		studentNames.add(studentName);
	}

	public String getRandomStudent()
	{
		return studentNames.get(ThreadLocalRandom.current().nextInt(studentNames.size()));
	}

	public void deletePeriod()
	{
		databaseHandler.deleteFile();
	}

}
