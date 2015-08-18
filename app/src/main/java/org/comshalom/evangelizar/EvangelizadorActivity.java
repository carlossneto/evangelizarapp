package org.comshalom.evangelizar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.comshalom.evangelizar.dao.CadastroDAO;
import org.comshalom.evangelizar.dao.EvangelizadorDAO;
import org.comshalom.evangelizar.model.Cadastro;
import org.comshalom.evangelizar.model.Evangelizador;

public class EvangelizadorActivity extends AppCompatActivity {

    EditText editTextNome;
    Spinner spinnerTipo;
    EditText editTextTelefone;
    EditText editTextEmail;
    Spinner spinnerEvento;
    Evangelizador evangelizador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evangelizador);

        EvangelizadorDAO repo = new EvangelizadorDAO(this);

        evangelizador = repo.getEvangelizadorById(1);

        editTextNome = (EditText) findViewById(R.id.editTextEvangelizadorNome);
        spinnerTipo = (Spinner) findViewById(R.id.spinnerEvangelizadorTipo);
        editTextTelefone = (EditText) findViewById(R.id.editTextEvangelizadorTelefone);
        editTextEmail = (EditText) findViewById(R.id.editTextEvangelizadorEmail);
        spinnerEvento = (Spinner) findViewById(R.id.spinnerEvangelizadorEvento);

        if(evangelizador != null) {
            editTextNome.setText(evangelizador.getNome());
            editTextNome.setEnabled(false);
            spinnerTipo.setSelection(evangelizador.getTipo());
            spinnerTipo.setEnabled(false);
            editTextTelefone.setText(evangelizador.getTelefone());
            editTextTelefone.setEnabled(false);
            editTextEmail.setText(evangelizador.getEmail());
            editTextEmail.setEnabled(false);
            spinnerEvento.setSelection(evangelizador.getEvento());
            spinnerEvento.setEnabled(true);

        } else {

            editTextNome.setEnabled(true);
            spinnerTipo.setEnabled(true);
            editTextTelefone.setEnabled(true);
            editTextEmail.setEnabled(true);
            spinnerEvento.setEnabled(true);

        }
    }

    public void salvarEvangelizador(View view) {

        boolean primeiraVez = true;
        if (evangelizador != null) {
            primeiraVez = false;
        }

        editTextNome = (EditText) findViewById(R.id.editTextEvangelizadorNome);
        spinnerTipo = (Spinner) findViewById(R.id.spinnerEvangelizadorTipo);
        editTextTelefone = (EditText) findViewById(R.id.editTextEvangelizadorTelefone);
        editTextEmail = (EditText) findViewById(R.id.editTextEvangelizadorEmail);
        spinnerEvento = (Spinner) findViewById(R.id.spinnerEvangelizadorEvento);

        EvangelizadorDAO repo = new EvangelizadorDAO(this);
        evangelizador = new Evangelizador();
        evangelizador.setNome(editTextNome.getText().toString());
        evangelizador.setTipo(spinnerTipo.getSelectedItemPosition());
        evangelizador.setTelefone(editTextTelefone.getText().toString());
        evangelizador.setEmail(editTextEmail.getText().toString());
        evangelizador.setEvento(spinnerEvento.getSelectedItemPosition());

        if(primeiraVez) {
            repo.insert(evangelizador);
        } else {
            repo.update(evangelizador);
        }

        Toast.makeText(this, "Dados Atualizados", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}