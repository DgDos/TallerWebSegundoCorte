package dao;

import model.Tabla;
import util.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TablaDAO {

    private Connection connection;

    public TablaDAO() {
        connection = DbUtil.getConnection();
    }

    public void addTabla(Tabla tabla) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into tabla(nombre_tabla, id_esquema) values (?, ?)");
        preparedStatement.setString(1, tabla.getNombre_tabla());
        preparedStatement.setInt(2, tabla.getId_esquema());
        preparedStatement.executeUpdate();
    }

    public void deleteTabla(int id_tabla) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from tabla where id_tabla=?");
        preparedStatement.setInt(1, id_tabla);
        preparedStatement.executeUpdate();
    }

    public void updateTabla(Tabla tabla) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update tabla set nombre_tabla=?, id_esquema=?" + "where id_tabla=?");
        preparedStatement.setString(1, tabla.getNombre_tabla());
        preparedStatement.setInt(2, tabla.getId_esquema());
        preparedStatement.executeUpdate();
    }

    public List<Tabla> getAllTables() throws SQLException {
        List<Tabla> tablas = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from tabla");
        while (rs.next()) {
            Tabla tabla = new Tabla();
            tabla.setId_tabla(rs.getInt("id_tabla"));
            tabla.setNombre_tabla(rs.getString("nombre_tabla"));
            tabla.setId_esquema(rs.getInt("id_esquema"));
            tablas.add(tabla);
        }
        return tablas;
    }

    public Tabla getTablaById(int id_tabla) throws SQLException {
        Tabla tabla = new Tabla();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from tabla where id_tabla=?");
        preparedStatement.setInt(1, id_tabla);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            tabla.setId_tabla(rs.getInt("id_tabla"));
            tabla.setNombre_tabla(rs.getString("nombre_tabla"));
            tabla.setId_esquema(rs.getInt("id_esquema"));
        }
        return tabla;
    }

}
