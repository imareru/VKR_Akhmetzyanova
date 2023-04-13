package repository;

import entity.AnswerEntity;
import entity.QuestionEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.Types;
import java.util.ArrayList;

public class QuestionRepository implements IRestRepository<QuestionEntity>{

    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"question_id\", \"question_text\", \"image\", \"test_id\", \"answer_id\"" +
            "FROM \"questions\" " +
            "ORDER BY \"question_id\"";

    private static String selectBySourceIdQuery = "SELECT \"question_id\", \"question_text\", \"image\", \"test_id\", \"answer_id\"" +
            "FROM \"questions\" " +
            "WHERE \"question_id\" = ?";

    private static String selectByIdQuery = "SELECT \"question_id\", \"question_text\", \"image\", \"test_id\", \"answer_id\"" +
            "FROM \"questions\" " +
            "WHERE \"question_id\" = ?";

    private static String insertQuery = "INSERT INTO \"questions\"( \"question_text\", \"image\", \"test_id\", \"answer_id\") " +
            "VALUES (?, ?, ?, ?) " +
            "RETURNING \"question_id\", \"question_text\", \"image\", \"test_id\", \"answer_id\"";

    private static String updateQuery = "UPDATE \"questions\" " +
            "SET \"question_text\" = ?, \"image\" = ?, \"test_id\" = ?, \"answer_id\" = ? " +
            "WHERE \"question_id\" = ? " +
            "RETURNING \"question_id\", \"question_text\", \"image\", \"test_id\", \"answer_id\"";

    private static String deleteQuery = "DELETE FROM \"questions\" " +
            "WHERE \"question_id\" = ? " +
            "RETURNING \"question_id\", \"question_text\", \"image\", \"test_id\", \"answer_id\"";

    public QuestionRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public QuestionEntity[] select() {
        ArrayList<QuestionEntity> values = new ArrayList<QuestionEntity>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new QuestionEntity(
                            rowSet.getInt(1),
                            rowSet.getString(2),
                            rowSet.getByte(3),
                            rowSet.getInt(4),
                            rowSet.getInt(5)
                    )
            );
        }
        QuestionEntity[] result = new QuestionEntity[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public QuestionEntity select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new QuestionEntity(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getByte(3),
                rowSet.getInt(4),
                rowSet.getInt(5)
        );
    }

    @Override
    public QuestionEntity insert(QuestionEntity entity) {
        Object[] params = new Object[] {entity.getQuestion_text(), entity.getImage(), entity.getTest_id(), entity.getAnswer_id()};
        int[] types = new int[] { Types.VARCHAR, Types.BLOB, Types.INTEGER, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new QuestionEntity(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getByte(3),
                rowSet.getInt(4),
                rowSet.getInt(5)
        );
    }

    @Override
    public QuestionEntity update(Integer id, QuestionEntity entity) {
        Object[] params = new Object[] {entity.getQuestion_text(), entity.getImage(), entity.getTest_id(), entity.getAnswer_id(), id };
        int[] types = new int[] { Types.VARCHAR, Types.BLOB, Types.INTEGER, Types.INTEGER,  Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new QuestionEntity(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getByte(3),
                rowSet.getInt(4),
                rowSet.getInt(5)
        );
    }

    @Override
    public QuestionEntity delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new QuestionEntity(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getByte(3),
                rowSet.getInt(4),
                rowSet.getInt(5)
        );
    }

    public QuestionEntity[] selectBySourceId(Integer sourceId) {
        ArrayList<QuestionEntity> values = new ArrayList<QuestionEntity>();
        Object[] params = new Object[] { sourceId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectBySourceIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new QuestionEntity(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getByte(3),
                    rowSet.getInt(4),
                    rowSet.getInt(5)
            ));
        }
        QuestionEntity[] result = new QuestionEntity[values.size()];
        result = values.toArray(result);
        return result;
    }
}
