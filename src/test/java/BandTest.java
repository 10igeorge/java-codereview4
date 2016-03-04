import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class BandTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void save_savesBandIntoDatabase(){
    Band band = new Band("Modest Mouse", "info");
    band.save();
    assertEquals(Band.all().get(0), band);
  }

  @Test
  public void all_returnsAllBands_emptyAtFirst() {
    assertEquals(Band.all().size(), 0);
  }

  @Test
  public void all_ensuresAllBandsAreSavedCorrectly(){
    Band firstBand = new Band("Modest Mouse", "info");
    Band secondBand = new Band("Beach House", "info");
    firstBand.save();
    secondBand.save();
    assertEquals(Band.all().size(), 2);
  }

  @Test
  public void find_findsBandInDatabase(){
    Band firstBand = new Band("Modest Mouse", "info");
    Band secondBand = new Band("Beach House", "info");
    firstBand.save();
    secondBand.save();
    Band savedBand = Band.find(firstBand.getId());
    assertTrue(savedBand.equals(firstBand));
  }

  @Test
  public void update_updatesBandName(){
    Band firstBand = new Band("Modest House", "info");
    firstBand.save();
    firstBand.update("Modest House", "info");
    Band savedBand = Band.find(firstBand.getId());
    assertEquals(savedBand.getBandName(), "Modest House");
  }

  @Test
   public void delete_deletesObjectFromDatabase(){
     Band firstBand = new Band("Modest Mouse", "info");
     Band secondBand = new Band("Beach House", "info");
     firstBand.save();
     secondBand.save();
     firstBand.delete();
     assertFalse(Band.all().contains(firstBand));
   }

  @Test
  public void addVenue_addsAVenueToBand(){
    Band firstBand = new Band("Modest Mouse", "info");
    Band secondBand = new Band("Beach House", "info");
    firstBand.save();
    secondBand.save();
    Venue firstVenue = new Venue("Ash Street Saloon");
    Venue secondVenue = new Venue("Doug Fir");
    firstVenue.save();
    secondVenue.save();
    firstBand.addVenue(secondVenue.getId());
    firstBand.addVenue(firstVenue.getId());
    secondBand.addVenue(firstVenue.getId());
    assertTrue(firstBand.getVenues().contains(secondVenue));
    assertEquals(secondBand.getVenues().size(), 1);
  }
}
