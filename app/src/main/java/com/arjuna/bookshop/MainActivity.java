package com.arjuna.bookshop;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String nama;
    Integer jumlah;
    String email;
    String Gendre;
    String[] datagendre = new String[]{
      "None", "Novel", "Bussiness", "Enscylopedia", "Education", "Fabel"
    };

    EditText txtnama;
    TextView deskripsi, txtjumlah, txtemail, txtbuku, txtharga;
    Spinner spGendre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtnama = (EditText) findViewById(R.id.txtnama);
        deskripsi = (TextView) findViewById(R.id.deskripsi);
        txtjumlah = (TextView) findViewById(R.id.txtjumlah);
        txtemail = (EditText) findViewById(R.id.txtemail);
        txtbuku = (EditText) findViewById(R.id.txtbuku);
        txtharga = (EditText) findViewById(R.id.txtharga);
        spGendre = (Spinner) findViewById(R.id.spGendre);


        spGendre = (Spinner) findViewById(R.id.spGendre);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datagendre);

        spGendre.setAdapter(adapter);
        spGendre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Gendre = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    public void tambahjual(View view) {
        jumlah = Integer.valueOf(txtjumlah.getText().toString()) + 1;
        txtjumlah.setText(String.valueOf(jumlah));
        tampilDeskripsi(jumlah);
    }

    private void tampilDeskripsi(Integer jumlah) {


        String harga = txtharga.getText().toString();
        int aharga = Integer.parseInt(harga);
        Integer total = jumlah * (aharga);
        String nama = txtnama.getText().toString();
        String email = txtemail.getText().toString();
        String gendre = txtbuku.getText().toString();


        String deksripsi = "Nama : " + nama
                + "\nJumlah : " + jumlah
                + "\nTotal : " + total + ".00"
                + "\nEmail : " + email
                + "\nJudul :" + gendre
                + "\nGendre:" + Gendre;
        deskripsi.setText(deksripsi);
    }

    public void tambahkurang(View view) {
        Integer jumlahsekarang = Integer.valueOf(txtjumlah.getText().toString());
        if (jumlahsekarang > 1) {
            jumlah = jumlahsekarang - 1;
        } else {
            jumlah = 1;
        }

        txtjumlah.setText(String.valueOf(jumlah));
        tampilDeskripsi(jumlah);


    }
    public void pesanbuku(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { email });
        intent.putExtra(Intent.EXTRA_SUBJECT, "Pesanan Buku");
        intent.putExtra(Intent.EXTRA_TEXT, deskripsi.getText().toString());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    public void Hapus(View view) {
        String harga = txtharga.getText().toString();
        int aharga = Integer.parseInt(harga);
        Integer total = jumlah * aharga;
        String nama = txtnama.getText().toString();
        String deksripsi = "Nama : "
                + "\nJumlah : "
                + "\nTotal : "
                + "\nJudul :"
                + "\nGendre";
        deskripsi.setText(deksripsi);
        txtjumlah.setText("0");


    }


}
