package org.icet.learn.service.custom;

import org.icet.learn.dto.Login;
import org.icet.learn.service.SuperService;

public interface LoginService extends SuperService {

    boolean searchLogin (Login login);

}
