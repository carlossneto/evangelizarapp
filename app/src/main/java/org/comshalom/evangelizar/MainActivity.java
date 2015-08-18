package org.comshalom.evangelizar;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;


import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.comshalom.evangelizar.backend.cadastroApi.CadastroApi;
import org.comshalom.evangelizar.backend.cadastroApi.model.CadastroVO;
import org.comshalom.evangelizar.dao.CadastroDAO;

import org.comshalom.evangelizar.R;
import org.comshalom.evangelizar.model.Cadastro;

class EndpointsAsyncTask extends AsyncTask<Pair<Context, List<Cadastro>>, Void, String> {

    private static CadastroApi cadastroApi;
    private Context context;

    @Override
    protected String doInBackground(Pair<Context, List<Cadastro>>... params) {

        cadastroApi = CloudEndpointBuilderHelper.getEndpoints();

        context = params[0].first;
        List<Cadastro> listaCadastro = params[0].second;

        for (Cadastro cadastro :listaCadastro) {
            CadastroVO novoCadastro = new CadastroVO();
            novoCadastro.setNome(cadastro.getNome());
            novoCadastro.setTel(cadastro.getTel());
            novoCadastro.setBairro(cadastro.getBairro());
            novoCadastro.setFacebook(cadastro.getFacebook());
            novoCadastro.setIdade(cadastro.getIdade());
            novoCadastro.setEmail(cadastro.getEmail());
            novoCadastro.setLocal(cadastro.getLocal());

            novoCadastro.setNomeEvangelizador("Teste");
            novoCadastro.setTipoEvangelizador(0);
            novoCadastro.setTelefoneEvangelizador("21");
            novoCadastro.setEmailEvangelizador("teste@tetse");
            novoCadastro.setEventoEvangelizador(0);

            try {
                cadastroApi.cadastroEndpoint().inserirCadastro(novoCadastro).execute();
            } catch (IOException e) {
                e.printStackTrace();
                return "Erro ao sincronizar a lista!";
            }
        }

        return "Lista sincronizada!";
    }

    @Override
    protected void onPostExecute(String result) {
        MainActivity mainActivity = (MainActivity) context;

        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    TextView cadastro_Id;

    public void novoCadastro(View view) {
        Intent intent = new Intent(this, CadastroActivity.class);
        intent.putExtra("cadastro_ID",0);
        startActivity(intent);
    }

    private void montarListaCadastro() {
        CadastroDAO repo = new CadastroDAO(this);

        ArrayList<HashMap<String, String>> cadastroList =  repo.getCadastroList(0);
        if(cadastroList.size()!=0) {
            ListView lv = (ListView) findViewById(R.id.list_cadastro);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                    cadastro_Id = (TextView) view.findViewById(R.id.cadastro_Id);

                    String cadastroId = cadastro_Id.getText().toString();

                    System.out.println("Id parametro:"+ cadastroId);

                    Intent objIndent = new Intent(getApplicationContext(),CadastroActivity.class);
                    objIndent.putExtra("cadastro_Id", Integer.parseInt( cadastroId));
                    startActivity(objIndent);
                }
            });
            ListAdapter adapter = new SimpleAdapter( MainActivity.this,cadastroList, R.layout.ver_cadastro, new String[] { "id","nome"}, new int[] {R.id.cadastro_Id, R.id.cadastro_nome});
            lv.setAdapter(adapter);


            //TODO pegar a lista e passar no parametro new EndpointsAsyncTask().execute(new Pair<Context, List<Cadastro>>(this, cadastroList));

        }else{
            Toast.makeText(this, "Sem Cadastro!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        montarListaCadastro();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


}
