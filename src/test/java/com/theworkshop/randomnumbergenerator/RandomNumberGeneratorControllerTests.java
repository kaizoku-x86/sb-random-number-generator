import com.theworkshop.randomnumbergenerator.controller.RandomNumberGeneratorController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(RandomNumberGeneratorController.class)
@ContextConfiguration(classes = RandomNumberGeneratorController.class)
public class RandomNumberGeneratorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private RandomNumberGeneratorController randomNumberGeneratorController;

    private Random random;

    @BeforeEach
    public void setup() {
        random = new Random();
    }

    @Test
    public void testGenerateRandomNumbers_Success() throws Exception {
        int count = 5;
        int min = 10;
        int max = 50;

        mockMvc.perform(get("/generateRandomNumbers")
                .param("count", String.valueOf(count))
                .param("min", String.valueOf(min))
                .param("max", String.valueOf(max))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(count));
    }

    @Test
    public void testGenerateRandomNumbers_InvalidCount() throws Exception {
        int count = -1;
        int min = 10;
        int max = 50;

        mockMvc.perform(get("/generateRandomNumbers")
                .param("count", String.valueOf(count))
                .param("min", String.valueOf(min))
                .param("max", String.valueOf(max))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGenerateRandomNumbers_InvalidMinMax() throws Exception {
        int count = 5;
        int min = 50;
        int max = 10; // Invalid min > max

        mockMvc.perform(get("/generateRandomNumbers")
                .param("count", String.valueOf(count))
                .param("min", String.valueOf(min))
                .param("max", String.valueOf(max))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
