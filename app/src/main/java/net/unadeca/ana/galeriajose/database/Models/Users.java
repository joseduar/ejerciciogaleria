package net.unadeca.ana.galeriajose.database.Models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import net.unadeca.ana.galeriajose.database.GaleriaDB;

/**
 * Created by ANA on 29/03/2018.
 */
@Table(database = GaleriaDB.class)
public class Users extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    public long id;

    @Column
    public String username;

    @Column
    public String password;


    @Column
    public String nombre;

    @Column
    public String roll;

}

