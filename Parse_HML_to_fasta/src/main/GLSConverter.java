package main;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;



public class GLSConverter {
	String type = "text/plain";
	// HTTP POST request
		public String encode(String gls) throws Exception {

			String url = "http://mn4s35003:8080/mac/api/encode";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            
			//add request header
			con.setRequestMethod("POST");
			con.setRequestProperty( "Content-Type", type );
			
			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(gls);
			wr.flush();
			wr.close();
		
 
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + gls);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			//print result
			System.out.println(response.toString());
			return response.toString();
			

		}
		
		// HTTP GET request
		public String decode(String gls, boolean expand) throws Exception{
			String url = "http://mn4s35003:8080/mac/api/encode?";
			String inputGLS = "typing =" + gls;
			String inputExpand;
			if(expand){
				inputExpand = "expand = true";
			}else{
				inputExpand = "expand = false";
			}
			//Build get url string
			StringBuilder sb = new StringBuilder();
			sb.append(url);
			sb.append(inputGLS);
			sb.append("&");
			sb.append(inputExpand);
			
			URL obj = new URL(sb.toString());
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");
			con.setRequestProperty( "Content-Type", type );

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			System.out.println(response.toString());
			return response.toString();
			
		}


}
