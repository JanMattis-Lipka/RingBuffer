package ring_buffer;


/**
 * This class implements a RingBuffer using a HashMap as its 
 * underlying data structure.
 * 
 * @author Jan M. L.
 *
 * @param <T> The type of objects this RingBuffer should store.
 */
public class HashRingBuffer <T> extends RingBuffer <T> {

	/**
	 * Creates a new HashRingBuffer object with default size 10.
	 */
	public HashRingBuffer() {
		super();
		structure = new HashMapWrapped<T>(super.size);
		super.fill();
	}
	
	/**
	 * Creates a new HashRingBuffer object with the given size
	 * and appends all the given elements to the ring buffer.
	 * It throws an unchecked IllegalArgumentException if the size 
	 * is not positive.
	 * 
	 * Note that if the amount of elements exceeds the given size, the 
	 * older elements will be discarded as by the principle of a ring buffer.
	 * 
	 * @param size The size of the new HashRingBuffer
	 * @param ts The potential elements to add to the HashRingBuffer on creation
	 */
	@SafeVarargs
	public HashRingBuffer(int size, T...ts) {
		super(size);
		structure = new HashMapWrapped<T>(super.size);
		super.fill();
		
		if (ts != null) {
			for (T elem : ts) {
				super.append(elem);
			}
		}
	}
	
}
