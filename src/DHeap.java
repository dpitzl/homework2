import java.util.Scanner;

/***************************************************     
 *   Program Title: DHeap.jav                       *    
 *   Author:  Andrew Pitzl                          *                       
 *   Class: CSCI3320,  Spring 2021                  *       
 *   Assignment #2                                  *    
 ****************************************************/ 
public class DHeap<AnyType extends Comparable<? super AnyType>>
{
	/**
	 * d-value for the d-heap.
	 */
	private int d;
	
    /**
     * Construct the binary heap.
     */
    public DHeap( )
    {
        this( DEFAULT_CAPACITY );
    }

    /**
     * Construct the binary heap.
     * @param capacity the capacity of the binary heap.
     */
    public DHeap( int capacity )
    {
        currentSize = 0;
        array = (AnyType[]) new Comparable[ capacity + 1 ];
    }
    
    /**
     * Construct the binary heap given an array of items.
     */
    public DHeap( AnyType [ ] items , int d)
    {
    	this.d = d;
        currentSize = items.length;
        array = (AnyType[]) new Comparable[ ( currentSize + 2 ) * 11 / 10 ];
        
        int i = 1;
        for( AnyType item : items )
            array[ i++ ] = item;
        buildHeap( );
    }
    
    /**
     * Sets the d-value for the d-heap.
     * @param d, the value for d to be set to. 
     */
    public void setDValue(int d) {
    	this.d = d;
    }
    
    /**
     * Retrieves the d-value for the d-heap.
     * @return d, the d-value.
     */
    public int getDValue() {
    	return d;
    }

    /**
     * Insert into the priority queue, maintaining heap order.
     * Duplicates are allowed.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        if( currentSize == array.length - 1 )
            enlargeArray( array.length * getDValue() + 1 );

            // Percolate up
        int hole = ++currentSize;
        for( ; hole > 1 && x.compareTo( array[ hole / getDValue()] ) < 0; hole /= getDValue() )
            array[ hole ] = array[ hole / getDValue()];
        array[ hole ] = x;
    }


    private void enlargeArray( int newSize )
    {
        AnyType [] old = array;
        array = (AnyType []) new Comparable[ newSize ];
        for( int i = 0; i < old.length; i++ )
            array[ i ] = old[ i ];        
    }
    
    /**
     * Find the smallest item in the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return array[ 1 ];
    }

    /**
     * Remove the smallest item from the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public AnyType deleteMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );

        AnyType minItem = findMin( );
        array[ 1 ] = array[ currentSize-- ];
        percolateDown( 1 );

        return minItem;
    }

    /**
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     */
    private void buildHeap( )
    {
        for( int i = currentSize / getDValue(); i > 0; i-- )
            percolateDown( i );
    }

    /**
     * Test if the priority queue is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return currentSize == 0;
    }

    /**
     * Make the priority queue logically empty.
     */
    public void makeEmpty( )
    {
        currentSize = 0;
    }

    public void print( )
    {
        for( int i = 0; i < currentSize; i++ )
            System.out.printf("%d ",  array[i+1]);
        System.out.println();

    }

    private static final int DEFAULT_CAPACITY = 10;

    private int currentSize;      // Number of elements in heap
    private AnyType [ ] array; // The heap array

    /**
     * Internal method to percolate down in the heap.
     * @param hole the index at which the percolate begins.
     */
    private void percolateDown( int hole )
    {
        int child;
        AnyType tmp = array[ hole ];

        for( ; hole * getDValue() <= currentSize; hole = child )
        {
            child = hole * getDValue();
            if( child != currentSize &&
                    array[ child + 1 ].compareTo( array[ child ] ) < 0 )
                child++;
            if( array[ child ].compareTo( tmp ) < 0 )
                array[ hole ] = array[ child ];
            else
                break;
        }
        array[ hole ] = tmp;
    }

    /***************************************************   
     * FUNCTION  name:main                              *     
     * main driver program                              *                                            
     * OUTPUT :                                         *                 
     * All user interface stuff for input               *        
     * **************************************************/
    public static void main( String [ ] args )
    {
    	Scanner kbd = new Scanner(System.in);
    	System.out.println("Enter heap elements: ");
    	String arrayValues = kbd.nextLine();
    	String[] numString = arrayValues.split(" ");
    	Integer[] numValues = new Integer[numString.length];
    	
    	for (int i = 0; i < numString.length; i++) {
    		numValues[i] = Integer.parseInt(numString[i]);
    	}
    	
    	System.out.println("Enter d: ");
    	int d = kbd.nextInt();
    	@SuppressWarnings("unchecked")
		DHeap<Integer> dheap = new DHeap<Integer>(numValues, d);
    	System.out.print("Output: Heap (d=" + dheap.getDValue() + ") "); 
    	dheap.print();
    	
    	//User interface code
    	boolean terminate = false;
    	while(!terminate) {
    		System.out.println("Press 1) for insert, 2) for deleteMin, 3) for buildHeap with new d value, 4) to quit ");
    		System.out.println("Enter choice: ");
    		int input = kbd.nextInt();
    		switch (input) {
    		case 1:
    			System.out.println("Enter element to insert: ");
    			dheap.insert(kbd.nextInt());
    			System.out.print("Output: Heap (d=" + dheap.getDValue() + ") "); 
    	    	dheap.print();
    	    	break;
    		case 2: 
    			dheap.deleteMin();
    			System.out.print("Output: Heap (d=" + dheap.getDValue() + ") "); 
    	    	dheap.print();
    	    	break;
    		case 3:
    			System.out.println("Enter d: ");
    			d = kbd.nextInt();
    			dheap.setDValue(d);
    			System.out.print("Output: Heap (d=" + dheap.getDValue() + ") "); 
    	    	dheap.print();
    	    	break;
    		case 4:
    			terminate = true;
    			System.out.println("Program Terminated");
    			break;
    		}
    	}
    }
}
