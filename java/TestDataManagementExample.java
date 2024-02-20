import org.junit.*;

public class TestDataManagementExample {
    // Problem: Static, Hard-Coded Test Data
    @Test
    public void testWithStaticData() {
        // Use static data, leading to potential test data pollution
        String userId = "staticUserId"; // Static user ID
    }

    // Solution: Dynamic Test Data via API
    private String dynamicUserId;

    @Before
    public void setUp() {
        // Dynamically create test data
        dynamicUserId = APIClient.create("/users", "dynamicUser");
    }

    @Test
    public void testWithDynamicData() {
        // Use dynamically created user ID
        System.out.println("Testing with user: " + dynamicUserId);
        // Test logic here
    }

    @After
    public void tearDown() {
        // Clean up dynamic test data
        APIClient.delete("/users/" + dynamicUserId);
    }
}
