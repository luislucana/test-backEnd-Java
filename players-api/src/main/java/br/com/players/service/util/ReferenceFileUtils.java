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
 * 
 * 
 * @author Luis
 *
 */
public class ReferenceFileUtils {

	public static final String VINGADORES_URL_FILE = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json";
	public static final String LIGA_JUSTICA_URL_FILE = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml";

	public static VingadoresRootVO getAllowedVingadores() {
		VingadoresRootVO vingadoresRootVO = null;
		String fileContent = null;
		
		try {
			fileContent = getUrlFileContent(VINGADORES_URL_FILE);
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			vingadoresRootVO = gson.fromJson(fileContent, VingadoresRootVO.class);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return vingadoresRootVO;
	}
	
	public static LigaJusticaVO getAllowedLigaJustica() {
		LigaJusticaVO ligaJusticaVO = null;
		String fileContent = null;
		
		try {
			fileContent = getUrlFileContent(LIGA_JUSTICA_URL_FILE);

			Serializer serializer = new Persister();
			ligaJusticaVO = serializer.read(LigaJusticaVO.class, fileContent);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ligaJusticaVO;
	}
	
	private static String getUrlFileContent(final String fileUrl) throws IOException {
		String content = null;
		
		HttpGet httpGet = new HttpGet(fileUrl);
		HttpEntity httpEntity = null;
		
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
				
				if (response.getStatusLine().getStatusCode() != HttpStatus.OK.value()) {
					throw new RuntimeException("Não foi possível obter o conteúdo do arquivo de referência: " + fileUrl);
				}
				
				httpEntity = response.getEntity();
				content = EntityUtils.toString(httpEntity);
			} 
		} finally {
			EntityUtils.consume(httpEntity);
		}
		
		return content;
	}
}
