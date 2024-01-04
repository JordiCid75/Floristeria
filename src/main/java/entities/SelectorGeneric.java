package entities;

import java.util.ArrayList;
import java.util.Scanner;

public class SelectorGeneric {
	static Scanner sc = new Scanner(System.in);
	
	public <TIPO> TIPO selecionarElemento(ArrayList<TIPO> lista)
	{
		lista.forEach((l)->System.out.println(lista.indexOf(l) +" - " + l.toString()));
		System.out.println("Elija una opcion: ");
		int op = sc.nextInt();
		if (op >=0 && op <= lista.size())
		{
			return lista.get(op);
		}
		return null;
	}
}
