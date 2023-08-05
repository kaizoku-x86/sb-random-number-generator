@RunWith(MockitoJUnitRunner.class)
public class RandomNumberControllerTest {

    @InjectMocks
    private RandomNumberController randomNumberController;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Test
    public void testInvalidInput_NonNumericCount() {
        // Prepare test data
        String count = "not_a_number";
        int min = 10;
        int max = 20;

        // Mock request parameters
        when(httpServletRequest.getParameter("count")).thenReturn(count);
        when(httpServletRequest.getParameter("min")).thenReturn(String.valueOf(min));
        when(httpServletRequest.getParameter("max")).thenReturn(String.valueOf(max));

        // Call the method under test
        try {
            randomNumberController.generateRandomNumbers(count, min, max);
            fail("Should throw an exception for non-numeric count");
        } catch (NumberFormatException e) {
            // Exception expected
            assertEquals("For input string: \"not_a_number\"", e.getMessage());
        }
    }

    @Test
    public void testInvalidInput_NonNumericMin() {
        // Prepare test data
        int count = 5;
        String min = "not_a_number";
        int max = 20;

        // Mock request parameters
        when(httpServletRequest.getParameter("count")).thenReturn(String.valueOf(count));
        when(httpServletRequest.getParameter("min")).thenReturn(min);
        when(httpServletRequest.getParameter("max")).thenReturn(String.valueOf(max));

        // Call the method under test
        try {
            randomNumberController.generateRandomNumbers(count, Integer.parseInt(min), max);
            fail("Should throw an exception for non-numeric min");
        } catch (NumberFormatException e) {
            // Exception expected
            assertEquals("For input string: \"not_a_number\"", e.getMessage());
        }
    }
    @Test
    public void testInvalidInput_NonNumericMax() {
        // Prepare test data
        int count = 5;
        int min = 10;
        String max = "not_a_number";

        // Mock request parameters
        when(httpServletRequest.getParameter("count")).thenReturn(String.valueOf(count));
        when(httpServletRequest.getParameter("min")).thenReturn(String.valueOf(min));
        when(httpServletRequest.getParameter("max")).thenReturn(max);

        // Call the method under test
        try {
            randomNumberController.generateRandomNumbers(count, min, Integer.parseInt(max));
            fail("Should throw an exception for non-numeric max");
        } catch (NumberFormatException e) {
            // Exception expected
            assertEquals("For input string: \"not_a_number\"", e.getMessage());
        }
    }
    public void testInvalidInput_MinGreaterThanMax() {
        // Prepare test data
        int count = 5;
        int min = 20;
        int max = 10;

        // Mock request parameters
        when(httpServletRequest.getParameter("count")).thenReturn(String.valueOf(count));
        when(httpServletRequest.getParameter("min")).thenReturn(String.valueOf(min));
        when(httpServletRequest.getParameter("max")).thenReturn(String.valueOf(max));

        // Call the method under test
        try {
            randomNumberController.generateRandomNumbers(count, min, max);
            fail("Should throw an exception for min greater than max");
        } catch (IllegalArgumentException e) {
            // Exception expected
            assertEquals("Invalid interval: min must be less than max", e.getMessage());
        }
    }
}
