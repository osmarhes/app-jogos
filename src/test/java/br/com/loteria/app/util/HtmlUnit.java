package br.com.loteria.app.util;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HtmlUnit {
	@Test
	public void homePage() throws Exception {
		// final WebClient webClient = new WebClient();
		try (final WebClient webClient = new WebClient()) {
			final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
			Assert.assertEquals("HtmlUnit – Welcome to HtmlUnit", page.getTitleText());

			final String pageAsXml = page.asXml();
			Assert.assertTrue(pageAsXml.contains("<body class=\"composite\">"));

			final String pageAsText = page.asText();
			Assert.assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
		}
	}

	@Test
	public void xpath() throws Exception {
		try (final WebClient webClient = new WebClient()) {
			final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");

			// get list of all divs
			@SuppressWarnings("unused")
			final List<?> divs = page.getByXPath("//div");

			// get div which has a 'name' attribute of 'John'
			final HtmlDivision div = (HtmlDivision) page.getByXPath("//div[@class='clear']").get(0);
			System.out.println(div);
		}
	}

	@Test
	public void getElements() throws Exception {
		
		try (final WebClient webClient = new WebClient(BrowserVersion.CHROME)) {
			webClient.setCssErrorHandler(new SilentCssErrorHandler());
			final HtmlPage page = webClient
					.getPage("http://loterias.caixa.gov.br/wps/portal/loterias/landing/megasena");
			final HtmlDivision div = page.getHtmlElementById("resultados");
			final HtmlAnchor anchor = (HtmlAnchor) div.getByXPath("//a[@title='Anterior']").get(0);
			final HtmlPage page2 = anchor.click();
			System.out.println(page2.asText());
		}
	}
}
