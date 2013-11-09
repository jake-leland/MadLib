import java.io.*;
import java.util.*;

public class MadLib
{
	public static void main(String[] args)
	{
		// Get the name of the file from the user, catching not found exceptions
		Scanner kb = new Scanner(System.in);
		String fileName;
		File file = null;
		Scanner fileScanner1 = null;
		Scanner fileScanner2 = null;
		while(fileScanner1 == null)
		{
			System.out.print("Filename: ");
			fileName = kb.next();
			file = new File(fileName);
			try
			{
				fileScanner1 = new Scanner(file);
				fileScanner2 = new Scanner(file);
			}
			catch (FileNotFoundException e)
			{
				System.out.println("\nFile does not exist,\ntry again.\n");
			}
		}

		// Get the information from the file.
		// The top line of the file is the title.
		// The next line of the file is a number indicating the number of lines in the mad lib.
		System.out.println();
		String title = fileScanner1.nextLine();
		int numBlanks = fileScanner1.nextInt();
		fileScanner1.next();
		Queue<String> blanks = new LinkedList<String>();
		// Go through the file and pick out all of the blanks, putting them into a queue.
		// The blanks will be indicated with asterisks as such: *noun*, *verb*, *adverb*, etc.
		while(fileScanner1.hasNext())
		{
			String line = fileScanner1.nextLine();
			for(int i=0; i<line.length(); i++)
			{
				if((int)line.charAt(i) == ((int)'*'))
				{
					int beginStar = i;
					i++;
					while((int)line.charAt(i) != ((int)'*'))
					{
						i++;
					}
					int endStar = i;
					blanks.add(line.substring(beginStar+1,endStar));
				}
			}
		}

		// Based on all of the blanks stored in the queue, ask the user to fill them
		// based on what type of word is needed to fill in the blank.
		Queue<String> inserts = new LinkedList<String>();
		while(!blanks.isEmpty())
		{
			System.out.print("Type a " + blanks.remove() + ": ");
			inserts.add(kb.next());
		}

		// Go through the file once again, this time with the intention of filling in the blanks.
		// The file will print just as it is scanned in, but the blanks will be filled with the user's inputs.
		System.out.println("\n");
		System.out.println("	" + title);
		System.out.println();
		fileScanner2.nextLine();
		fileScanner2.nextLine();
		while(fileScanner2.hasNext())
		{
			String line = fileScanner2.nextLine();
			for(int i=0; i<line.length(); i++)
			{
				if((int)line.charAt(i) == ((int)'*'))
				{
					int beginStar = i;
					i++;
					while((int)line.charAt(i) != ((int)'*'))
					{
						i++;
					}
					int endStar = i;
					String insert = inserts.remove();
					line = line.substring(0,beginStar) + insert + line.substring(endStar+1);
					i = beginStar + insert.length();
				}
			}
			System.out.println(line);
		}
	}
}