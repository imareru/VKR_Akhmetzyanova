package demo.repository;

import demo.entity.ResultEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class ResultRepository implements IRestRepository<ResultEntity> {

    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"result_id\", \"student_id\", \"test_id\", \"passed_time\", \"pass_date\", \"score\"" +
            "FROM \"results\" " +
            "ORDER BY \"result_id\"";

    private static String selectBySourceIdQuery = "SELECT \"result_id\", \"student_id\", \"test_id\", \"passed_time\", \"pass_date\", \"score\"" +
            "FROM \"results\" " +
            "WHERE \"result_id\" = ?";

    private static String selectByIdQuery = "SELECT \"result_id\", \"student_id\", \"test_id\", \"passed_time\", \"pass_date\", \"score\"" +
            "FROM \"results\" " +
            "WHERE \"result_id\" = ?";

    private static String insertQuery = "INSERT INTO \"results\"(\"student_id\", \"test_id\", \"passed_time\", \"pass_date\", \"score\") " +
            "VALUES (?, ?) " +
            "RETURNING \"result_id\", \"student_id\", \"test_id\", \"passed_time\", \"pass_date\", \"score\"";

    private static String updateQuery = "UPDATE \"results\" " +
            "SET \"student_id\" = ?, \"test_id\" = ?, \"passed_time\" = ?, \"pass_date\" = ?, \"score\" = ? " +
            "WHERE \"result_id\" = ? " +
            "RETURNING \"result_id\", \"student_id\", \"test_id\", \"passed_time\", \"pass_date\", \"score\"";

    private static String deleteQuery = "DELETE FROM \"results\" " +
            "WHERE \"result_id\" = ? " +
            "RETURNING \"result_id\", \"student_id\", \"test_id\", \"passed_time\", \"pass_date\", \"score\"";

    public ResultRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public ResultEntity[] select() {
        ArrayList<ResultEntity> values = new ArrayList<ResultEntity>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new ResultEntity(
                            rowSet.getInt(1),
                            rowSet.getInt(2),
                            rowSet.getInt(3),
                            rowSet.getString(4),
                            rowSet.getString(5),
                            rowSet.getInt(6)
                    )
            );
        }
        ResultEntity[] result = new ResultEntity[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public ResultEntity select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new ResultEntity(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getString(4),
                rowSet.getString(5),
                rowSet.getInt(6)
        );
    }

    @Override
    public ResultEntity insert(ResultEntity entity) {
        Object[] params = new Object[] {entity.getStudent_id(), entity.getTest_id(), entity.getPassed_time(), entity.getPass_date(), entity.getScore()};
        int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.TIME, Types.DATE, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new ResultEntity(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getString(4),
                rowSet.getString(5),
                rowSet.getInt(6)
        );
    }

    @Override
    public ResultEntity update(Integer id, ResultEntity entity) {
        Object[] params = new Object[] {entity.getStudent_id(), entity.getTest_id(), entity.getPassed_time(), entity.getPass_date(), entity.getScore(), id };
        int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.TIME, Types.DATE, Types.INTEGER, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new ResultEntity(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getString(4),
                rowSet.getString(5),
                rowSet.getInt(6)
        );
    }

    @Override
    public ResultEntity delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new ResultEntity(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getString(4),
                rowSet.getString(5),
                rowSet.getInt(6)
        );
    }

    public ResultEntity[] selectBySourceId(Integer sourceId) {
        ArrayList<ResultEntity> values = new ArrayList<ResultEntity>();
        Object[] params = new Object[] { sourceId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectBySourceIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new ResultEntity(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getString(4),
                    rowSet.getString(5),
                    rowSet.getInt(6)
            ));
        }
        ResultEntity[] result = new ResultEntity[values.size()];
        result = values.toArray(result);
        return result;
    }
}
