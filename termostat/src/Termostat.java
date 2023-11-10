import java.util.Scanner;

public class Termostat {
    public double aktualnaTemperatura = 20.0;
    public double ustawionaTemperatura = 22.0;
    public boolean ogrzewanieWlaczone = false;
    public boolean chlodzenieWlaczone = false;

    public Termostat() {
        aktualnaTemperatura = 25.0;
        ustawionaTemperatura = 500.0;
    }

    public void wlaczOgrzewanie() {
        ogrzewanieWlaczone = true;
        chlodzenieWlaczone = false;
        System.out.println("Włączono ogrzewanie.");
    }


    public void wlaczChlodzenie() {
        chlodzenieWlaczone = true;
        ogrzewanieWlaczone = false;
        System.out.println("Włączono chłodzenie.");
    }

    public void wylaczOgrzewanie() {
        ogrzewanieWlaczone = false;
        System.out.println("Wyłączono ogrzewanie.");

    }


    public void wylaczChlodzenie() {
        chlodzenieWlaczone = false;
        System.out.println("Wyłączono chłodzenie.");
    }

    public void sprawdzTemperature() {
        if (aktualnaTemperatura < ustawionaTemperatura && !ogrzewanieWlaczone) {
            wlaczOgrzewanie();
        } else if (aktualnaTemperatura > ustawionaTemperatura && !chlodzenieWlaczone) {
            wlaczChlodzenie();
        } else if (aktualnaTemperatura == ustawionaTemperatura) {
            wylaczOgrzewanie();
            wylaczChlodzenie();
        }
    }

    public double getAktualnaTemperatura() {
        return aktualnaTemperatura;
    }

    public double getUstawionaTemperatura() {
        return ustawionaTemperatura;
    }

    public void zmienTemperature(double zmiana) {
        aktualnaTemperatura = zmiana;
        System.out.println("Aktualna temperatura: " + aktualnaTemperatura + " stopni Celsjusza");
        sprawdzTemperature();
    }

    public void zmienUstawionaTemperature(double nowaTemperatura) {
        ustawionaTemperatura = nowaTemperatura;
        System.out.println("Nowa ustawiona temperatura: " + ustawionaTemperatura + " stopni Celsjusza");
        sprawdzTemperature();
    }

    public static void main(String[] args) {
        Termostat termostat = new Termostat();
        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            System.out.println("Aktualna temperatura: " + termostat.getAktualnaTemperatura() + " stopni Celsjusza");
            System.out.println("Ustawiona temperatura: " + termostat.getUstawionaTemperatura() + " stopni Celsjusza");

            System.out.print("Podaj komendę (ogrzewanie/chlodzenie), nową temperaturę lub 'wyjscie' aby zakończyć: ");
            userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("ogrzewanie")) {
                if (termostat.ustawionaTemperatura<30){
                    System.out.println(termostat.ustawionaTemperatura);
                    termostat.wlaczOgrzewanie();
                    termostat.aktualnaTemperatura += 1.0;
                    termostat.sprawdzTemperature();
                    if(termostat.aktualnaTemperatura>termostat.ustawionaTemperatura){
                        System.out.println("Nie mozesz juz wiecej ustawic bo za goraco jest");
                        termostat.aktualnaTemperatura-=1;
                    }
                }
                else if (termostat.ustawionaTemperatura>=30){
                    System.out.println("Błąd: Za wysoka temperatura ustawiona na termostacie");
                }

            }
            if (userInput.equalsIgnoreCase("chlodzenie")) {
                if (termostat.ustawionaTemperatura>10){
                    termostat.wlaczChlodzenie();
                    if(termostat.aktualnaTemperatura < termostat.ustawionaTemperatura){
                        System.out.println("Nie możesz zmienić temperatury, ponieważ jest mniejsza, niż ustawiona w termostacie  ");
                    } else if (termostat.aktualnaTemperatura == termostat.ustawionaTemperatura) {
                        System.out.println("Nie możesz zmienić temperatury, ponieważ jest równa ustawionej w termostacie  ");
                    } else{
                        termostat.aktualnaTemperatura -= 1.0;
                    }
                    termostat.sprawdzTemperature();
                } else if (termostat.ustawionaTemperatura<=10) {
                    System.out.println("Błąd: Za niska temperatura ustawiona na termostacie");
                }
            }

        } while (!userInput.equalsIgnoreCase("wyjscie"));

        scanner.close();
    }
}