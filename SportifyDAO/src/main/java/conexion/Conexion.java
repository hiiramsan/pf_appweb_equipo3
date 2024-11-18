package conexion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexion {
    protected static EntityManagerFactory emf;
    
    static {
        try {
            emf = Persistence.createEntityManagerFactory("com.mycompany_SportifyDAO_jar_1.0-SNAPSHOTPU");
            System.out.println("EntityManagerFactory created successfully");
        } catch (Exception e) {
            System.err.println("Error al crear el EntityManagerFactory: " + e.getMessage());
            e.printStackTrace(); // This will print the full stack trace for debugging
        }
    }
    
    // Make this static
    public static EntityManager getEntityManager() {
        if (emf == null) {
            try {
                emf = Persistence.createEntityManagerFactory("com.mycompany_SportifyDAO_jar_1.0-SNAPSHOTPU");
            } catch (Exception e) {
                throw new RuntimeException("Failed to initialize EntityManagerFactory", e);
            }
        }
        return emf.createEntityManager();
    }
    
    // Make this static
    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}