package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.List;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident save(Accident accident) {
        jdbc.update("insert into accident (name) values (?)",
                accident.getName());
        return accident;
    }

    public List<Accident> getAll() {
        return jdbc.query("select id, name, text, address from accident", (rs, row) -> {
            String nameAcc = rs.getString("name");
            String textAcc = rs.getString("text");
            String addressAcc = rs.getString("address");
            Accident accident = new Accident(nameAcc, textAcc, addressAcc);
            accident.setId(rs.getInt("id"));
            return accident;
        });
    }
}