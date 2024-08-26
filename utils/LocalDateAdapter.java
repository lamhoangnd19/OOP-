package vn.viettuts.qlsv.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public LocalDate unmarshal(String v) throws Exception {
    	try {
    		return LocalDate.parse(v, formatter);
    	} catch (Exception e) {
			// TODO: handle exception
		}
    	return null;
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.format(formatter);
    }
}