package ring_buffer;

import java.util.HashMap;

/**
 * This class implements a ring buffer using a HashMap as 
 * the underlying data structure to store entries.
 * @author Jan M. L.
 *
 * @param <T> The type of elements that should be stored in the RingBuffer object. 
 */
public class HashRingBuffer<T> extends RingBuffer<T> {
	
	/**
	 * The actual data structure to store entries.
	 */
	private HashMap<Integer, T> ringBuffer;
	
	/**
	 * {@inheritDoc}
	 */
	public HashRingBuffer() {
		super();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SafeVarargs
	public HashRingBuffer(int psize, T...ts) {
		super(psize, ts);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reset() {
		init();
		this.size = this.DEFAULT_SIZE;
		ringBuffer = new HashMap<Integer, T>(this.size);
		this.fill();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reset(int size) {
		if(size <= 0) throw new IllegalArgumentException("New size: " + size);
		
		init();
		this.size = size;
		ringBuffer = new HashMap<Integer, T>(size);
		this.fill();
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fill() {
		for(int i = 0; i < this.size; i++) {
			ringBuffer.put(i, null);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void append(T elem) {
		ringBuffer.replace(this.head,  elem);
		this.head = (this.head + 1) % this.size;
		if(this.currentSize != this.size) {
			this.currentSize++;
			
		}else {
			this.tail = (this.tail + 1) % this.size;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getFirst() {
		if(this.hasNext()) {
			return ringBuffer.get(this.tail);
		}else {
			throw new BufferEmptyException("Buffer Empty");
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getLast() {
		if(this.hasNext()) {
			if(this.head > 0) {
				return ringBuffer.get(this.head - 1);
			}else {
				return ringBuffer.get(this.size - 1);
			}
		}else {
			throw new BufferEmptyException("Buffer Empty");
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void resize(int size) {
		HashRingBuffer<T> auxBuffer = new HashRingBuffer<T>(size); // new ringBuffer instance to copy to
		
		// store the smaller size
		int useSize;
		if(this.currentSize > size) {
			useSize = size;
		}else {
			useSize = this.currentSize;
		}
		
		// append the elements to the auxiliary buffer
		for (int i = 0; i < useSize; i++) {
			T elem = this.getFirst();
			this.dropFirst();
			auxBuffer.append(elem);
		}
		
		// reset this buffer and append all the elements again.
		this.reset(size);
		useSize = auxBuffer.currentSize;
		for (int i = 0; i < useSize; i++) {
			this.append(auxBuffer.getFirst());
			auxBuffer.dropFirst();
		}
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T get(int index) {
		if(index >= this.currentSize || index < 0) {
			throw new IndexOutOfBoundsException("Index not in range");
		}
		return ringBuffer.get((this.tail + index) % this.size);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void set(int index, T elem) {
		if (index >= this.currentSize || index < 0) {
			throw new IndexOutOfBoundsException("Index not valid");
		} else if (elem == null) {
			throw new IllegalArgumentException();
		}
		ringBuffer.replace((this.tail + index) % this.size, elem);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {

		if (obj == null 
			|| !this.getClass().equals(obj.getClass()) 
			|| this.currentSize != ((HashRingBuffer<T>)obj).currentSize 
			|| this.size != ((HashRingBuffer<T>)obj).size) {
			
			return false;
			
		} else {
			if (this.currentSize != 0) {
				for (int i = 0; i < this.currentSize; i++) {
					T elem1 = this.getFirst();
					T elem2 = (T) ((HashRingBuffer<T>)obj).getFirst();
					if (!elem1.equals(elem2)) {
						return false;
					}
					this.dropFirst();
					this.append(elem1);
					((HashRingBuffer<T>)obj).dropFirst();
					((HashRingBuffer<T>)obj).append(elem2);
					
				}
				return true;
			}else {
				return true;
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		String str = new String();
		str = "MAX " + this.size + ": [";
		int index = this.tail;
		for(int i = 0; i < this.currentSize; i++) {
			str = str + ringBuffer.get(index);
			index = (index + 1) % this.size;
			if(i < this.currentSize - 1) {
				str = str + ", ";
			}
		}
		str = str + "]";
		return str;
	}

}
