package Spring2024.CS220.Labs.Lab03;

public class ArrayList {

	private static final int DEFAULT_CAPACITY = 10;
	private String[] data;
	private int size = 0;
	
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	public ArrayList(int length) {
		data = new String[length];
	}
	
	public boolean add(String str) {
		// do we need to increase the length of data?
		if(data.length == size) {
			// increase data
			increaseLength();
		}
		
		// add str
		data[size] = str;
		size++;
		
		// return true
		return true;
	}
	
	public void add(int index, String str) {
		//valid index?
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException(""+index);
		}
		
		//valid index
		
		// do we need to increase the length of data?
		if(data.length == size) {
			// increase data
			increaseLength();
		}
		
		//shifting
		for(int i = size; i > index; i--) {
			data[i] = data[i-1];
		}
		
		//addition
		data[index] = str;
		size++;
		
	}
	
	public String remove(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(""+index);
		}
		
		//valid index
		String toReturn = data[index];
		
		//shifting
		for(int i = index; i < size-1; i++) {
			data[i] = data[i+1];
		}
		
		data[size-1] = null;
		size--;
		
		return toReturn;
	}
	
	public boolean remove(String str) {
		for(int i = 0; i < size; i++) {
			if(data[i].equals(str)) {
				remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	private void increaseLength() {
		String[] newArr = new String[(int) Math.ceil(data.length*1.5)];
		
		for(int i = 0; i < data.length; i++) {
			newArr[i] = data[i];
		}
		
		data = newArr;
	}

    public void ensureCapacity(int cap) {

    }

	public void trimToSize() {}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {return true;}

	public void clear() {}

	public String get(int index) {return null;}

	public String set(int index, String s) {return null;}

	public int indexOf(String s) {return 0;}

	public boolean contains(String s) {return true;}
	
}