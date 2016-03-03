import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class BandTest {
  @Test
  public void all_returnsAllBands_emptyAtFirst() {
    assertEquals(Band.all().size(), 0);
  }
}
