package no.kantega.cloud.util;

import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class SqlUtils {
    private SqlUtils() {
        throw new IllegalAccessError("Utility class");
    }

    public static <T> List<T> executeQuery(DataSource dataSource, String query, CheckedFunction<ResultSet, T, SQLException> mapper, Object... args) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            populatePreparedStatement(ps, args);

            try (ResultSet rs = ps.executeQuery()) {
                List<T> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(mapper.apply(rs));
                }
                return list;
            }
        } catch (SQLException e) {
            log.error("Failed to execute query", e);
            return Collections.emptyList();
        }
    }

    public static int executeUpdate(DataSource dataSource, String sql, Object... args) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            populatePreparedStatement(ps, args);

            return ps.executeUpdate();
        } catch (SQLException e) {
            log.error("Failed to execute update", e);
            return 0;
        }
    }

    private static void populatePreparedStatement(PreparedStatement ps, Object[] args) throws SQLException {
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
        }
    }
}
