package br.smartmanager.util;

import javax.mail.Session;
import javax.persistence.EntityManager;

public class ObjectEntityUtil {

    public Long maxId(EntityManager em, String tabela, String id) {

        String sql = "select max(" + id + ") from public." + tabela;

        Long max = (Long) em.createNativeQuery(sql)
                .getSingleResult();

        if (max == null) {
            max = 1L;
        } else {
            max++;
        }

        return max;
    }

    public Integer maxIdInteger(EntityManager em, String tabela, String id) {

        String sql = "select max(" + id + ") from public." + tabela;

        Integer max = (Integer) em.createNativeQuery(sql)
                .getSingleResult();

        if (max == null) {
            max = 1;
        } else {
            max++;
        }

        return max;
    }

}
