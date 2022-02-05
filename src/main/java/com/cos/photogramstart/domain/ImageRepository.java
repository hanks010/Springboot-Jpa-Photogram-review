package com.cos.photogramstart.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.photogramstart.domain.image.Image;

public interface ImageRepository extends JpaRepository<Image, Integer>{

}
