package org.icet.learn.repository.custom;

import org.icet.learn.entity.RegisterEntity;
import org.icet.learn.service.SuperService;

public interface RegisterDao extends SuperService {
    boolean addRegister(RegisterEntity register);
}


