package controller;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import com.opencsv.CSVReader;
import model.data_structures.ArregloDinamico;
import model.data_structures.Comparaciones;
import model.data_structures.MaxColaPrioridad;
import model.data_structures.MaxHeapCP;
import model.util.Sort;
import model.vo.LocationVO;
import model.vo.VOMovingViolations;
import view.MovingViolationsManagerView;

public class Controller {

	private MovingViolationsManagerView view;
	private Comparable<LocationVO>[] muestra;
	private Comparable<LocationVO>[] copia;
	private MaxColaPrioridad<LocationVO> cola;
	private MaxHeapCP<LocationVO> heap;
	/**
	 * Ruta de archivo CSV Enero.
	 */
	public static final String rutaEnero = "./data/Moving_Violations_Issued_in_January_2018-2.csv";

	/**
	 * Ruta de archivo CSV Febrero.
	 */
	public static final String rutaFebrero = "./data/Moving_Violations_Issued_in_February_2018.csv";

	/**
	 * Ruta de archivo CSV Marzo.
	 */
	public static final String rutaMarzo = "./data/Moving_Violations_Issued_in_March_2018.csv";

	/**
	 * Ruta de archivo CSV Abril.
	 */
	public static final String rutaAbril = "./data/Moving_Violations_Issued_in_April_2018.csv";

	private ArregloDinamico<VOMovingViolations> arreglo;

	public Controller() {
		view = new MovingViolationsManagerView();
		//TODO inicializar pila 
		arreglo=new ArregloDinamico<VOMovingViolations>(160000);
		cola = new MaxColaPrioridad<LocationVO>();
		heap = new MaxHeapCP<LocationVO>();
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		boolean fin=false;
		int nMuestra = 0;
		long startTime = 0;
		long endTime = 0;
		long duration = 0;
		int nDatos = 0;
		while(!fin)
		{
			view.printMenu();

			int option = sc.nextInt();

			switch(option)
			{
			case 0:
				nDatos = this.loadMovingViolations();
				view.printMessage("Datos cargados, total de datos: " + nDatos);
				break;
			case 1:
				view.printMessage("Dar tamaNo de la muestra: ");
				nMuestra = sc.nextInt();
				muestra = this.generarMuestra( nMuestra );
				int tam = muestra.length;
				view.printMessage("Muestra generada, tamano: " + tam);
				break;
			case 2: 
				if ( nMuestra > 0 && muestra != null && muestra.length == nMuestra )
				{    
					view.printDatosMuestra( nMuestra, muestra);
				}
				else
				{
					view.printMessage("Muestra invalida");
				}
				break;
			case 3:
				if ( nMuestra > 0 && muestra != null  )
				{
					System.out.println(muestra.length);
					copia = this.obtenerCopia(muestra);
					startTime = System.currentTimeMillis();
					this.agregarColaPrioridad(copia);
					endTime = System.currentTimeMillis();
					duration = endTime - startTime;
					view.printMessage("Agregar terminado con Cola de Prioridad.");
					view.printMessage("Tiempo en agregar con Cola de Prioridad: " + duration + " milisegundos");

				}
				else
				{
					view.printMessage("Muestra invalida");
				}
				break;

			case 4:
				if ( nMuestra > 0 && muestra != null  )
				{
					System.out.println(muestra.length);
					copia = this.obtenerCopia(muestra);
					startTime = System.currentTimeMillis();
					this.agregarMaxHeap(copia);
					endTime = System.currentTimeMillis();
					duration = endTime - startTime;
					view.printMessage("Agregar terminado con HeapMAX.");
					view.printMessage("Tiempo en agregar con HeapMAX: " + duration + " milisegundos");
				}
				else
				{
					view.printMessage("Muestra invalida");
				}
				break;
			case 5:
				if ( nMuestra > 0 && muestra != null && cola.darNumElementos() > 0 )
				{
					System.out.println(muestra.length);

					copia = this.obtenerCopia(muestra);
					startTime = System.currentTimeMillis();
					this.borrarMaxCola(copia);
					endTime = System.currentTimeMillis();
					duration = endTime - startTime;
					view.printMessage("Eliminar máximo terminado con Cola de Prioridad.");
					view.printMessage("Tiempo en eliminar máximo con Cola de Prioridad: " + duration + " milisegundos");
				}
				else
				{
					view.printMessage("Muestra invalida");
				}
				break;

			case 6:
				System.out.println("Tam muestra "+muestra.length);
				System.out.println("Tam heap " +heap.darNumElementos());
				if ( nMuestra > 0 && muestra != null && heap.darNumElementos() > 0)
				{
					System.out.println(muestra.length);

					copia = this.obtenerCopia(muestra);
					startTime = System.currentTimeMillis();
					this.borrarMaxHeap(copia);
					endTime = System.currentTimeMillis();
					duration = endTime - startTime;
					view.printMessage("Eliminar máximo terminado con HeapMAX.");
					view.printMessage("Tiempo en eliminar máximo con HeapMAX: " + duration + " milisegundos");
				}
				else
				{
					view.printMessage("Muestra invalida");
				}
				break;
			case 7:	
				fin=true;
				sc.close();
				break;
			}
		}

	}


	public int loadMovingViolations() {
		int contador = 0;
		boolean hayNulo = false;
		try {

			CSVReader lectorEnero = new CSVReader(new FileReader(rutaEnero));
			String[] lineaEnero = lectorEnero.readNext();
			while ((lineaEnero = lectorEnero.readNext()) != null) {

				String address = lineaEnero[3];
				int addressID = 0;
				if(address.equals("")){
					addressID = 0;
				}
				else{
					addressID = Integer.parseInt(address);
				}

				String location = lineaEnero[2];
				String issueDate = lineaEnero[13];

				arreglo.agregar(new VOMovingViolations(issueDate, addressID, location));
				contador++;
			}
			lectorEnero.close();

			CSVReader lectorFebrero = new CSVReader(new FileReader(rutaFebrero));
			String[] lineaFebrero = lectorFebrero.readNext();
			while ((lineaFebrero = lectorFebrero.readNext()) != null) {
				String address = lineaFebrero[3];
				int addressID = 0;
				if(address.equals("")){
					addressID = 0;
				}
				else{
					addressID = Integer.parseInt(address);
				}
				String location = lineaFebrero[2];
				String issueDate = lineaFebrero[13];

				arreglo.agregar(new VOMovingViolations(issueDate, addressID, location));
				contador++;

			}
			lectorFebrero.close();

			CSVReader lectorMarzo = new CSVReader(new FileReader(rutaMarzo));
			String[] lineaMarzo = lectorMarzo.readNext();
			while ((lineaMarzo = lectorMarzo.readNext()) != null) {
				String address = lineaMarzo[3];
				int addressID = 0;
				if(address.equals("")){
					addressID = 0;
				}
				else{
					addressID = Integer.parseInt(address);
				}
				String location = lineaMarzo[2];
				String issueDate = lineaMarzo[13];

				arreglo.agregar(new VOMovingViolations(issueDate, addressID, location));
				contador++;

			}
			lectorMarzo.close();

			CSVReader lectorAbril = new CSVReader(new FileReader(rutaAbril));
			String[] lineaAbril = lectorAbril.readNext();
			while ((lineaAbril = lectorAbril.readNext()) != null) {
				String address = lineaAbril[3];
				int addressID = 0;
				if(address.equals("")){
					addressID = 0;
				}
				else{
					addressID = Integer.parseInt(address);
				}
				String location = lineaAbril[2];
				String issueDate = lineaAbril[13];

				arreglo.agregar(new VOMovingViolations(issueDate, addressID, location));				

				contador++;
			}
			lectorAbril.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
		return contador;
	}

	public Comparable<LocationVO>[] generarMuestra(int numElems){

		Comparable<VOMovingViolations>[] temp = new Comparable[numElems];	

		int pos=0;
		int aleatorio = 0;
		while(pos<numElems)
		{
			aleatorio =  ThreadLocalRandom.current().nextInt(0, arreglo.darTamano());
			temp[pos] = arreglo.darElem(aleatorio);
			pos++;
		}
		Sort.ordenarMergeSort(temp, Comparaciones.ADDRESSID.comparador , true);
		muestra = new Comparable[temp.length];
		int numAddressID = 0;
		int pos1 = 0 ;
		for(int j=0; j<temp.length-1;j++){
			VOMovingViolations actual = (VOMovingViolations) temp[j];
			if((actual.darAddressID() - ((VOMovingViolations) temp[j+1]).darAddressID()) == 0){
				numAddressID++;
			}
			else{

				muestra[pos1] = new LocationVO(actual.darAddressID(), actual.darLocation(), numAddressID);
				pos1++;
				numAddressID = 1;
			}

		}
		return muestra;
	}
	public Comparable<LocationVO> [ ] obtenerCopia( Comparable<LocationVO> [ ] muestra)
	{
		Comparable<LocationVO> [ ] copia = new Comparable[ muestra.length ]; 
		for ( int i = 0; i < muestra.length; i++)
		{    copia[i] = muestra[i];    }
		return copia;
	}

	public void agregarColaPrioridad(Comparable<LocationVO>[] arreglo ){

		for(int i=0; i<arreglo.length && arreglo[i]!= null; i++){
			LocationVO actual = (LocationVO) arreglo[i];
			cola.agregar((LocationVO) actual); 
		}
	}
	public void agregarMaxHeap(Comparable<LocationVO>[] arreglo){

		for(int i=0; i<arreglo.length && arreglo[i]!= null; i++){
			LocationVO actual = (LocationVO) arreglo[i];
			heap.agregar((LocationVO) actual); 
		}
	}

	public void borrarMaxCola(Comparable<LocationVO>[] pArreglo){
		for(int i = 0; i<pArreglo.length && pArreglo[i] != null;i++){

			cola.delMax();
		}
	}

	public void borrarMaxHeap(Comparable<LocationVO>[] pArreglo){

		for(int i = 0; i<pArreglo.length && pArreglo[i] != null;i++){

			heap.delMax();
		}
	}

	/**
	 * Convertir fecha a un objeto LocalDate
	 * @param fecha fecha en formato dd/mm/aaaa con dd para dia, mm para mes y aaaa para agno
	 * @return objeto LD con fecha
	 */
	private static LocalDate convertirFecha(String fecha)
	{
		return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}


	/**
	 * Convertir fecha y hora a un objeto LocalDateTime
	 * @param fecha fecha en formato dd/mm/aaaaTHH:mm:ss con dd para dia, mm para mes y aaaa para agno, HH para hora, mm para minutos y ss para segundos
	 * @return objeto LDT con fecha y hora integrados
	 */
	private static LocalDateTime convertirFecha_Hora_LDT(String fechaHora)
	{
		return LocalDateTime.parse(fechaHora, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'.000Z'"));
	}


	public LocalTime darHora(String fecha){
		return convertirFecha_Hora_LDT(fecha).toLocalTime();
	}
}
