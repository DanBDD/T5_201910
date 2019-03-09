package TestsAgregarDelMax;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import model.data_structures.MaxColaPrioridad;
import model.data_structures.MaxHeapCP;

public class Tests extends TestCase{
	protected static final int[] ARREGLO = {350, 383, 105, 233, 140, 266, 356, 236, 80, 360, 221, 241, 130, 244, 352, 446, 18, 98, 97, 396};

	protected MaxColaPrioridad<Integer> cola;
	protected MaxHeapCP<Integer> heap;
	@Before
	public void setUp()
	{
		cola= new MaxColaPrioridad<Integer>();
		heap=new MaxHeapCP<Integer>(1);
		for(int actual: ARREGLO)
		{
			cola.agregar(actual);
//			System.out.println(cola.darNumElementos());
			heap.agregar(actual);
//			System.out.println(heap.darNumElementos());
		}

	}
	@Test
	public void test()
	{
		assertEquals(cola.darNumElementos(), 20);
		assertEquals(heap.darNumElementos(), 20);

	}
	
}

