package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

//@Repository
public class AccidentHibernateRepos {
    private final SessionFactory sf;

    public AccidentHibernateRepos(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T tx(final Function<Session, T> command) {
        Session session = sf.openSession();
        session.beginTransaction();
        try {
            T rsl = command.apply(session);
            session.getTransaction().commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public Accident createOrUpdate(Accident accident, int typeId, int[] rIds) {
        int idAcc = accident.getId();
        AccidentType accidentType = getAccidentType(typeId);
        accident.setType(accidentType);

        List<Rule> ruleList = getRuleForIds(rIds);
        Set<Rule> ruleSet = new HashSet<>(ruleList);
        accident.setRules(ruleSet);

        return tx(session -> {
            if (idAcc != 0) {
                session.update(accident);
            } else {
                session.save(accident);
            }
            return accident;
        });
    }

    public List<Accident> getAllAccident() {
        String queryTxt = "select DISTINCT acc from ru.job4j.accident.model.Accident acc join fetch acc.rules";
        return tx(session -> session.createQuery(queryTxt, Accident.class).list());
    }

    public List<AccidentType> getAllAccidentType() {
        return tx(session -> session.createQuery("from ru.job4j.accident.model.AccidentType", AccidentType.class).list());
    }

    public List<Rule> getAllRule() {
        return tx(session -> session.createQuery("from ru.job4j.accident.model.Rule", Rule.class).list());
    }

    public AccidentType getAccidentType(int id) {
        return tx(session -> session.get(AccidentType.class, id));
    }

    public Rule getRuleForId(int id) {
        return tx(session -> session.get(Rule.class, id));
    }

    public List<Rule> getRuleForIds(int[] rIds) {
        List<String> lstIdsRule = new ArrayList<>();
        for (int idR : rIds) {
            lstIdsRule.add(String.valueOf(idR));
        }
        String queryIdsRule = String.join(",", lstIdsRule);
        String queryTxt = String.format("from ru.job4j.accident.model.Rule rul where rul.id IN (%s)", queryIdsRule);
        return tx(session -> session.createQuery(queryTxt, Rule.class).list());
    }
}