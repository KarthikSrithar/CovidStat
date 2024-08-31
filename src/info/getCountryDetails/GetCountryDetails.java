package info.getCountryDetails;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GetCountryDetails 
{
	//Getting country details from the text file as an input
	public String[] countryDetails(String file) throws FileNotFoundException
	{
		File countryFile = new File(file);
		Scanner sc = new Scanner(countryFile);
		String[] countries = null;
		while(sc.hasNextLine())
		{
			String allCountryList = sc.nextLine();
			if(allCountryList.contains("#"))
			{
				continue;
			}
			else
			{	
				countries = allCountryList.split(",");
			}
		}
		return countries;
	}
}
