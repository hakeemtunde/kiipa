package com.corebyte.mob.kiipa.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

public class BaseModel {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name="created_at")
    public Date createdAt;
    @ColumnInfo(name="updated_at")
    public Date updatedAt;
}