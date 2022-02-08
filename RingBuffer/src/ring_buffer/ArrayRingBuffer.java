package ring_buffer;

/**
 * This class implements a RingBuffer using an ArrayList as its 
 * underlying data structure.
 * 
 * @author Jan M. L.
 *
 * @param <T> The type of objects this RingBuffer should store.
 */
public class ArrayRingBuffer <T> extends RingBuffer <T> {
	
	/**
	 * Creates a new ArrayRingBuffer object with default size 10.
	 */
	public ArrayRingBuffer() {	
		super();
		structure = new ArrayListWrapped<T>(super.size);
		super.fill();
	}
	
	/**
	 * Creates a new ArrayRingBuffer object with the given size
	 * and appends all the given elements to the ring buffer.
	 * It throws an unchecked IllegalArgumentException if the size 
	 * is not positive.
	 * 
	 * Note that if the amount of elements exceeds the given size, the 
	 * older elements will be discarded as by the principle of a ring buffer.
	 * 
	 * @param size The size of the new ArrayRingBuffer
	 * @param ts The potential elements to add to the ArrayRingBuffer on creation
	 */
	@SafeVarargs
	public ArrayRingBuffer(int size, T...ts) {
		super(size);
		structure = new ArrayListWrapped<T>(super.size);
		super.fill();
		
		if (ts != null) {
			for (T elem : ts) {
				super.append(elem);
			}
		}
		
	}

}
