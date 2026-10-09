package backend.repository.impl;

import backend.repository.IPositionRepository;
import entity.Position;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PositionRepositoryImpl implements IPositionRepository {
    @Override
    public List<Position> hienThiPos() {
        List<Position> positionList = new ArrayList<>();
        Connection connection = JDBCUtils.getConnection();

        try {
            String sql = "SELECT * FROM position order by position_id asc";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt("position_id");
                String positionName = resultSet.getString("position_name");

                Position pos = new Position(id, positionName);
                positionList.add(pos);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeConnection();
        }
        return positionList;
    }
}
