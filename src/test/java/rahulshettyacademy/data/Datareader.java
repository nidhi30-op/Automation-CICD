package rahulshettyacademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Datareader {

	public List<HashMap<String, String>> getJsonDataToHashmap(String FilePath) throws IOException {
		// Read the file content into a string
		String jsonData = FileUtils.readFileToString(new File(FilePath), StandardCharsets.UTF_8);

		// Convert JSON string into List of HashMaps
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonData,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;

	}

}
