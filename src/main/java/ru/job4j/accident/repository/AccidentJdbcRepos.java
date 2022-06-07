package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;

@Repository
public class AccidentJdbcRepos {
    private final JdbcTemplate jdbc;

    public AccidentJdbcRepos(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void createOrUpdate(Accident accident, int typeId, int[] rIds) {
        int idAcc = accident.getId();
        if (idAcc != 0) {
            update(accident, typeId, rIds);
        } else {
            create(accident, typeId, rIds);
        }
    }

    private void create(Accident accident, int typeId, int[] rIds) {
        int accidentTypeId = findAccidentType(typeId).getId();
        jdbc.update("insert into Accident (name, text, address, accident_type_id) values (?, ?, ?, ?)",
                accident.getName(), accident.getText(), accident.getAddress(), accidentTypeId);
        int idAcc = findAccidentId(accident);
        for (var ruleId : rIds) {
            jdbc.update("insert into Accident_Rule (accident_id, rules_id) values (?, ?)", idAcc, ruleId);
        }

    }

    private void update(Accident accident, int typeId, int[] rIds) {
        int idAcc = accident.getId();
        int accidentTypeId = findAccidentType(typeId).getId();
        jdbc.update("update Accident set name = ?, text = ?, address = ?, accident_type_id = ? where id = ?",
                accident.getName(), accident.getText(), accident.getAddress(), accidentTypeId, idAcc);
        clearAccidentRule(idAcc);
        for (var ruleId : rIds) {
            jdbc.update("insert into Accident_Rule (accident_id, rules_id) values (?, ?)", idAcc, ruleId);
        }
    }

    private Integer findAccidentId(Accident accident) {
        return jdbc.queryForObject("select id from Accident where name = ? and text = ? and address = ?",
                Integer.class, accident.getName(), accident.getText(), accident.getAddress());
    }

    private void clearAccidentRule(int id) {
        jdbc.update("delete from Accident_Rule where accident_id = ?", id);
    }

    public List<Accident> getAllAccident() {
        return jdbc.query("select id, name, text, address, accident_type_id from Accident ORDER BY id",
                (rs, row) -> {
                    int idAcc = rs.getInt("id");
                    String namedAcc = rs.getString("name");
                    String textAcc = rs.getString("text");
                    String addressAcc = rs.getString("address");
                    int accidentTypeId = rs.getInt("accident_type_id");
                    Accident accident = new Accident(namedAcc, textAcc, addressAcc);
                    accident.setId(idAcc);

                    AccidentType accidentType = findAccidentType(accidentTypeId);
                    accident.setType(accidentType);

                    Set<Rule> ruleSet = findSetRule(idAcc);
                    accident.setRules(ruleSet);
                    return accident;
                });
    }

    public List<AccidentType> getAllAccidentType() {
        return jdbc.query("select id, name from Accident_Type",
                (rs, row) -> {
                    int idAccT = rs.getInt("id");
                    String namedAccT = rs.getString("name");
                    return AccidentType.of(idAccT, namedAccT);
                });
    }

    public List<Rule> getAllRule() {
        return jdbc.query("select id, name from Rule",
                (rs, row) -> {
                    int idRule = rs.getInt("id");
                    String nameRule = rs.getString("name");
                    return Rule.of(idRule, nameRule);
                });
    }

    public Accident findAccident(int id) {
        return jdbc.queryForObject("select * from Accident where id = ?",
                (rs, row) -> {
                    int idAcc = rs.getInt("id");
                    String namedAcc = rs.getString("name");
                    String textAcc = rs.getString("text");
                    String addressAcc = rs.getString("address");
                    int accidentTypeId = rs.getInt("accident_type_id");
                    Accident accident = new Accident(namedAcc, textAcc, addressAcc);
                    accident.setId(idAcc);

                    AccidentType accidentType = findAccidentType(accidentTypeId);
                    accident.setType(accidentType);

                    Set<Rule> ruleSet = findSetRule(idAcc);
                    accident.setRules(ruleSet);
                    return accident;
                }, id);
    }

    public AccidentType findAccidentType(int id) {
        return jdbc.queryForObject("select name from Accident_Type where id = ?",
                (rs, row) -> {
                    String namedAccT = rs.getString("name");
                    return AccidentType.of(id, namedAccT);
                }, id);
    }

    public Rule findRule(int id) {
        return jdbc.queryForObject("select id, name from Rule where id = ?",
                (rs, row) -> {
                    int idRule = rs.getInt("id");
                    String nameRule = rs.getString("name");
                    return Rule.of(idRule, nameRule);
                }, id);
    }

    public Set<Rule> findSetRule(int idAcc) {
        String queryRuleId = "SELECT rules_id FROM Accident_Rule WHERE accident_id = ?";
        List<Integer> ruleListId = jdbc.query(queryRuleId,
                (rs, rowNum) -> rs.getInt("rules_id"), idAcc);

        String inSql = String.join(",", Collections.nCopies(ruleListId.size(), "?"));
        String queryRule = String.format("SELECT * FROM Rule WHERE id IN (%s)", inSql);
        List<Rule> ruleList = jdbc.query(queryRule, ruleListId.toArray(),
                (rs, rowNum) -> Rule.of(rs.getInt("id"), rs.getString("name")));
        return new HashSet<>(ruleList);
    }
}