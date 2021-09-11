package com.moan.pet.moanHealthPrj.persistance.dao.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class DaoTypeHolder {
    private static DaoType typeHolder;

    public static void setDaoType(DaoType repositoryType) {
        typeHolder = repositoryType;
    }

    public static DaoType getDaoType() {
        return typeHolder;
    }
}
