package com.hocafe.controller;

import com.hocafe.domain.Cafe;
import com.hocafe.domain.CafeForm;
import com.hocafe.domain.UploadFile;
import com.hocafe.file.FileStore;
import com.hocafe.repository.MemoryCafeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("cafe", cafe);
        return "/cafe/cafes";
    }

    @GetMapping("add")
    public String addCafe(Model model){
        Cafe cafe = new Cafe();
        model.addAttribute("cafe", cafe);
        return "/cafe/addForm";
    }

    @PostMapping("add")
    public String addCafe(@ModelAttribute CafeForm form, RedirectAttributes redirectAttributes){
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

        cafeRepository.save(cafe);
        log.info("cafe = {} ", cafe.toString());

        redirectAttributes.addAttribute("{cafeId}", cafe.getId());

        return "redirect:/cafes/{cafeId}";
    }
}
