
public class MyList{

	private int[] numbers;
	private int size = 0;
	private int capacity = 0;
	
	public MyList(int capacity) {
		numbers = new int[capacity];
		this.capacity = capacity;
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size==0;
	}

	public boolean add(int e) {
		if(size == capacity)
			throw new IllegalStateException("Array is full");
		numbers[size] = e;
		size++;
		return true;
	}


	public int get(int index) {
		if(index < 0||index >= size)
			throw new IndexOutOfBoundsException("Illegal index: "+index);
		return numbers[index];
	}
	public int indexOf(int e) {
		int index = -1;
		for(int i = 0;i<size;i++) {
			if(numbers[i] == e)
				index = i;
		}
		return index;
	}

}
