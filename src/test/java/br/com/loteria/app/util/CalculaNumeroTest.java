package br.com.loteria.app.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

public class CalculaNumeroTest {
	private Integer[][] sorteado = new Integer[][] { 
		{ 1,  4, 26, 39, 47, 55 },
		{ 5, 10, 12, 22, 28, 46 }, 
		{ 8, 11, 25, 39, 41, 60 },
		{ 9, 11, 27, 46, 51, 53 },
		{ 9, 11, 13, 15, 19, 51 },
		{20, 23, 32, 34, 37, 45 },
		{ 5, 17, 32, 35, 37, 57 }};
			
	@Test
	public void getNumeros() {
		
		Set<Integer> num = new HashSet<>();
		num.add(2); num.add(6); num.add(10); num.add(15); num.add(53); num.add(56);
		boolean condition = true;
		int contador = 0;
		while (condition) {
			contador++;
			Map<Integer, Integer> ranking = montaRanking(sorteado, 60);
			Set<Integer> numerosTop = getNumeros(7, getByRanking(6, 7, ranking));
			System.out.println(numerosTop);
			numerosTop.addAll(num);
			Integer acertos = ((7+6) - numerosTop.size());
			System.out.println("Acertos: " + acertos);
			if(acertos > 4){
				System.out.println("Contador: " + contador);
				condition = false;
			}
			Set<Integer> numeroMeioTop = getNumeros(7, getByRanking(3, 7, ranking));
			System.out.println(numeroMeioTop);
			numeroMeioTop.addAll(num);
			acertos = ((7+6) - numeroMeioTop.size());
			System.out.println("Acertos: " + acertos);
			if(acertos > 4){
				System.out.println("Contador: " + contador);
				condition = false;
			}
			Set<Integer> numeroUmTop = getNumeros(7, getByRanking(1, 7, ranking));
			System.out.println(numeroUmTop);
			numeroUmTop.addAll(num);
			acertos = ((7+6) - numeroUmTop.size());
			System.out.println("Acertos: " + acertos);
			if(acertos > 4){
				System.out.println("Contador: " + contador);
				condition = false;
			}
		}
	}	
		
	public Map<Integer, Integer> montaRanking(Integer[][] sorteios, Integer limite) {
		Map<Integer, Integer> ranking = new HashMap<>();
		for (int i = 1; i <= limite; i++) {
			ranking.put(i, sorteios.length);
		}
		for (int i = 0; i < sorteios.length; i++) {
			Integer[] numeros = sorteios[i];
			for (int j = 0; j < numeros.length; j++) {
				Integer key = numeros[j];
				if(!(ranking.get(key) == sorteios.length) && ranking.get(key) > 0){
					ranking.put(key, 0);
				}
				ranking.put(key, ranking.get(key) - ( sorteios.length - i));
			}
		}
		return ranking;
	}

	public List<Integer> getByRanking(int init, int finale, Map<Integer, Integer> ranking) {
		List<Integer> byRanking = new ArrayList<>();
		Set<Integer> keys =ranking.keySet();
		for (Integer key : keys) {
			if((ranking.get(key) >= init) && (ranking.get(key) <= finale)){
				byRanking.add(key);
			}
		}
		return byRanking;
	}
	
	private Set<Integer> getNumeros(int limite, List<Integer> numeros) {
		SortedSet<Integer> sorteados = new TreeSet<>();
		Random r = new Random();
		while (sorteados.size() < limite ) {
			sorteados.add(numeros.get(r.nextInt(numeros.size())));
		}
		return sorteados;
	}
}
