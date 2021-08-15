package ca.sheridancollege.teixerya.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.teixerya.bean.Drink;


@Repository
public class DrinkRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    public void addDrink(Drink drink) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        String query = " INSERT INTO easy_drinks"
                + " (drink_name, main, amount1, second, amount2, directions)VALUES "+
                "(:name, :main, :a1, :sec, :a2, :dir)";
        parameters.addValue("name", drink.getName());
        parameters.addValue("main", drink.getMain());
        parameters.addValue("a1",drink.getAmount1());
        parameters.addValue("sec",drink.getSecond());
        parameters.addValue("a2",drink.getAmount2());
        parameters.addValue("dir",drink.getDirections());

        jdbc.update(query, parameters);

    }

    public ArrayList<Drink> getDrinks(){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM easy_drinks ";
        List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
        ArrayList<Drink> drinks = new ArrayList<Drink>();

        for(Map<String, Object> row : rows) {
            Drink d = new Drink();
            d.setId((int)row.get("id"));
            d.setName((String)row.get("drink_name"));
            d.setMain((String)row.get("main"));
            d.setAmount1((Double)row.get("amount1"));
            d.setSecond((String)row.get("second"));
            d.setAmount2((Double)row.get("amount2"));
            d.setDirections((String)row.get("directions"));
            drinks.add(d);
        }
        return drinks;
    }

    public ArrayList<Drink> getDrinks2(){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM easy_drinks ";
//		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
        ArrayList<Drink> drinks = (ArrayList<Drink>)
                jdbc.query(query, new BeanPropertyRowMapper<Drink>(Drink.class));
        return drinks;
    }

    public Drink getDrinkById(int id){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM easy_drinks WHERE id=:id";
        parameters.addValue("id", id);

        List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
        ArrayList<Drink> drinks = new ArrayList<Drink>();

        for(Map<String, Object> row : rows) {
            Drink d = new Drink();
            d.setId((int)row.get("id"));
            d.setName((String)row.get("drink_name"));
            d.setMain((String)row.get("main"));
            d.setAmount1((Double)row.get("amount1"));
            d.setSecond((String)row.get("second"));
            d.setAmount2((Double)row.get("amount2"));
            d.setDirections((String)row.get("directions"));
            drinks.add(d);
        }
        if(drinks.isEmpty()) {
            return null;
        }else {

            return drinks.get(0);
        }
    }


    public void editDrink(Drink drink) {
        String query = "UPDATE easy_drinks SET drink_name=:name, main=:main,"+
                "amount1=:a1, second=:sec, amount2=:a2, directions=:dir WHERE id=:id";

        MapSqlParameterSource parameters = new MapSqlParameterSource();

        parameters.addValue("id",drink.getId());
        parameters.addValue("name", drink.getName());
        parameters.addValue("main", drink.getMain());
        parameters.addValue("a1",drink.getAmount1());
        parameters.addValue("sec",drink.getSecond());
        parameters.addValue("a2",drink.getAmount2());
        parameters.addValue("dir",drink.getDirections());

        jdbc.update(query,  parameters);

    }

    public void deleteById(int id) {
        String query = "DELETE FROM easy_drinks WHERE id=:id";
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        parameters.addValue("id",id);
        jdbc.update(query,  parameters);
    }


}















