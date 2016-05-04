package br.com.loteria.app.util;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class TestXml {

	@Test
	public void testFileXml() throws IOException{
		File file = new File("D:\\Java\\loterias\\mega\\D_MEGA.HTM");
		Document doc =  Jsoup.parse(file, "UTF-8");
		
		Element raiz = doc.body();
		
		Elements list = raiz.select("tr");
		
		for (Element element : list) {
			Elements td  = element.select("td");
			for (Element dados : td) {
				System.out.print(dados.text() + " | ");
			}
			System.out.println("\n_____________________________________________________________________________________________________________________________");
		}
	}
	
	@Test
	public void testSiteXml() throws IOException{
		Document doc = Jsoup.connect("http://loterias.caixa.gov.br/wps/portal/loterias/landing/megasena/").get();
		
		Element raiz = doc.body();

		Element div = raiz.getElementById("resultados");
		
		System.out.println(div.text());
	}
	
	
}
