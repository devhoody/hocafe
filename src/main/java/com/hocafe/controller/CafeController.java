package com.hocafe.controller;

import com.hocafe.domain.Cafe;
import com.hocafe.domain.CafeForm;
import com.hocafe.domain.UploadFile;
import com.hocafe.file.FileStore;
import com.hocafe.repository.MemoryCafeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cafes")
public class CafeController {

    private final MemoryCafeRepository cafeRepository;
    private final FileStore fileStore;


    @GetMapping
    public String Cafes(Model model){
        Cafe cafe = new Cafe();
        List<Cafe> cafes = cafeRepository.findAll();
        model.addAttribute("cafes", cafes);
        return "/cafe/cafes";
    }

    @GetMapping("add")
    public String addCafe(Model model){
        Cafe cafe = new Cafe();
        model.addAttribute("cafe", cafe);
        return "/cafe/addForm";
    }

    @PostMapping("add")
    public String addCafe(@ModelAttribute CafeForm form, RedirectAttributes redirectAttributes) throws IOException {
        MultipartFile mainImageFile = form.getMainImageFile();
        List<MultipartFile> imageFiles = form.getImageFiles();
        UploadFile uploadFile = fileStore.storeFile(mainImageFile);
        List<UploadFile> uploadFiles = fileStore.storeFiles(imageFiles);

        //데이터베이스 저장
        Cafe cafe = new Cafe();
        cafe.setCafeName(form.getCafeName());
        cafe.setAddress(form.getAddress());
        cafe.setImageFiles(uploadFiles);
        cafe.setMainImageFile(uploadFile);

        Cafe savedCafe = cafeRepository.save(cafe);
        log.info("cafe = {} ", cafe.toString());

        log.info("cafeMainImage ={}", cafe.getMainImageFile().getStoreFileName());

        redirectAttributes.addAttribute("cafeId", savedCafe.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/cafes/{cafeId}";
    }

    @GetMapping("{id}")
    public String viewCafe(@PathVariable(name = "id") Long id, Model model){
        Cafe findCafe = cafeRepository.findById(id);
        model.addAttribute(findCafe);
        return "cafe/cafe";
    }

    @ResponseBody
    @GetMapping("images/{filename}")
    public Resource viewImage(@PathVariable(name = "filename") String filename) throws MalformedURLException {
        String fullPath = fileStore.getFullPath(filename);
        return new UrlResource("file:" + fullPath);
    }
}
