package flight.reservation.plane;

public class AircraftFactory {

    // Method to create instances of different types of aircraft
    public static Object createAircraft(String type, String model) {
        switch (type) {
            case "PassengerPlane":
                return new PassengerPlane(model);
            case "Helicopter":
                return new Helicopter(model);
            case "PassengerDrone":
                return new PassengerDrone(model);
            default:
                throw new IllegalArgumentException("Aircraft type not recognized");
        }
    }
}

