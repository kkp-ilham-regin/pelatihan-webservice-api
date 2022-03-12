package com.kuycook.kuycookinternalapi.controllers;

import com.kuycook.kuycookinternalapi.dto.requests.BannerRequest;
import com.kuycook.kuycookinternalapi.dto.requests.SearchDataRequest;
import com.kuycook.kuycookinternalapi.models.Banner;
import com.kuycook.kuycookinternalapi.repositories.BannerRepository;
import com.kuycook.kuycookinternalapi.services.banner.BannerService;
import com.kuycook.kuycookinternalapi.services.banner.BannerServiceImpl;
import com.kuycook.kuycookinternalapi.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600, origins = "*")
@RequestMapping("/kuycook/api/banners")
public class BannerController {

    private BannerService bannerService;

    @Autowired
    private BannerServiceImpl bannerServiceImpl;

    @Autowired
    BannerRepository bannerRepository;

    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @GetMapping()
    public List<Banner> getAllList() {
        return bannerRepository.bannerList();
    }

    @PostMapping("/search/{size}/{page}")
    public Iterable<Banner> findBannerByName(@RequestBody SearchDataRequest searchData,
                                             @PathVariable("size") int size, @PathVariable("page") int page) {
        System.out.println("Banner List Pagination≈" + bannerRepository.bannerList());
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return bannerServiceImpl.findByName(searchData.getSearchKey(), pageable);
    }

    @PostMapping("/search/{size}/{page}/{sort}")
    public Iterable<Banner> findBannerByName(@RequestBody SearchDataRequest searchData,
                                             @PathVariable("size") int size, @PathVariable("page") int page,
                                             @PathVariable("sort") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        if (sort.equalsIgnoreCase("asc"))
            pageable = PageRequest.of(page, size, Sort.by("id").ascending());

        return bannerServiceImpl.findByName(searchData.getSearchKey(), pageable);
    }

    @GetMapping("/{id}")
    public Object bannerDetail(@PathVariable("id") Long id) {
        System.out.println("banner detail: " + bannerServiceImpl.findBannerById(id));
        if (bannerServiceImpl.findBannerById(id) == null) {
            return new ResourceNotFoundException("Banner", "ID", id);
        }
        return bannerServiceImpl.findBannerById(id);
    }

    @PostMapping()
    public Banner bannerCreate(@RequestBody BannerRequest bannerRequest) {
        Banner banner = new Banner(bannerRequest.getTitle(), bannerRequest.getImage(),
                bannerRequest.getCreatedAt(), bannerRequest.getUpdatedAt());
        return bannerServiceImpl.createBanner(banner);
    }

    @PutMapping("/{id}")
    public Banner bannerUpdate(@RequestBody BannerRequest bannerRequest, @PathVariable("id") Long id) {
        Banner banner = new Banner(bannerRequest.getTitle(), bannerRequest.getImage(),
                bannerRequest.getCreatedAt(), bannerRequest.getUpdatedAt());
        return bannerServiceImpl.updateBanner(id, banner);
    }

    @DeleteMapping("/{id}")
    public Banner deleteBanner(@RequestBody BannerRequest bannerRequest, @PathVariable("id") Long id) {
        Banner banner = new Banner(bannerRequest.getDeletedAt());

        return bannerServiceImpl.deletedBanner(id, banner);
    }
}
