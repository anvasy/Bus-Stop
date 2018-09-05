import java.util.Calendar;

public class Bus {
    private String companyName;
    private Calendar departureTime;
    private Calendar arrivalTime;

    public Bus(String companyName, String departureTime, String arrivalTime) {
        this.companyName = companyName;
        this.departureTime = Calendar.getInstance();
        this.departureTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(departureTime.substring(0, 2)));
        this.departureTime.set(Calendar.MINUTE, Integer.valueOf(departureTime.substring(3, 5)));
        this.arrivalTime = Calendar.getInstance();
        this.arrivalTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(arrivalTime.substring(0, 2)));
        this.arrivalTime.set(Calendar.MINUTE, Integer.valueOf(arrivalTime.substring(3, 5)));
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getTravelTime() {
        return (arrivalTime.getTimeInMillis() - departureTime.getTimeInMillis())/60000;
    }

    public Calendar getArrivalTime() {
        return arrivalTime;
    }

    public Calendar getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTimeString() {
        return String.format("%02d:%02d", arrivalTime.get(Calendar.HOUR_OF_DAY), arrivalTime.get(Calendar.MINUTE));
    }

    public String getDepartureTimeString() {
        return String.format("%02d:%02d", departureTime.get(Calendar.HOUR_OF_DAY), departureTime.get(Calendar.MINUTE));
    }
}
