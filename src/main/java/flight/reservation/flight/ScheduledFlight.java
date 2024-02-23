package flight.reservation.flight;

import flight.reservation.Airport;
import flight.reservation.Passenger;
import flight.reservation.plane.Helicopter;
import flight.reservation.plane.PassengerDrone;
import flight.reservation.plane.PassengerPlane;
import flight.reservation.flight.FlightObserver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ScheduledFlight extends Flight {

    private final List<Passenger> passengers;
    private final List<FlightObserver> observers; // List of observers
    private Date departureTime;
    private double currentPrice = 100;

    public ScheduledFlight(int number, Airport departure, Airport arrival, Object aircraft, Date departureTime) {
        super(number, departure, arrival, aircraft);
        this.departureTime = departureTime;
        this.passengers = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public ScheduledFlight(int number, Airport departure, Airport arrival, Object aircraft, Date departureTime,
            double currentPrice) {
        super(number, departure, arrival, aircraft);
        this.departureTime = departureTime;
        this.passengers = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.currentPrice = currentPrice;
    }
    
        // Register observer
    public void addObserver(FlightObserver observer) {
        observers.add(observer);
    }

    // Unregister observer
    public void removeObserver(FlightObserver observer) {
        observers.remove(observer);
    }

    // Notify observers about departure time update
    private void notifyObservers() {
        for (FlightObserver observer : observers) {
            observer.updateDepartureTime(departureTime);
        }
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
        notifyObservers(); // Notify observers when departure time changes
    }

    public int getCrewMemberCapacity() throws NoSuchFieldException {
        if (this.aircraft instanceof PassengerPlane) {
            return ((PassengerPlane) this.aircraft).crewCapacity;
        }
        if (this.aircraft instanceof Helicopter) {
            return 2;
        }
        if (this.aircraft instanceof PassengerDrone) {
            return 0;
        }
        throw new NoSuchFieldException("this aircraft has no information about its crew capacity");
    }

    public void addPassengers(List<Passenger> passengers) {
        this.passengers.addAll(passengers);
    }

    public void removePassengers(List<Passenger> passengers) {
        this.passengers.removeAll(passengers);
    }

    public int getCapacity() throws NoSuchFieldException {
        if (this.aircraft instanceof PassengerPlane) {
            return ((PassengerPlane) this.aircraft).passengerCapacity;
        }
        if (this.aircraft instanceof Helicopter) {
            return ((Helicopter) this.aircraft).getPassengerCapacity();
        }
        if (this.aircraft instanceof PassengerDrone) {
            return 4;
        }
        throw new NoSuchFieldException("this aircraft has no information about its capacity");
    }

    public int getAvailableCapacity() throws NoSuchFieldException {
        return this.getCapacity() - this.passengers.size();
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }
}
