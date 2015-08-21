package org.comshalom.evangelizar;

import android.app.ProgressDialog;
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
import java.util.Map;

import org.comshalom.evangelizar.backend.cadastroApi.CadastroApi;
import org.comshalom.evangelizar.backend.cadastroApi.model.CadastroVO;
import org.comshalom.evangelizar.dao.CadastroDAO;

import org.comshalom.evangelizar.R;
import org.comshalom.evangelizar.dao.EvangelizadorDAO;
import org.comshalom.evangelizar.model.Cadastro;
import org.comshalom.evangelizar.model.Evangelizador;
import org.comshalom.evangelizar.type.TipoEvangelizadorEnum;
import org.comshalom.evangelizar.type.TipoEventoEnum;
import org.comshalom.evangelizar.type.TipoLocalEnum;

class EndpointsAsyncTask extends AsyncTask<Map<Evangelizador, List<Cadastro>>, Void, String> {

    private static CadastroApi cadastroApi;
    private Context context;
    private ProgressDialog progressDialog;

    public EndpointsAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context, ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Sincronizando...");
        progressDialog.setIndeterminate(false);
        progressDialog.show();

    }

    @Override
    protected String doInBackground(Map<Evangelizador, List<Cadastro>>... params) {

        cadastroApi = CloudEndpointBuilderHelper.getEndpoints();

        Map<Evangelizador, List<Cadastro>> mapaEvangelizadorCadastros =  params[0];

        CadastroDAO repo = new CadastroDAO(context);
        Evangelizador evangelizador = mapaEvangelizadorCadastros.entrySet().iterator().next().getKey();
        List<Cadastro> listaCadastro = mapaEvangelizadorCadastros.entrySet().iterator().next().getValue();

        for (Cadastro cadastro :listaCadastro) {
            CadastroVO novoCadastro = new CadastroVO();
            novoCadastro.setNome(cadastro.getNome());
            novoCadastro.setTelefone(cadastro.getTel());
            novoCadastro.setBairro(cadastro.getBairro());
            novoCadastro.setFacebook(cadastro.getFacebook());
            novoCadastro.setIdade(cadastro.getIdade());
            novoCadastro.setEmail(cadastro.getEmail());
            novoCadastro.setLocal(TipoLocalEnum.getDescricaoByCodigo(cadastro.getLocal()));

            novoCadastro.setEvangelizadorNome(evangelizador.getNome());
            novoCadastro.setEvangelizadorTipo(TipoEvangelizadorEnum.getDescricaoByCodigo(evangelizador.getTipo()));
            novoCadastro.setEvangelizadorTelefone(evangelizador.getTelefone());
            novoCadastro.setEvangelizadorEmail(evangelizador.getEmail());
            novoCadastro.setEvangelizadorEvento(TipoEventoEnum.getDescricaoByCodigo(evangelizador.getEvento()));

            try {
                cadastroApi.cadastroEndpoint().inserirCadastro(novoCadastro).execute();
                repo.updateSync(cadastro.getCadastro_ID());
            } catch (IOException e) {
                e.printStackTrace();
                return "Erro ao sincronizar a lista! Tente mais tarde com a Internet ligada!" ;
            }
        }

        return "Lista sincronizada com sucesso!";
    }

    @Override
    protected void onPostExecute(String result) {
        if(progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

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

    public void montarListaCadastro() {
        CadastroDAO repo = new CadastroDAO(this);

        List<Cadastro> cadastroList =  repo.getCadastroList(0);
        if(!cadastroList.isEmpty()) {
            ListView lv = (ListView) findViewById(R.id.list_cadastro);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    cadastro_Id = (TextView) view.findViewById(R.id.cadastro_Id);
                    String cadastroId = cadastro_Id.getText().toString();
                    Intent objIndent = new Intent(getApplicationContext(), CadastroActivity.class);
                    objIndent.putExtra("cadastro_Id", Integer.parseInt(cadastroId));
                    startActivity(objIndent);
                }
            });

            List<Map<String, String>> listaCadastroView =  new ArrayList<>();
            for (Cadastro cadastro :cadastroList) {
                Map<String, String> mapaCadastroView = new HashMap<>();
                mapaCadastroView.put("id", String.valueOf(cadastro.getCadastro_ID()));
                mapaCadastroView.put("nome", cadastro.getNome());
                listaCadastroView.add(mapaCadastroView);
            }
            ListAdapter adapter = new SimpleAdapter(this, listaCadastroView, R.layout.ver_cadastro, new String[] { "id","nome"}, new int[] {R.id.cadastro_Id, R.id.cadastro_nome});
            lv.setAdapter(adapter);
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

            CadastroDAO repo = new CadastroDAO(this);
            List<Cadastro> cadastroList =  repo.getCadastroList(0);
            if (cadastroList.isEmpty()) {
                Toast.makeText(this, "Lista já sincronizada!", Toast.LENGTH_SHORT).show();
            } else {
                EvangelizadorDAO evangelizadorDAO = new EvangelizadorDAO(this);
                Evangelizador evangelizador = evangelizadorDAO.getEvangelizadorById(1);

                Map<Evangelizador, List<Cadastro>> mapaEvangelizadorCadastros = new HashMap<Evangelizador, List<Cadastro>>();
                mapaEvangelizadorCadastros.put(evangelizador, cadastroList);

                new EndpointsAsyncTask(this).execute(mapaEvangelizadorCadastros);
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
