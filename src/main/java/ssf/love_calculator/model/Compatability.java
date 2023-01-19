package ssf.love_calculator.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Compatability implements Serializable {
   
    private String fname;
    private String sname;
    private String percentage;
    private String result;


    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static Compatability create(String json) throws IOException {
        Compatability c = new Compatability();
        try(InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            c.setFname(o.getString("fname"));
            c.setSname(o.getString("sname"));
            c.setPercentage(o.getString("percentage"));
            c.setResult(o.getString("result"));
        }
        return c;
    }
}
