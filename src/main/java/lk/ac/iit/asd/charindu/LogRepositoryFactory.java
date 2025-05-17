package lk.ac.iit.asd.charindu;

import java.sql.SQLException;

/**
 * Factory for creating {@link LogRepository} instances based on a type
 * and an optional target (file path or JDBC URL).
 */
public class LogRepositoryFactory {

    /**
     * Creates and returns a repository of the requested type.
     *
     * @param type   The kind of repository to create (console, file, XML, or DB).
     * @param target For FILE and XML: the file path; for DB: the JDBC URL.
     *               Ignored when type == CONSOLE.
     * @return A new instance of {@link LogRepository}.
     * @throws IllegalArgumentException if {@code type} is unrecognized.
     * @throws Error                    if there is an SQL error creating a DB repo.
     */
    public static LogRepository create(LogRepositoryType type, String target)
            throws IllegalArgumentException {
        try {
            return switch (type) {
                case CONSOLE -> new ConsoleLogRepository();
                case FILE -> new FileLogRepository(target);
                case XML -> new XMLFileLogRepository(target);
                case DB -> new DatabaseLogRepository(target);
                default -> throw new IllegalArgumentException(
                        "Unknown repository type: " + type);
            };
        } catch (SQLException e) {
            // Wrap SQLExceptions from DatabaseLogRepository in an unchecked Error
            throw new Error("Error creating DatabaseLogRepository: " + e.getMessage(), e);
        }
    }
}