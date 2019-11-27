package djole;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonWriter {
	
	public JacksonWriter() {
		
	}
	
	public void writeToJackson(Nutrition nutrition, String jacksonFilePath) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(jacksonFilePath)));
		bufferedWriter.write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(nutrition));
		bufferedWriter.flush();
		bufferedWriter.close();
		
	}

}
