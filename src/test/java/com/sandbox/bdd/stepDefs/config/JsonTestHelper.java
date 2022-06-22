package com.sandbox.bdd.stepDefs.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class JsonTestHelper {
	private ObjectMapper mapper;
	public JsonTestHelper() {
	}
	public <T> T getRequest(String file, Class<T> t) throws IOException {
		//URL filePath = this.getClass().getResource(file);
		//	FileReader fr = new FileReader("/Users/khanabdulshariff/Downloads/BraintreeSandBox/src/test/resources/request/AddCustomer.json");
		FileReader fr = new FileReader("/Users/khanabdulshariff/Downloads/BraintreeSandBox/src/test/resources/request/test.json");
		//FileReader fr = new FileReader("/Users/khanabdulshariff/Downloads/BraintreeSandBox/src/test/resources/request/ChargeTransaction.json");
		BufferedReader br = new BufferedReader(fr);
		String buffer = null;
		String json;
		for(json = ""; (buffer = br.readLine()) != null; json = json + buffer) {
		}
		br.close();
		return this.parseJsonAndReturnRequest(json, t);
	}
	public <T> T parseJsonAndReturnRequest(String json, Class<T> t) {
		T requestObj = null;
		this.mapper = new ObjectMapper();
		this.mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		try {
			requestObj = this.mapper.readValue(json, t);
		} catch (IOException var5) {
			var5.printStackTrace();
		}
		return requestObj;
	}
}

