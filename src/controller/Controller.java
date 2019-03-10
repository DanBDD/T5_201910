package controller;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import com.opencsv.CSVReader;
import model.data_structures.ArregloDinamico;
import model.util.Sort;
import model.vo.LocationVO;
import model.vo.VOMovingViolations;
import view.MovingViolationsManagerView;

public class Controller {

	private MovingViolationsManagerView view;
	private Comparable<VOMovingViolations>[] muestra;

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

	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		boolean fin=false;
		Controller controller = new Controller();

		while(!fin)
		{
			view.printMenu();

			int option = sc.nextInt();

			switch(option)
			{
			case 0:
				controller.loadMovingViolations();
				break;
			case 1:
				view.printMessage("Ingrese el tamaño de la muestra:");
				int num = sc.nextInt();
				controller.generarMuestra(num);
			case 2:
				
			case 13:	
				fin=true;
				sc.close();
				break;
			}
		}

	}


	public void loadMovingViolations() {
		try {
			
				CSVReader lectorEnero = new CSVReader(new FileReader(rutaEnero));
				String[] lineaEnero = lectorEnero.readNext();
				while ((lineaEnero = lectorEnero.readNext()) != null) {
					
					String address = lineaEnero[3];
					int addressID = Integer.parseInt(address);
					String location = lineaEnero[2];
					String issueDate = lineaEnero[13];
					
					arreglo.agregar(new VOMovingViolations(issueDate, addressID, location));

				}
				lectorEnero.close();

				CSVReader lectorFebrero = new CSVReader(new FileReader(rutaFebrero));
				String[] lineaFebrero = lectorFebrero.readNext();
				while ((lineaFebrero = lectorFebrero.readNext()) != null) {
					String address = lineaFebrero[3];
					int addressID = Integer.parseInt(address);
					String location = lineaFebrero[2];
					String issueDate = lineaFebrero[13];
					
					arreglo.agregar(new VOMovingViolations(issueDate, addressID, location));
				}
				lectorFebrero.close();

				CSVReader lectorMarzo = new CSVReader(new FileReader(rutaMarzo));
				String[] lineaMarzo = lectorMarzo.readNext();
				while ((lineaMarzo = lectorMarzo.readNext()) != null) {
					String address = lineaMarzo[3];
					int addressID = Integer.parseInt(address);
					String location = lineaMarzo[2];
					String issueDate = lineaMarzo[13];
					
					arreglo.agregar(new VOMovingViolations(issueDate, addressID, location));

				}
				lectorMarzo.close();

				CSVReader lectorAbril = new CSVReader(new FileReader(rutaAbril));
				String[] lineaAbril = lectorAbril.readNext();
				while ((lineaAbril = lectorAbril.readNext()) != null) {
					String address = lineaAbril[3];
					int addressID = Integer.parseInt(address);
					String location = lineaAbril[2];
					String issueDate = lineaAbril[13];
					
					arreglo.agregar(new VOMovingViolations(issueDate, addressID, location));				
				}
				lectorAbril.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public Comparable<LocationVO>[] generarMuestra(int numElems){
		
		muestra = new Comparable[numElems];	
		Comparable<LocationVO> [] res = new Comparable[numElems/2];
		int pos=0;
		int aleatorio = 0;
		while(pos<numElems)
		{
			aleatorio =  ThreadLocalRandom.current().nextInt(0, arreglo.darTamano());
			muestra[pos] = arreglo.darElem(aleatorio);
 			pos++;
		}
		Sort.ordenarMergeSort(muestra, Compa , true);
		return res;
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
