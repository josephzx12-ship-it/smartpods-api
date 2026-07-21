package com.smartpods.api.repository;

import com.smartpods.api.entity.EstadoLocker;
import com.smartpods.api.entity.Locker;
import com.smartpods.api.entity.TamanoLocker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LockerRepository extends JpaRepository<Locker, Long> {

    List<Locker> findByEstado(EstadoLocker estado);

    Optional<Locker> findFirstByEstadoAndTamano(EstadoLocker estado, TamanoLocker tamano);
}