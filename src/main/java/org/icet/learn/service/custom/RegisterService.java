package org.icet.learn.service.custom;

import org.icet.learn.dto.Register;
import org.icet.learn.service.SuperService;

public interface RegisterService extends SuperService {

    boolean addLogin(Register register);

}


