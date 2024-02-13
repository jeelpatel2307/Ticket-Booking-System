import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Ticket {
    private String eventName;
    private int availableTickets;
    private double price;

    public Ticket(String eventName, int availableTickets, double price) {
        this.eventName = eventName;
        this.availableTickets = availableTickets;
        this.price = price;
    }

    public String getEventName() {
        return eventName;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public double getPrice() {
        return price;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    @Override
    public String toString() {
        return eventName + " - Available Tickets: " + availableTickets + ", Price: $" + price;
    }
}

class TicketBookingSystem {
    private Map<String, Ticket> events;

    public TicketBookingSystem() {
        events = new HashMap<>();
    }

    public void addEvent(String eventName, int availableTickets, double price) {
        events.put(eventName, new Ticket(eventName, availableTickets, price));
    }

    public void bookTicket(String eventName, int quantity) {
        Ticket ticket = events.get(eventName);
        if (ticket != null && ticket.getAvailableTickets() >= quantity) {
            ticket.setAvailableTickets(ticket.getAvailableTickets() - quantity);
            System.out.println("Tickets booked for " + eventName + ". Total cost: $" + (quantity * ticket.getPrice()));
        } else {
            System.out.println("Tickets not available for " + eventName + " or insufficient quantity.");
        }
    }

    public void displayAvailableEvents() {
        System.out.println("Available Events:");
        for (Ticket ticket : events.values()) {
            System.out.println(ticket);
        }
    }
}

public class TicketBookingSystemApp {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();
        system.addEvent("Concert", 100, 50.0);
        system.addEvent("Movie", 200, 15.0);
        system.addEvent("Theater", 50, 30.0);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Book tickets");
            System.out.println("2. Display available events");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter event name: ");
                    String eventName = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    system.bookTicket(eventName, quantity);
                    break;
                case 2:
                    system.displayAvailableEvents();
                    break;
                case 3:
                    System.out.println("Thank you for using the Ticket Booking System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
