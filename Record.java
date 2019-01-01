/**
 * @Programmer Zeel Shah
 * @Date Nov 18 2018
 * @Student No 250970094
 * 
 * This class represents a record in the ordered dictionary. Each record consists of two parts: a key
and the data associated with the key. 
 *
 */
public class Record {

	private Pair key;
	private String data;
	/**
	 * The constructor initializes the new record with the specific key and data given
	 * @param key
	 * @param data
	 */
	public Record (Pair key, String data)
	{
		this.key = key;
		this.data = data;
	}
	
	/**
	 * @return the key stored in the record
	 */
	public Pair getKey()
	{
		return key;
	}
	/**
	 * @return the data stored in the record
	 */
	public String getData()
	{
		return data;
	}
}
