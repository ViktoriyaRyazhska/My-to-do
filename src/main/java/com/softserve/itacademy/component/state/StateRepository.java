package com.softserve.itacademy.component.state;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    State findByName(String name);

//    @Query(value = "select * from states order by id", nativeQuery = true)
//    @Query("from State order by id")
    List<State> findAllByOrderById();

//    List<State> findAllByOrderByIdAsc();

}
