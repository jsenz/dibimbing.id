package com.dibimbing.assignment3.model;


import lombok.Data;
import org.hibernate.annotations.Where;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "karyawan")
@Where(clause = "deleted_date is null")
public class Karyawan extends AbstractDate implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama", nullable = false, length = 100)
    private String nama;

    @Column(name = "jk", nullable = false, length = 45)
    private String jk;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dob", nullable = false)
    private Date dob;

    @Column(name = "alamat", nullable = false)
    private String alamat;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "nik", nullable = false, length = 16)
    private String nik;

    @Column(name = "npwp", nullable = false, length = 16)
    private String npwp;

//    @JsonIgnore
//    @OneToMany(targetEntity = Rekening.class, cascade = CascadeType.MERGE)
//    private List<Rekening> rekeningList;
//
//    @OneToMany(targetEntity = KaryawanTraining.class, cascade = CascadeType.MERGE)
//    private List<KaryawanTraining> karyawanTraining;


}
