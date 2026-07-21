package com.smartpods.api.config;

import com.smartpods.api.entity.EstadoLocker;
import com.smartpods.api.entity.Locker;
import com.smartpods.api.entity.TamanoLocker;
import com.smartpods.api.repository.LockerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final LockerRepository lockerRepository;

    public DataSeeder(LockerRepository lockerRepository) {
        this.lockerRepository = lockerRepository;
    }

    @Override
    public void run(String... args) {
        if (lockerRepository.count() > 0) {
            return; // ya están sembrados, no hacer nada
        }

        String[][] grandes = {{"A-01"}, {"A-08"}, {"G-01"}, {"G-08"}};
        String[][] medianos = {{"A-03"}, {"A-06"}, {"C-01"}, {"C-08"}, {"D-01"}, {"D-08"}, {"E-01"}, {"E-08"}, {"G-03"}, {"G-06"}};
        String[][] pequenos = {{"B-03"}, {"B-04"}, {"B-05"}, {"B-06"}, {"H-03"}, {"H-04"}, {"H-05"}, {"H-06"},
                                {"I-01"}, {"I-02"}, {"I-03"}, {"I-04"}, {"I-05"}, {"I-06"}, {"I-07"}, {"I-08"}};

        for (String[] c : grandes) crearLocker(c[0], TamanoLocker.GRANDE);
        for (String[] c : medianos) crearLocker(c[0], TamanoLocker.MEDIANO);
        for (String[] c : pequenos) crearLocker(c[0], TamanoLocker.PEQUENO);
    }

    private void crearLocker(String codigo, TamanoLocker tamano) {
        Locker locker = new Locker();
        locker.setCodigo(codigo);
        locker.setTamano(tamano);
        locker.setEstado(EstadoLocker.DISPONIBLE);
        lockerRepository.save(locker);
    }
}