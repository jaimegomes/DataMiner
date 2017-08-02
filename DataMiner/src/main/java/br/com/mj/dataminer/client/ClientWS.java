package br.com.mj.dataminer.client;

import java.io.File;
import java.util.List;

import br.com.mj.dataminer.model.Cliente;
import br.com.mj.dataminer.util.Util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * Classe que consome o WebService de captura de dados do Prestador
 * 
 * @author Jaime
 * 
 */
public class ClientWS {

	public static List<Cliente> getInformacoesClienteWS(String cpf,
			File arquivoLog) {
		Client c = null;
		try {
			String urlws = "http://ws.consulta.plus/v2/govsc/cadastro/"
					+ cpf
					+ "?apiKey=9j8588LtIPjopyV6UwjrF12892lI45AEabt2sC7PltAyCBRAXyryiuo0iHODAE8A";

			c = Client.create();
			c.setConnectTimeout(15000);
			c.setReadTimeout(15000);

			WebResource wr = c.resource(urlws);

			String json = wr.get(String.class);

			Gson gson = new Gson();

			return gson.fromJson(json, new TypeToken<List<Cliente>>() {
			}.getType());

		} catch (Exception e) {

			System.out.println("CPF: " + cpf + " " + e.getMessage());
			Util.escreveArquivoLog(arquivoLog,
					"Erro ao conectar ao WS. " + e.getMessage());

			if (e.getMessage().indexOf("Gateway") > 0) {

				System.out.println("TRATANDO ERRO, REPETINDO CPF " + cpf);
				Util.escreveArquivoLog(arquivoLog,"TRATANDO ERRO, REPETINDO CPF: " + cpf);
				getInformacoesClienteWS(cpf, arquivoLog);
			}
		} finally {
			if (c != null) {
				c.destroy();
			}
		}
		return null;

	}
}
