package com.spy13.financemanager.dao;

import com.spy13.financemanager.domain.entity.Purse;

import java.util.List;

public interface IPurseDao {
    List<Purse> get();
    Purse getById(int id);
}
