package repository;

import entity.AnswerEntity;
import entity.ClassEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.Types;
import java.util.ArrayList;

public class ClassRepository implements IRestRepository<ClassEntity> {

    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"class_id\", \"class_number\"" +
            "FROM \"classes\" " +
            "ORDER BY \"class_id\"";

    private static String selectBySourceIdQuery = "SELECT \"class_id\", \"class_number\" " +
            "FROM \"classes\" " +
            "WHERE \"class_id\" = ?";

    private static String selectByIdQuery = "SELECT \"class_id\", \"class_number\" " +
            "FROM \"classes\" " +
            "WHERE \"class_id\" = ?";

    private static String insertQuery = "INSERT INTO \"classes\"(\"class_number\") " +
            "VALUES (?, ?) " +
            "RETURNING \"class_id\", \"class_number\"";

    private static String updateQuery = "UPDATE \"classes\" " +
            "SET \"class_number\" = ?" +
            "WHERE \"class_id\" = ? " +
            "RETURNING \"class_id\", \"class_number\"";

    private static String deleteQuery = "DELETE FROM \"classes\" " +
            "WHERE \"class_id\" = ? " +
            "RETURNING \"class_id\", \"class_number\"";

    public ClassRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public ClassEntity[] select() {
        ArrayList<ClassEntity> values = new ArrayList<ClassEntity>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new ClassEntity(
                            rowSet.getInt(1),
                            rowSet.getInt(2)
                    )
            );
        }
        ClassEntity[] result = new ClassEntity[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public ClassEntity select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new ClassEntity(
                rowSet.getInt(1),
                rowSet.getInt(2)
        );
    }

    @Override
    public ClassEntity insert(ClassEntity entity) {
        Object[] params = new Object[] {entity.getClass_number()};
        int[] types = new int[] { Types.VARCHAR, Types.BOOLEAN };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new ClassEntity(
                rowSet.getInt(1),
                rowSet.getInt(2)
        );
    }

    @Override
    public ClassEntity update(Integer id, ClassEntity entity) {
        Object[] params = new Object[] {entity.getClass_number(), id };
        int[] types = new int[] { Types.INTEGER, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new ClassEntity(
                rowSet.getInt(1),
                rowSet.getInt(2)
        );
    }

    @Override
    public ClassEntity delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new ClassEntity(
                rowSet.getInt(1),
                rowSet.getInt(2)
        );
    }

    public ClassEntity[] selectBySourceId(Integer sourceId) {
        ArrayList<ClassEntity> values = new ArrayList<ClassEntity>();
        Object[] params = new Object[] { sourceId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectBySourceIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new ClassEntity(
                    rowSet.getInt(1),
                    rowSet.getInt(2)
            ));
        }
        ClassEntity[] result = new ClassEntity[values.size()];
        result = values.toArray(result);
        return result;
    }
}
