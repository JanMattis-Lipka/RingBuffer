package ring_buffer;

public abstract class StructureWrapper <T> {

	/**
	 * Adds the given element to the data structure at position index.
	 * 
	 * @param index The position the element should be added in
	 * @param elem The element to add
	 * @return True if adding the element was successful
	 */
	protected abstract boolean add(int index, T elem);
	
	/**
	 * Replaces the element at position index with the given element.
	 * 
	 * @param index The position of the element that should be replaced 
	 * @param elem The new element
	 * @return True if replacing was successful
	 */
	protected abstract boolean replace(int index, T elem);
	
	/**
	 * Returns the element at the given position.
	 * 
	 * @param index The position of which to fetch the entry for
	 * @return The element at position index
	 */
	protected abstract T get(int index);
	
}
