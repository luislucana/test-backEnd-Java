package br.com.players.service.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.http.HttpStatus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.players.service.vo.LigaJusticaVO;
import br.com.players.service.vo.VingadoresRootVO;

/**
 * Classe utilitaria para obter os arquivos de referencia atraves de requisicoes
 * HTTP.
 * 
 * @author Luis Lucana (luislucana@gmail.com)
 *
 */
public class ReferenceFileUtils {

	public static final String VINGADORES_URL_FILE = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json";
	public static final String LIGA_JUSTICA_URL_FILE = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml";

	public static VingadoresRootVO getCodinomesVingadores() {
		VingadoresRootVO vingadoresRootVO = null;
		String fileContent = null;

		try {
			fileContent = getUrlFileContent(VINGADORES_URL_FILE);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			vingadoresRootVO = gson.fromJson(fileContent, VingadoresRootVO.class);

		} catch (IOException ex) {
			throw new RuntimeException("Não foi possível obter o conteúdo do arquivo de referência: " + VINGADORES_URL_FILE, ex);
		}

		return vingadoresRootVO;
	}

	public static LigaJusticaVO getCodinomesLigaJustica() {
		LigaJusticaVO ligaJusticaVO = null;
		String fileContent = null;

		try {
			fileContent = getUrlFileContent(LIGA_JUSTICA_URL_FILE);

			Serializer serializer = new Persister();
			ligaJusticaVO = serializer.read(LigaJusticaVO.class, fileContent);

		} catch (Exception ex) {
			throw new RuntimeException("Não foi possível obter o conteúdo do arquivo de referência: " + LIGA_JUSTICA_URL_FILE, ex);
		}

		return ligaJusticaVO;
	}

	private static String getUrlFileContent(final String fileUrl) throws IOException {
		String content = null;

		HttpGet httpGet = new HttpGet(fileUrl);
		HttpEntity httpEntity = null;

		try (CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse response = httpClient.execute(httpGet)) {
			
			if (response.getStatusLine().getStatusCode() != HttpStatus.OK.value()) {
				throw new RuntimeException("Não foi possível obter o conteúdo do arquivo de referência: " + fileUrl);
			}

			httpEntity = response.getEntity();
			content = EntityUtils.toString(httpEntity);
		} finally {
			EntityUtils.consume(httpEntity);
		}

		return content;
	}
}
