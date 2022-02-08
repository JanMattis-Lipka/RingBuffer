package ring_buffer;

import java.util.HashMap;

/**
 * This class implements a wrapper for a HashMap to conform to the 
 * standards set in StructureWrapper
 * 
 * @author Jan M. L.
 *
 * @param <T> The type this HashMap should store
 */
public class HashMapWrapped <T> extends StructureWrapper <T>{

	private HashMap<Integer, T> hashMap;
	
	/**
	 * Creates a new ArrayList object with the given size.
	 * 
	 * @param size The size of the new ArrayList
	 */
	public HashMapWrapped(int size) {
		hashMap = new HashMap<Integer, T>(size);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean add(int index, T elem) {
		hashMap.put(index, elem);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean replace(int index, T elem) {
		hashMap.replace(index, elem);
		return true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected T get(int index) {
		return hashMap.get(index);
	}

	
	
}
