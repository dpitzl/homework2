/** Exception class for access in empty containers (stacks, queues etc). */
/***************************************************     
 *    Program Title: UnderflowException              *     
 *    Author:  Andrew Pitzl                          *                        
 *     Class: CSCI3320,  Spring 2021                 *      
 *     Assignment #2                                 *    
 *****************************************************/ 
public class UnderflowException extends RuntimeException
{
	/** Construct this exception object. */
	public UnderflowException(String message)
	{
		super(message);
	}
	public UnderflowException()
    {
        super();
    }

}
