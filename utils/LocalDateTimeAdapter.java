package vn.viettuts.qlsv.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public LocalDateTime unmarshal(String v) throws Exception {
    	try {
    		return LocalDateTime.parse(v, formatter);
    	} catch (Exception e) {
			// TODO: handle exception
		}
    	return null;
    }

    @Override
    public String marshal(LocalDateTime v) throws Exception {
        return v.format(formatter);
    }
}
