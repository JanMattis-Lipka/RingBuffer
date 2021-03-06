package ring_buffer;

/**
 * This abstract class implements basic functionality for a ring buffer in which the user
 * stores a set amount of elements constantly overwriting the oldest elements 
 * in said ring buffer if the maximum size has already been reached.
 * 
 * @author Jan M. L.
 * 
 * @param <T> The type of elements that should be stored in the RingBuffer object.
 */
public abstract class RingBuffer <T>{
		
	/* Variables for ring buffer operation:
	 * head:		index where new element gets appended
	 * tail:		index where first element is located
	 * size:		maximum size of this ring buffer
	 * DEFAULT_SIZE: default value for initial constructor
	 * currentSize:	the amount of elements in the ring buffer at a given time
	 */
	protected int head, tail, size, currentSize;
	protected final int DEFAULT_SIZE = 10;
	
	protected StructureWrapper <T> structure;
	
	/**
	 * Initializes a new RingBuffer Object with defaultSize.
	 */
	public RingBuffer() {
		this.reset();
	}
	
	/** 
	 *  Initializes a new RingBuffer Object with given size.
	 *  Throws an unchecked IllegalArgumentException if the
	 *  given size is less or equal to 0.
	 *  
	 * 
	 *  @param psize 	The size a new RingBuffer Object 
	 *  				should be created with.
	 */
	public RingBuffer(int size) {
		if (size <= 0) throw new IllegalArgumentException("Capacity " + size + " is non positive");
		
		this.init();
		this.size = size;
	}
	
	/**
	 * Initializes the ring buffer by setting the correct values
	 * for default operation.
	 */
	protected void init() {
		this.tail = 0;
		this.head = 0;
		this.currentSize = 0;
		this.size = this.DEFAULT_SIZE;
	}
	
	/**
	 * Resets the RingBuffer to default values. 
	 */
	public void reset() {
		this.init();
		this.size = this.DEFAULT_SIZE;
		this.fill();
	}
	
	/**
	 * Resets the RingBuffer to default values and 
	 * changes the size to a given one.
	 * Throws an unchecked IllegalArgumentException if
	 * the given size is invalid meaning less or equal to 0.
	 * @param psize The new size of the RingBuffer.
	 */
	public void reset(int size) {
		if(size <= 0) throw new IllegalArgumentException("New size invalid: " + size);
		
		init();
		this.size = size;
		this.fill();
	}
	
	/**
	 * Fills the RingBuffer with null elements
	 * ensuring functionality.
	 */
	protected void fill() {
		for(int i = 0; i < this.size; i++) {
			structure.add(i, null);
		}
	}
	
	/**
	 * Appends a given element to the ring buffer and 
	 * overwrites the oldest element if the maximum size
	 * has already been hit.
	 * 
	 * @param elem The element to be added.
	 */
	public void append(T elem) {
		structure.replace(this.head,  elem);
		this.head = (this.head + 1) % this.size;
		if(this.currentSize != this.size) {
			this.currentSize++;
			
		}else {
			this.tail = (this.tail + 1) % this.size;
		}
	}
	
	/**
	 * Returns the first element in the ring buffer.
	 * Throws an unchecked BufferEmptyException if 
	 * the buffer does not contain any element.
	 * 
	 * @return The first element in the RingBuffer.
	 */	
	public T getFirst() {
		if(this.hasNext()) {
			return structure.get(this.tail);
		}else {
			throw new BufferEmptyException("Buffer Empty");
		}
	}
	
	/**
	 * Returns the last element in the ring buffer.
	 * Throws an unchecked BufferEmptyException 
	 * if the ring buffer is empty.
	 * 
	 * @return The last element in the ring buffer.
	 */
	public T getLast() {
		if(this.hasNext()) {
			if(this.head > 0) {
				return structure.get(this.head - 1);
			}else {
				return structure.get(this.size - 1);
			}
		}else {
			throw new BufferEmptyException("Buffer Empty");
		}
	}
	
	/**
	 * Removes the first element of the ring buffer.
	 * Throws an unchecked BufferEmptyException if the 
	 * ring buffer does not contain an element.
	 */
	public void dropFirst() {
		if(this.hasNext()) {
			this.tail = (this.tail + 1) % this.size;
			this.currentSize--;
		}else {
			throw new BufferEmptyException("Buffer Empty");
		}
	}
	
	/**
	 * Drops the last element in the ring buffer 
	 * and throws an unchecked BufferEmptyException 
	 * if the ring buffer is empty.
	 * 
	 */
	public void dropLast() {
		if(this.hasNext()) {
			if(this.head > 0) {
				this.head--;
			}else {
				this.head = this.size - 1;
			}
			this.currentSize--;
		}else {
			throw new BufferEmptyException("Buffer Empty");
		}
	}
	
	/**
	 * Returns the entry at the given index in range [0,..., usedSize-1].
	 * Throws an unchecked IndexOutOfBoundsException if the index is not valid.
	 * 
	 * @param index The index of the entry that should be fetched.
	 * @return The element in position index in the RingBuffer.
	 */
	public T get(int index) {
		if(index >= this.currentSize || index < 0) {
			throw new IndexOutOfBoundsException("Index not in range");
		}
		return structure.get((this.tail + index) % this.size);
	}
	
	/**
	 * Sets the entry on the given index position to
	 * the given element. Throws an unchecked IndexOutOfBoundsException
	 * if the index is generally not in range, or is referring to 
	 * a position in the ring buffer that is currently not 
	 * in use.
	 * 
	 * @param index The position to set the entry for.
	 * @param elem The element the entry should be.
	 */
	public void set(int index, T elem) {
		if (index >= this.currentSize || index < 0) {
			throw new IndexOutOfBoundsException("Index not valid");
		} else if (elem == null) {
			throw new IllegalArgumentException();
		}
		structure.replace((this.tail + index) % this.size, elem);
	}
	
	
	/**
	 * Removes a given element from the ring buffer.
	 * If the ring buffer is empty or does not contain the given
	 * element, the function does nothing. If the given boolean
	 * value is true, then all occurrences of said element are 
	 * removed from the ring buffer. If it is false, then only
	 * the first instance of the element will be removed.
	 * 
	 * @param 	elem The element to be removed.
	 * @param 	allOccurrences True if every instance of the given
	 * 			element should be removed from the ring buffer, 
	 *			false if only the first instance of the given element
	 *			should be removed from the ring buffer.
	 */
	public void removeEntry(T elem, boolean allOccurrences) {
		
		int size = this.currentSize;
		boolean done = false;
		if(elem == null) return;
		
		for(int i = 0; i < size; i++) {
			// Get the first element
			T tempElement = this.getFirst();
			this.dropFirst();
			// Check if the element should not be removed
			if(!tempElement.equals(elem)) {
				this.append(tempElement);
			}else {
				// Check if it has already removed one element
				if(done) {
					this.append(tempElement);
				}else {
					// Set the value if it has not removed an entry yet.
					// If all entries should be removed, done will be set to false again
					// signaling the removal of another entry
					done = !allOccurrences;
				}
				
				
			}
		}
	
	}
	
	/**
	 * Removes an entry at the given index. Throws an unchecked
	 * IndexOutOfBoundsException if the index is not in the valid 
	 * range between 0 and usedSize - 1.
	 * 
	 * @param index The index at which to remove the entry.
	 */
	public void removeAtIndex(int index) {
		
		// Invalid Input Handling
		if (index >= this.currentSize || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		final int size = this.currentSize;
		for (int i = 0; i < size; i++) {
			T elem = this.getFirst();
			this.dropFirst();
			if(i != index) {
				this.append(elem);
			}
		}
		
		
	}
	
	/**
	 * Checks if the ring buffer contains a given element.
	 * 
	 * @param elem The element to be checked for
	 * @return True if the ring buffer contains the element, false otherwise
	 */
	public boolean contains(T elem) {
		for (int i = 0; i < this.currentSize; i++) {
			if (this.get(i).equals(elem)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Resizes the ring buffer to the given size while keeping all entries. 
	 * If the new size is smaller than the current maximum size of the 
	 * ring buffer, then the newest elements will be discarded.
	 * 
	 * @param size The new maximum size of the ring buffer.
	 */
	public void resize(int size) {
		ArrayRingBuffer<T> auxBuffer = new ArrayRingBuffer<T>(size); // new ringBuffer instance to copy to
		
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
	 * Checks if a given object equals this object.
	 * It returns true only if both ring buffers are empty, or
	 * if both have the same maximum size and each entry in one ring
	 * is equal to the entry in the other ring at the same position / order.
	 * 
	 * @param obj The object to check for equality.
	 * @return 	True if both objects are equal indicating same size and same
	 * 			elements in the same order, false otherwise.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (obj == null 
				|| !this.getClass().equals(obj.getClass()) 
				|| this.currentSize != ((RingBuffer<T>)obj).currentSize 
				|| this.size != ((RingBuffer<T>)obj).size) 
				{
				
				return false;
				
			} else {
				if (this.currentSize != 0) {
					for (int i = 0; i < this.currentSize; i++) {
						T elem1 = this.getFirst();
						T elem2 = (T) ((RingBuffer<T>)obj).getFirst();
						if (!elem1.equals(elem2)) {
							return false;
						}
						this.dropFirst();
						this.append(elem1);
						((RingBuffer<T>)obj).dropFirst();
						((RingBuffer<T>)obj).append(elem2);
						
					}
					return true;
				}else {
					return true;
				}
			}
	}
	
	/**
	 * Overrides the general toString function. Returns a String for a given 
	 * RingBuffer object.
	 * The String is formated like: MAX {this.size}: [elem1, elem2, ...]
	 * where elem1, elem2, ... are using their in-built toString function. 
	 *
	 * @return 	The String representation of the calling RingBuffer of format
	 * 			"MAX {this.size}: [elem1, elem2, ...]".
	 */
	@Override
	public String toString() {
		String str = new String();
		str = "MAX " + this.size + ": [";
		int index = this.tail;
		for(int i = 0; i < this.currentSize; i++) {
			str = str + structure.get(index);
			index = (index + 1) % this.size;
			if(i < this.currentSize - 1) {
				str = str + ", ";
			}
		}
		str = str + "]";
		return str;
	}
	
	/**
	 *  Returns true if the ring buffer contains at least one element. 
	 *  @return If RingBuffer contains at least one element.
	 */ 
	public boolean hasNext() {
		return this.currentSize > 0;
	}
	
	/**
	 * Returns true if the ring buffer does not contain any elements.
	 * @return If RingBuffer is empty.
	 */
	public boolean isEmpty() {
		return this.currentSize == 0;
	}
	
	/**
	 * Returns the maximum size of the given ring buffer.
	 * @return Maximum size of the RingBuffer.
	 */
	public int getMaxSize() {
		return this.size;
	}
	
	/**
	 * Returns the current used size of the ring buffer.
	 * @return Amount of elements in the RingBuffer.
	 */
	public int getUsedSize() {
		return this.currentSize;
	}
	
	/**
	 * Prints the ring buffer and sets a new line.
	 */
	public void print() {
		System.out.println(this);
	}
}