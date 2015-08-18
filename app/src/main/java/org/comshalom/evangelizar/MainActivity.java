package org.comshalom.evangelizar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import org.comshalom.evangelizar.dao.CadastroDAO;

import org.comshalom.evangelizar.R;

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
        }else{
            Toast.makeText(this, "Sem Cadastro!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("passei no oncreate");
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
