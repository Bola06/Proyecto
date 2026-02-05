package com.example.registro;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class API {

    public void subirUsuario(String nombre,String correo,String contrasena){
        new Thread(() -> {
            try {
                URL url = new URL("http://192.130.0.16:8080/tema5maven/rest/proyecto/registro");
                HttpURLConnection con = (HttpURLConnection) url.openConnection(); //Abrir conexion
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type","application/json");
                con.setDoOutput(true); //Voy a escribir el body

                JSONObject json = new JSONObject();
                json.put("nombre",nombre);
                json.put("correo",correo);
                json.put("contraseña",contrasena);

                System.out.println(json);

                try(OutputStream os = con.getOutputStream()) {
                    os.write(json.toString().getBytes(StandardCharsets.UTF_8)); //Esto siempre es igual, sirve para enviar el body
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                int code = con.getResponseCode(); //Forzar envío
                Log.i("CODIGO APIRES","EE CÓDIGO RESULTANTE ES "+code);

            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }).start();
    }

    public interface LoginCallback {
        void onLoginResult(boolean success, User u);
    }

    public void obtenerDatosUsuario(String nombre, String contrasena, LoginCallback callback){

        new Thread(() -> {
            try {
                URL url = new URL("http://192.130.0.16:8080/tema5maven/rest/proyecto/inicio?nombreUsuario="+nombre+"&contra="+contrasena);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept","application/json");


                int code = conn.getResponseCode();
                System.out.println("Código HTTP: "+ code);

                User user;

                if (code == 200){
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8)
                    );

                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line.trim());
                    }


                    JSONObject obj = new JSONObject(response.toString());
                    System.out.println(obj);
                    if (obj != null) {
                        String username = obj.getString("nombreUsuario");
                        String contra = obj.getString("contrasena");

                        user= new User(username, contra);

                        callback.onLoginResult(true,user);

                    }
                }else{
                    callback.onLoginResult(false,null);
                }

            }catch (Exception e){
                e.printStackTrace();
                callback.onLoginResult(false,null);
            }
        }).start();
    }
    public interface CommentCallback {
        void onCommentResult(boolean success, ArrayList<Comentario> comentario);
    }
    public void obtenerComentarios(CommentCallback callback){
        ArrayList<Comentario> comentarios = new ArrayList<>();
        new Thread(()-> {

            try{
                URL url = new URL("http://192.130.0.16:8080/tema5maven/rest/proyecto/recogerComentarios");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept","application/json");

                int code = conn.getResponseCode();
                System.out.println("Código HTTP: "+ code);



                if (code == 200){

                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8)
                    );

                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line.trim());
                    }

                    JSONArray array = new JSONArray(response.toString());

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject json = array.getJSONObject(i);
                        System.out.println(json);
                        int id = json.getInt("id");
                        String comentario = json.getString("comentario");
                        int idUsuario = json.getInt("idUsuario");
                        int mg = json.getInt("mg");
                        int nmg = json.getInt("nmg");
                        int idRespuesta = json.getInt("idRespuesta");

                        comentarios.add(new Comentario(id,comentario,idUsuario,mg,nmg,idRespuesta));

                    }
                    callback.onCommentResult(true,comentarios);
                    for (int i2 = 0; i2 < comentarios.size(); i2++) {
                        System.out.println(comentarios.get(i2).getComentario());
                    }

                }
            }catch (Exception e){
                e.printStackTrace();
                callback.onCommentResult(false,null);
            }


        }).start();



    }


}
