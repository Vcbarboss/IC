package metric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Medidas {
	
	static String moda = "";
	static String media;
	static String mediana;
	static String min;
	static String max;
	static int maior = 0;
	static int menor = 100000;
	static int posmaior = 0;
	static int posmenor = 0;
	
	public static void main(String[] args) {

		List<Integer> numeros = new ArrayList<>();

		numeros.add(1);
		numeros.add(3);
		numeros.add(3);
		numeros.add(3);
		numeros.add(5);
		numeros.add(5);
		numeros.add(5);
		numeros.add(8);
		numeros.add(9);
		numeros.add(9);
		numeros.add(9);

		calcularMedia(numeros);
		calcularMediana(numeros);
		calcularModa(numeros);
	}
	
	public static String minimo(List<Integer> numeros) {
		

		int total = 0;
		for (int s = 0; s < numeros.size(); s++) {
			
			if (numeros.get(s) < menor) {
				menor = numeros.get(s);
				posmenor = s;
			}

			total += numeros.get(s);
		}
		min = menor + "";
		return min;
	}
	
	public static String maximo(List<Integer> numeros) {
		
		int total = 0;
		for (int s = 0; s < numeros.size(); s++) {
			
			if (numeros.get(s) > maior) {
				maior = numeros.get(s);
				posmaior = s;
			}

			total += numeros.get(s);
		}
		
		max = maior + "";
		return max;
	}

	public static String calcularModa(List<Integer> numeros) {
		Map<Integer, Integer> frequenciaNumeros = new HashMap<>();
		moda = "";
		int maiorFrequencia = 0;

		for (Integer numero : numeros) {

			Integer quantidade = frequenciaNumeros.get(numero);

			if (quantidade == null) {
				quantidade = 1;
			} else {
				quantidade += 1;
			}
			frequenciaNumeros.put(numero, quantidade);

			if (maiorFrequencia < quantidade) {
				maiorFrequencia = quantidade;
			}
		}
		
		System.out.print("A(s) moda(s) é (são) : ");
		for (Integer numeroChave : frequenciaNumeros.keySet()) {
			int quantidade = frequenciaNumeros.get(numeroChave);
			if (maiorFrequencia == quantidade) {
				System.out.print(numeroChave + " ");
				moda += numeroChave + " ";
			}
		}
		System.out.print( "\n ");
		
		return moda.replace(".", ",");
		
		
	}

	public static String calcularMedia(List<Integer> numeros) {

		float resultado = 0.0f;

		for (Integer numero : numeros) {
			resultado += numero;
		}

		float resultadoFinal = resultado / numeros.size();

		System.out.println("A média é " + resultadoFinal);
		
		media = resultadoFinal + "";
		return media.replace(".", ",");
	}

	public static String calcularMediana(List<Integer> numeros) {

		float resultado = 0.0f;

		if (numeros.size() % 2 != 0) {
			int posicaoNumero = ((numeros.size() + 1) / 2) - 1;
			resultado = numeros.get(posicaoNumero);
		} else {
			int posicaoNumero = Math.round((numeros.size() + 1) / 2) - 1;
			resultado = (numeros.get(posicaoNumero) + numeros.get(posicaoNumero + 1)) / 2;
		}

		System.out.println("A Mediana é " + resultado);
		mediana = resultado + "";
		
		return mediana.replace(".", ",");
	}

}