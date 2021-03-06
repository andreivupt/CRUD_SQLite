package br.com.crud_sqlite.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.crud_sqlite.Adapter.AdapterUsuario;
import br.com.crud_sqlite.Interface.ServiceUsuario;
import br.com.crud_sqlite.Model.Pessoa;
import br.com.crud_sqlite.Model.UsuarioGitHub;
import br.com.crud_sqlite.R;
import br.com.crud_sqlite.Sqlite.Create;
import br.com.crud_sqlite.Sqlite.Delete;
import br.com.crud_sqlite.Sqlite.Read;
import br.com.crud_sqlite.Sqlite.Update;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etNome;
    EditText etIdade;
    EditText etRamo;

    Button btnCreate;
    Button btnUpdate;
    Button btnRead;
    Button btnDelete;

    ListView lvUsers;

    ServiceUsuario service;
    AdapterUsuario adapter;
    Call<List<UsuarioGitHub>> call = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome = (EditText) findViewById(R.id.etNome);
        etIdade = (EditText) findViewById(R.id.etIdade);
        etRamo = (EditText) findViewById(R.id.etRamo);

        btnCreate = (Button) findViewById(R.id.btnAdd);
        btnCreate.setOnClickListener(this);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);
        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);

        lvUsers = (ListView) findViewById(R.id.lvUsers);

        service = ServiceUsuario.retrofit.create(ServiceUsuario.class);

        call = service.getUsers();
        call.enqueue(new Callback<List<UsuarioGitHub>>() {
            @Override
            public void onResponse(Call<List<UsuarioGitHub>> call, Response<List<UsuarioGitHub>> response) {
                if (response != null){
                    List<UsuarioGitHub> list = response.body();
                    if (list != null){
                        adapter = new AdapterUsuario(getBaseContext(), list);
                        lvUsers.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<UsuarioGitHub>> call, Throwable t) {
                Toast.makeText(getBaseContext(),"Erro em buscar as informações",Toast.LENGTH_LONG).show();
            }
        });

        Create c = new Create(getApplicationContext());
        c.createTable();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnAdd:


                Pessoa p = new Pessoa();
                p.setNome(etNome.getText().toString());
                p.setIdade(Integer.parseInt(etIdade.getText().toString()));
                p.setRamo(etRamo.getText().toString());

                Update u = new Update(getApplicationContext());
                if (u.insertPessoa(p)){
                    Toast.makeText(this, "Inserida com sucesso", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Nao foi inserida", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnDelete:

                Pessoa dp = new Pessoa();
                dp.setNome(etNome.getText().toString());
                dp.setIdade(Integer.parseInt(etIdade.getText().toString()));
                dp.setRamo(etRamo.getText().toString());

                Delete delete = new Delete(getApplicationContext());
                if (delete.deletePessoa(dp)){
                    Toast.makeText(this, "Deletada com sucesso", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Nao foi deletada", Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.btnRead:

                Read r = new Read(getApplicationContext());
                ArrayList<Pessoa> pArray = r.getPessoas();
                for (int i = 0; i < pArray.size(); i++) {
                    Pessoa pessoa = pArray.get(i);
                    System.out.println("Nome: "+pessoa.getNome()+ " Idade: "+pessoa.getIdade()+ " Ramo: "+pessoa.getRamo());
                }
                break;

            case R.id.btnUpdate:
                Pessoa up = new Pessoa();
                up.setNome(etNome.getText().toString());
                up.setIdade(Integer.parseInt(etIdade.getText().toString()));
                up.setRamo(etRamo.getText().toString());

                Update update = new Update(getApplicationContext());
                if (update.updatePessoa(up)){
                    Toast.makeText(this, "Atualizada com sucesso", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Nao foi atualizada", Toast.LENGTH_LONG).show();
                }
                break;

            default:
                Toast.makeText(this, "Opção invalida", Toast.LENGTH_LONG).show();
                break;
        }

    }
}
