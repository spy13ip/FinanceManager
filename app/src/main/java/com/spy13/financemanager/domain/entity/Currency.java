package com.spy13.financemanager.domain.entity;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "currency")
public class Currency {
    @DatabaseField(columnName = "_id", generatedId = true)
    private int id;
    @DatabaseField(columnName = "code")
    private String code;
    @DatabaseField(columnName = "name")
    private String name;
    @ForeignCollectionField
    private ForeignCollection<Purse> purses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ForeignCollection<Purse> getPurses() {
        return purses;
    }

    public void setPurses(ForeignCollection<Purse> purses) {
        this.purses = purses;
    }
}
