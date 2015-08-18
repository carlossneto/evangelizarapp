
package org.comshalom.evangelizar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.comshalom.evangelizar.dao.CadastroDAO;
import org.comshalom.evangelizar.model.Cadastro;

public class CadastroActivity extends AppCompatActivity  {

    Button btnSave;
    EditText editTextNome;
    EditText editTextEmail;
    EditText editTextTel;
    EditText editTextBairro;
    EditText editTextFacebook;
    EditText editTextIdade;
    Spinner spinnerLocal;

    private int _Cadastro_Id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editTextNome = (EditText) findViewById(R.id.editTextNome);
        editTextTel = (EditText) findViewById(R.id.editTextTel);
        editTextBairro = (EditText) findViewById(R.id.editTextBairro);
        editTextFacebook = (EditText) findViewById(R.id.editTextFacebook);
        editTextIdade = (EditText) findViewById(R.id.editTextIdade);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        spinnerLocal = (Spinner) findViewById(R.id.spinnerCadastroLocal);

        _Cadastro_Id =0;
        Intent intent = getIntent();
        _Cadastro_Id =intent.getIntExtra("cadastro_Id", 0);
        CadastroDAO repo = new CadastroDAO(this);

        Cadastro cadastro = new Cadastro();

        cadastro = repo.getCadastroById(_Cadastro_Id);

        editTextNome.setText( cadastro.getNome() );
        editTextEmail.setText( cadastro.getEmail() );
        editTextTel.setText(cadastro.getTel());
        editTextBairro.setText( cadastro.getBairro() );
        editTextFacebook.setText( cadastro.getFacebook() );
        editTextIdade.setText(String.valueOf(cadastro.getIdade()));
        spinnerLocal.setSelection(cadastro.getLocal());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.cadastro_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void salvarCadastro(View view) {

        CadastroDAO repo = new CadastroDAO(this);
        Cadastro cadastro = new Cadastro();

        cadastro.setNome(editTextNome.getText().toString());
        cadastro.setBairro(editTextBairro.getText().toString());
        cadastro.setFacebook(editTextFacebook.getText().toString());
        cadastro.setIdade(editTextIdade.getText().toString());
        cadastro.setEmail(editTextEmail.getText().toString());
        cadastro.setTel(editTextTel.getText().toString());
        cadastro.setLocal ( spinnerLocal.getSelectedItemPosition() );
        cadastro.setCadastro_ID( _Cadastro_Id );

        //System.out.print("debug_email :" +  cadastro.email);
        //System.out.print("debug_local :" +  cadastro.local);

        if (_Cadastro_Id==0){
            _Cadastro_Id = repo.insert(cadastro);
            Toast.makeText(this,"Cadastro inserido com sucesso!",Toast.LENGTH_SHORT).show();
        }else{
            repo.update(cadastro);
            Toast.makeText(this,"Cadastro atualizado com sucesso!",Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}