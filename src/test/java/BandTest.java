import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class BandTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void save_savesBandIntoDatabase(){
    Band band = new Band("Modest Mouse");
    band.save();
    assertEquals(Band.all().get(0), band);
  }

  @Test
  public void all_returnsAllBands_emptyAtFirst() {
    assertEquals(Band.all().size(), 0);
  }

  @Test
  public void all_ensuresAllBandsAreSavedCorrectly(){
    Band firstBand = new Band("Modest Mouse");
    Band secondBand = new Band("Beach House");
    firstBand.save();
    secondBand.save();
    assertEquals(Band.all().size(), 2);
  }

  @Test
  public void find_findsBandInDatabase(){
    Band firstBand = new Band("Modest Mouse");
    Band secondBand = new Band("Beach House");
    firstBand.save();
    secondBand.save();
    Band savedBand = Band.find(firstBand.getId());
    assertTrue(savedBand.equals(firstBand));
  }

  @Test
  public void delete_deletesBandFromDatabase(){
    Band firstBand = new Band("Modest Mouse");
    Band secondBand = new Band("Beach House");
    firstBand.save();
    secondBand.save();
    firstBand.delete();
    assertFalse(Band.all().contains(firstBand));
  }

  @Test
  public void update_updatesBandInfo(){
    Band firstBand = new Band("Modest House");
    firstBand.save();
    firstBand.update("Modest Mouse");
    Band savedBand = Band.find(firstBand.getId());
    assertEquals(savedBand.getBandName(), "Modest Mouse");
  }
}
