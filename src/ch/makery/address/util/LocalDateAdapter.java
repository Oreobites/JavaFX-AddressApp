package ch.makery.address.util;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/*
 * Adapter for JAXB to convert between the LocalDate and
 * the ISO 8601 String representation of the date such as '2016-09-06'.
 */

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

	@Override
	public LocalDate unmarshal(String v) throws Exception {
		return LocalDate.parse(v);
	}
	
	@Override
	public String marshal(LocalDate v) throws Exception {
		return v.toString();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
