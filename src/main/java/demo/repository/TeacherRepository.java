package demo.repository;

import demo.entity.TeacherEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class TeacherRepository implements IRestRepository<TeacherEntity> {

    protected final JdbcOperations jdbcOperations;


    private static String selectQuery = "SELECT \"teacher_id\", \"t_surname\", \"t_name\", \"t_patronymic\", \"t_login\", \"t_password\", \"t_birth_date\"" +
            "FROM \"teachers\" " +
            "ORDER BY \"teacher_id\"";

    private static String selectBySourceIdQuery = "SELECT \"teacher_id\", \"t_surname\", \"t_name\", \"t_patronymic\", \"t_login\", \"t_password\", \"t_birth_date\"" +
            "FROM \"teachers\" " +
            "WHERE \"teacher_id\" = ?";

    private static String selectByIdQuery = "SELECT \"teacher_id\", \"t_surname\", \"t_name\", \"t_patronymic\", \"t_login\", \"t_password\", \"t_birth_date\"" +
            "FROM \"teachers\" " +
            "WHERE \"teacher_id\" = ?";

    private static String insertQuery = "INSERT INTO \"teachers\"(\"t_surname\", \"t_name\", \"t_patronymic\", \"t_login\", \"t_password\", \"t_birth_date\") " +
            "VALUES (?, ?, ?, ?, ?, ?) " +
            "RETURNING \"teacher_id\", \"t_surname\", \"t_name\", \"t_patronymic\", \"t_login\", \"t_password\", \"t_birth_date\"";

    private static String updateQuery = "UPDATE \"teachers\" " +
            "SET \"teacher_id\" = ?, \"t_surname\" = ?, \"t_name\" = ?, \"t_patronymic\" = ?, \"t_login\" = ?, \"t_password\" = ?, \"t_birth_date\" = ? " +
            "WHERE \"teacher_id\" = ? " +
            "RETURNING \"teacher_id\", \"t_surname\", \"t_name\", \"t_patronymic\", \"t_login\", \"t_password\", \"t_birth_date\"";

    private static String deleteQuery = "DELETE FROM \"teachers\" " +
            "WHERE \"teacher_id\" = ? " +
            "RETURNING \"teacher_id\", \"t_surname\", \"t_name\", \"t_patronymic\", \"t_login\", \"t_password\", \"t_birth_date\"";

    public TeacherRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }


    @Override
    public TeacherEntity[] select() {
        ArrayList<TeacherEntity> values = new ArrayList<TeacherEntity>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new TeacherEntity(
                            rowSet.getInt(1),
                            rowSet.getString(2),
                            rowSet.getString(3),
                            rowSet.getString(4),
                            rowSet.getString(5),
                            rowSet.getString(6),
                            rowSet.getString(7)
                    )
            );
        }
        TeacherEntity[] result = new TeacherEntity[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public TeacherEntity select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new TeacherEntity(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getString(5),
                rowSet.getString(6),
                rowSet.getString(7)
        );
    }

    @Override
    public TeacherEntity insert(TeacherEntity entity) {
        Object[] params = new Object[] {entity.getT_surname(), entity.getT_name(), entity.getT_patronymic(), entity.getT_login(), entity.getT_password(), entity.getT_birth_date()};
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new TeacherEntity(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getString(5),
                rowSet.getString(6),
                rowSet.getString(7)
        );
    }

    @Override
    public TeacherEntity update(Integer id, TeacherEntity entity) {
        Object[] params = new Object[] {entity.getT_surname(), entity.getT_name(), entity.getT_patronymic(), entity.getT_login(), entity.getT_password(), entity.getT_birth_date(), id };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new TeacherEntity(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getString(5),
                rowSet.getString(6),
                rowSet.getString(7)
        );
    }

    @Override
    public TeacherEntity delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new TeacherEntity(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getString(5),
                rowSet.getString(6),
                rowSet.getString(7)
        );
    }

    public TeacherEntity[] selectBySourceId(Integer sourceId) {
        ArrayList<TeacherEntity> values = new ArrayList<TeacherEntity>();
        Object[] params = new Object[] { sourceId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectBySourceIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new TeacherEntity(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getString(3),
                    rowSet.getString(4),
                    rowSet.getString(5),
                    rowSet.getString(6),
                    rowSet.getString(7)
            ));
        }
        TeacherEntity[] result = new TeacherEntity[values.size()];
        result = values.toArray(result);
        return result;
    }
}
