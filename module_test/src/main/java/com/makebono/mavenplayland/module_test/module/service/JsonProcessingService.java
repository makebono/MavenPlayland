package com.makebono.mavenplayland.module_test.module.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.makebono.mavenplayland.module_test.common.connector.InterfaceSqlConnector;
import com.makebono.mavenplayland.module_test.module.entities.MicroArchitecture;

/** 
 * @ClassName: JsonProcessingService 
 * @Description: Using JSON.simple to deal with Json.
 * @author makebono
 * @date 2018年1月10日 下午2:48:42 
 *  
 */
@SuppressWarnings(value = { "unchecked" })
@Service
public class JsonProcessingService {
    private static List<MicroArchitecture> list = new ArrayList<MicroArchitecture>();

    public void writeJsonFile(final String fileName) {
        list = InterfaceSqlConnector.selectAllMicroArchitecture();
        final JSONArray jsonFile = new JSONArray();

        for (final MicroArchitecture cursor : list) {
            final JSONObject temp = new JSONObject();
            temp.put("brand", cursor.getBrand());
            temp.put("core", cursor.getCore());
            temp.put("model", cursor.getModel());
            jsonFile.add(temp);
        }

        try {
            final File output = new File("d:/workspace/MavenPlayland/outputs/" + fileName);
            final BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            System.out.println(jsonFile.toJSONString());
            writer.write(jsonFile.toJSONString());
            writer.close();
        }
        catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public String parseJsonFile(final String fileName) {
        final StringBuilder sb = new StringBuilder();

        final JSONParser jsonParser = new JSONParser();

        try {
            final FileReader reader = new FileReader("d:/workspace/MavenPlayland/outputs/" + fileName);
            final Object result = jsonParser.parse(reader);
            final JSONArray list = (JSONArray) result;
            sb.append("{");
            for (int i = 0; i < list.size(); i++) {
                final JSONObject cursor = (JSONObject) list.get(i);
                if (i != list.size() - 1) {
                    sb.append(
                            "[" + cursor.get("brand") + " " + cursor.get("model") + "(" + cursor.get("core") + ")], ");
                } else {
                    sb.append("[" + cursor.get("brand") + " " + cursor.get("model") + "(" + cursor.get("core") + ")]");
                }
            }
            sb.append("}");
        }
        catch (final Exception e) {
            return "Error occurs, message: " + e.getMessage();
        }

        return sb.toString();
    }
}
