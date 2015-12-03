package com.spy13.financemanager.models.services;

import com.spy13.financemanager.models.entities.Purse;

import java.util.List;

public interface IPurseService {
    List<Purse> get();
    Purse getById(int id);
}
