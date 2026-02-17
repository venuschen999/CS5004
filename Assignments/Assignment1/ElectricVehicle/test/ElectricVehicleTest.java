import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests - ElectricVehicle
 */
public class ElectricVehicleTest {

  // tolerance for floating-point comparisons
  private static final double EPS = 1e-9;

  private ElectricVehicle ev;

  /**
   * Runs before each test.
   * Creates a fresh ElectricVehicle for reuse.
   */
  @BeforeEach
  void setUp() {
    ev = new ElectricVehicle("EV", 50.0, 0.5, 4.0);
  }

  /**
   * Constructor: battery size should clamp to min 10.0 and max 150.0.
   */
  @Test
  void constructorClampsBatterySize() {
    ElectricVehicle evLow = new ElectricVehicle("A", 1.0, 0.5, 2.0);
    assertEquals(10.0, evLow.getBatterySize(), EPS);

    ElectricVehicle evHigh = new ElectricVehicle("B", 999.0, 0.5, 2.0);
    assertEquals(150.0, evHigh.getBatterySize(), EPS);
  }

  /**
   * Constructor: default efficiency should clamp to min 0.5 and max 4.5
   * and currentEfficiency starts as default.
   */
  @Test
  void constructorClampsDefaultEfficiencyAndSetsCurrent() {
    ElectricVehicle evLow = new ElectricVehicle("A", 50.0, 0.5, 0.1);
    assertEquals(0.5, evLow.getEfficiency(), EPS);

    ElectricVehicle evHigh = new ElectricVehicle("B", 50.0, 0.5, 10.0);
    assertEquals(4.5, evHigh.getEfficiency(), EPS);
  }

  /**
   * Constructor: state of charge should clamp to min 0.15 and max 1.0.
   */
  @Test
  void constructorClampsStateOfCharge() {
    ElectricVehicle evLow = new ElectricVehicle("A", 50.0, -1.0, 2.0);
    assertEquals(0.15, evLow.getStateOfCharge(), EPS);

    ElectricVehicle evHigh = new ElectricVehicle("B", 50.0, 2.0, 2.0);
    assertEquals(1.0, evHigh.getStateOfCharge(), EPS);
  }

  /**
   * Constructor: null/empty names should become "unknown EV".
   */
  @Test
  void constructorFixesBadName() {
    ElectricVehicle evNull = new ElectricVehicle(null, 50.0, 0.5, 2.0);
    assertEquals("unknown EV", evNull.getName());

    ElectricVehicle evEmpty = new ElectricVehicle("", 50.0, 0.5, 2.0);
    assertEquals("unknown EV", evEmpty.getName());
  }

  /**
   * updateEfficiency: in [65, 77] inclusive => 100% of default.
   */
  @Test
  void updateEfficiencyComfortRangeInclusive() {
    ev.updateEfficiency(65.0);
    assertEquals(4.0, ev.getEfficiency(), EPS);

    ev.updateEfficiency(77.0);
    assertEquals(4.0, ev.getEfficiency(), EPS);
  }

  /**
   * updateEfficiency: >77 => 85% of default.
   */
  @Test
  void updateEfficiencyHot() {
    ev.updateEfficiency(77.1);
    assertEquals(3.4, ev.getEfficiency(), EPS); // 0.85 * 4.0
  }

  /**
   * updateEfficiency: below 65 => lose 1% per degree below 65, fractional allowed.
   */
  @Test
  void updateEfficiencyColdFractional() {
    ev.updateEfficiency(64.0);   // 1.0 below => 99%
    assertEquals(3.96, ev.getEfficiency(), EPS);

    ev.updateEfficiency(64.1);   // 0.9 below => 99.1%
    assertEquals(4.0 * 0.991, ev.getEfficiency(), EPS);
  }

  /**
   * updateEfficiency: max decrease 50% (floor at 50% of default).
   */
  @Test
  void updateEfficiencyColdFloor50Percent() {
    ev.updateEfficiency(15.0);   // 50 below => exactly 50%
    assertEquals(2.0, ev.getEfficiency(), EPS);

    ev.updateEfficiency(0.0);    // more than 50 below => still 50%
    assertEquals(2.0, ev.getEfficiency(), EPS);
  }

  /**
   * range(): range = currentEfficiency * stateOfCharge * batterySize.
   */
  @Test
  void rangeUsesCurrentEfficiency() {
    assertEquals(4.0 * 0.5 * 50.0, ev.range(), EPS);

    ev.updateEfficiency(90.0); // hot => 85%
    assertEquals(3.4 * 0.5 * 50.0, ev.range(), EPS);
  }

  /**
   * setStateOfCharge: should clamp like constructor.
   */
  @Test
  void setStateOfChargeClamps() {
    ev.setStateOfCharge(-10.0);
    assertEquals(0.15, ev.getStateOfCharge(), EPS);

    ev.setStateOfCharge(2.0);
    assertEquals(1.0, ev.getStateOfCharge(), EPS);

    ev.setStateOfCharge(0.75);
    assertEquals(0.75, ev.getStateOfCharge(), EPS);
  }

  /**
   * toString(): should include name, SOC as percent with 1 decimal, and range with 1 decimal.
   */
  @Test
  void toStringMatchesFormat() {
    ElectricVehicle ev2 =
        new ElectricVehicle("Ford MachE", 100.0, 0.5, 2.636);

    assertEquals(
        "Ford MachE SOC: 50.0% Range (miles): 131.8",
        ev2.toString()
    );
  }
}
