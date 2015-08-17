
package org.comshalom.evangelizar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.comshalom.evangelizar.dao.CadastroDAO  ;
import org.comshalom.evangelizar.model.Cadastro;

public class CadastroActivity extends ActionBarActivity implements View.OnClickListener{

    Button btnSave ,  btnDelete, btnClose;
    EditText editTextNome;
    EditText editTextEmail;
    EditText editTextEnd;
    EditText editTextTel;
    EditText editTextBairro;
    EditText editTextFacebook;
    EditText editTextIdade;
    EditText editTextLocal;

    private int _Cadastro_Id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);


        editTextNome = (EditText) findViewById(R.id.editTextNome);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextEnd = (EditText) findViewById(R.id.editTextEnd);
        editTextTel = (EditText) findViewById(R.id.editTextTel);
        editTextBairro = (EditText) findViewById(R.id.editTextBairro);
        editTextFacebook = (EditText) findViewById(R.id.editTextFacebook);
        editTextIdade = (EditText) findViewById(R.id.editTextIdade);
        editTextLocal = (EditText) findViewById(R.id.editTextLocal);



        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);


        _Cadastro_Id =0;
        Intent intent = getIntent();
        _Cadastro_Id =intent.getIntExtra("cadastro_Id", 0);
        CadastroDAO repo = new CadastroDAO(this);

        Cadastro cadastro = new Cadastro();

        cadastro = repo.getCadastroById(_Cadastro_Id);

        editTextNome.setText( cadastro.getNome() );
        editTextEmail.setText( cadastro.getEmail() );
        editTextEnd.setText( cadastro.getEndereco() );
        editTextTel.setText( String.valueOf(cadastro.getTel() ));
        editTextBairro.setText( cadastro.getBairro() );
        editTextFacebook.setText( cadastro.getFacebook() );
        editTextIdade.setText( String.valueOf(cadastro.getIdade()) );
        editTextLocal.setText( cadastro.getLocal() );

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

    public void onClick(View view) {
        if (view == findViewById(R.id.btnSave)){
            CadastroDAO repo = new CadastroDAO(this);
            Cadastro cadastro = new Cadastro();

            cadastro.setNome(editTextNome.getText().toString());
            cadastro.setEndereco(editTextEnd.getText().toString());
            cadastro.setBairro(editTextBairro.getText().toString());
            cadastro.setFacebook(editTextFacebook.getText().toString());
            cadastro.setIdade(Integer.parseInt(editTextIdade.getText().toString()));
            cadastro.setEmail(editTextEmail.getText().toString());
            cadastro.setTel(Integer.parseInt(editTextTel.getText().toString()));
            cadastro.setLocal ( editTextLocal.getText().toString() );
            cadastro.setCadastro_ID( _Cadastro_Id );

            //System.out.print("debug_email :" +  cadastro.email);
            //System.out.print("debug_local :" +  cadastro.local);

            if (_Cadastro_Id==0){
                _Cadastro_Id = repo.insert(cadastro);

                Toast.makeText(this,"New Cadastro Insert",Toast.LENGTH_SHORT).show();
            }else{

                repo.update(cadastro);
                Toast.makeText(this,"Cadastro Record updated",Toast.LENGTH_SHORT).show();
            }
        }else if (view== findViewById(R.id.btnDelete)){
            CadastroDAO repo = new CadastroDAO(this);
            repo.delete(_Cadastro_Id);
            Toast.makeText(this, "Cadastro Record Deleted", Toast.LENGTH_SHORT);
            finish();
        }else if (view== findViewById(R.id.btnClose)){
            finish();
        }

    }

}