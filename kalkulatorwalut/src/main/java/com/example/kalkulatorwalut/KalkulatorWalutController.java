package com.example.kalkulatorwalut;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class KalkulatorWalutController {

    private static final Map<String, Double> KURSY_WYMIANY = new HashMap<>();

    static {
        KURSY_WYMIANY.put("EURtoUSD", 1.1);
        KURSY_WYMIANY.put("USDtoEUR", 0.91);
        KURSY_WYMIANY.put("PLNtoUSD", 0.27);
        KURSY_WYMIANY.put("USDtoPLN", 3.7);
        KURSY_WYMIANY.put("PLNtoEUR", 0.22);
        KURSY_WYMIANY.put("EURtoPLN", 4.5);
    }

    @GetMapping("/przelicz")
    public Map<String, String> przeliczWalute(
            @RequestParam String zWaluty,
            @RequestParam String naWalute,
            @RequestParam double kwota) {

        Map<String, String> odpowiedz = new HashMap<>();

        if (kwota < 0) {
            odpowiedz.put("blad", "Proszę o wpisanie liczby dodatniej");
            return odpowiedz;
        }

        String kluczKursu = zWaluty + "to" + naWalute;
        Double kursWymiany = KURSY_WYMIANY.get(kluczKursu);

        if (kursWymiany == null) {
            odpowiedz.put("blad", "Wybrano niepoprawną konwersję waluty");
            return odpowiedz;
        }

        double przeliczonaKwota = kwota * kursWymiany;
        odpowiedz.put("wynik", String.format("Wynik: %.2f %s", przeliczonaKwota, naWalute));
        odpowiedz.put("kursWymiany", String.format("Kurs wymiany: 1 %s = %.2f %s", zWaluty, kursWymiany, naWalute));

        return odpowiedz;
    }
}
