import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class VenueTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void save_savesVenueIntoDatabase(){
    Venue venue = new Venue("Ash Street Saloon");
    venue.save();
    assertEquals(Venue.all().get(0), venue);
  }

  @Test
  public void all_returnsAllVenues_emptyAtFirst() {
    assertEquals(Venue.all().size(), 0);
  }

  @Test
  public void all_ensuresAllVenuesAreSavedCorrectly(){
    Venue firstVenue = new Venue("Ash Street Saloon");
    Venue secondVenue = new Venue("Doug Fir");
    firstVenue.save();
    secondVenue.save();
    assertEquals(Venue.all().size(), 2);
  }

  @Test
  public void save_addsIdToLocalObject() {
    Venue firstVenue = new Venue("Ash Street Saloon");
    firstVenue.save();
    assertEquals(Venue.all().get(0).getId(), firstVenue.getId());
  }

  @Test
  public void find_findsVenueInDatabase(){
    Venue firstVenue = new Venue("Ash Street Saloon");
    Venue secondVenue = new Venue("Doug Fir");
    firstVenue.save();
    secondVenue.save();
    Venue savedVenue = Venue.find(firstVenue.getId());
    assertTrue(savedVenue.equals(firstVenue));
  }
  
  // @Test
  // public void delete_deletesVenueFromDatabase(){
  //   Venue firstVenue = new Venue("Ash Street Saloon");
  //   Venue secondVenue = new Venue("Doug Fir");
  //   firstVenue.save();
  //   secondVenue.save();
  //   firstVenue.delete();
  //   assertFalse(Venue.all().contains(firstVenue));
  // }
  //
  // @Test
  // public void update_updatesVenueInfo(){
  //   Venue firstVenue = new Venue("Ash Street Pub");
  //   firstVenue.save();
  //   firstVenue.update("Ash Street Saloon");
  //   Venue savedVenue = Venue.find(firstVenue.getId());
  //   assertEquals(savedVenue.getVenueName(), "Ash Street Saloon");
  // }
}
