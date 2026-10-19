package backend.repository.impl;


import backend.repository.IPositionRepository;
import entity.Position;
import entity.PositionName;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PositionRepositoryImpl implements IPositionRepository {
    @Override
    public List<Position> findAll() {
        List<Position> positions = new ArrayList<>();
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from position";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int positionId = resultSet.getInt("position_id");
                PositionName positionName = PositionName.valueOf(resultSet.getString("position_name"));
                Position position = new Position(positionId, positionName);
                positions.add(position);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            JDBCUtils.closeConnection(connection);
        }
        return positions;
    }
}
