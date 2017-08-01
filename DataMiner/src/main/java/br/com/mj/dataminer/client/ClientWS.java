package br.com.mj.dataminer.client;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import br.com.mj.dataminer.model.Cliente;
import br.com.mj.dataminer.util.Util;

/**
 * Classe que consome o WebService de captura de dados do Prestador
 * 
 * @author Jaime
 *
 */
public class ClientWS {
	
	public static List<Cliente> getInformacoesClienteWS(String cpf, File arquivoLog) {

		try {
			String urlws = "http://ws.consulta.plus/v2/govsc/cadastro/" + cpf
					+ "?apiKey=9j8588LtIPjopyV6UwjrF12892lI45AEabt2sC7PltAyCBRAXyryiuo0iHODAE8A";

			Client c = Client.create();

			WebResource wr = c.resource(urlws);

			String json = wr.get(String.class);

			Gson gson = new Gson();

			return gson.fromJson(json, new TypeToken<List<Cliente>>() {
			}.getType());

		} catch (Exception e) {
			
			System.out.println("CPF: " + cpf + " " + e.getMessage());
			Util.escreveArquivoLog(arquivoLog , "Erro ao conectar ao WS. "+ e.getMessage());

			if (e.getMessage().indexOf("Gateway") > 0) {

				System.out.println("TRATANDO ERRO, REPETINDO CPF " + cpf);

				getInformacoesClienteWS(cpf, arquivoLog);
			}
		}
		return null;

	}

}
