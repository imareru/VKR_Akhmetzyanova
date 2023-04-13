package repository;

import entity.AnswerEntity;
import entity.SubjectEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.Types;
import java.util.ArrayList;

public class SubjectRepository implements IRestRepository<SubjectEntity> {

    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"subject_id\", \"sub_name\", \"teacher_id\"" +
            "FROM \"subjects\" " +
            "ORDER BY \"subject_id\"";

    private static String selectBySourceIdQuery = "SELECT \"subject_id\", \"sub_name\", \"teacher_id\" " +
            "FROM \"subjects\" " +
            "WHERE \"subject_id\" = ?";

    private static String selectByIdQuery = "SELECT \"subject_id\", \"sub_name\", \"teacher_id\"" +
            "FROM \"subjects\" " +
            "WHERE \"subject_id\" = ?";

    private static String insertQuery = "INSERT INTO \"subjects\"(\"sub_name\", \"teacher_id\") " +
            "VALUES (?, ?) " +
            "RETURNING \"subject_id\", \"sub_name\", \"teacher_id\"";

    private static String updateQuery = "UPDATE \"subjects\" " +
            "SET \"sub_name\" = ?, \"teacher_id\" = ? " +
            "WHERE \"subject_id\" = ? " +
            "RETURNING \"subject_id\", \"sub_name\", \"teacher_id\"";

    private static String deleteQuery = "DELETE FROM \"subjects\" " +
            "WHERE \"subject_id\" = ? " +
            "RETURNING \"subject_id\", \"sub_name\", \"teacher_id\"";

    public SubjectRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public SubjectEntity[] select() {
        ArrayList<SubjectEntity> values = new ArrayList<SubjectEntity>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new SubjectEntity(
                            rowSet.getInt(1),
                            rowSet.getString(2),
                            rowSet.getInt(3)
                    )
            );
        }
        SubjectEntity[] result = new SubjectEntity[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public SubjectEntity select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new SubjectEntity(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getInt(3)
        );
    }

    @Override
    public SubjectEntity insert(SubjectEntity entity) {
        Object[] params = new Object[] {entity.getSub_name(), entity.getTeacher_id()};
        int[] types = new int[] { Types.VARCHAR, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new SubjectEntity(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getInt(3)
        );
    }

    @Override
    public SubjectEntity update(Integer id, SubjectEntity entity) {
        Object[] params = new Object[] {entity.getSub_name(), entity.getTeacher_id(), id };
        int[] types = new int[] { Types.VARCHAR, Types.INTEGER, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new SubjectEntity(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getInt(3)
        );
    }

    @Override
    public SubjectEntity delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new SubjectEntity(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getInt(3)
        );
    }

    public SubjectEntity[] selectBySourceId(Integer sourceId) {
        ArrayList<SubjectEntity> values = new ArrayList<SubjectEntity>();
        Object[] params = new Object[] { sourceId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectBySourceIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new SubjectEntity(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getInt(3)
            ));
        }
        SubjectEntity[] result = new SubjectEntity[values.size()];
        result = values.toArray(result);
        return result;
    }
}
