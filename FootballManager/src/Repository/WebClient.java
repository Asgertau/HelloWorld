package Repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.*;

import Models.*;

public class WebClient {

	private Gson gson;

	public WebClient() {
		gson = new Gson();

		// Player p = g.fromJson(jsonString, Player.class)
	}

	public ArrayList<FootiePlayer> getRandomPersonsFromAPI(int amount) {
		try {

			URL url = new URL("http://uinames.com/api/?gender=male&region=england&amount=" + Integer.toString(amount));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
			}
			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(in);
			String output;
			FootiePlayer[] persons = null;
			while ((output = br.readLine()) != null) {
				persons = gson.fromJson(output, FootiePlayer[].class);
			}

			ArrayList<FootiePlayer> result = new ArrayList<FootiePlayer>();
			for (FootiePlayer p : persons) {

				result.add(p);
			}

			conn.disconnect();
			return result;

		} catch (Exception e) {
			System.out.println("Exception in NetClientGet:- " + e);
			return null;
		}

	}

}
