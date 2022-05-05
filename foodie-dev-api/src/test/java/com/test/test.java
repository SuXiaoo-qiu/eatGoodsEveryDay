package com.test;

import com.joeworld.Application;
import com.joeworld.pojo.Carousel;
import com.joeworld.service.CarouselService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class test {
    @Autowired
    private CarouselService carouselService;

    @Test
    public void  tesat(){
        Carousel carousel = new Carousel();
        carousel.setCatId("5");
        carousel.setId("9");
        carousel.setImageUrl("sdfaniyeedddd");
        carouselService.byz(carousel);
    }


    public void findOrder (int[][] prerequisites, int n) {

        for (int i = 0; i < prerequisites.length; i++) {




        }


    }
}
