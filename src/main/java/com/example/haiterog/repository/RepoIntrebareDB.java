package com.example.haiterog.repository;

import com.example.haiterog.domain.Intrebare;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;

public class RepoIntrebareDB extends MemoryRepository<Intrebare> {

    private Connection conn = null;
    private final String JDBC_URL;

    public RepoIntrebareDB(String dbLocation) {
        this.JDBC_URL = "jdbc:sqlite:" + dbLocation;
        openConnection();
        createSchema();
        loadData();

    }

    private void loadData() {
        ArrayList<Intrebare> comenzi = this.getAll();
        for (Intrebare c : comenzi) {
            try {
                super.add(c);
            } catch (RepositoryException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void openConnection() {
        try {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void createSchema() {
        try {
            try (final Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Intrebari(id int,text varchar(50),raspunsA varchar(50),raspunsB varchar(50),raspunsC varchar(50),corect varchar(50),punctaj int);");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] createSchema : " + e.getMessage());
        }
    }

    @Override
    public void add(Intrebare o) throws RepositoryException {
        try {
            super.add(o);
        } catch (RepositoryException e) {
            throw new DuplicateRepository("Exista deja un element cu acest id!");
        }
        try {
            try (PreparedStatement statement = conn.prepareStatement("INSERT INTO Intrebari VALUES (?, ?,?,?,?,?,?)")) {
                statement.setInt(1, o.getId());
                statement.setString(2, o.getText());
                statement.setString(3, o.getRaspunsA());
                statement.setString(4, o.getRaspunsB());
                statement.setString(5, o.getRaspunsC());
                statement.setString(6, o.getCorect());
                statement.setInt(7, o.getPunctaj());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) throws RepositoryException {
        try {
            super.remove(id);
        } catch (RepositoryException e) {
            throw new RepositoryException("Nu exista elementul cu id-ul cautat");
        }
        try {
            try (PreparedStatement statement = conn.prepareStatement("DELETE FROM Intrebari WHERE id=?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Intrebare o) throws RepositoryException {
        try {
            super.update(o);
        } catch (RepositoryException e) {
            throw new RepositoryException("Nu exista elementul cu id-ul cautat");
        }
        try {
            conn.setAutoCommit(false);
            try (PreparedStatement updateProdus = conn.prepareStatement("UPDATE Intrebari SET text = ?,raspunsA = ?,raspunsB = ?,raspunsC = ?,corect = ?,punctaj = ? WHERE id=?")) {
                updateProdus.setString(1, o.getText());
                updateProdus.setString(2, o.getRaspunsA());
                updateProdus.setString(3, o.getRaspunsB());
                updateProdus.setString(4, o.getRaspunsC());
                updateProdus.setString(5, o.getCorect());
                updateProdus.setInt(6, o.getPunctaj());
                updateProdus.setInt(7, o.getId());
                updateProdus.executeUpdate();
                conn.commit();
                conn.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RepositoryException("Error updating record: " + ex.getMessage(), ex);
        }
    }

    @Override
    public ArrayList<Intrebare> getAll() {
        ArrayList<Intrebare> intrebari = new ArrayList<>();
        try {
            try (PreparedStatement statement = conn.prepareStatement("SELECT * from Intrebari "); ResultSet rs = statement.executeQuery();) {
                while (rs.next()) {
                    Intrebare i = new Intrebare(rs.getInt("id"), rs.getString("text"),rs.getString("raspunsA"),rs.getString("raspunsB"),rs.getString("raspunsC"),rs.getString("corect"), rs.getInt("punctaj"));
                    intrebari.add(i);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return intrebari;
    }
}