package com.dibimbing.assignment3.controller;


import com.dibimbing.assignment3.model.Karyawan;
import com.dibimbing.assignment3.repository.KaryawanRepository;
import com.dibimbing.assignment3.service.KaryawanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/v1/view/karyawan")
public class KaryawanControllerMVC {

    @Autowired
    public KaryawanRepository karyawanRepository;

    @Autowired
    public KaryawanService karyawanService;


    private final int ROW_PER_PAGE = 5;

    //    Index Page
    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("title", "Title Saya");
        return "index";
    }


    @GetMapping(value = "/list")
    public String getKaryawan(Model model,
                            @RequestParam(value = "page", defaultValue = "0") int pageNumber) {
        Map data = karyawanService.getAll( ROW_PER_PAGE,pageNumber);
        Object data1 =   data.get("data");

        Page<Karyawan> data2 = (Page<Karyawan>) data1;

        List<Karyawan> kry =    data2.getContent();
        long count = karyawanRepository.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;
        model.addAttribute("kry", kry);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);
        return "karyawan-list";
    }

    @GetMapping(value = {"/add"})
    public String showAddKaryawan(Model model) {
        Karyawan kry = new Karyawan();
        model.addAttribute("add", true);
        model.addAttribute("karyawan", kry);

        return "karyawan-edit";
    }

    @PostMapping(value = "/add")
    public String addKaryawan(Model model,
                            @ModelAttribute("karyawan") Karyawan karyawan) {
        try {
            System.out.println("Nama karyawan " + karyawan.getNama());
            Map data = karyawanService.insert(karyawan);
            Karyawan newKry = (Karyawan) data.get("data");
            return "redirect:/v1/view/karyawan/" + String.valueOf(newKry.getId());
        } catch (Exception ex) {
            // log exception first,
            // then show error
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            //model.addAttribute("karyawan", karyawan);
            model.addAttribute("add", true);
            return "karyawan-edit";
        }
    }

    @GetMapping(value = {"/{karyawanId}/edit"})
    public String showEditKaryawan(Model model, @PathVariable long karyawanId) {
        Karyawan karyawan = null;
        try {
            karyawan = karyawanRepository.getbyIDKaryawan(karyawanId);
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "Karyawan not found");
        }
        model.addAttribute("add", false);
        model.addAttribute("karyawan", karyawan);
        return "karyawan-edit";
    }

    @PostMapping(value = {"/{karyawanId}/edit"})
    public String updateKaryawan(Model model,
                               @PathVariable long karyawanId,
                               @ModelAttribute("karyawan") Karyawan karyawan) {
        try {
            karyawan.setId(karyawanId);
            karyawanService.update(karyawan);
            return "redirect:/v1/view/karyawan/" + String.valueOf(karyawan.getId());
        } catch (Exception ex) {
            // log exception first,
            // then show error
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", false);
            return "karyawan-edit";
        }
    }

    @GetMapping(value = "/{karyawanId}")
    public String getKaryawanById(Model model, @PathVariable long karyawanId) {
        Karyawan karyawan = null;
        try {
            karyawan = karyawanRepository.getbyIDKaryawan(karyawanId);
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "karyawan not found");
        }
        model.addAttribute("karyawan", karyawan);
        return "karyawan";
    }

    //delete
    @GetMapping(value = {"/{karyawanId}/delete"})
    public String showDeleteKaryawanById(
            Model model, @PathVariable long karyawanId) {
        Karyawan karyawan = null;
        try {
            karyawan = karyawanRepository.getbyIDKaryawan(karyawanId);
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "karyawan not found");
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("karyawan", karyawan);
        return "karyawan";
    }

    @PostMapping(value = {"/{karyawanId}/delete"})
    public String deleteKaryawanById(
            Model model, @PathVariable long karyawanId) {
        try {
            karyawanService.delete(karyawanId);
            return "redirect:/v1/view/karyawan/list";
        } catch (ResourceNotFoundException ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "karyawan";
        }
    }
}
