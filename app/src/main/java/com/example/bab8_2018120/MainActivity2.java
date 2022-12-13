package com.example.bab8_2018120;

import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import com.example.bab8_2018120.databinding.NetworkBinding;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{
    //declaration variable
    private NetworkBinding binding;
    String index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setup view binding
        binding = NetworkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.fetchButton.setOnClickListener(this);
    }
    //onclik button fetch
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fetch_button){
            index = binding.inputId.getText().toString();
            try {
                getData();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }
    //get data using api link
    public void getData() throws MalformedURLException {
        Uri uri =
                Uri.parse("https://datausa.io/api/data?drilldowns=Nation&measures=Population")
                                .buildUpon().build();
        URL url = new URL(uri.toString());
        new DOTask().execute(url);
    }
    class DOTask extends AsyncTask<URL, Void, String>{
        //connection request
        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls [0];
            String data = null;
            try {
                data = NetworkUtils.makeHTTPRequest(url);
            }catch (IOException e){
                e.printStackTrace();
            }
            return data;
        }
        @Override
        protected void onPostExecute(String s){
            try {
                parseJson(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //get data json
        public void parseJson(String data) throws JSONException{
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(data);
            }catch (JSONException e){
                e.printStackTrace();
            }
            JSONArray cityArray =
                    jsonObject.getJSONArray("data");
            for (int i =0; i <cityArray.length(); i++){
                JSONObject obj = cityArray.getJSONObject(i);
                String Sobj = obj.get("ID Nation").toString();
                if (Sobj.equals(index)){
                    String id = obj.get("ID Nation").toString();
                    binding.resultId.setText(id);

                    String nation = obj.get("Nation").toString();
                    binding.resultNation.setText(nation);

                    String year = obj.get("ID Year").toString();
                    binding.resultYear.setText(year);

                    String population = obj.get("Population").toString();
                    binding.resultPopulation.setText(population);

                    String slug = obj.get("Slug Nation").toString();
                    binding.resultSlug.setText(slug);

                    break;
                }
                else{
                    binding.resultNation.setText("Not Found");
                }
            }
        }
    }
}

