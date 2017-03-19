import org.apache.commons.lang3.math.NumberUtils;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Program wykonujacy dzialania matematyczne, który nie bierze pod uwagê kolejnoœci wykonywania dzia³añ.
 * @author      Piotrek Mazur
 * @version     1.3
 */

public class Calc implements Calculator {
	
	double wynik = 0;
	
	@Override
	public double licz(String dane) {
		
		StringTokenizer s = new StringTokenizer(dane, "[\\+\\-\\*\\/]", true);

		double liczba;
		Operacje dzialanie = null;
		
		while (s.hasMoreElements()) {
			
			String nextToken = s.nextToken();
			
			if (NumberUtils.isCreatable(nextToken)) {
				liczba = NumberUtils.toDouble(nextToken);
				if (dzialanie != null) {
					oblicz(dzialanie, liczba);
				} else {
					wynik = liczba;
				}
			} 
			else {
				dzialanie = jakieDzialanie(nextToken);
			}
		}
		return this.wynik;
	}
	
	/**
	 * Metoda wykonuj¹ca obliczenia
	 * @param dzialanie
	 * @param liczba
	 */
	
	private void oblicz(Operacje dzialanie, double liczba) {
		
		switch (dzialanie) {
		case DODAJ:
			this.wynik += liczba;
			break;
		case ODEJMIJ:
			this.wynik -= liczba;
			break;
		case MNOZ:
			this.wynik *= liczba;
			break;
		case DZIEL:
			this.wynik /= liczba;
			break;
		}

	}
	
	/**
	 * Metoda, która wskazuje jak¹ operacjê bêdziemy wykonywaæ
	 * @param nextToken
	 * @return operator
	 */
	
	private Operacje jakieDzialanie(String nextToken) {
		
		Operacje operator = null;
		
		switch (nextToken) {
		case "+":
			operator = Operacje.DODAJ;
			break;
		case "-":
			operator = Operacje.ODEJMIJ;
			break;
		case "*":
			operator = Operacje.MNOZ;
			break;
		case "/":
			operator = Operacje.DZIEL;
			break;
		}
		return operator;
	}
	
	/**
	 * Metoda, wyswietlaj¹ca menu g³ówne programu
	 * @throws FileNotFoundException
	 * @author Piotrek Mazur
	 */
	@SuppressWarnings("resource")
	public static void menu() throws FileNotFoundException{{
		
		Scanner in= new Scanner (System.in);
		System.out.println("===========\nMENU G£ÓWNE\n===========");
		System.out.println();
		System.out.println("1. Operacje z klawiatury");
		System.out.println("2. Operacje z pliku");
		System.out.println();
		System.out.println("0. Zamknij program");
		System.out.println();
		System.out.print("Opcja: ");
		
		int to_do = in.nextInt();
		
		if (to_do == 1){
			
			in.nextLine();
			String dzialanie="";
			
			System.out.println("Aby zakoñczyæ wpisz 'koniec' !");
			
			while(!dzialanie.equals("koniec")){
				
			Calculator calc = new Calc();
			System.out.print("Podaj dzia³anie: ");
			dzialanie = in.nextLine();
			Double wynik = calc.licz(dzialanie);
			System.out.println(dzialanie + "=" + wynik);
			}
			if (dzialanie.equals("koniec")){
				menu();
			}
		}
		if (to_do == 2){
			
			in.nextLine();
			String dzialanie="";
			String nazwa;
			Calculator calc = new Calc();
			System.out.print("Podaj nazwe pliku: ");
			nazwa = in.nextLine();
			
			File file = new File(nazwa);
			Scanner ff = new Scanner(file);
			
			while (ff.hasNext()){
					dzialanie = ff.nextLine();
					Double wynik = calc.licz(dzialanie);
					System.out.println(dzialanie + "=" + wynik);
			}
					
			menu();
		}
	}
	}

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	
	public static void main(String[] args) throws FileNotFoundException {
		
		System.out.println("KALKULATOR v1.3");
		// OTWIERAMY MENU
		menu();
		System.out.println("Dziêkujemy za skorzystanie z naszego programu !");
	
	}
}