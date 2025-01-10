import config.DataSource;
import org.example.entity.Borrowing;
import org.example.repository.BorrowingRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Formatter;
import java.util.Optional;

public class BorrowingRepositoryImpl implements BorrowingRepository {
        @Override
        public Borrowing get(String id) throws SQLException {
                try (
                                Connection connection = DataSource.getConnection()) {
                        String sql = """
                                        SELECT * FROM borrowings WHERE id = ?;
                                        """;
                        PreparedStatement ps = connection.prepareStatement(sql);
                        ps.setInt(1, Integer.parseInt(id));

                        ResultSet rs = ps.executeQuery();

                        if (!rs.next())
                                return null;

                        return mapResultSetToBorrowing(rs);
                }
        }

        @Override
        public Collection<Borrowing> get() throws SQLException {
                String sql = """
                                SELECT * FROM borrowings LIMIT 100;
                                """;
                try (
                                Connection connection = DataSource.getConnection()) {
                        PreparedStatement ps = connection.prepareStatement(sql);
                        ResultSet rs = ps.executeQuery();
                        Collection<Borrowing> borrowings = new ArrayList<>();
                        while (rs.next()) {
                                Borrowing borrowing = mapResultSetToBorrowing(rs);
                                borrowings.add(borrowing);
                        }
                        return borrowings;
                }
        }

        @Override
        public Borrowing create(Borrowing entity) throws SQLException {
                String sql = """
                                INSERT INTO borrowings
                                (due_date, return_date, book_id, patron_id)
                                VALUES (?, ?, ?, ?)
                                RETURNING *;
                                """;
                try (
                                Connection connection = DataSource.getConnection()) {
                        PreparedStatement ps = connection.prepareStatement(sql);
                        ps.setTimestamp(1, Timestamp.valueOf(entity.getDueDate()));

                        if (entity.getReturnDate() == null) {
                                ps.setTimestamp(2, null);
                        } else {
                                ps.setTimestamp(2, Timestamp.valueOf(entity.getReturnDate()));
                        }

                        ps.setInt(3, Integer.parseInt(entity.getBookId()));
                        ps.setInt(4, Integer.parseInt(entity.getPatronId()));

                        ps.execute();
                        ResultSet rs = ps.getResultSet();
                        if (!rs.next())
                                throw new SQLException("Cannot create borrowing");

                        return mapResultSetToBorrowing(rs);
                }
        }

        @Override
        public int update(Borrowing entity) throws SQLException {
                String sql = """
                                UPDATE borrowings
                                SET borrowed_date = ?,
                                due_date = ?,
                                return date = ?,
                                book_id = ?,
                                patron_id = ?
                                WHERE id = ?;
                                """;
                try (
                                Connection connection = DataSource.getConnection()) {
                        PreparedStatement ps = connection.prepareStatement(sql);
                        ps.setTimestamp(1, Timestamp.valueOf(entity.getDueDate()));
                        ps.setTimestamp(2, Timestamp.valueOf(entity.getReturnDate()));
                        ps.setInt(3, Integer.parseInt(entity.getBookId()));
                        ps.setInt(4, Integer.parseInt(entity.getPatronId()));

                        return ps.executeUpdate();
                }
        }

        @Override
        public int delete(Borrowing entity) throws SQLException {
                String sql = """
                                DELETE FROM borrowings
                                WHERE id = ?;
                                """;

                try (
                                Connection connection = DataSource.getConnection()) {
                        PreparedStatement ps = connection.prepareStatement(sql);
                        ps.setInt(1, Integer.parseInt(entity.getId()));
                        return ps.executeUpdate();
                }
        }

        private static Borrowing mapResultSetToBorrowing(ResultSet rs) throws SQLException {

                Borrowing borrowing = new Borrowing();
                borrowing.setId(rs.getString("id"));

                borrowing.setBorrowedDate(
                                parseToLocalDateTime(rs.getString("borrowed_date")));
                borrowing.setDueDate(
                                parseToLocalDateTime(rs.getString("due_date")));
                borrowing.setDueDate(
                                parseToLocalDateTime(rs.getString("due_date")));
                if (borrowing.getReturnDate() != null) {
                        borrowing.setDueDate(
                                        parseToLocalDateTime(rs.getString("return_date")));
                }
                borrowing.setBookId(
                                String.valueOf(rs.getInt("book_id")));
                borrowing.setBookId(
                                String.valueOf(rs.getInt("patron_id")));
                return borrowing;
        }

        private static LocalDateTime parseToLocalDateTime(String date) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
                return LocalDateTime.parse(date, dateTimeFormatter);
        }
}
