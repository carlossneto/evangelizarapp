
package org.comshalom.evangelizar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
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
        editTextIdade.setText(cadastro.getIdade());
        spinnerLocal.setSelection(cadastro.getLocal());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    public void salvarCadastro(View view) {

        CadastroDAO repo = new CadastroDAO(this);
        Cadastro cadastro = new Cadastro();


        String nome = editTextNome.getText().toString().trim();
        if(nome.equals("")) {
            editTextNome.setError("Nome é obrigatório!");
            return;
        } else {
            cadastro.setNome(nome);
        }

        String bairro = editTextBairro.getText().toString().trim();
        cadastro.setBairro(bairro);

        String facebook = editTextFacebook.getText().toString().trim();
        cadastro.setFacebook(facebook);

        String idade = editTextIdade.getText().toString().trim();
        cadastro.setIdade(idade);

        String email = editTextEmail.getText().toString().trim();
        cadastro.setEmail(email);

        String tel = editTextTel.getText().toString().trim();
        cadastro.setTel(tel);

        cadastro.setLocal ( spinnerLocal.getSelectedItemPosition() );

        cadastro.setCadastro_ID( _Cadastro_Id );

        if (_Cadastro_Id==0){
            _Cadastro_Id = repo.insert(cadastro);
            Toast.makeText(this,"Cadastro inserido com sucesso!",Toast.LENGTH_SHORT).show();
        }else{
            repo.update(cadastro);
            Toast.makeText(this,"Cadastro atualizado com sucesso!",Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(this, ListaCadastroActivity.class);
        startActivity(intent);
    }

}