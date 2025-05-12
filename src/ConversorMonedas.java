import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class ConversorMonedas {

        public double convertirADolar(double monto, String from) {
        try {
            String urlStr = String.format(
                    "https://v6.exchangerate-api.com/v6/7aca1063e8a9dba8ac07157d/latest/%s", from);
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");


            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String linea;
            while ((linea = reader.readLine()) != null) {
                response.append(linea);
            }
            reader.close();


            JSONObject jsonResponse = new JSONObject(response.toString());


            if (jsonResponse.getString("result").equals("success")) {
                // Obtener la tasa de conversión de la moneda de origen a USD
                JSONObject rates = jsonResponse.getJSONObject("conversion_rates");
                double rate = rates.getDouble("USD"); // Obtener el valor para USD

                // Convertir la cantidad a USD
                return monto * rate;
            } else {
                System.out.println("Error en la conversión. Respuesta: " + jsonResponse);
                return -1;
            }
        } catch (Exception e) {
            System.out.println("Error al convertir a USD: " + e.getMessage());
            return -1;
        }
    }


    public double convertirDeDolar(double monto, String to) {
        try {
            String urlStr = String.format(
                    "https://v6.exchangerate-api.com/v6/7aca1063e8a9dba8ac07157d/latest/USD", to);
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Leer la respuesta
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String linea;
            while ((linea = reader.readLine()) != null) {
                response.append(linea);
            }
            reader.close();

            // Convertir la respuesta a un objeto JSON
            JSONObject jsonResponse = new JSONObject(response.toString());

            // Verificar si la respuesta es exitosa
            if (jsonResponse.getString("result").equals("success")) {
                // Obtener la tasa de conversión de USD a la moneda destino
                JSONObject rates = jsonResponse.getJSONObject("conversion_rates");
                double rate = rates.getDouble(to); // Obtener el valor para la moneda destino

                // Convertir la cantidad desde USD
                return monto * rate;
            } else {
                System.out.println("Error en la conversión. Respuesta: " + jsonResponse);
                return -1;
            }
        } catch (Exception e) {
            System.out.println("Error al convertir de USD: " + e.getMessage());
            return -1;
        }
    }

    // Método principal de conversión entre dos monedas
    public double convertir(String from, String to, double amount) {
        // Convertir la moneda de origen a USD
        double amountInUSD = convertirADolar(amount, from);
        if (amountInUSD == -1) {
            return -1; // Error en la conversión a USD
        }

        // Convertir de USD a la moneda de destino
        return convertirDeDolar(amountInUSD, to);
    }
}
