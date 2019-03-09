package view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import controller.Controller;
import model.data_structures.ArregloDinamico;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.vo.VOMovingViolations;

public class MovingViolationsManagerView 
{
	/**
	 * Constante con el nÃºmero maximo de datos maximo que se deben imprimir en consola
	 */
	public static final int N = 20;
	
	public void printMenu() {
		System.out.println("---------ISIS 1206 - Estructuras de datos----------");
		System.out.println("---------------------Taller 3----------------------");
		System.out.println("0. Cargar datos del cuatrimestre");
		System.out.println("1. Verificar que OBJECTID es en realidad un identificador uÌ�nico");
		System.out.println("2. Consultar infracciones por fecha/hora inicial y fecha/hora final");
		System.out.println("3. Dar FINEAMT promedio con y sin accidente por VIOLATIONCODE");
		System.out.println("4. Consultar infracciones por direccion entre fecha inicial y fecha final");

		
		System.out.println("5. Consultar los tipos de infracciones (VIOLATIONCODE) con su valor (FINEAMT) promedio en un rango dado");
		System.out.println("6. Consultar infracciones donde la cantidad pagada (TOTALPAID) esta en un rango dado. Se ordena por fecha de infraccioÌ�n");
		System.out.println("7. Consultar infracciones por hora inicial y hora final, ordenada ascendentemente por VIOLATIONDESC");
		System.out.println("8. Dado un tipo de infraccioÌ�n (VIOLATIONCODE) informar el (FINEAMT) promedio y su desviacioÌ�n estaÌ�ndar.");

		System.out.println("9. El nuÌ�mero de infracciones que ocurrieron en un rango de horas del diÌ�a. Se define el rango de horas por valores enteros en el rango [0, 24]");
		System.out.println("10. Grafica ASCII con el porcentaje de infracciones que tuvieron accidentes por hora del diÌ�a");
		System.out.println("11. La deuda (TOTALPAID â€“ FINEAMT - PENALTY1 â€“ PENALTY2) total por infracciones que se dieron en un rango de fechas.");
		System.out.println("12. Grafica ASCII con la deuda acumulada total por infracciones");

		
		System.out.println("13. Salir");
		System.out.println("Digite el nï¿½mero de opciï¿½n para ejecutar la tarea, luego presione enter: (Ej., 1):");
		
	}
	
	public void printMessage(String mensaje) {
		System.out.println(mensaje);
	}


	
}
