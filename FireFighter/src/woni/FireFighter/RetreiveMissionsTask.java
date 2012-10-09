package woni.FireFighter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.os.AsyncTask;

class RetreiveMissionsTask extends AsyncTask<String, Void, List<Mission>> {

	private Exception exception;
	ArrayList<MissionReceivedListener> listeners = new ArrayList<MissionReceivedListener>();

	protected List<Mission> doInBackground(String... url) {
		try {
			Document document = Jsoup.connect(url[0]).get();

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
		} catch (Exception e) {
			this.exception = e;
			return new ArrayList<Mission>();
		}
	}

	protected void onPostExecute(List<Mission> missions) {
		// TODO: check this.exception
		if (exception == null) {
			for (MissionReceivedListener listener : listeners) {
				listener.onMissionsReceived(missions);
			}
		} else {
			
			
		}

	}

	public void setOnDocumentUpdateListener(MissionReceivedListener listener) {
		// Store the listener object
		this.listeners.add(listener);
	}

}
