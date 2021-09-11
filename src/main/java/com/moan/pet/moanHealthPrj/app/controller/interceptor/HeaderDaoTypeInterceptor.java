package com.moan.pet.moanHealthPrj.app.controller.interceptor;

import com.moan.pet.moanHealthPrj.persistance.dao.helper.DaoType;
import com.moan.pet.moanHealthPrj.persistance.dao.helper.DaoTypeHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HeaderDaoTypeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!ObjectUtils.isEmpty(request.getHeader("dao")) && request.getHeader("dao").equals("jdbc")) {
            DaoTypeHolder.setDaoType(DaoType.JDBC);
        } else {
            DaoTypeHolder.setDaoType(DaoType.JPA);
        }
        return true;
    }
}
