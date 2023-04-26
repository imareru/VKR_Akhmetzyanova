package demo.repository;

import demo.entity.StudentEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class StudentRepository implements IRestRepository<StudentEntity> {

    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"student_id\", \"s_surname\", \"s_name\", \"s_patronymic\", \"s_login\", \"s_password\", \"s_birth_date\", \"class_id\"" +
            "FROM \"students\" " +
            "ORDER BY \"student_id\"";

    private static String selectBySourceIdQuery = "SELECT \"student_id\", \"s_surname\", \"s_name\", \"s_patronymic\", \"s_login\", \"s_password\", \"s_birth_date\", \"class_id\" " +
            "FROM \"students\" " +
            "WHERE \"student_id\" = ?";

    private static String selectByIdQuery = "SELECT \"student_id\", \"s_surname\", \"s_name\", \"s_patronymic\", \"s_login\", \"s_password\", \"s_birth_date\", \"class_id\" " +
            "FROM \"students\" " +
            "WHERE \"student_id\" = ?";

    private static String insertQuery = "INSERT INTO \"students\"(\"s_surname\", \"s_name\", \"s_patronymic\", \"s_login\", \"s_password\", \"s_birth_date\", \"class_id\") " +
            "VALUES (?, ?, ?, ?, ?, ?, ?) " +
            "RETURNING \"student_id\", \"s_surname\", \"s_name\", \"s_patronymic\", \"s_login\", \"s_password\", \"s_birth_date\", \"class_id\"";

    private static String updateQuery = "UPDATE \"students\" " +
            "SET \"student_id\" = ?, \"s_surname\" = ?, \"s_name\" = ?, \"s_patronymic\" = ?, \"s_login\" = ?, \"s_password\" = ?, \"s_birth_date\" = ?, \"class_id\" = ? " +
            "WHERE \"student_id\" = ? " +
            "RETURNING \"student_id\", \"s_surname\", \"s_name\", \"s_patronymic\", \"s_login\", \"s_password\", \"s_birth_date\", \"class_id\"";

    private static String deleteQuery = "DELETE FROM \"students\" " +
            "WHERE \"student_id\" = ? " +
            "RETURNING \"student_id\", \"s_surname\", \"s_name\", \"s_patronymic\", \"s_login\", \"s_password\", \"s_birth_date\", \"class_id\"";

    public StudentRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public StudentEntity[] select() {
        ArrayList<StudentEntity> values = new ArrayList<StudentEntity>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new StudentEntity(
                            rowSet.getInt(1),
                            rowSet.getString(2),
                            rowSet.getString(3),
                            rowSet.getString(4),
                            rowSet.getString(5),
                            rowSet.getString(6),
                            rowSet.getString(7),
                            rowSet.getInt(8)
                    )
            );
        }
        StudentEntity[] result = new StudentEntity[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public StudentEntity select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new StudentEntity(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getString(5),
                rowSet.getString(6),
                rowSet.getString(7),
                rowSet.getInt(8)
        );
    }

    @Override
    public StudentEntity insert(StudentEntity entity) {
        Object[] params = new Object[] {entity.getS_surname(), entity.getS_name(), entity.getS_patronymic(), entity.getS_login(), entity.getS_password(), entity.getS_birth_date(), entity.getClass_id()};
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new StudentEntity(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getString(5),
                rowSet.getString(6),
                rowSet.getString(7),
                rowSet.getInt(8)
        );
    }

    @Override
    public StudentEntity update(Integer id, StudentEntity entity) {
        Object[] params = new Object[] {entity.getS_surname(), entity.getS_name(), entity.getS_patronymic(), entity.getS_login(), entity.getS_password(), entity.getS_birth_date(), entity.getClass_id(), id };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new StudentEntity(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getString(5),
                rowSet.getString(6),
                rowSet.getString(7),
                rowSet.getInt(8)
        );
    }

    @Override
    public StudentEntity delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new StudentEntity(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getString(5),
                rowSet.getString(6),
                rowSet.getString(7),
                rowSet.getInt(8)
        );
    }

    public StudentEntity[] selectBySourceId(Integer sourceId) {
        ArrayList<StudentEntity> values = new ArrayList<StudentEntity>();
        Object[] params = new Object[] { sourceId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectBySourceIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new StudentEntity(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getString(3),
                    rowSet.getString(4),
                    rowSet.getString(5),
                    rowSet.getString(6),
                    rowSet.getString(7),
                    rowSet.getInt(8)
            ));
        }
        StudentEntity[] result = new StudentEntity[values.size()];
        result = values.toArray(result);
        return result;
    }
}
