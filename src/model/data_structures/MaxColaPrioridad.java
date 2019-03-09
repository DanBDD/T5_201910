package model.data_structures;

import java.util.Iterator;

public class MaxColaPrioridad <T extends Comparable<T>>implements IQueue<T>, ColaDePrioridad<T>{

	private int numElementos;
	private Nodo<T> primerNodo;

	private Nodo<T> ultimo;
	public MaxColaPrioridad()
	{
		primerNodo=null;
		numElementos=0;
	}
	@Override
	public Iterator<T> iterator() {
		return new Iterador<T>(primerNodo);
	}
	@Override
	public int darNumElementos() {
		return numElementos;
	}
	@Override
	public T delMax() {
		T max=primerNodo.darElem();
		primerNodo=primerNodo.darSiguiente();
		return max;
	}
	@Override
	public T max() {
		return primerNodo.darElem();
	}
	@Override
	public boolean estaVacia() {
		return numElementos==0;
	}
	@Override
	public void agregar(T elemento) {
		if(numElementos==0)
		{
			primerNodo=new Nodo<T>(elemento);
		}
		else
		{
			Nodo<T> actual=primerNodo;
			Nodo<T> nuevo = new Nodo<T>(elemento);
			while(actual.darSiguiente()!=null)
			{
				if(actual.darSiguiente().darElem().compareTo(elemento)>0)
				{
					actual=actual.darSiguiente();
				}
				else
				{
					nuevo.cambiarSiguiente(actual.darSiguiente());
					actual.cambiarSiguiente(nuevo);
				}
			}
		}
		numElementos++;

	}
	@Override
	public boolean isEmpty() {
		return numElementos==0;
	}
	@Override
	public int size() {
		return numElementos;
	}
	@Override
	public void enqueue(T t) {
		Nodo<T> nNode = new Nodo<T>(t);
		if(ultimo != null)
			ultimo.cambiarSiguiente(nNode);
		else
			primerNodo = nNode;
		ultimo = nNode;
		numElementos++;
	}
	@Override
	public T dequeue() {
		T elem = primerNodo.darElem();
		primerNodo=primerNodo.darSiguiente();
		numElementos--;
		return elem;
	}

}