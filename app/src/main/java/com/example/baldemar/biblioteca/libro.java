package com.example.baldemar.biblioteca;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



public class libro extends ActionBarActivity {

    EditText et_numero;
    EditText et_nombre;
    EditText et_autor;
    EditText et_editorial;
    EditText et_isbn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libro);


        et_numero = (EditText) findViewById(R.id.et_numero);
        et_nombre = (EditText) findViewById(R.id.et_nombre);
        et_autor = (EditText) findViewById(R.id.et_autor);
        et_editorial = (EditText) findViewById(R.id.et_editorial);
        et_isbn = (EditText) findViewById(R.id.et_isbn);

    }

    public void Guardar (View v) {

        try {


            Conexion admin = new Conexion(this, "biblioteca", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();

            String numero_libro = et_numero.getText().toString();
            String nombre = et_nombre.getText().toString();
            String autor = et_autor.getText().toString();
            String editorial = et_editorial.getText().toString();
            String isbn = et_isbn.getText().toString();

            ContentValues registro = new ContentValues();

            registro.put("numero_libro", numero_libro);
            registro.put("nombre_libro", nombre);
            registro.put("autor", autor);
            registro.put("editorial", editorial);
            registro.put("isbn", isbn);


            bd.insert("biblioteca", null, registro);
            bd.close();

            et_numero.setText("");
            et_nombre.setText("");
            et_autor.setText("");
            et_editorial.setText("");
            et_isbn.setText("");

            Toast.makeText(this, "Se agrego un nuevo libro", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
        }


    }

    public void Consulta (View v) {
        Conexion admin = new  Conexion(this, "biblioteca" , null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String numero = et_numero.getText().toString();

        Cursor fila = bd.rawQuery("select nombre_libro, autor, editorial, isbn from biblioteca where numero_libro=" + numero, null);

        if (fila.moveToFirst()) {
            et_nombre.setText(fila.getString(0));
            et_autor.setText(fila.getString(1));
            et_editorial.setText(fila.getString(2));
            et_isbn.setText(fila.getString(3));

        } else {
            Toast.makeText(this, "No existe el libro", Toast.LENGTH_SHORT).show();
        }
        bd.close();

    }

    public void Modificar (View v) {
        Conexion admin = new Conexion(this, "biblioteca", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String numero_libro = et_numero.getText().toString();
        String nombre = et_nombre.getText().toString();
        String autor = et_autor.getText().toString();
        String editorial = et_editorial.getText().toString();
        String isbn = et_isbn.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("numero_libro", numero_libro);
        registro.put("nombre_libro", nombre);
        registro.put("autor", autor);
        registro.put("editorial", editorial);
        registro.put("isbn", isbn);


        int cant = bd.update("biblioteca", registro, "numero_libro = " + numero_libro, null);
        bd.close();

        if (cant == 1) {
            Toast.makeText(this, "Se modificaron los datos del libro", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe el libro", Toast.LENGTH_SHORT).show();
        }

        et_numero.setText("");
        et_nombre.setText("");
        et_autor.setText("");
        et_editorial.setText("");
        et_isbn.setText("");


        Toast.makeText(this, "Se modifico el libro", Toast.LENGTH_SHORT).show();
    }


    public void Eliminar (View v) {
        Conexion admin = new Conexion(this, "biblioteca", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String numero = et_numero.getText().toString();
        int cant = bd.delete("biblioteca", "numero_libro = " + numero, null);
        bd.close();
        et_numero.setText("");
        et_nombre.setText("");
        et_autor.setText("");
        et_editorial.setText("");
        et_isbn.setText("");

        if (cant == 1) {
            Toast.makeText(this, "Se borró el libro", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe el libro", Toast.LENGTH_SHORT).show();
        }



    }

    public void Limpiar (View v){
        et_numero.setText("");
        et_nombre.setText("");
        et_autor.setText("");
        et_editorial.setText("");
        et_isbn.setText("");

        Toast.makeText(this, "Se Limpio el registro", Toast.LENGTH_SHORT).show();







    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_libro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
