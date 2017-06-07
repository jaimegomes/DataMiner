package br.com.mj.dataminer.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import br.com.mj.dataminer.model.Cliente;

/**
 * Classe que consome o WebService de captura de dados do Prestador
 * 
 * @author Jaime
 *
 */
public class ClientWS {
	
	static Logger log = Logger.getLogger("log");

	public static List<Cliente> getInformacoesClienteWS(String cpf) {
		
		PropertyConfigurator.configure("src/main/resources/log4j.properties");

		try {
			String urlws = "http://ws.consulta.plus/v2/govsc/cadastro/" + cpf
					+ "?apiKey=XlYYdir53yerhDz5sgkgktyudgoLF4o7vde4";

			Client c = Client.create();

			WebResource wr = c.resource(urlws);

			String json = wr.get(String.class);

			Gson gson = new Gson();

			return gson.fromJson(json, new TypeToken<List<Cliente>>() {
			}.getType());

		} catch (Exception e) {
			
			log.error("CPF: " + cpf + " " + e.getMessage());
			System.out.println("CPF: " + cpf + " " + e.getMessage());

			if (e.getMessage().indexOf("Gateway") > 0) {

				log.info("TRATANDO ERRO, REPETINDO CPF " + cpf);
				System.out.println("TRATANDO ERRO, REPETINDO CPF " + cpf);

				getInformacoesClienteWS(cpf);
			}
		}
		return null;

	}

}
