package ring_buffer;

import java.util.ArrayList;

/**
 * This class implements a wrapper for an ArrayList to conform to the 
 * standards set in StructureWrapper
 * 
 * @author Jan M. L.
 *
 * @param <T> The type this ArrayList should store
 */
public class ArrayListWrapped <T> extends StructureWrapper <T>{

	private ArrayList<T> arrayList;
	
	/**
	 * Creates a new ArrayList object with the given size.
	 * 
	 * @param size The size of the new ArrayList
	 */
	public ArrayListWrapped(int size) {
		arrayList = new ArrayList<T>(size);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean add(int index, T elem) {
		arrayList.add(index, elem);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean replace(int index, T elem) {
		arrayList.set(index, elem);
		return true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected T get(int index) {
		return arrayList.get(index);
	}

	
	
}
