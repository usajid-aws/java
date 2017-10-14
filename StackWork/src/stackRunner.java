import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class stackRunner{

 
Stack<Person> stack1;
Stack<Person> stack2;
Stack<Person> removeStack;


static ArrayList<Person> list;
int floor = 1;
int currentSize = 0;

public stackRunner()
{
	stack1 = new Stack<Person>(); //main stack; repersents people in elevator
	stack2 = new Stack<Person>(); //temporary stack
	removeStack = new Stack<Person>(); // stack to hold people when getting off
	list = new ArrayList<Person>();
	
}

/*
 * keeps track of remove stack, removes from main stack to the remove stack
 * pops from main stack, if person end floor == current  floor,
 * put to remove stack, else put to stack2. after everyone is off, 
 * put stack2 back into stack 1, print removeStack contents
 */


public void resetCheck() {
	Stack<Person> temp = new Stack<Person>();
	while(!stack1.isEmpty())
	{
		Person p = stack1.pop();
		p.checked = false;
		temp.push(p);
	}
	while(!temp.isEmpty())
	{
		stack1.push(temp.pop());
	}
	
	
}

public void removeAtFloor()
{
	if(needsToGetOff())
    {
    	while(!stack1.isEmpty())
    	{
    		Person p = stack1.pop();
    		if(p.getEndLevel() == floor)
    		{
    			addOnetoPrev();
    			removeStack.push(p); 
    			currentSize--;
    			continue;
    		}
    		else
    		{
    			stack2.push(p);
    		}
    	}
    	while(!stack2.isEmpty())
    	{
    		stack1.push(stack2.pop());
    	}
    	printRemoved();
    }
    else
    {
       	return;
    }
	
}

private void addOnetoPrev() {
	Stack<Person> temp = new Stack<Person>();
	while(!stack2.isEmpty())
	{
		Person p = stack2.pop();
		if(!p.checked)
		{
			p.setRemoved(p.getRemoved()+1);
			temp.push(p); p.checked = true;
		}
		
	}
	
	while(!temp.isEmpty())
	{   
		Person p = temp.pop();
		stack2.push(p);
	}
	
}

/*
 * method to check how long loop will run
 * loop will run until every person has gotten on elevator
 */
public boolean go()
{
	
	for(Person p: list)
	{
		if(p.off == false) return true;
		
	}
	return false;
}



private void printRemoved()
{

	if(removeStack.isEmpty()) return;
	else
	{
		System.out.println("Current floor: " + floor);
		while(!removeStack.isEmpty())
		{
			Person p = removeStack.pop();
			int i = p.getRemoved();
			if(p.checked) i--;
			System.out.println(p.getName() + " got off: "+(i) + " times");
			
		}
		if(currentSize == 5)
		{
			System.out.println("elevator full, people wating to get on: ");
			for(int j =0 ; j< list.size(); j++)
			{
				if(list.get(j).off == false)
				System.out.print(list.get(j).getName() + " ");
			}
			
		}
		else 
		{
			System.out.println("elevator not full, eleveator size: "  + currentSize);
			addToStack();
			
		}
		System.out.println("\n\n");
		return;
	}
	
}

/*
 * adds to main stack
 * gets Person item from arrayList, and sets person.off to true,
 * indicating person has gotten on the elevator
 */


public void addToStack()
{
	
	if(currentSize == 5)
	{
		  return;
	}
	else
	{
		while(currentSize<=5)
		{  
			
		    if(currentSize == 5 || list.size() == 0) return;
			for(int i=0; i<list.size(); i++)
			{   
				if(list.get(i).getStartLevel() == floor)
				{
					if(currentSize<5)
					{
						if(i+1==list.size()) 
						{
							stack1.push(list.get(0));
							System.out.println(list.get(0).getName()+" getting on at: "+list.get(0).getStartLevel()+" getting off at: "+ list.get(0).getEndLevel());
							list.remove(0);
							currentSize++;
							return;
						}
						Person p = list.get(i);
						if(p.off == false)
						{
							
							stack1.push(p);
							System.out.println(list.get(i).getName()+" getting on at: "+list.get(i).getStartLevel()+" getting off at: "+ list.get(i).getEndLevel());
							currentSize++;
							p.off = true;
						}
					}
					
					
				}
			
				else if(i+1<list.size()) continue;
				else return;
				
			}
		}
	}
	
}


/*
 * checks if any person has to get off at a certain floor
 * goes through stack1, empties to stack2, when a person is found to have
 * person.endfloor == cuurent floor, stack1 is refilled and true is returned.
 */

public boolean needsToGetOff()
{
	while(!stack1.isEmpty())
	{
		
		Person p = stack1.pop();
		if(p.getEndLevel() == floor)
		{
			if(stack2.isEmpty())
			{
				stack1.push(p);
				return true;
			}
			else
			{
				stack1.push(p);
				while(!stack2.isEmpty())
				{
					stack1.push(stack2.pop());
				}
				return true;
			}
			
		}
		else
		{
			stack2.push(p);
		}
	}
	while(!stack2.isEmpty())
	{
		stack1.push(stack2.pop());
	}
	return false;
}

/*
 * reads in data from File directory
 * seperates line data by ",", ; 
 * name,start floor, end floor
 * gets data, makes new Person, adds to arraylist
 */
public ArrayList<Person> readIn(String filename)
{
	ArrayList<Person> p = new ArrayList<Person>();
	try {
		
		File file = new File(filename);
		FileInputStream stream = new FileInputStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		while(reader.ready())
		{
			String data = reader.readLine();
	        if(!data.startsWith("//")&&data.length()>1)
		    {
	           	String[] arr = data.split(",");
	           	Person person = new Person(arr[0], Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
	           	p.add(person);
	           	
		    }
		}
		


	} catch (Exception e) {
		e.printStackTrace();
		System.exit(0);
	}
	return p;
}



public static void main(String[] args)
{
	
	stackRunner s = new stackRunner();
	
	s.list = s.readIn("C:\\Users\\usana\\Desktop\\work\\java\\personElevator stuff\\StackWork\\src\\names.txt");
	s.addToStack();
	boolean up = true, down=true;
	int i=0, big=s.list.size();
	while(s.go() && !s.stack1.isEmpty())
	{
		i++;
		if(s.floor == 1 )
		{
			up = true;
			down = false;
		}
		if(s.floor == 5)
		{
			down = true;
			up = false; 
		}
		s.removeAtFloor();
		s.resetCheck();
    	if(up) s.floor++;
		else s.floor--;
       	s.addToStack(); 
             	
	}
	
//	if(!s.stack1.isEmpty())
//	{
//		while(!s.stack1.isEmpty())
//		{
//			Person p = s.stack1.pop();
//			int t = p.getRemoved();
//			System.out.println(p.getName() + " got off: "+(t-1) + " times");
//		}
//	}
    
	System.out.println(" \n \n terminating program");
	System.exit(0);	
	
}





}