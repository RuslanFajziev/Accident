package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;

@Repository
public class AccidentJdbcRepos {
    private final JdbcTemplate jdbc;

    public AccidentJdbcRepos(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void createOrUpdate(Accident accident) {
        int idAcc = accident.getId();
        if (idAcc != 0) {
            jdbc.update("update accident set name = ?, text = ?, address = ? where id = ?",
                    accident.getName(), accident.getText(), accident.getAddress(), accident.getId());
        } else {
            jdbc.update("insert into accident (name, text, address) values (?, ?, ?)",
                    accident.getName(), accident.getText(), accident.getAddress());
        }
    }

    public List<Accident> getAll() {
        return jdbc.query("select id, name, text, address from accident",
                (rs, row) -> {
                    Integer idAcc = rs.getInt("id");
                    String namedAcc = rs.getString("name");
                    String textAcc = rs.getString("text");
                    String addressAcc = rs.getString("address");
                    Accident accident = new Accident(namedAcc, textAcc, addressAcc);
                    accident.setId(rs.getInt("id"));
                    return accident;
                });
    }

    public List<AccidentType> getAllAccidentType() {
        return jdbc.query("select id, name from AccidentType",
                (rs, row) -> {
                    Integer idAccT = rs.getInt("id");
                    String namedAccT = rs.getString("name");
                    AccidentType accidentType = AccidentType.of(idAccT, namedAccT);
                    return accidentType;
                });
    }

    public List<Rule> getAllRule() {
        return jdbc.query("select id, name from Rule",
                (rs, row) -> {
                    Integer idRule = rs.getInt("id");
                    String nameRule = rs.getString("name");
                    Rule rule = Rule.of(idRule, nameRule);
                    return rule;
                });
    }

    public Accident find(int id) {
        return jdbc.queryForObject("select id, name, text, address from Accident where id = ?",
                (rs, row) -> {
                    Integer idAcc = rs.getInt("id");
                    String nameAcc = rs.getString("name");
                    String textAcc = rs.getString("text");
                    String addressAcc = rs.getString("address");
                    Accident accident = new Accident(nameAcc, textAcc, addressAcc);
                    accident.setId(idAcc);
                    return accident;
                }, id);
    }

    public AccidentType findAccidentType(int id) {
        return jdbc.queryForObject("select id, name from AccidentType where id = ?",
                (rs, row) -> {
                    Integer idAccT = rs.getInt("id");
                    String namedAccT = rs.getString("name");
                    AccidentType accidentType = AccidentType.of(idAccT, namedAccT);
                    return accidentType;
                }, id);
    }

    public Rule findRule(int id) {
        return jdbc.queryForObject("select id, name from Rule where id =" + id,
                (rs, row) -> {
                    Integer idRule = rs.getInt("id");
                    String nameRule = rs.getString("name");
                    Rule rule = Rule.of(idRule, nameRule);
                    return rule;
                });
    }
}