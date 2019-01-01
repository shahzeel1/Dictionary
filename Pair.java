/**
 * @Programmer Zeel Shah
 * @Date Nov 18 2018
 * @Student No 250970094
 *
 *This class represents the key attribute of a record in the ordered dictionary. Each key has two parts:
word and type. The types can be text, image or audio
 */
public class Pair {

	private String word; 
	private String type;
	/**A constructor which intializes a new Pair object with the specified word and type
	@param word a string of one or more characters and type which is the type of file we are comparing
	 */
	public Pair(String word, String type)
	{
		this.word = word.toLowerCase();
		this.type = type;
	}

	/**
	 * This method gets the word stored in the Pair object
	 * @return word
	 */
	public String getWord()
	{
		return word;
	}

	/**
	 * This method gets the type stored in the Pair object
	 * @return type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * checks to see if the key is stored in this Pair object
	 * @return 0 if key stored in this Pair, -1 if the key stored in This Pair is smaller
	 * than k and 1 otherwise
	 */
	public int compareTo(Pair k)
	{
		if (k.getWord().equals(word) && type.equals(k.getType()))
		{
			return 0;
		}
		else if (word.compareTo(k.getWord())<0|| (word.equals(k.getWord()) && type.compareTo(k.getType())<0))
		{
			return -1;
		}
		else
			
			return 1;
	}
}
