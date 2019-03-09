package model.data_structures;

/**
 *Los m�todos de esta clase se tomaron del libro Algorithms de Sedgewick y Wayne
 * @param <T>
 */
public class MaxHeapCP<T extends Comparable<T>> implements ColaDePrioridad<T>
{
	private int numElementos;
	private int tamanoArreglo;
	private T[] heap;
	private T[] elems;

	public MaxHeapCP(int pTamano)
	{
		heap = (T[])new Comparable[pTamano];
		agregar(null);
		numElementos=0;
		tamanoArreglo=pTamano;
	}
	private boolean less(int i, int j)
	{  
		return heap[i].compareTo(heap[j]) < 0;  
	}
	private void exch(int i, int j)
	{ 
		T t = heap[i]; 
		heap[i] = heap[j]; 
		heap[j] = t; 
	}
	private void swim(int k)
	{
		while (k > 1 && less(k/2, k))
		{
			exch(k/2, k);
			k = k/2; 
		}
	}
	private void sink(int k)
	{
		while (2*k <= numElementos)
		{
			int j = 2*k;
			if (j < numElementos && less(j, j+1)){ 
				j++;}
			if (!less(k, j)){
				break;
			}
			exch(k, j);
			k = j;
		} }
	@Override
	public int darNumElementos() {
		return numElementos;
	}

	@Override
	public T delMax() {
		T temp=heap[1];
		exch(1, numElementos--);
		heap[numElementos+1] = null;
		sink(1);
		return temp;
	}

	@Override
	public T max() {
		return heap[1];
	}

	@Override
	public boolean estaVacia() {
		return numElementos==0;
	}

	@Override
	public void agregar(T elemento) {
		 if ( numElementos == tamanoArreglo )
         {  // caso de arreglo lleno (aumentar tamaNo)
              tamanoArreglo = 2 * tamanoArreglo;
              T [ ] copia = heap;
              heap = (T[])new Comparable[tamanoArreglo];
              for ( int i = 0; i < numElementos; i++)
              {
               	 heap[i] = copia[i];
              } 
         }	
         heap[numElementos++] = elemento;
         swim(numElementos);
       
	}


}
