import java.util.Arrays;

public class ArrayList<E> {

	private static final int DEFAULT_CAPACITY = 10;
	private E[] data;
	private int size = 0;

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	public ArrayList(int length) {
		data = (E[]) new String[length];
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

	public boolean remove(String str) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(str)) {
				remove(i);
				return true;
			}
		}

		return false;
	}

	private void increaseLength() {
		String[] newArr = new String[(int) Math.ceil(data.length * 1.5)];

		for (int i = 0; i < data.length; i++) {
			newArr[i] = (String) data[i];
		}

		data = (E[]) newArr;
	}

	public void ensureCapacity(int cap) {

		if (cap > data.length) {
			String[] newArr = new String[cap];

			for (int i = 0; i < data.length; i++) {
				newArr[i] = (String) data[i];
			}

			data = (E[]) newArr;
		}
	}

	public void trimToSize() {

		String[] newArr = new String[size];

		for (int i = 0; i < size; i++) {
			newArr[i] = (String) data[i];
		}

		data = (E[]) newArr;

	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	public void clear() {
		for (int i = 0; i < size; i++) {
			data[i] = null;
		}
	}

	public String get(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("" + index);
		} else {
			return (String) data[index];
		}
	}

	public String set(int index, String s) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("" + index);
		} else {
			String toReturn = (String) data[index];
			data[index] = (E) s;
			return toReturn;
		}
	}

	public int indexOf(String s) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(s)) {
				return i;
			}
		}
		return -1;
	}

	public boolean contains(String s) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(s)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "ArrayList [" + "Size=" + this.size + ", Data.length=" + data.length + ", Data=" + Arrays.toString(data)
				+ "]";
	}

}
