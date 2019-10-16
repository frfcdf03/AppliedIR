package IO;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JsonWriter {
    private FileWriter file;
    public JsonWriter(String filename) throws IOException {
        this.file = new FileWriter(filename);
    }
    public void write(JSONObject object) throws IOException {
        file.write(object.toJSONString());
        file.flush();
        file.close();
    }

}
