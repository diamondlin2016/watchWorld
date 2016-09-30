package com.rongyi.diamond.baselibiary.base.mvp;

import com.rongyi.diamond.baselibiary.annotation.Implement;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      16/9/29 下午9:19
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 16/9/29      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */
public class LogicProxy {
    private static final LogicProxy m_instance = new LogicProxy();

    public static LogicProxy getInstance() {
        return m_instance;
    }

    private LogicProxy() {
        m_objects = new HashMap<>();
    }

    private Map<Class, Object> m_objects;

    public void init(Class... clss) {
        for (Class cls : clss) {
            if (cls.isAnnotationPresent(Implement.class)) {
                for (Annotation ann : cls.getDeclaredAnnotations()) {
                    if (ann instanceof Implement) {
                        try {
                            m_objects.put(cls, ((Implement) ann).value().newInstance());
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public <T> T getProxy(Class cls) {
        return (T) m_objects.get(cls);
    }

    public <T> T bind(Class cls, IBaseView o) {
        Object ret = m_objects.get(cls);

        ((BasePresenter) ret).attachView(o);

        return (T) ret;
    }
}
