
/**
 * Represents an electric vehicle (EV)
 * fields include name, battery size, state of charge, and efficiency that
 * can change based on outside temperature.
 */

public class ElectricVehicle {

  // Fields

  private String name;
  private double batterySize;
  private double stateOfCharge;
  private double currentEfficiency;
  private final double defaultEfficiency;

  // Constructor

  /**
   * Constructs a new ElectricVehicle.
   *
   * Rules:
   * - name can't be null/empty; otherwise "unknown EV"
   * - batterySize (10.0, 150.0)
   * - stateOfCharge (0.15, 1.0)
   * - defaultEfficiency (0.5, 4.5)
   * - currentEfficiency starts equal to defaultEfficiency
   *
   * @param name the EV name
   * @param batterySize the battery size (kWh)
   * @param stateOfCharge the state of charge (0.15 to 1.0)
   * @param defaultEfficiency the rated efficiency (0.5 to 4.5)
   */
  public ElectricVehicle(String name, double batterySize, double stateOfCharge, double defaultEfficiency) {
    // if name is null or empty, set name to "unknown EV"
    if (name == null || name.length() == 0) {
      this.name = "unknown EV";
    } else {
      this.name = name;
    }

    // Clamp numeric fields
    this.batterySize = clamp(batterySize, 10.0, 150.0);
    this.stateOfCharge = clamp(stateOfCharge, 0.15, 1.0);

    // defaultEfficiency never changes, so we store it in a final field
    this.defaultEfficiency = clamp(defaultEfficiency, 0.5, 4.5);

    // current efficiency starts as default efficiency
    this.currentEfficiency = this.defaultEfficiency;
  }

  // Range

  /**
   * Calculates the EV's current range.
   * range = currentEfficiency * stateOfCharge * batterySize
   *
   * @return the computed range
   */
  public double range() {
    return this.currentEfficiency * this.stateOfCharge * this.batterySize;
  }

  /**
   * Updates current efficiency based on temperature(F).
   *
   * Rules:
   * - {65.0 <= temp <= 77.0} => 100% of defaultEfficiency
   * - {temp > 77.0 } => 85% of defaultEfficiency
   * - {temp < 65.0 } => reduce by 1% per degree below 65, max 50% reduction
   *
   * @param currentTemp temperature in Fahrenheit
   */
  public void updateEfficiency(double currentTemp) {
    if (currentTemp >= 65.0 && currentTemp <= 77.0) {
      this.currentEfficiency = 1.0 * this.defaultEfficiency;
      return;
    }

    if (currentTemp > 77.0) {
      this.currentEfficiency = 0.85 * this.defaultEfficiency;
      return;
    }

    // currentTemp < 65.0
    // factor is the percentage of default efficiency the EV have at this temp
    double degreesBelow = 65.0 - currentTemp;      // fractional allowed
    double factor = 1.0 - (degreesBelow * 0.01);   // 1% per degree below

    // factor cannot go below 0.50
    if (factor < 0.50) {
      factor = 0.50;
    }

    this.currentEfficiency = factor * this.defaultEfficiency;
  }

  // Getters

  /**
   * Returns the current efficiency
   *
   * @return current efficiency
   */
  public double getEfficiency() {
    return this.currentEfficiency;
  }

  /**
   * Returns the battery size (in kWh).
   *
   * @return battery size
   */
  public double getBatterySize() {
    return this.batterySize;
  }

  /**
   * Returns the state of charge (0.15 - 1.0).
   *
   * @return state of charge as decimal
   */
  public double getStateOfCharge() {
    return this.stateOfCharge;
  }

  /**
   * Returns the EV name.
   *
   * @return name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Sets a new state of charge (0.15, 1.0).
   *
   * @param stateOfCharge new SoC decimal
   */
  public void setStateOfCharge(double stateOfCharge) {
    this.stateOfCharge = clamp(stateOfCharge, 0.15, 1.0);
  }

  // toString

  /**
   * Returns a formatted string describing the EV.
   * e.g. Ford MachE SOC: 50.0% Range (miles): 131.8
   *
   * @return formatted string
   */

  public String toString() {
    return String.format(
        "%s SOC: %.1f%% Range (miles): %.1f",
        this.name,
        this.stateOfCharge * 100.0,
        this.range()
    );
  }


  // Helper - clamp

  /**
   * Clamps forces a value to stay inside legal range (min - max).
   * if {the value < min}, returns min,
   * if {the value > max}, return max.
   *
   * @param value input value
   * @param min minimum allowed
   * @param max maximum allowed
   * @return clamped value
   */
  private static double clamp(double value, double min, double max) {
    if (value < min) {
      return min;
    }
    if (value > max) {
      return max;
    }
    return value;
  }
}
