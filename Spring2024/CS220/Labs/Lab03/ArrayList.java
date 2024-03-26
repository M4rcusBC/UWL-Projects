import java.util.Arrays;

public class ArrayList<E> {

	private static final int DEFAULT_CAPACITY = 10;
	private E[] data;
	private int size = 0;

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	
	public ArrayList(int length) {
		try {
		data = (E[]) new String[length];
		}catch(Exception e) {
			System.out.println("Exception: " + e);
		}
	}

	public boolean add(String str) {
		// do we need to increase the length of data?
		if (data.length == size) {
			// increase data
			increaseLength();
		}

		// add str
		data[size] = (E) str;
		size++;

		// return true
		return true;
	}

	public void add(int index, String str) {
		// valid index?
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("" + index);
		}

		// valid index

		// do we need to increase the length of data?
		if (data.length == size) {
			// increase data
			increaseLength();
		}

		// shifting
		for (int i = size; i > index; i--) {
			data[i] = data[i - 1];
		}

		// addition
		data[index] = (E) str;
		size++;

	}

	public String remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("" + index);
		}

		// valid index
		String toReturn = (String) data[index];

		// shifting
		for (int i = index; i < size - 1; i++) {
			data[i] = data[i + 1];
		}

		data[size - 1] = null;
		size--;

		return toReturn;
	}

	// remove the first occurrence of str
	public boolean remove(String str) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(str)) {
				remove(i);
				return true;
			}
		}

		return false;
	}

	// increase the length of data by 1.5 times
	private void increaseLength() {
		String[] newArr = new String[(int) Math.ceil(data.length * 1.5)];

		for (int i = 0; i < data.length; i++) {
			newArr[i] = (String) data[i];
		}

		data = (E[]) newArr;
	}

	// increase the length of data to cap
	public void ensureCapacity(int cap) {

		if (cap > data.length) {
			String[] newArr = new String[cap];

			for (int i = 0; i < data.length; i++) {
				newArr[i] = (String) data[i];
			}

			data = (E[]) newArr;
		}
	}

	// trim the length of data to size
	public void trimToSize() {

		String[] newArr = new String[size];

		for (int i = 0; i < size; i++) {
			newArr[i] = (String) data[i];
		}

		data = (E[]) newArr;

	}

	// return the size of data
	public int size() {
		return this.size;
	}

	// return true if data is empty, false otherwise
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	// clear the data - set all elements to null
	public void clear() {
		for (int i = 0; i < size; i++) {
			data[i] = null;
		}
	}

	// return the element at index
	public String get(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("" + index);
		} else {
			return (String) data[index];
		}
	}

	// set the element at index to s
	public String set(int index, String s) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("" + index);
		} else {
			String toReturn = (String) data[index];
			data[index] = (E) s;
			return toReturn;
		}
	}

	// return the index of the first occurrence of s
	public int indexOf(String s) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(s)) {
				return i;
			}
		}
		return -1;
	}

	// return true if s is in the data, false otherwise
	public boolean contains(String s) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(s)) {
				return true;
			}
		}
		return false;
	}

	// print the data and size
	@Override
	public String toString() {
		return "ArrayList [" + "Size=" + this.size + ", Data.length=" + data.length + ", Data=" + Arrays.toString(data)
				+ "]";
	}

}
