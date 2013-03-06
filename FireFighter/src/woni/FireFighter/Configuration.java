package woni.FireFighter;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Configuration implements IConfiguration {

	public Hashtable<String, String> getDistricts() {
		Hashtable<String, String> districts = new Hashtable<String,String>();
		
		districts.put("RA", "Bereich Radkersburg");
		districts.put("VO", "Bereich Voitsberg");
		districts.put("WZ", "Bereich Weiz");
		districts.put("BM", "Bereich Bruck an der Mur");
		districts.put("DL", "Bereich Deutschlandsberg");
		districts.put("FB", "Bereich Feldbach");
		districts.put("FF", "Bereich Fürstenfeld");
		districts.put("GU", "Bereich Graz Umgebung");
		districts.put("HB", "Bereich Hartberg");
		districts.put("JU", "Bereich Judenburg");
		districts.put("KF", "Bereich Knittelfeld");
		districts.put("LB", "Bereich Leibnitz");
		districts.put("LE", "Bereich Leoben");
		districts.put("LI", "Bereich Liezen");
		districts.put("MU", "Bereich Murau");
		districts.put("MZ", "Bereich Mürzzuschlag");
		
		return districts;
	}

	public String getUrl(District district) {
		return String.format("http://178.188.171.236/rpweb/onlinestatus.aspx?form=EVENT&bez=%s", district.getShortText());
	}

	public List<Mission> parseMissions(Document document) {
		Element centerElement = document.getElementsByTag("center").first();
		Element table = centerElement.getElementsByTag("table").first();
		Elements rows = table.getElementsByTag("tr");
		List<Mission> missions = new ArrayList<Mission>();

		for (Element row : rows) {
			String[] fieldValues = new String[8];
			Elements fields = row.getElementsByTag("td");
			if (fields.size() > 0) {
				int iterator = 0;

				for (Element td : fields) {
					fieldValues[iterator] = td.text();
					iterator++;
				}
				missions.add(new Mission(fieldValues));
			}
		}

		return missions;
	}

}
