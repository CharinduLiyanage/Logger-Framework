package lk.ac.iit.asd.charindu;

/**
 * Enumeration of all supported logging backends.
 * Used by {@link LogRepositoryFactory} to decide which
 * {@link LogRepository} implementation to instantiate.
 */
public enum LogRepositoryType {
    /**
     * Write log messages to the console (standard output).
     */
    CONSOLE,
    /**
     * Append log messages to a plain text file.
     */
    FILE,
    /**
     * Write log messages into an XML file with a root &lt;log&gt; element.
     */
    XML,
    /**
     * Insert log messages into a relational database via JDBC.
     */
    DB
}
