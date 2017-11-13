package br.com.crud_sqlite.Sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import br.com.crud_sqlite.Model.Pessoa;

/**
 * Created by andrei.vupt on 12/11/2017.
 */

public class Read extends SQLiteOpenHelper {

    private static final String NOME_DB = "FAMILIA_DB";
    private static final String TABELA_PESSOA = "TABELA_PESSOA";
    private static final int VERSAO_DB = 1;

    private static final String PATH_DB = "/data/user/0/br.com.crud_sqlite/FAMILIA_DB";
    Context context;
    SQLiteDatabase db;

    public Read(Context context){
        super(context,NOME_DB,null,VERSAO_DB);
        this.context = context;
        db = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Pessoa> getPessoas(){
        openDB();
        ArrayList<Pessoa> pArray = new ArrayList<>();
        String getPessoa = "SELECT * FROM "+ TABELA_PESSOA;

        try {
            Cursor c = db.rawQuery(getPessoa,null);

            if (c.moveToFirst()){
                do {
                    Pessoa p = new Pessoa();

                    p.setNome(c.getString(0));
                    p.setIdade(c.getInt(1));
                    p.setRamo(c.getString(2));

                    pArray.add(p);
                }while (c.moveToNext());
            }

        } catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            db.close();
        }
        return pArray;
    }

    //FAZER CONSULTAR/ESCREVER
    private void openDB(){
        if (!db.isOpen()){
            db = context.openOrCreateDatabase(PATH_DB,SQLiteDatabase.OPEN_READWRITE,null);
        }
    }
}
