package com.example.reposteria;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class Adaptador2 extends RecyclerView.Adapter<Adaptador2.ViewHolder> {
    public List<dataGetSet> Lista;
    public Context Cont;

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nombre, direccion, pedido;
        private Button btnAcep;
        private ImageView imagen;
        private Context context;
        private RequestQueue volley;

        public ViewHolder(View itemview) {
            super(itemview);
            context = itemview.getContext();
            nombre = itemview.findViewById(R.id.txtNombre);
            direccion = itemview.findViewById(R.id.txtDireccion);
            pedido = itemview.findViewById(R.id.txtPedido);
            btnAcep = itemview.findViewById(R.id.btnTermina);
            imagen = itemview.findViewById(R.id.item_imagen);

            btnAcep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        PostEstatusEntrega(btnAcep.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        public void PostEstatusEntrega(String ID) throws JSONException {
            System.out.println("se obtuvo entrar al post");
            String url = "https://royalback.herokuapp.com/api/UpdateEntrega/";

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("idLineaPedido", ID+"");
            final String mRequestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Toast.makeText(context, "Sucess", Toast.LENGTH_SHORT).show();
                    },
                    error -> {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    }){

                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return mRequestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            volley = Volley.newRequestQueue(context);
            volley.add(stringRequest);
        }

    }

    public Adaptador2(List<dataGetSet> lista, Context cont){
        this.Lista = lista;
        this.Cont = cont;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(Cont);
        View view = inflater.inflate(R.layout.cardsviews, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        dataGetSet data = Lista.get(position);

        holder.nombre.setText(data.getNombre());
        holder.direccion.setText(data.getDireccion());
        holder.pedido.setText(data.getPedido());
        holder.btnAcep.setText(data.getId());
        holder.imagen.setImageResource(R.drawable.ic_launcher_foreground);
    }

    @Override
    public int getItemCount() {
        return Lista.size();
    }


}