package com.dibimbing.assignment3.repository;

import com.dibimbing.assignment3.model.Karyawan;
import com.dibimbing.assignment3.model.Training;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TrainingRepository extends PagingAndSortingRepository<Training, Long> {

    @Query("select t from Training t Where t.id = :id")
    public Training getbyID(@Param("id") Long id);

    public Page<Training> findByPengajar (String pengajar, Pageable pageable);

    @Query("select t from Training t")
    public Page<Training> getAllData(Pageable pageable);

    public Page<Karyawan> findByTema(String tema, Pageable show_data);
}
