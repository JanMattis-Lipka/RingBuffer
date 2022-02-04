package ring_buffer;

public class BufferEmptyException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5199990542440501389L;

	public BufferEmptyException(String message) {
		super(message);
	}
	
	public BufferEmptyException(Throwable cause) {
		super(cause);
	}
	
	public BufferEmptyException(String message, Throwable cause) {
		super(message, cause);
	}
}
