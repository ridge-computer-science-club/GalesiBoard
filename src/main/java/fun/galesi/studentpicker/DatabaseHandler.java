package fun.galesi.studentpicker;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseHandler
{

	private static final String DATA_LOCATION = "./PeriodData/";

	private final File file;

	public DatabaseHandler(String fileName)
	{
		this.file = new File(DATA_LOCATION, fileName + ".txt");

		try
		{
			// Create the containing directories and file if it does not exist.
			file.getParentFile().mkdirs();
			file.createNewFile();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public ArrayList<String> readLines()
	{
		ArrayList<String> toReturn = new ArrayList<>();
		String line = null;

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file)))
		{
			while ((line = bufferedReader.readLine()) != null)
			{
				System.out.print("Read line: " + line + "\n");
				toReturn.add(line);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return toReturn;
	}


	public String read()
	{
		StringBuilder returnString = new StringBuilder();
		String line = null;
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file)))
		{

			while ((line = bufferedReader.readLine()) != null)
			{
				System.out.print("Read line: " + line + "\n");
				returnString.append(line).append("\n");
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return returnString.toString();
	}

	public void write(String toWrite)
	{
		try (PrintWriter writer = new PrintWriter(new FileWriter(file, true)))
		{
			writer.println(toWrite);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void clear()
	{
		try
		{
			new PrintWriter(file).close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void removeLine(String lineContent)
	{
		try
		{
			List<String> out = Files.lines(file.toPath())
					.filter(line -> !line.contains(lineContent))
					.collect(Collectors.toList());
			Files.write(file.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	public void deleteFile()
	{
		file.delete();
	}

}
