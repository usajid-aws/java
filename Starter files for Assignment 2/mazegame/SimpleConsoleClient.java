package mazegame;

import java.util.Scanner;

import mazegame.boundary.IMazeClient;

public class SimpleConsoleClient implements IMazeClient {
	
	public String getReply (String question)
	{
		System.out.println("\n" + question + " ");
		Scanner in = new Scanner (System.in);
		return in.nextLine();
	}
	
	public void playerMessage (String message)
	{
		System.out.print(message);
		
	}

	@Override
	public String getYorNo(String message) {
		System.out.println("\n" + message + " ");
		Scanner in = new Scanner (System.in);
		String reply = "";
		reply = in.next();
		while(true)
		{			
			if(reply.equalsIgnoreCase("Y")
			  || reply.equalsIgnoreCase("n")) break;
			else
			{
				//reply = in.next();
				System.out.println("Invalid response. " + message);
				reply = in.next();
				continue;
			}
		}
		return reply;
		
	}
}
