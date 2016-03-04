import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Concerts");
  }

  @Test
  public void addBand(){
    goTo("http://localhost:4567/");
    fill("#bandName").with("Modest Mouse");
    submit("#createBand");
    assertThat(pageSource()).contains("Modest Mouse has successfully been added!");
  }

  @Test
  public void addVenue(){
    goTo("http://localhost:4567/");
    fill("#venueName").with("Doug Fir");
    submit("#createVenue");
    assertThat(pageSource()).contains("Doug Fir has successfully been added to the venue list!");
  }

  @Test
  public void listAllBands() {
    Band firstBand = new Band("Modest Mouse");
    Band secondBand = new Band("Beach House");
    firstBand.save();
    secondBand.save();
    goTo("http://localhost:4567/bands");
    assertThat(pageSource()).contains("Modest Mouse");
    assertThat(pageSource()).contains("Beach House");
  }

  @Test
  public void listAllVenues() {
    Venue firstVenue = new Venue("Ash Street Saloon");
    Venue secondVenue = new Venue("Doug Fir");
    firstVenue.save();
    secondVenue.save();
    goTo("http://localhost:4567/venues");
    assertThat(pageSource()).contains("Ash Street Saloon");
    assertThat(pageSource()).contains("Doug Fir");
  }
}
