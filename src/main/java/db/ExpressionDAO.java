package db;

import dao.DAO;
import model.Expression;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpressionDAO implements DAO {

    private Connection getConnection() {
        DBManager dbManager = new DBManager("localhost:5432", "postgres", "admin2233", "test");
        return dbManager.connect();
    }

    @Override
    public Object get(long id) {
        Connection connection = this.getConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if NOT EXISTS expressions(id serial,expr VARCHAR(100), res real);");
            ResultSet expressionsSet = statement.executeQuery("SELECT * FROM expressions;");

            while(expressionsSet.next()) {
                int expressionId = expressionsSet.getInt("id");
                String expression = expressionsSet.getString("expr");
                double result = expressionsSet.getInt("res");
                Expression e = new Expression(expressionId,expression,result);
                if ((long)expressionId == id) {
                    return e;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public List getAll() {
        Connection connection = this.getConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if NOT EXISTS expressions(id serial, \n" +
                    "            expr VARCHAR(100), res real);");
            ResultSet expressionsSet = statement.executeQuery("SELECT * FROM expressions;");
            ArrayList<Expression> expressions = new ArrayList<>();

            while(expressionsSet.next()) {
                int expressionId = expressionsSet.getInt("id");
                String expression = expressionsSet.getString("expr");
                double result = expressionsSet.getDouble("res");
                Expression e = new Expression(expressionId,expression,result);
                expressions.add(e);
            }
            return expressions;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Object o) throws SQLException {
        Connection connection = this.getConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if NOT EXISTS expressions(id serial, " +
                    "            expr VARCHAR(100), res real);");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        Expression e = (Expression) o;
        PreparedStatement insertExpression = connection.prepareStatement("INSERT INTO expressions(expr,res) VALUES (?,?)");
        insertExpression.setString(1, e.getText());
        insertExpression.setDouble(2, e.getRes());
        insertExpression.executeUpdate();
    }

    @Override
    public void update(Object o, int id) throws SQLException {

        /*Connection connection = this.getConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if NOT EXISTS expressions(id serial, " +
                    "            expr VARCHAR(100), res real);");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        Expression e = (Expression) o;
        PreparedStatement insertExpression = connection.prepareStatement("INSERT INTO expressions(id, expr,res) VALUES (?,?,?)");
        insertExpression.setInt(1,e.getId());
        insertExpression.setString(2, e.getText());
        insertExpression.setDouble(3, e.getRes());
        insertExpression.executeUpdate();*/
        Connection connection = this.getConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if NOT EXISTS expressions(id serial, expr VARCHAR(100), res real);");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        Expression e = (Expression) o;
        PreparedStatement updateExpression = connection.prepareStatement("UPDATE expressions SET expr=?, res=? " +
                "WHERE id=?");
        updateExpression.setString(1,e.getText());
        updateExpression.setDouble(2,e.getRes());
        updateExpression.setInt(3,id);
        updateExpression.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = this.getConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE if NOT EXISTS expressions(id serial, expr VARCHAR(100), res real);");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        PreparedStatement deleteExpression = connection.prepareStatement("DELETE FROM expressions WHERE id=?");
        deleteExpression.setInt(1, id);
        deleteExpression.executeUpdate();
    }
}

