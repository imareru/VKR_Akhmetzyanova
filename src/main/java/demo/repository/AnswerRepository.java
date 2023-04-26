package demo.repository;

import demo.entity.AnswerEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class AnswerRepository implements IRestRepository<AnswerEntity> {

    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"answer_id\", \"answer_text\", \"bool_val\"" +
            "FROM \"answers\" " +
            "ORDER BY \"answer_id\"";

    private static String selectBySourceIdQuery = "SELECT \"answer_id\", \"answer_text\", \"bool_val\" " +
            "FROM \"answers\" " +
            "WHERE \"answer_id\" = ?";

    private static String selectByIdQuery = "SELECT \"answer_id\", \"answer_text\", \"bool_val\" " +
            "FROM \"answers\" " +
            "WHERE \"answer_id\" = ?";

    private static String insertQuery = "INSERT INTO \"answers\"(\"answer_text\", \"bool_val\") " +
            "VALUES (?, ?) " +
            "RETURNING \"answer_id\", \"answer_text\", \"bool_val\"";

    private static String updateQuery = "UPDATE \"answers\" " +
            "SET \"answer_text\" = ?, \"bool_val\" = ? " +
            "WHERE \"answer_id\" = ? " +
            "RETURNING \"answer_id\", \"answer_text\", \"bool_val\"";

    private static String deleteQuery = "DELETE FROM \"answers\" " +
            "WHERE \"answer_id\" = ? " +
            "RETURNING \"answer_id\", \"answer_text\", \"bool_val\"";

    public AnswerRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public AnswerEntity[] select() {
        ArrayList<AnswerEntity> values = new ArrayList<AnswerEntity>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new AnswerEntity(
                            rowSet.getInt(1),
                            rowSet.getString(2),
                            rowSet.getBoolean(3)
                    )
            );
        }
        AnswerEntity[] result = new AnswerEntity[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public AnswerEntity select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new AnswerEntity(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getBoolean(3)
        );
    }

    @Override
    public AnswerEntity insert(AnswerEntity entity) {
        Object[] params = new Object[] {entity.getAnswer_text(), entity.getBool_val()};
        int[] types = new int[] { Types.VARCHAR, Types.BOOLEAN };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new AnswerEntity(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getBoolean(3)
        );
    }

    @Override
    public AnswerEntity update(Integer id, AnswerEntity entity) {
        Object[] params = new Object[] {entity.getAnswer_text(), entity.getBool_val(), id };
        int[] types = new int[] { Types.VARCHAR, Types.BOOLEAN, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new AnswerEntity(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getBoolean(3)
        );
    }

    @Override
    public AnswerEntity delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new AnswerEntity(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getBoolean(3)
        );
    }

    public AnswerEntity[] selectBySourceId(Integer sourceId) {
        ArrayList<AnswerEntity> values = new ArrayList<AnswerEntity>();
        Object[] params = new Object[] { sourceId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectBySourceIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new AnswerEntity(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getBoolean(3)
            ));
        }
        AnswerEntity[] result = new AnswerEntity[values.size()];
        result = values.toArray(result);
        return result;
    }
}
