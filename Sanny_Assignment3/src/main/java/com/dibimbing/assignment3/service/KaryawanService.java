package com.dibimbing.assignment3.service;

import com.dibimbing.assignment3.model.Karyawan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface KaryawanService {
    public Map insert(Karyawan karyawan);
    public Map update(Karyawan karyawan);
    public Map delete(Long karyawan);
    public Map getbyIDKaryawan(Long idKaryawan);

    public Map getAll(int size, int page);

}
