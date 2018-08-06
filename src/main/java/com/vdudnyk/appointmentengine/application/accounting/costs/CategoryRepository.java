package com.vdudnyk.appointmentengine.application.accounting.costs;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
