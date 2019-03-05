package br.com.players.service.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.players.service.vo.LigaJusticaVO;
import br.com.players.service.vo.VingadoresRootVO;

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
	
	private static String getUrlFileContent(final String fileUrl) throws ParseException, IOException {
		String content = null;
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(fileUrl);
		
		HttpResponse response = httpClient.execute(httpGet);

		HttpEntity httpEntity = response.getEntity();
		
		content = EntityUtils.toString(httpEntity);
		
		return content;
	}
}
