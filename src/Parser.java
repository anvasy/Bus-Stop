import java.io.*;
import java.util.*;

public class Parser {
    private List<Bus> timetable;

    public Parser() {
        timetable = new ArrayList<>();
    }

    public void justDoIt() {
        read();

        for (int i = 0; i < timetable.size(); i++) {
            Bus bus = timetable.get(i);
            if(bus.getTravelTime() <= 60) {
                for (int j = i + 1; j < timetable.size(); j++) {
                    Bus current = timetable.get(j);
                    if(current.getDepartureTime().equals(bus.getDepartureTime())
                            && current.getArrivalTime().before(bus.getArrivalTime())) {
                        timetable.remove(bus);
                        i--;
                        break;
                    }
                    else if(current.getDepartureTime().after(bus.getDepartureTime())
                            & current.getArrivalTime().before(bus.getArrivalTime())) {
                        timetable.remove(bus);
                        i--;
                        break;
                    }
                    else if(current.getDepartureTime().after(bus.getDepartureTime())
                            && current.getArrivalTime().equals(bus.getArrivalTime())) {
                        timetable.remove(bus);
                        i--;
                        break;
                    }
                    else if(current.getDepartureTime().equals(bus.getDepartureTime())
                            && current.getArrivalTime().equals(bus.getArrivalTime())){
                        if(bus.getCompanyName().equals("Grotty")) {
                            timetable.remove(bus);
                            i--;
                        } else {
                            timetable.remove(current);
                        }
                        break;
                    }
                }
            } else {
                timetable.remove(bus);
                i--;
            }
        }

        for(int i = timetable.size()-1; i > 0 ; i--) {
            for (int j = 0; j < i; j++) {
                if (timetable.get(j).getDepartureTime().after(timetable.get(j + 1).getDepartureTime())) {
                    Bus tmp = timetable.get(j);
                    timetable.set(j, timetable.get(j + 1));
                    timetable.set(j + 1, tmp);
                }
            }
        }

        ArrayList<Bus> resultGrotty = new ArrayList<>();

        for (int i = 0; i < timetable.size(); i++) {
            if(timetable.get(i).getCompanyName().equals("Grotty")) {
                resultGrotty.add(timetable.get(i));
                timetable.remove(i);
                i--;
            }
        }

        write(arrayToString(timetable, resultGrotty));
    }

    private void read() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            String s;
            try {
                while ((s = br.readLine()) != null) {
                    String[] current = s.split(" ");
                    timetable.add(new Bus(current[0], current[1], current[2]));
                }
            } finally {
                br.close();
            }
        }
         catch (IOException ex) {
            System.out.println(ex.getMessage());
         }
    }

    private void write(StringBuilder data) {
        File file = new File("output.txt");

        try {
            if(!file.exists()){
                file.createNewFile();
            }

            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                out.print(data);
            } finally {
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    private StringBuilder arrayToString(List<Bus> posh, ArrayList<Bus> grotty) {
        StringBuilder sb = new StringBuilder();

        for (Bus bus : posh) {
            sb.append(bus.getCompanyName() + " " + bus.getDepartureTimeString()+ " " + bus.getArrivalTimeString() );
            sb.append("\n");
        }

        sb.append("\n");

        for (Bus bus : grotty) {
            sb.append(bus.getCompanyName() + " " + bus.getDepartureTimeString()+ " " + bus.getArrivalTimeString() );
            sb.append("\n");
        }

        sb.deleteCharAt(sb.length() - 1);

        return sb;
    }
}
