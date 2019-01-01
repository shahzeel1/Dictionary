/**
 * @Programmer Zeel Shah
 * @Date Nov 18 2018
 * @Student No 250970094
 * This class implements the user interface and it contains the main method
 */

import java.io.*;

public class UserInterface {
	public static void main(String[] args)
	{
		String word;
		String data;
		String type;
		int length;
		Record rec;
		Pair key;

		OrderedDictionary dict = new OrderedDictionary();
		try
		{
			// Open the file specified
			FileReader file = new FileReader(args[0]);
			BufferedReader bf = new BufferedReader(file);

			//Read each line figure out the type and input it in the dictionary.
			word = bf.readLine();
			data=bf.readLine();
			
			// while the end of the file isn't reached add the word and the data to the dictionary
			while(word!=null)
			{
				// lower case the word
				word = word.toLowerCase();
				length = data.length();
				
				// check to see what kind of data the "data" is and set the type to associate with it, i.e if it's a .wav file set the type to audio
				if(data.substring(length-3,length).equals("wav")||data.substring(length-3,length).equals("mid"))
				{
					type = "audio";
				}
				else if (data.substring(length-3,length).equals("gif")||data.substring(length-3,length).equals("jpg"))
				{
					type = "image";
				}
				else 
					type = "text";

				//add record to dictionary
				key = new Pair(word,type);
				rec = new Record(key, data);
				dict.put(rec);
				
				word = bf.readLine();
				data = bf.readLine();

			}

			// read the user input
			StringReader keyboard = new StringReader();
			String command;  
			String arr[];
			SoundPlayer player = new SoundPlayer();
			PictureViewer viewer = new PictureViewer();
			boolean validCommand = true;

			// if the command is valid 
			while (validCommand)
			{
				command = keyboard.read("Enter next command: ");
				validCommand = false;
				// split into command and parameters
				arr = command.split("\\s");
				
				//get command
				if(arr[0].equals("get"))
				{
					validCommand=true;
					word = arr[1]; // get the word user is looking for
					//check to see if the word exists in the dictionary
					Pair text = new Pair (word,"text");
					Pair audio = new Pair (word,"audio");
					Pair image = new Pair (word,"image");
					// see how many types there are for the word
					int matches =0;
					
					// get the data if found in dictionary
					if(dict.get(audio)!=null)
					{
						player.play((dict.get(audio).getData()));
						matches++;
					}
					if(dict.get(image)!=null)
					{
						viewer.show(dict.get(image).getData());
						matches++;
					}
					if (dict.get(text)!=null)
					{
						System.out.println(dict.get(text).getData());
						matches++;
					}
					
					// if the word is not in the dictionary get the predecessor and successor of the word and display it
					if (matches ==0)
					{
						System.out.println("Word is not in the dictionary");
						if(dict.predecessor(text)!=null||dict.predecessor(audio)!=null||dict.predecessor(image)!=null)
						{
							System.out.print("The word that immediately preceedes the given word is: ");
							System.out.println(dict.predecessor(text).getKey().getWord());
						}
						else
						{
							System.out.println("There is no word preceding the given word" );
						}

						if(dict.successor(text)!=null||dict.successor(audio)!=null||dict.successor(image)!=null)
						{
							System.out.print("The word that immediately follows the given word is: ");
							System.out.println(dict.successor(text).getKey().getWord());
						}
						else
						{
							System.out.println("There is no word following the given word" );
						}
					}

				}

				
				// delete command
				else if(arr[0].equals("delete"))
				{
					validCommand=true;
					Pair delete = new Pair(arr[1],arr[2]);
					// delete the word from the dictionary
					if(dict.get(delete)!=null)
						dict.remove(delete);
					else 
						System.out.println("No record in the ordered dictionary has key "+ arr[1]+","+arr[2]);
				}

				// put
				else if(arr[0].equals("put"))
				{
					// add the word, type and data in the dictionary if it does not already exist
					validCommand=true;
					Pair put = new Pair(arr[1],arr[2]);
					int lengthArr= arr.length;
					
					// dataPut is used if the data is of type text
					String dataPut =(arr[3]);
					for(int i=4; i<lengthArr; i++)
					{
						dataPut = dataPut +" "+ arr[i];
					}
					
					// add the record to the dictionary
					Record putRec = new Record(put,dataPut);
					if(dict.get(put)==null)
						dict.put(putRec);
					else 
						System.out.println("The record of the given key "+ arr[1]+","+arr[2]+" is already in the dictionary");
				}
				
				// list
				else if (arr[0].equals("list"))
				{
					validCommand=true;
					//audio is lexographically first for all types
					Pair ado = new Pair(arr[1],"audio");
					int numPrinted =0;

					//check if the prefix in the ordered dictionary
					if(dict.get(ado)!=null)
					{
						System.out.println(arr[1]);
					}

					// get the successor of the prefix until there aren't any more words that start with the prefix
					Record prefix = dict.successor(ado);
					while(prefix!=null&&prefix.getKey().getWord().startsWith(arr[1]))
					{
						System.out.println(prefix.getKey().getWord());
						prefix = dict.successor(prefix.getKey());
						numPrinted++;
					}
					
					//if no words start with the prefix let the user know
					if (prefix!=null || numPrinted ==0)
						System.out.println("No words in the ordered dictionary start with the prefix "+ arr[1]);


				}

				// smallest
				else if (arr[0].equals("smallest"))
				{
					//get the first word in the dictionary
					validCommand=true;
					System.out.println(dict.smallest().getKey().getWord()+","+dict.smallest().getKey().getType()+","+dict.smallest().getData());
				}

				//largest
				else if (arr[0].equals("largest"))
				{
					//get the lst word in the dictionary
					validCommand=true;
					System.out.println(dict.largest().getKey().getWord()+","+dict.largest().getKey().getType()+","+dict.largest().getData());
				}
				
				// finish
				else if (arr[0].equals("finish"))
				{
					// close the buffered reader and file
					// then exit the system
					bf.close();
					file.close();
					System.exit(0);
				}
				
				// invalid command option was chosen
				else
				{
					System.out.println("Invalid command entered try again");
				}
			}
			
			

		}

		// Media file is not in the directory
		catch(MultimediaException e)
		{
			System.out.println("The media file does not exist");

		}
		
		// the input file with all the words does not exist
		catch(IOException exception)
		{
			System.out.println("Input file does not exist");
		}
		
		// the parameters of the command is incorrect
		catch (ArrayIndexOutOfBoundsException a)
		{
			System.out.println("Incorrect command parameters");
		}




	}
}