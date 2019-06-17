package fun.galesi.studentpicker;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class RandomStudent
{

	private static DatabaseHandler periodDatabaseHandler;
	private static ArrayList<Period> Periods;

	public static Period getPeriodByName(String periodName)
	{
		for (Period period : Periods)
		{
			if (periodName.equals(period.toString()))
			{
				return period;
			}
		}

		System.err.println("Couldn't find a period of name " + periodName);
		return Periods.get(0); // Couldn't find period of that name
	}

	public static ArrayList<String> getPeriodNames()
	{
		return Periods
				.stream()
				.map(Object::toString).collect(Collectors.toCollection(ArrayList::new));
	}


	public RandomStudent()
	{
		periodDatabaseHandler = new DatabaseHandler("allClasses");

		Periods = periodDatabaseHandler.readLines()
				.stream()
				.map(Period::new).collect(Collectors.toCollection(ArrayList::new));
	}

	public static void addPeriod(String periodName)
	{
		for (Period period : Periods)
		{
			if (periodName.equals(period.toString()))
			{
				return;
			}
		}

		periodDatabaseHandler.write(periodName);
		Periods.add(new Period(periodName));
	}

	public static void removePeriod(String periodName)
	{
		int index = getPeriodNames().indexOf(periodName);

		if (index != -1)
		{
			periodDatabaseHandler.removeLine(periodName);
			Periods.get(index).deletePeriod();
			Periods.remove(index);
		}
	}

}
