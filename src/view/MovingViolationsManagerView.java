package view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import controller.Controller;
import model.data_structures.ArregloDinamico;
import model.data_structures.IQueue;
import model.data_structures.MaxColaPrioridad;
import model.data_structures.MaxHeapCP;
import model.vo.LocationVO;
import model.vo.VOMovingViolations;

public class MovingViolationsManagerView 
{
	/**
	 * Constante con el nÃºmero maximo de datos maximo que se deben imprimir en consola
	 */
	public static final int N = 20;

	public void printMenu() {
		System.out.println("---------ISIS 1206 - Estructuras de datos----------");
		System.out.println("---------------------Taller 5----------------------");
		System.out.println("0. Cargar datos del cuatrimestre");
		System.out.println("1. Obtener muestra");
		System.out.println("2. Ver datos de la muestra");
		System.out.println("3. Agregar datos de la muestra en Cola de Prioridad");
		System.out.println("4. Agregar datos de la muestra en HeapMAX");
		System.out.println("5. Eliminar el m�ximo con Cola de Prioridad");
		System.out.println("6. Eliminar el m�ximo con HeapMAX");
		System.out.println("7. Saber las N vias con mas infracciones por Cola de prioridad");
		System.out.println("8. Saber las N vias con mas infracciones por Heap");
		System.out.println("9. Salir");
		System.out.println("Digite el nï¿½mero de opciï¿½n para ejecutar la tarea, luego presione enter: (Ej., 1):");

	}
	public void printDatosMuestra( int nMuestra, Comparable [ ] muestra)
	{
		for ( int i = 0; i<muestra.length && muestra[i] != null; i++){
			LocationVO dato = (LocationVO) muestra[i];
			System.out.println(  dato.toString() );   
		}
	}

	public void printMessage(String mensaje) {
		System.out.println(mensaje);
	}
	public void printElementos( int tam, MaxColaPrioridad <LocationVO> h)
	{
		for ( int i = 0; i<tam ; i++){
			LocationVO dato = h.delMax();
			System.out.println(dato.toString());   
		}
	}
	public void printElementos2( int tam, MaxHeapCP<LocationVO> c)
	{
		for ( int i = 0; i<tam ; i++){
			LocationVO dato = c.delMax();
			System.out.println(dato.toString());   
		}
	}



}
