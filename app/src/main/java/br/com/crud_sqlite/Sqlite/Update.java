package br.com.crud_sqlite.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.crud_sqlite.Model.Pessoa;

/**
 * Created by andrei.vupt on 12/11/2017.
 */

public class Update extends SQLiteOpenHelper {

    private static final String NOME_DB = "FAMILIA_DB";
    private static final String TABELA_PESSOA = "TABELA_PESSOA";
    private static final int VERSAO_DB = 1;

    private static final String PATH_DB = "/data/user/0/br.com.crud_sqlite/FAMILIA_DB";
    Context context;
    SQLiteDatabase db;

    public Update(Context context){
        super(context,NOME_DB,null,VERSAO_DB);
        this.context = context;
        db = getWritableDatabase();
    }
    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertPessoa(Pessoa p){
        openDB();
        try {
            ContentValues cv = new ContentValues();

            cv.put("nome", p.getNome());
            cv.put("idade", p.getIdade());
            cv.put("ramo", p.getRamo());
            db.insert(TABELA_PESSOA, null, cv);
            return true;
        } catch (Exception c){
            c.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }

    public boolean updatePessoa(Pessoa p){
        openDB();
        try {
            String where = "nome = '" + p.getNome() + "'";
            ContentValues cv = new ContentValues();

            cv.put("nome", p.getNome());
            cv.put("idade", p.getIdade());
            cv.put("ramo", p.getRamo());
            db.update(TABELA_PESSOA, cv, where, null);
            return true;
        } catch (Exception c){
            c.printStackTrace();
            return false;
        } finally {
            db.close();
        }
    }


    //FAZER CONSULTAR/ESCREVER
    private void openDB(){
        if (!db.isOpen()){
            db = context.openOrCreateDatabase(PATH_DB,SQLiteDatabase.OPEN_READWRITE,null);
        }
    }
}
