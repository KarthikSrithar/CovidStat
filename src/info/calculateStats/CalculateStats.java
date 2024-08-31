package info.calculateStats;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import info.getCaseDetails.*;

public class CalculateStats extends GetCaseDetails
{
	//Calculating the COVID affectted percentage and mortality percentage
	public void calculate(HashMap<String, ArrayList<String>> details) throws IOException
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\Tech\\Codes\\CovidStat\\TestResult\\Result.txt"));
		for(Entry<String, ArrayList<String>> entry : details.entrySet())
		{
			int totalPopulation = 0, totalAffected = 0 , totalDeath = 0;
			ArrayList<String> value = entry.getValue();
			for(int i=0; i<value.size(); i++)
			{
				if(i==0)
				{
					totalPopulation = Integer.parseInt(value.get(i));
				}
				else if(i==1)
				{
					totalAffected = Integer.parseInt(value.get(i));
				}
				else
				{
					totalDeath = Integer.parseInt(value.get(i));
				}
			}
			DecimalFormat dFormat = new DecimalFormat("0.00");
			double affectedPercentage = ((double) totalAffected / totalPopulation) * 100;
		    double mortalityPercentage = ((double) totalDeath / totalAffected) * 100;
		    writer.write("Total population in "+entry.getKey()+" is "+totalPopulation+" and the total COVID affected percentage is "+dFormat.format(affectedPercentage)+"% and the mortality percentage is "+dFormat.format(mortalityPercentage)+"%.");
		    writer.newLine();
		}
		writer.close();
		System.out.println("Please check 'Result.txt' file under 'TestResult' folder");
	}
	public static void main (String[] args) throws IOException
	{
		CalculateStats stats = new CalculateStats();
		String[] allCountries = stats.countryDetails("D:\\Tech\\Codes\\CovidStat\\TestData\\CountryDetails");
		HashMap<String, ArrayList<String>> caseDetails = stats.caseDetails(allCountries);
		stats.calculate(caseDetails);
	}
}
